<#import "/common/common.ftl" as c>
<#import "/common/pagination.ftl" as p>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />

<@c.page "${process.name}">
<div class="card border-dark mb-3" >
    <!--<div class="card-header">Header</div>-->
    <div class="card-body text-dark">
        <h3 class="card-title">Бизнес-процесс - ${process.name}</h3><br><br>
        <div class="row">
            <div class="col-sm">
                <h5>Заказчик</h5>
                ${process.customer}<br><br>
                <h5>Дата ввода в эксплуатацию</h5>
                ${process.startDate?string["yyyy-MM-dd"]}<br><br>
            </div>
            <div class="col-sm">
                <#if process.computer??>
                <h5>Техника</h5>
                <a href="../../computers/details/${process.computer.id?c}">${process.computer.name}</a><br><br>
                <#else>
                <h5>Техника</h5>
                <a href="../../equipments/details/${process.equipment.id?c}">${process.equipment.type} - ${process.equipment.name}</a><br><br>
                </#if>
                <h5>Статус</h5>
                <#if process.inProcess>
                В процессе
                <#else>
                Завершен
                </#if>
            </div>
        </div>
        <h5 >Выполняемые работы в рамках бизнес-процесаа</h5>
        <#if process.works?has_content>
        <#list process.works as w>
        <a href="../../processes/workdetails/${w.id?c}">${w.name} <#if w.inProcess><#else> - Завершена</#if></a><br>
        </#list>
        <a href="../../processes/addworks?id=${process.id}&count=1">Добавить работу</a>
        <#else>
        <a href="../../processes/addworks?id=${process.id}&count=1">Добавить работу</a>
        </#if>
    </div>
</div>
</@c.page>