"use strict";

function RegistrationForm(username, password, passwordValidation, email, description) {
    this.username = username;
    this.password = password;
    this.passwordValidation = passwordValidation;
    this.email = email;
    this.description = description;
}



function postData(data) {
    var csrf = $("csrf").getAttribute("content");
    var csrf_header = $("csrf_header").getAttribute("content");
    var param = JSON.stringify(data);
    new Ajax.Request("/signup", {
        method: "post",
        requestHeaders: [csrf_header,csrf],
        contentType:"application/json",
        parameters: param,
        onSuccess: ajaxSusccess,
        onFailure: ajaxFaulure,
        onException: ajaxFaulure
    });
}

function ajaxSusccess(response) {
    alert(response.status);
}

function ajaxFaulure(response) {
    if(response.status == 400) {
        $("exception").innerText = "ERR : " + response.responseJSON.errorCode + "\nCause : " + response.responseJSON.message;
    }else{
    	alert(response.status);
    }
}

function doRegistration() {
    var username = $("id").getValue();
    var password = $("password").getValue();
    var passwordValidation = $("passwordValidation").getValue();
    var email = $("email").getValue();
    var description = $("description").getValue();
    var data = new RegistrationForm(username,password,passwordValidation,email,description);
    postData(data);
}

document.observe('dom:loaded', function() {
    $("btn").observe("click",doRegistration);
});
