<#macro navbar active_link search="true">
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
<nav class="navbar navbar-expand-lg navbar-light">
    <a class="navbar-brand" href="#">ОАО "ГЗЛиН"</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="<#if active_link == 1> active</#if>" class="nav-item">
                <a class="nav-link" href="/">Главная <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if active_link == 2> active</#if>" href="/reports/">Задачи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link <#if active_link == 3> active</#if>" href="/tasks/">Журнал процессов</a>
            </li>
            <li class="nav-item dropdown <#if active_link == 3> active</#if>" style="border:none;">
                <a class="nav-link dropdown-toggle" style="border:none;" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    Устройства
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/computers/">Компьютеры</a>
                    <a class="dropdown-item" href="/equipments/">Переферийные устройства</a>
                </div>
            </li>
            <@security.authorize access="hasRole('ADMIN')">
            <li class="nav-item dropdown <#if active_link == 4> active</#if>" style="border:none;">
                <a class="nav-link dropdown-toggle" style="border:none;" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    Пользователи
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/users/registration">Добавить пльзователя</a>
                </div>
            </li>
            </@security.authorize>
        </ul>
        <@security.authorize access="! isAuthenticated()">
        <a href="/login" class="btn btn-outline-success mr-1 my-2 my-sm-0">Войти</a>
        </@security.authorize>
        <@security.authorize access="isAuthenticated()">
        <a class="nav-link my-2 my-sm-0 disabled" href="#" tabindex="-1" aria-disabled="true">
            <@security.authentication property="principal.username" />
        </a>
        <a href="/logout" class="btn btn-outline-danger my-2 my-lg-0 mr-sm-2">Выйти</a>
        </@security.authorize>
        <#if search == "true">
        <form id="search-form" class="form-inline my-2 my-lg-0">
            <input class="form-control search" type="search" placeholder="Поиск" aria-label="Search">
        </form>
        </#if>
    </div>
  </nav>
</#macro>