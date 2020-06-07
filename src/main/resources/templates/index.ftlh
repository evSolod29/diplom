<#import "/common/common.ftl" as c>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@c.page "Добро пожаловать" 1 "">
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">Статистика</h3><br><br>
        <div class="row">
            <div class="col-sm">
                <h5>Общее количество ремонтов</h5>
                ${totalRepairs}<br><br>
                <h5>Общее количество ремонтов(за месяц)</h5>
                ${addRepairsMonth}<br><br>
                <h5>Общее количество завершенных ремонтов(за месяц)</h5>
                ${endRepairsMonth}<br><br>
            </div>
            <div class="col-sm">
                <h5>Количество компьютеров</h5>
                ${totalComputers}<br><br>
                <h5>Количество новых компьютеров(за месяц)</h5>
                ${addComputersMonth}<br><br>
                <@security.authorize access="isAuthenticated()">
                <h5>Количество не завершенных ремонтов пользователя</h5>
                ${activeRepairsPersonal}<br><br>
                </@security.authorize>
            </div>
            <div class="col-sm">
                <h5>Количество дополнительных устройств</h5>
                ${totalEquipments}<br><br>
                <h5>Количество новых доп. устройств(за месяц)</h5>
                ${addEquipmentsMonth}<br><br>
                <@security.authorize access="isAuthenticated()">
                <h5>Количество завершенных ремонтов пользователя(за месяц)</h5>
                ${endRepairsMonthPersonal}<br><br>
                </@security.authorize>
            </div>
        </div>
    </div>
</div>
</@c.page>