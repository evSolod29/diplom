<#import "/common/common.ftl" as c>
<#import "/common/pagination.ftl" as p>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@c.page "Журнал ремонта ПЭВМ" 2>
<#if page.content?has_content>
<table class=" text-center table table-bordered table-hover" style="width:100%">
        <tr class="thead">
            <th scope="col">№</th>
            <th scope="col">Наименование</th>
            <th scope="col">Отправитель</th>
            <th scope="col">Дата начала ремонта</th>
            <th scope="col">Дата окончания ремонта</th>
            <th scope="col">Выполняет ремонт</th>
        </tr>
        <#list page.content as r>
            <tr>
                <td>${r.id?c}</td>
                <#if r.comp?has_content>
                <td>${r.comp.invNo?c} - Компьютер - ${r.comp.name}</td>
                <td>${r.comp.subdivision}</td>
                <#else>
                <td>${r.equipment.invNo?c} - ${r.equipment.type} - ${r.equipment.name}</td>
                <td>${r.equipment.subdivision}</td>
                </#if>
                <td>${r.startDate?string["yyyy-MM-dd"]}</td>
                <td>
                    <@security.authorize access="hasRole('ADMIN')">
                        <#if r.endDate??>
                            ${r.endDate?string["yyyy-MM-dd"]}
                        <#else>
                            <button onclick="endRepair('${r.id?c}')" id="end-btn" value="${r.id?c}" class="btn dark-btn">Закончить</button>
                        </#if>
                    </@security.authorize>
                    <@security.authorize access="! hasRole('ADMIN')">
                        <#if r.endDate??>
                            ${r.endDate?string["yyyy-MM-dd"]}
                        <#else>
                        В ремонте
                        </#if>
                    </@security.authorize>
                </td>
                <td>
                    <#list r.users as u>
                        <p>${u.lastName + " " + u.firstName}</p>
                        <@security.authorize access="hasRole('ADMIN')">

                        </@security.authorize>
                    <#else>
                        <@security.authorize access="hasRole('ADMIN')">

                        </@security.authorize>
                        <@security.authorize access="hasRole('USER')">
                        <button onclick="addTask('${r.id?c}','${userid}')" id="end-btn" class="btn dark-btn">Взять задание</button>
                        </@security.authorize>
                    </#list>
                </td>
            </tr>
        </#list>
</table>
<#if !sometext?has_content>
    <@p.pager url+"?" page />
<#else>
    <@p.pager url+"?sometext="+sometext+"&" page />
</#if>
<#else>
Ничего не найдено
</#if>
<script>
$(document).ready(function()
{
    search("/repair/","sometext",'${sometext}');
});
function addTask(taskId, userId) {
    $.ajax
    ({
        url:"/repair/setuser",
        type:"POST",
        data:{repairid : taskId, userid : userId},
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        success: function()
        {
            location.reload();
        }
    });
}
function endRepair(id) {
    //console.log(id);
    $.ajax
    ({
        url:"/repair/endrepair",
        type:"POST",
        data:{id : id},
        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
        success: function()
        {
            location.reload();
        }
    });
}

</script>
</@c.page>