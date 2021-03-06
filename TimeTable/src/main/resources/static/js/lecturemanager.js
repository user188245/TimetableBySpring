"use strict";

var lectureList = [];
var tempTimeList = [];
// mode=0; neutral, mode=1; create, mode2; modify.
var mode = 0;
var target = 0;
var targetID;

function SendLecture(lecture) {
    this.lecture = lecture;
}

function dragElement(event) {
    var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
    var ap_header = $("addpopup_header");
    if (ap_header) {
        ap_header.onmousedown = dragMouseDown;
    } else {
        event.onmousedown = dragMouseDown;
    }

    function dragMouseDown(e) {
        e = e || window.event;
        pos3 = e.clientX;
        pos4 = e.clientY;
        document.onmouseup = closeDragElement;
        document.onmousemove = elementDrag;
    }

    function elementDrag(e) {
        e = e || window.event;
        pos1 = pos3 - e.clientX;
        pos2 = pos4 - e.clientY;
        pos3 = e.clientX;
        pos4 = e.clientY;
        event.style.top = (event.offsetTop - pos2) + "px";
        event.style.left = (event.offsetLeft - pos1) + "px";
    }

    function closeDragElement() {
        document.onmouseup = null;
        document.onmousemove = null;
    }
}


function prepareLectureView() {
    clearElement("lecture_list");
    var ul = $("lecture_list");
    try {
        for(var i=0; i<lectureList.length; i++) {
            var li = document.createElement("li");
            var s = lectureList[i];

            li.appendChild(document.createTextNode(s.name));
            li.setAttribute("class", "w3-hover-green w3-border");

            var modify = document.createElement("span");
            modify.lecture = s;
            modify.index = i;
            modify.appendChild(document.createTextNode("✎"));
            modify.setAttribute("class", "w3-button");
            modify.observe("click", modifyAdder);
            li.appendChild(modify);

            var remove = document.createElement("span");
            remove.setAttribute("index", i);
            remove.appendChild(document.createTextNode("×"));
            remove.setAttribute("class", "w3-button");
            remove.observe("click", removeLecture);
            li.appendChild(remove);

            ul.appendChild(li);
        }
    }catch(e){
        alert(e.message);
    }
}

function refreshAdder(){
    $("lec_add_name").value = "";
    $("lec_add_instructor").value = "";
    $("lec_add_homepage").value = "http://";
    $("lec_add_week").value = 0;
    $("lec_add_location").value = "";
    $("lec_add_timeStart").value = "00:00";
    $("lec_add_timeEnd").value = "00:00";
    //$("lec_add_credit").value = 0;
    clearElement("lec_add_timeList");
    tempTimeList = [];
}

function openAdder(event) {
    if(mode === 0) {
        refreshAdder();
        var popup = $("lecture_popup");
        popup.style.setProperty("display", "block");
        mode = 1;
    }
}

function closeAdder(event) {
    refreshAdder();
    var popup = $("lecture_popup");
    popup.style.setProperty("display","none");
    mode = 0;
}

function modifyAdder() {
    refreshAdder();
    $("lec_add_name").value = this.lecture.name;
    $("lec_add_instructor").value = this.lecture.instructor;
    $("lec_add_homepage").value = this.lecture.homepage;
    tempTimeList = this.lecture.scheduleList;
    prepareTimeView();
    target = this.index;
    targetID = this.lecture.id;
    var popup = $("lecture_popup");
    popup.style.setProperty("display", "block");
    mode = 2;
}

function reportAdder(event) {
    var popup = $("lecture_popup");
    var name = $("lec_add_name").value;
    var instructor = $("lec_add_instructor").value;
    var homepage = $("lec_add_homepage").value;
    var lecture = new Lecture(name,instructor,homepage,null,null);
    lecture.setRegularScheduleList(tempTimeList);
    var method = "N/A";
    if(mode === 1) {
        lectureList.push(lecture);
        method = "add";
    }
    else if(mode === 2){
        lecture.id = targetID;
        lectureList[target] = lecture;
        method = "update";
    }
    prepareLectureView();
    var send = new SendLecture(lecture);
    doAjax("ajax/lecture",method,send,true,postAjax,null);
    popup.style.setProperty("display","none");
    mode = 0;
}

function postAjax(ajax){
	alert("성공적으로 요청되었습니다.")
	lectureList[lectureList.length-1].id = ajax.responseJSON.data;
}

function addTime(event) {
    try {
        var week = $("lec_add_week").value;
        var location = $("lec_add_location").value;
        var startHour = $("lec_add_timeStart").valueAsDate.getUTCHours();
        var startMinute = $("lec_add_timeStart").valueAsDate.getMinutes();
        var endHour = $("lec_add_timeEnd").valueAsDate.getUTCHours();
        var endMinute = $("lec_add_timeEnd").valueAsDate.getMinutes();
        var schedule = new RegularSchedule(new ScheduleTime(startHour,startMinute,endHour,endMinute),week,location,false,lecture);
        tempTimeList[tempTimeList.length] = schedule;
        prepareTimeView();
    }catch(e){
        alert(e.message);
    }
}

function removeLecture(event) {
    if (mode === 0 && confirm("정말로 삭제하시겠습니까?")){
        var index = parseInt(this.getAttribute("index"));
        var lecture = lectureList[index];
        lectureList.splice(index, 1);
        prepareLectureView();
        doAjax("ajax/lecture","remove",{id: lecture.id},false,null,null);
    }
}

function removeTime(event) {
    tempTimeList.splice(parseInt(this.getAttribute("index")), 1);
    prepareTimeView();
}

function prepareTimeView(){

    clearElement("lec_add_timeList");
    var ul = $("lec_add_timeList");
    try {
        for(var i=0; i<tempTimeList.length; i++) {
            var li = document.createElement("li");
            var s = tempTimeList[i];
            if(!(s.scheduleTime instanceof ScheduleTime))
                s.scheduleTime = parseScheduleTime(s.scheduleTime);
            li.appendChild(document.createTextNode("장소:" + s.location + ", 시간: (" + s.scheduleTime.toString() + "[" + Week[s.week] + "]" + ")　　"));
            li.setAttribute("class", "w3-display-container w3-light-grey");

            var close = document.createElement("span");
            close.setAttribute("index", i);
            close.appendChild(document.createTextNode("×"));
            close.setAttribute("class", "w3-button w3-transparent w3-display-right");
            close.observe("click", removeTime);
            li.appendChild(close);
            ul.appendChild(li);
        }
    }catch(e){
        alert(e.message);
    }
}

function init(){
    doAjax("ajax/lecture","get",null,false,initLectures,null);
}

function ajaxFaulure(ajax, exception) {
    alert("Error : \n[Server_status]:" +  ajax.status + "\n[message]:" + ajax.responseText);
    if(exception){
        throw exception;
    }
}

function initLectures(response) {
    var json = response.responseJSON;
    lectureList = json.data;
    prepareLectureView();
}

document.observe('dom:loaded', function() {
    $("lecture_add").observe("click",openAdder);
    $("lec_add_cancel").observe("click",closeAdder);
    $("lec_add_ok").observe("click",reportAdder);
    $("lec_add_timeButton").observe("click",addTime);
    init();
    dragElement($("addpopup"));
});
