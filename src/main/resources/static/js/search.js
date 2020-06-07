function search(url, attrname, value){
    $('.search').attr("name", attrname);
    console.log(value);
    if(value != null && value !="")
        $('.search').attr("value", value);
    //$('#search-form').submit();
}