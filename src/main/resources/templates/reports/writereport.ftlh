<#import "/common/common.ftl" as c>
<@c.page "Написание отчёта">
<#if prevReport??>
<div class="card border-dark mb-3" >
    <div class="card-header">Отчёт о предыдущей задаче</div>
    <div class="card-body text-dark">${prevReport.report}</div>
</div>
</#if>
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
            <span class="input-group-text" id="report-addon">Отчёт о проделанной работе</span>
        </div>
        <textarea type="" id="report" class="form-control" placeholder="" rows="4"></textarea>
    </div>
    <div class="form-group">
        <a href="/tasks/" class="btn dark-btn">Назад</a>
        <button id="submit-bt-error" type="button" class="btn dark-btn">Отправить отчёт об ошибке</button>
        <button id="submit-bt-success" type="button" class="btn dark-btn">Отправить отчёт об успешом выполнении</button>
    </div>
</form>
<script>
    $(document).ready(function(){
        $("#submit-bt-error").on("click", function(){
            $.ajax({
                url:"/reports/writereport/errorreport",
                method:"POST",
                data:{
                    id : "${report.id}",
                    report : $("#report").val()
                },
                success:function(){
                    console.log("success");
                    window.location.href = "/reports/";
                }
            })
        });
        $("#submit-bt-success").on("click", function(){
            $.ajax({
                url:"/reports/writereport/successreport",
                method:"POST",
                data:{
                    id : "${report.id}",
                    report : $("#report").val()
                },
                success:function(){
                    console.log("success");
                    window.location.href = "/reports/";
                }
            })
        });
    });
</script>
</@c.page>