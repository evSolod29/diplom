<#import "/common/common.ftl" as c>
<#import "/common/pagination.ftl" as p>

<@c.page "Журнал бизнес-процессов" 3>
<div class="topmenu mb-3">
    <a href="/tasks/add">Начать бизнес-процесс</a>
</div>
<#if page.content?has_content>
<table class=" text-center table table-bordered table-hover d-none d-sm-block" style="width:100%">
        <tr class="thead">
            <th style="visibility: hidden; display: none"></th>
            <th scope="col">Наименование</th>
            <th scope="col">Техника</th>
            <th scope="col">Дата начала</th>
            <th scope="col">Статус</th>
        </tr>
        <#list page.content as task>
            <tr id="equipments-table">
                <td style="visibility: hidden; display: none">${task.id?c}</td>
                <td>${task.name}</td>
                <td>
                    <#if task.computer??>
                        ${task.computer.invNo?c} - Компьютер - ${task.computer.name}
                    <#else>
                        ${task.equipment.invNo?c} - ${task.equipment.type} - ${task.equipment.name}
                    </#if>
                </td>
                <td>${task.startDate?string["yyyy-MM-dd HH:mm"]}</td>
                <td>${task.statusType}</td>
            </tr>
        </#list>
</table>
<#if !sometext?has_content>
    <@p.pager url+"?" page />
<#else>
    <@p.pager url+"?search="+search+"&" page />
</#if>
<#else>
Ничего не найдено
</#if>
<script>
$(document).ready(function(){
    search("${url}","search",'${search}');
    $('#equipments-table').click(function() {
        console.log("click");
        let id = $(this).find("td:first").html();
        if(id) {
            window.location.href = "/tasks/details/" + id;
        }
    });
    
});
</script>
    
</@c.page>