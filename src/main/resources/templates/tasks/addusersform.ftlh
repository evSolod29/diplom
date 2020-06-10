<#if subprocesses?? && users??>
<#assign index = 0 >
<#list subprocesses as s>
<div id="user-${index}-select" class="input-group mb-3">
    <div class="input-group-prepend">
        <input style="display:none" value="${s.id?c}" id="subprocess-${index}">
        <label class="input-group-text" for="userId-${index}">${s.subprocessName}</label>
    </div>
    <select class="form-control" id="userId-${index}">
        <option selected value="-1">Нет</option>
        <#list users as user>
        <option value="${user.id?c}">${user.lastName} ${user.firstName}</option>
        </#list>
    </select>
</div>
<#assign index = index + 1>
</#list>
<input type="text" id="count-subprocesses" style="display: none;" value="${index}">
<#else> 
<div class="alert alert-danger" role="alert">
    Ошибка! Не найдены подпроцессы. Попробуйте позже.
</div>
</#if>