function text_validation(selector){
    $(selector).on("keyup focus",function(){
        let value = $(selector).val();
        if(value.trim() != '' && /^[\w+\W]{3,}$/.test(value)==true){
            $(selector).removeClass("is-invalid");
            $(selector).addClass("is-valid");
        } else{
            $(selector).addClass("is-invalid");
            $(selector).removeClass("is-valid");
            $(selector+"error").text("Введены некорректные данные");
        }
    });
}

function password_validation(selector, selector2){
    text_validation(selector);
    $(selector2).on("keyup focus",function(){
        let password = $(selector).val();
        let pswd_confirm = $(selector2).val();
        if(password == pswd_confirm){
            $(selector2).removeClass("is-invalid");
            $(selector2).addClass("is-valid");
        } else{
            $(selector2).addClass("is-invalid");
            $(selector2).removeClass("is-valid");
            $(selector2+"error").text("Введены некорректные данные");
        }
    });
}

function date_validation(selector){
    $(selector).attr("alt","YYYY-MM-dd")
    $(selector).on("keyup focus",function(){
        let value = $(selector).val();
        if(!isNaN(Date.parse(value)) && value.match(/^(\d{4})-(\d\d?)-(\d\d?)$/)){
            $(selector).removeClass("is-invalid");
            $(selector).addClass("is-valid");
        } else{
            $(selector).addClass("is-invalid");
            $(selector).removeClass("is-valid");
            $(selector+"error").text("Введены некорректные данные");
        }
    });
}

function int_validation(selector){
    $(selector).on("keyup focus",function(){
        let value = $(selector).val();
        if(value.match(/^-{0,1}\d+$/)){
            $(selector).removeClass("is-invalid");
            $(selector).addClass("is-valid");
        } else{
            $(selector).addClass("is-invalid");
            $(selector).removeClass("is-valid");
            $(selector+"error").text("Введены некорректные данные");
        }
    });
}

function correct_input(data,button_id){
    let count = -1;
    $(button_id).attr('disabled', true);
    let hasPassword = false;
    let elements = "";
    data.forEach(element => {
        if(element[0]=="password"){
            hasPassword = true;
            elements += element[1] + ", " + element[2] + ", ";
        }
        else{
            elements += element[1] + ", ";
        }
        switch(element[0]){
            case "text":{
                text_validation(element[1]);
                break;
            }
            case "password":{
                password_validation(element[1], element[2]);
                break;
            }
            case "date":{
                date_validation(element[1]);
                break;
            }
            case "int":{
                int_validation(element[1]);
                break;
            }
        }
    });
    elements = elements.substring(0,elements.length - 2);
    if(hasPassword){
        count = data.length + 1;
    }
    else{
        count = data.length;
    }
    $(elements).on("keyup focus", function(){
        let count_is_valid = $(".is-valid").length;
        if(count_is_valid >= count){
            $(button_id).removeAttr('disabled');
        }
        else{
            $(button_id).attr('disabled', true);
        }
    });
}