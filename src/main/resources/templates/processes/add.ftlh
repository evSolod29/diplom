<#import "/common/common.ftl" as c>
<@c.page "Добавление бизнес-процесса" 2>
<div class="error-msg">
</div>
<form method="POST">
<div id = "process-add 0">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="name-addon1">Наименование бизнес-процесса</span>
        </div>
        <input type="text" id="name" class="form-control required" name="name" aria-describedby="name-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="customer-addon1">Заказчик</span>
        </div>
        <input type="text" id="customer" class="form-control required" name="customer" aria-describedby="customer-addon1" placeholder="">
    </div>
    <div class="form-check">
        <input class="form-check-input mt-3" type="radio" name="Radios" id="Radios1" checked>

        <div class="input-group form-check-label mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="computerid">Компьютер</label>
            </div>
            <div class="bootstrap-select is-valid">
                <select name="computerId" class="dropdown selectpicker" data-live-search="true" id="computerId">
                    <option selected value="-1">Нет</option>
                    <#if computers?has_content>
                    <#list computers as comp>
                    <option value="${comp.id?c}">${comp.invNo?c} - ${comp.name}</option>
                    </#list>
                    </#if>
                </select>
            </div>
        </div>
    </div>
    <div class="form-check">
        <input class="form-check-input mt-3" type="radio" name="Radios" id="Radios2">
        <div class="input-group form-check-label mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="computerid">Периферийное устройство</label>
            </div>
            <div class="bootstrap-select is-valid">
                <select name="equipmentid" class="dropdown selectpicker" data-live-search="true" id="equipmentId">
                    <option value="-1" selected>Нет</option>
                    <#if equipments?has_content>
                    <#list equipments as eq>
                    <option value="${eq.id?c}">${eq.invNo?c} - ${eq.name}</option>
                    </#list>
                    </#if>
                </select>
            </div>
        </div>
        
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="works-addon1">Количество выполняемых работ</span>
        </div>
        <input type="text" id="works" class="form-control required" name="works" aria-describedby="customer-addon1" placeholder="">
    </div>
    <div class="form-group">
        <a href="#" id="back-btn" class="btn dark-btn">Назад</a>
        <button  id="submit-bt" type="button" class="btn dark-btn">Далее</button>
    </div>

</form>
<script>
    $(document).ready(function(){
        //First row
        correct_input([
            ["text","#name"],
            ["text","#customer"],
            ["serchableselect", ,"#computerid"],
            ["","#equipmentid"],
            ["int", "#works"]
        ], "#submit-bt");

        $("#submit-bt").on("click", function(){
            $.ajax({
                url:"/processes/add",
                type:"POST",
                data:{
                    name : $('#name').val(),
                    customer : $('#customer').val(), 
                    computerId : $('#computerId').val(), 
                    equipmentId : $('#equipmentId').val(), 
                    works : $('#works').val() 
                },
                beforeSend: function(){
                    $("#submit-bt").attr("disabled","disabled")
                },
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                success: function(data)
                {
                    data=data.split(',')
                    //console.log(data);
                    $(location).attr('href',"/processes/addworks?id="+data[0]+"&count="+data[1]);
                }
            });
        })
        
    });
</script>
</@c.page>