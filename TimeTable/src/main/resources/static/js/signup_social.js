"use strict";

function RegistrationForm(username) {
    this.username = username;
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
    var data = new RegistrationForm(username);
    doAjax("/signup/social","add",data,false,ajaxSusccess,redirect);
}

document.observe('dom:loaded', function() {
    $("btn").observe("click",doRegistration);
});
