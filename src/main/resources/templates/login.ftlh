<#import "/common/common.ftl" as c>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<@c.page "Вход" 0 "">
<form method="POST" action="/login">
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="username-addon1">Логин</span>
        </div>
        <input type="text" id="username" class="form-control required" name="username" aria-describedby="username-addon1" placeholder="">
    </div>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="password-addon1">Пароль</span>
        </div>
        <input type="password" id="password" class="form-control required" name="password" aria-describedby="password-addon1" placeholder="">
    </div>
    <div class="form-group">
        <button id="submit-btn" class="btn dark-btn">Войти</button>
    </div>
</form>
</@c.page>