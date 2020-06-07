<#import "/common/common.ftl" as c>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<@c.page "Добавление нового пользователя" 4 "">
<form class="" method="POST">
    <#if errors?has_content>
    <div>
    <#list errors as error>
        ${error}<p>
    </#list>
    </div>
    </#if>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="username-addon1">Логин</span>
        </div>
        <input type="text" id="username" class="form-control required" name="username" aria-describedby="username-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="firstName-addon1">Имя</span>
        </div>
        <input type="text" id="firstName" class="form-control required" name="firstName" aria-describedby="firstName-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="lastName-addon1">Фамилия</span>
        </div>
        <input type="text" id="lastName" class="form-control required" name="lastName" aria-describedby="lastName-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="password-addon1">Пароль</span>
        </div>
        <input type="password" id="password" class="form-control required" name="password" aria-describedby="password-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="passwordConfirm-addon1">Повторите пароль</span>
        </div>
        <input type="password" id="passwordConfirm" class="form-control required" name="passwordConfirm" aria-describedby="passwordConfirm-addon1" placeholder="">
    </div>
    <div class="center form-group">
        <button type="submit" id="submit-btn" class="btn dark-btn">Зарегистрировать</button>
    </div>
</form>
<script>
    let validation = [
        ["text","#lastName"],
        ["text", "#firstName"],
        ["password", "#password", "#passwordConfirm"],
        ["text", "#username"]
    ];
    $(document).ready(function()
    {
        correct_input(validation, "#submit-btn");
    });
</script>
</@c.page>