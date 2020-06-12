<#import "/common/common.ftl" as c>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<@c.page "${task.name}">
<div class="topmenu mb-3">
    <a href="/tasks/">Назад</a>
    <@security.authorize access="hasRole('ADMIN')">
    <a href="/task/delete/${task.id?c}">Удалить</a>
    <a href="/task/edit/${task.id?c}">Изменить</a>
    </@security.authorize>
</div>
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">${task.name}</h3><br><br>
        <div >
            <h2>${task.description}</h2>
        </div>
        <div class="row">
                <div class="col-sm">
                    <h3>Наименование задачи</h3>
                </div>
                <div class="col-sm">
                    <h3>Статус</h3>
                </div>
            </div>
        <#list task.SReports as r>
        <div class="row">
            <div class="col-sm">
                <a href="/reports/details/${r.id?c}"><h5>${r.subprocess.subprocessName}</h5></a>
            </div>
            <div class="col-sm">
                ${r.statusType}
            </div>
        </div>
        </#list>
    </div>
</div>
<script>

</script>
</@c.page>