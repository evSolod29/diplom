<#import "/common/common.ftl" as c>
<@c.page "Добавление ПЭВМ">
<form method="POST">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="invNo-addon1">Инвентаризационный номер</span>
        </div>
        <input type="text" value="${computer.invNo?c}" id="invNo" class="form-control required" name="invNo" aria-describedby="invNo-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="name-addon1">Наименование</span>
        </div>
        <input type="text" value="${computer.name}" id="name" class="form-control required" name="name" aria-describedby="name-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="subdivision-addon1">Подразделение</span>
        </div>
        <input type="text" value="${computer.subdivision}" id="subdivision" class="form-control required" name="subdivision" aria-describedby="subdivision-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="commissioningDateStr-addon1">Дата ввода в эксплуатацию</span>
        </div>
        <input type="text" value='${computer.commissioningDate?string["yyyy-MM-dd"]}' class="form-control required" id="commissioningDateStr" name="commissioningDateStr" aria-describedby="commissioningDateStr-addon1" placeholder="">
    </div>
    <div class="form-row">
        <div class="input-group col-md-6 mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="cpu-addon1">Процессор</span>
            </div>
            <input id="cpu" type="text" value="${computer.cpu}" class="form-control required" name="cpu" aria-describedby="cpu-addon1" placeholder="">
        </div>
        <div class="input-group col-md-6 mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="ram-addon1">Оперативная память</span>
            </div>
            <input id="ram" value="${computer.ram}" type="text" class="form-control required" name="ram" aria-describedby="ram-addon1" placeholder="">
        </div>
        <div class="input-group col-md-6 mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="rom-addon1">Жесткий диск</span>
            </div>
            <input type="text" value="${computer.rom}" id="rom" class="form-control required" name="rom" aria-describedby="rom-addon1" placeholder="">
        </div>
        <div class="input-group col-md-6 mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text" id="videocard-addon1">Видеокарта</span>
            </div>
            <input type="text" value="${computer.videocard}" id="videocard" class="form-control required" name="videocard" aria-describedby="videocard-addon1" placeholder="">
        </div>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="movement-addon1">Перемещение</span>
        </div>
        <input type="text" class="form-control is-valid" name="movement" aria-describedby="movement-addon1" placeholder="">
    </div>
    <div class="form-check form-check-inline">
        <input class="form-check-input" ${computer.cdRom?string('checked', '')} name="cdRom" type="checkbox" id="cdRom" value="true">
        <input type="hidden" name="cdRom" id="buildInMonitor" value="false">
        <label class="form-check-label" for="cdRom">CD-ROM</label>
    </div>
    <div class="form-check mb-3 form-check-inline">
        <input class="form-check-input" ${computer.buildInMonitor?string('checked', '')} name="buildInMonitor" type="checkbox" id="buildInMonitor" value="true">
        <input type="hidden" name="buildInMonitor" id="buildInMonitor" value="false">
        <label class="form-check-label" for="buildInMonitor">Встроенный экран</label>
    </div>
    <div class="form-group">
        <a href="/computers/details/${computer.id}" class="btn dark-btn">Назад</a>
        <button id="submit-btn" class="btn dark-btn">Изменить</button>
    </div>
</form>
<script>
    $(document).ready(function(){
        //First row
        correct_input([
            ["int","#invNo"],
            ["text","#name"],
            ["text","#subdivision"],
            ["date", "#commissioningDateStr"],
            ["text","#cpu"],
            ["text","#ram"],
            ["text","#rom"],
            ["text","#videocard"],
            ["","#movement"]
        ], "#submit-btn");
    });
</script>
</@c.page>