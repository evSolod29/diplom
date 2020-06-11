<#import "/common/common.ftl" as c>
<@c.page report.subprocess.subprocessName>
<div class="topmenu mb-3">
    <a href="/reports/">Назад</a>
    <#if user.id == report.userId>
    <a href="/reports/writereport/${report.id?c}">Написать отчёт</a>
    </#if>
</div>
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">${report.subprocess.subprocessName}</h3><br><br>
        <div>
            <h3>Задача:</h3>
            <h4>${report.subprocess.taskDescription}</h4><br>
        </div>
        <div>
            <h3>Исполнитель:</h3>
            <h4>${report.user.lastName} ${report.user.firstName}</h4><br>
        </div>
        <#if report.report??>
        <div>
            <h3>Отчёт:</h3>
            <h4>${report.report}</h4><br>
        </div>
        </#if>
        <div>
            <h3>Статус:</h3>
            <h4>${report.statusType}</h4><br>
        </div>
    </div>
</div>
<script>

</script>
</@c.page>