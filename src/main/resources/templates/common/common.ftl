<#import "navbar.ftl" as n />
<#macro page title active_link=0 search="true">
<!DOCTYPE HTML>
<html>
    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>${title} - ОАО "ГЗЛиН"</title>
        <link rel="stylesheet" href="/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!--<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">-->
        <script src="/js/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="/css/bootstrap-select.min.css">
        <link rel="stylesheet" href="/css/main.css" />
        <script src="/js/search.js"></script>
        <script src="/js/correct_input.js"></script>
    </head>
    <body>
        <@n.navbar active_link search />
        <div class="container-fluid">
            <#nested />
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="/js/moment.js" ></script>
        <script src="/js/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="/js/bootstrap-datetimepicker.min.js"></script>
        
        <script src="/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="/js/bootstrap-select.min.js"></script>
        <!-- (Optional) Latest compiled and minified JavaScript translation files -->
        <!--<script src="https://kit.fontawesome.com/d08a3b2c67.js"></script>-->
        <!--<script src="/js/select.js"></script>-->
        
    </body>
</html>
</#macro>