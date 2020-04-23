<#import "/common/common.ftl" as c>
<@c.page "${computer.invNo?c} ${computer.name}">
<div class="topmenu mb-3">
    <a href="/computers/">Назад</a>
    <a href="/computers/delete/${computer.id?c}">Удалить</a>
    <a href="/computers/edit/${computer.id?c}">Изменить</a>
    <button value="${computer.id?c}" id="add-repair">Добавить запись о начале ремонта</button>
</div>
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">${computer.invNo?c} - ${computer.name}</h3><br><br>
        <div class="row">
            <div class="col-sm">
                <h5>Подразделение</h5>
                ${computer.subdivision}<br><br>
                <h5>Дата ввода в эксплуатацию</h5>
                ${computer.commissioningDate?string["yyyy-MM-dd"]}<br><br>
                <h5>Встроеный экран</h5>
                ${computer.buildInMonitor?string('Есть''Нет')}<br><br>
            </div>
            <div class="col-sm">
                <h5>Процессор</h5>
                ${computer.cpu}<br><br>
                <h5>Видеокарта</h5>
                ${computer.videocard}<br><br>
                <h5>CD-ROm</h5>
                ${computer.cdRom?string('Есть''Нет')}<br><br>
            </div>
            <div class="col-sm">
                <h5>Оперативная память</h5>
                ${computer.ram}<br><br>
                <h5>Жесткий диск</h5>
                ${computer.rom}<br><br>
            </div>
        </div>
        <h5 >Перемещение</h5>
        ${computer.movement}
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
        let id = $("#add-repair").val();
        console.log(id);
        $.ajax
        ({
            url:"/repair/addrepair",
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