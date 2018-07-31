"use strict";

function RegistrationForm(username, password, passwordValidation, email, description) {
    this.username = username;
    this.password = password;
    this.passwordValidation = passwordValidation;
    this.email = email;
    this.description = description;
}

function ajaxSusccess(response) {
    alert("성공적으로 생성되었습니다.");
}

function ajaxFaulure(response) {
    if(response.status == 400) {
        $("exception").innerText = "ERR : " + response.responseJSON.errorCode + "\nCause : " + response.responseJSON.message;
    }else{
        alert(response.status);
    }
}

function redirect(response){
    var redirectUrl = response.responseJSON.redirect;
    if(redirectUrl !== undefined && redirectUrl !== null){
        window.location.href = redirectUrl;
    }
}

function doRegistration() {
    var username = $("id").getValue();
    var password = $("password").getValue();
    var passwordValidation = $("passwordValidation").getValue();
    var email = $("email").getValue();
    var description = $("description").getValue();
    var data = new RegistrationForm(username,password,passwordValidation,email,description);
    doAjax("/signup","add",data,true,ajaxSusccess,redirect);
}

document.observe('dom:loaded', function() {
    $("btn").observe("click",doRegistration);
});
