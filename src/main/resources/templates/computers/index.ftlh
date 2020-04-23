<#import "/common/common.ftl" as c>
<@c.page "ПЭВМ">
<div class="topmenu mb-3">
    <a href="/computers/add">Добавить</a>
    
    <input class="mb-2" id="search" type="text" placeholder="Поиск...">
</div>
<table class="table table-bordered table-hover">
    <thead class="thead-dark">
        <tr>
            <th scope="col">Инв. №</th>
            <th scope="col">Наименование</th>
            <th scope="col">Подразделение</th>
            <th scope="col">Дата ввода в эксплуатацию</th>
            <th scope="col">Перемещение внутри предприятия</th>
        </tr>
    </thead>
    <tbody id="computers-table">
        <#list computers as computer>
            <tr>
                <td id="disabled">${computer.id?c}</td>
                <td>${computer.invNo?c}</td>
                <td>${computer.name}</td>
                <td>${computer.subdivision}</td>
                <td>${computer.commissioningDate?string["yyyy-MM-dd"]}</td>
                <td>${computer.movement}</td>
            </tr>
        </#list>
    </tbody>
</table>
<script>
    $(document).ready(function(){
        $('#computers-table tr').click(function() {
            let id = $(this).find("td:first").html();
            if(id) {
                window.location.href = "/computers/details/" + id;
            }
        });
        //Search
        $("#search").on("keyup", function() {
            var value = $(this).val();
            $.ajax({
                url:"search",
                type:"POST",
                data:{search : value},
                dataType:"json",
                contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                success: function(data, status){
                    //alert("Data: " + data + "\nStatus: " + status);
                    //alert($("#computers-table").html());
                    $("#computers-table").empty();
                    for (let index = 0; index < data.length; index++) {
                        $("#computers-table").append(
                            '<tr><td id="disabled">'+ data[index].id +'</td><td>' + data[index].invNo + '</td>' +
                            '<td>' + data[index].name + '</td>' +
                            '<td>' + data[index].subdivision + '</td>' +
                            '<td>' + data[index].commissioningDate + '</td>' +
                            '<td>' + data[index].movement + '</td></tr>'
                        );
                    }
                    $('#computers-table tr').click(function() {
                        let id = $(this).find("td:first").html();
                        if(id) {
                            window.location.href = "/computers/details/" + id;
                        }
                    });
                }
            });
        });
    });
</script>
    
</@c.page>