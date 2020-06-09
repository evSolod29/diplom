<#import "/common/common.ftl" as c>
<@c.page "Добавление работы" 2>
<div class="error-msg">
</div>
<form method="POST">
<div id = "process-add 0">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="name-addon1">Наименование работы</span>
        </div>
        <input type="text" id="name" class="form-control required" name="name" aria-describedby="name-addon1" placeholder="">
    </div>
    <div class="input-group form-check-label mb-3">
        <div class="input-group-prepend">
            <label class="input-group-text" for="userId">Работник</label>
        </div>
        <div class="bootstrap-select is-valid " id="user-select">
            <select name="userId" class="dropdown selectpicker" data-live-search="true" id="userId">
                <option selected value="-1">Не выбрано</option>
                <#if users?has_content>
                <#list users as user>
                <option value="${user.id?c}">${user.lastName} ${user.firstName}</option>
                </#list>
                </#if>
            </select>
        </div>
    </div>
</div>
<input type="text" id="processId" value="${id}" style="display:none" name="processId">
<input type="text" id ="preWorkId" style="display:none" name="preWorkId">
    <div class="form-group">
        <button  id="submit-bt" type="button" class="btn dark-btn">Далее</button>
    </div>
</form>
<script>
    var count = parseInt("${count}");
    $(document).ready(function(){
        //First row
        correct_input([
            ["text","#name"],
            ["","#userId"]
        ], "#submit-bt");
        let counter = 0;
        if(1 == count){
            $("#submit-bt").html("Добавить"); 
        }
        $("#submit-bt").on("click", function(){
            let preWorkId = $('#preWorkId').val();
            let pr = 1;
            if(preWorkId != null){
                pr = preWorkId;
            }
            console.log("click");
            $.ajax({
                url:"/processes/addworks",
                type:"POST",
                data:{
                    name : $('#name').val(),
                    userId : $('#userId').val(), 
                    processId : $('#processId').val(), 
                    preWorkId : pr
                },
                beforeSend: function(){
                    $("#submit-bt").attr("disabled","disabled")
                },
                dataType:"",
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                success: function(data)
                {
                    counter++;
                    $("form").trigger('reset');
                    $("#userId").prop('selectedIndex',0);
                    $(".filter-option-inner-inner").html("Не выбрано");
                    if(counter == count-1){
                        $("#submit-bt").html("Закончить"); 
                    }
                    if(counter >= count){
                        $(location).attr('href',"/processes/");
                    }
                }
            });
        })
        
    });
</script>
</@c.page>