<#import "/common/common.ftl" as c>
<#import "/common/pagination.ftl" as p>

<@c.page "Задачи" 2>
<#if page.content?has_content>
<table class=" text-center table table-bordered table-hover d-none d-sm-block" style="width:100%">
        <tr class="thead">
            <th style="visibility: hidden; display: none"></th>
            <th scope="col">Наименование</th>
            <th scope="col">Статус</th>
        </tr>
        <#list page.content as report>
            <tr id="report-table">
                <td style="visibility: hidden; display: none">${report.id?c}</td>
                <td>${report.subprocess.subprocessName}</td>
                <td>${report.statusType}</td>
            </tr>
        </#list>
</table>
<#if !sometext?has_content>
    <@p.pager url+"?" page />
<#else>
    <@p.pager url+"?search="+search+"&" page />
</#if>
<#else>
Ничего не найдено
</#if>
<script>
$(document).ready(function(){
    search("${url}","search",'${search}');
    $('#report-table').click(function() {
        console.log("click");
        let id = $(this).find("td:first").html();
        if(id) {
            window.location.href = "/reports/details/" + id;
        }
    });
    
});
</script>
    
</@c.page>