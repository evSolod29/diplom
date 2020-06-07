<#import "/common/common.ftl" as c>
<#import "/common/pagination.ftl" as p>

<@c.page "Переферийные устройства" 3>
<div class="topmenu mb-3">
    <a href="/equipments/add">Добавить</a>
</div>
<#if page.content?has_content>
<table class=" text-center table table-bordered table-hover d-none d-sm-block" style="width:100%">
        <tr class="thead">
            <th style="visibility: hidden; display: none"></th>
            <th scope="col">Инв. №</th>
            <th scope="col">Тип</th>
            <th scope="col">Наименование</th>
            <th scope="col">Подразделение</th>
            <th scope="col">Дата ввода в эксплуатацию</th>
            <th scope="col">Перемещение внутри предприятия</th>
        </tr>
        <#list page.content as equipment>
            <tr id="equipments-table">
                <td style="visibility: hidden; display: none">${equipment.id?c}</td>
                <td>${equipment.invNo?c}</td>
                <td>${equipment.name}</td>
                <td>${equipment.type}</td>
                <td>${equipment.subdivision}</td>
                <td>${equipment.commissioningDate?string["yyyy-MM-dd"]}</td>
                <td>${equipment.movement}</td>
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
    search("/equipments/","search",'${search}');
    $('#equipments-table').click(function() {
        console.log("click");
        let id = $(this).find("td:first").html();
        if(id) {
            window.location.href = "/equipments/details/" + id;
        }
    });
    
});
</script>
    
</@c.page>