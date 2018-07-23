"use strict";

function postData() {
    var csrf = $("csrf").getAttribute("content");
    var csrf_header = $("csrf_header").getAttribute("content");
    new Ajax.Request("/logout", {
        method: "post",
        requestHeaders: [csrf_header,csrf],
        onComplete: redirect
    });
}

function redirect(response){
	window.location.href = "/login";
}

document.observe('dom:loaded', function() {
    $("logout").observe("click",postData);
});
