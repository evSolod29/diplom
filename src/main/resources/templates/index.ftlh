<#import "/common/common.ftl" as c>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@c.page "Добро пожаловать" 1 "">
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">Статистика</h3><br><br>
        <div class="row">
            <div class="col-sm">
                <h5>Количество выполняющихся бизнес-процессов</h5>
                ${tasks}<br><br>
            </div>
            <div class="col-sm">
                <h5>Количество компьютеров</h5>
                ${computers}<br><br>
            </div>
            <div class="col-sm">
                <h5>Количество дополнительных устройств</h5>
                ${equipments}<br><br>
            </div>
        </div>
    </div>
</div>
</@c.page>