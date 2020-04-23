<#import "/common/common.ftl" as c>
<@c.page "Журнал ремонта ПЭВМ">
<div class="topmenu mb-3">
    <input class="mb-2" id="search" type="text" placeholder="Поиск...">
</div>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
        <tr>
            <th scope="col">№</th>
            <th scope="col">Наименование</th>
            <th scope="col">Отправитель</th>
            <th scope="col">Дата начала ремонта</th>
            <th scope="col">Дата окончания ремонта</th>
            <th></th>
        </tr>
    </thead>
    <tbody id="repair-table">
        <#list repairs as r>
            <tr>
                <td>${r.id?c}</td>
                <td>${r.comp.invNo?c} - ${r.comp.name}</td>
                <td>${r.comp.subdivision}</td>
                <td>${r.startDate?string["yyyy-MM-dd"]}</td>
                <td><#if r.endDate??>${r.endDate?string["yyyy-MM-dd"]}</#if></td>
                <td><button id="end-btn" value="${r.id?c}" class="btn dark-btn">Закончить</button></td>
            </tr>
        </#list>
    </tbody>
</table>
<script>
$(document).ready(function()
{
    endRepair();
});
function endRepair() {
    $("#end-btn").click(function()
    {
        let id = $("#end-btn").val();
        console.log(id);
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
    });
}

</script>
</@c.page>