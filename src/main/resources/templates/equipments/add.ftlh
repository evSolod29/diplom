<#import "/common/common.ftl" as c>
<@c.page "Добавление переферийного устройства">
<form method="POST">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="invNo-addon1">Инвентаризационный номер</span>
        </div>
        <input type="text" id="invNo" class="form-control required" name="invNo" aria-describedby="invNo-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="name-addon1">Наименование</span>
        </div>
        <input type="text" id="name" class="form-control required" name="name" aria-describedby="name-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="subdivision-addon1">Подразделение</span>
        </div>
        <input type="text" id="subdivision" class="form-control required" name="subdivision" aria-describedby="subdivision-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="commissioningDateStr-addon1">Дата ввода в эксплуатацию</span>
        </div>
        <input type="text" class="form-control required" id="commissioningDateStr" name="commissioningDateStr" aria-describedby="commissioningDateStr-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="movement-addon1">Перемещение</span>
        </div>
        <input type="text" id="movement" class="form-control is-valid" name="movement" aria-describedby="movement-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="computerid">Закрепить за компьютером</label>
        </div>
        <div class="bootstrap-select is-valid">
            <select name="computerid" class="dropdown selectpicker" data-live-search="true" id="computerid">
                <option selected value="-1">Нет</option>
                <#if computers?has_content>
                <#list computers as comp>
                <option value="${comp.id?c}">${comp.invNo?c} - ${comp.name}</option>
                </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="type">Тип</label>
        </div>
        <div class="bootstrap-select is-valid">
            <select name="type" class=" dropdown selectpicker" data-live-search="true" id="type">
                <option selected value="Принтер">Принтер</option>
                <option value="Модем">Модем</option>
                <option value="Монитор">Монитор</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <a href="/computers/" class="btn dark-btn">Назад</a>
        <button id="submit-btn" class="btn dark-btn" disabled>Добавить</button>
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
            ["","#computerid"],
            ["","#type"],
            ["","#movement"]
        ], "#submit-btn");
    });
</script>
</@c.page>