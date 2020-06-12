<#import "/common/common.ftl" as c>
<@c.page "Добавление задачи">
<form id="form-to-send" method="POST">
    <#if errors??>
        <div class="alert alert-danger" role="alert">
        <#list errors as error>
        ${error}<br>
        </#list>
        </div>
    </#if>
    <#if success??>
        <div class="alert alert-success" role="alert">
            ${success}
        </div>
    </#if>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="name-addon">Наименование бизнес-процесса</span>
        </div>
        <input type="text" id="name" class="form-control" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="description-addon">Описание бизнес-процесса</span>
        </div>
        <textarea type="" id="description" class="form-control" placeholder="" rows="4"></textarea>
    </div>
    <div class="form-group">
        <button id="choose-computer" type="button" class="btn dark-btn">Выбрать компьютер</button>
        <button id="choose-equipment" type="button" class="btn dark-btn">Выбрать периферийное устройство</button>
    </div>
    <div style="display:none" id="computer-select" class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="computerid">Закрепить за компьютером</label>
        </div>
        <div class="bootstrap-select">
            <select class="dropdown selectpicker" data-live-search="true" id="computerId">
                <option selected value="-1">Нет</option>
                <#if computers?has_content>
                <#list computers as comp>
                <option value="${comp.id?c}">${comp.invNo?c} - ${comp.name}</option>
                </#list>
                </#if>
            </select>
        </div>
    </div>
    <div style="display:none" id="equipment-select" class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="equipmentId">Закрепить за периферийным устройством</label>
        </div>
        <div class="bootstrap-select">
            <select class="dropdown selectpicker" data-live-search="true" id="equipmentId">
                <option selected value="-1">Нет</option>
                <#if equipments?has_content>
                <#list equipments as e>
                <option value="${e.id?c}">${e.invNo?c} - ${e.type} ${e.name}</option>
                </#list>
                </#if>
            </select>
        </div>
    </div>
    <div  id="bprocess-select" class="input-group mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="bProcessId">Выбирите тип бизнес-процесса</label>
        </div>
        <div class="bootstrap-select">
            <select class="dropdown selectpicker" data-live-search="true" id="bProcessId">
                <option selected value="-1">Нет</option>
                <#if bProcesses?has_content>
                <#list bProcesses as b>
                <option value="${b.id?c}">${b.name}</option>
                </#list>
                </#if>
            </select>
        </div>
    </div>
    <div class="form-group" id="add-users-to-subprocesses-form">

    </div>
    <button class="btn dark-btn mb-3" type="button" id="add-users-to-subprocesses">Распределить пользователей</button>
    <input id="jsondata" name="jsondata" type="text" style="display:none">
    <div class="form-group">
        <a href="/tasks/" class="btn dark-btn">Назад</a>
        <button id="submit-bt" type="button" class="btn dark-btn">Добавить</button>
    </div>
</form>
<script>
    $(document).ready(function(){
        var data;
        $("#choose-computer").on("click", function(){
            $("#computer-select").css("display","flex");
            $("#equipment-select").css("display","none");
            $("#equipmentId").val("-1");
        });
        $("#choose-equipment").on("click", function(){
            $("#computer-select").css("display","none");
            $("#computerId").val("-1");
            $("#equipment-select").css("display","flex");
        });
        $("#add-users-to-subprocesses").on("click", function(){
            console.log("click")
            $.ajax({
                url:"/tasks/addusersform",
                method:"GET",
                data:
                {
                    bProcessId:$("#bProcessId").val()
                },
                beforeSend:function(){
                    $(".container-fluid").attr("disabled", "disabled");
                },
                success:function(data){
                    $(".container-fluid").removeAttr("disabled");
                    $("#add-users-to-subprocesses-form").html(data);
                }
            });
        });
        $("#submit-bt").on("click", function(){
            let form = {
                name : $("#name").val(),
                description : $("#description").val(),
                computerId : $("#computerId").val(),
                equipmentId : $("#equipmentId").val(),
                bProcessId : $("#bProcessId").val(),
                users : {}
            }
            let count = parseInt($("#count-subprocesses").val());
            for (let i = 0; i < count ; i++) {
                let subprocess_selector = "#subprocess-" + i.toString();
                let key = $(subprocess_selector).val();
                let user_selector = "#userId-" + i.toString();
                let value = $(user_selector).val();
                //console.log(key + " - " + value);
                form["users"][key] = value;
            }
            var data = JSON.stringify(form);
            $("#jsondata").val(data);
            //console.log(data);
            $("#form-to-send").submit(); 
        });
        // First row
        // correct_input([
        //     ["int","#invNo"],
        //     ["text","#name"],
        //     ["text","#subdivision"],
        //     ["date", "#commissioningDateStr"],
        //     ["","#computerid"],
        //     ["","#type"],
        //     ["","#movement"]
        // ], "#submit-btn");

    });
</script>
</@c.page>