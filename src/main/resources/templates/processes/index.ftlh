<#import "/common/common.ftl" as c>
<#import "/common/pagination.ftl" as p>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@c.page "Журнал бизнес-процессов" 2>
<div class="topmenu mb-3">
    <a href="/processes/add">Добавить</a>
</div>
<#if page.content?has_content>
<table class=" text-center table table-bordered table-hover" style="width:100%">
        <tr class="thead">
            <th scope="col">№</th>
            <th scope="col">Наименование</th>
            <th scope="col">Устройство</th>
            <th scope="col">Заказчик</th>
            <th scope="col">Статус</th>
        </tr>
        <#list page.content as pr>
            <tr id="processes-table">
                <td>${pr.id?c}</td>
                <td>${pr.name}</td>
                <#if pr.computer?has_content>
                    <td>${pr.computer.invNo?c} - Компьютер - ${pr.computer.name}</td>
                    <#else>
                    <td>${pr.equipment.invNo?c} - ${pr.equipment.type} - ${pr.equipment.name}</td>
                </#if>
                <td>${pr.customer}</td>
                <td><#if pr.inProcess>Не завершен<#else>Завершен</#if></td>
            </tr>
        </#list>
</table>
<#if !search?has_content>
    <@p.pager url+"?" page />
<#else>
    <@p.pager url+"?search="+search+"&" page />
</#if>
<#else>
Ничего не найдено
</#if>
<script>
$(document).ready(function()
{
    search("${url}","search",'${search}');
    //Переход на страницу подробной информации о процессе 
    //при клике строку таблицы
    $('#processes-table').click(function() {
        console.log("click");
        let id = $(this).find("td:first").html();
        if(id) {
            window.location.href = "/processes/details/" + id;
        }
    });
});

</script>
</@c.page>