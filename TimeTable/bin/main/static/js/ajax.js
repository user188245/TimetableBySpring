
function doAjax(url,method,data,isJson,ajaxSusccess,redirect) {
    var csrf = $("csrf").getAttribute("content");
    var csrf_header = $("csrf_header").getAttribute("content");
    var param = (isJson)?JSON.stringify(data):data;
    new Ajax.Request(url + "/" + method, {
        method: method,
        requestHeaders: [csrf_header,csrf],
        contentType:(isJson)?"application/json":"application/x-www-form-urlencoded",
        parameters: param,
        onSuccess: ajaxSusccess,
        onFailure: ajaxFaulure,
        onException: ajaxFaulure,
	onComplete: redirect
    });
}
