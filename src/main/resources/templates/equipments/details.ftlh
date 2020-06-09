<#import "/common/common.ftl" as c>
<@c.page "${equipment.invNo?c} - ${equipment.name}">
<div class="topmenu mb-3">
    <a href="/equipments/">Назад</a>
    <a href="/equipments/delete/${equipment.id?c}">Удалить</a>
    <a href="/equipments/edit/${equipment.id?c}">Изменить</a>
    <a href="#" id="add-repair">Отправить в ремонт</a>
</div>
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">${equipment.invNo?c} - ${equipment.name}</h3><br><br>
        <div class="row">
            <div class="col-sm">
                <h5>Подразделение</h5>
                ${equipment.subdivision}<br><br>
                <h5>Дата ввода в эксплуатацию</h5>
                ${equipment.commissioningDate?string["yyyy-MM-dd"]}<br><br>
            </div>
            <div class="col-sm">
                <h5>Тип</h5>
                ${equipment.type}<br><br>
                <h5>Закреплено за</h5>
                <#if equipment.computer?has_content>
                <a href="//computers/details/${equipment.computer.id}">${equipment.computer.name}</a><br><br>
                <#else>
                Не закреплен за компьютером
                </#if>
            </div>
        </div>
        <h5 >Перемещение</h5>
        ${equipment.movement}
    </div>
</div>
<script>
$(document).ready(function()
{
    addRepair();
});
function addRepair() 
{
    $("#add-repair").click(function()
    {
        let id = "${equipment.id?c}"
        console.log(id);
        $.ajax
        ({
            url:"/repair/addrepair",
            type:"POST",
            data:{equipmentid : id},
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