"use strict";

var currentDate;

// mode=0; neutral, mode=1; create, mode2; modify.
var mode = 0;
var target;
var targetID;

var scheduleList = [];



function getMaxDay(date){
    var date = new Date(date);
    date.setDate(32);
    return 32-date.getDate();
}

function getStartWeek(date) {
    var date = new Date(date);
    date.setDate(1);
    return (date.getDay() + 6) % 7;
}

function makeli(param,active) {
    var li = document.createElement("li");
    if(active)
        li.setAttribute("class","active");
    li.appendChild(document.createTextNode(param));
    if(Number.isInteger(param)) {
        if(scheduleList[param] !== undefined && scheduleList[param].length > 0){
            var star = document.createElement("span");
            star.appendChild(document.createTextNode("*"));
            li.appendChild(star);
        }
        li.day = param;
        li.observe("click",moveCalendar);
    }
    return li;
}

function moveCalendar(e) {
    currentDate.setDate(this.day);
    makeCalendar(currentDate);
    prepareScheduleView();
}

function prevCalendar(e) {
    currentDate.setMonth(currentDate.getMonth()-1);
    init();
    makeCalendar(currentDate);
    prepareScheduleView();
}

function nextCalendar(e) {
    currentDate.setMonth(currentDate.getMonth()+1);
    init();
    makeCalendar(currentDate);
    prepareScheduleView();
}

function makeCalendar(date) {
    var days = $$("ul.days");
    for(var d=0; d<days.length; d++){
        while(days[d].firstChild)
            days[d].removeChild(days[d].firstChild);
        var startWeek = getStartWeek(date);
        var maxDate = getMaxDay(date);
        var nowDay = date.getDate();
        $("year").innerText = date.getFullYear();
        $("month").innerText = Month[date.getMonth()];
        for(var i=0; i<startWeek; i++)
            days[d].appendChild(makeli("",false));
        for(var i=1; i<=maxDate; i++){
            if(i === nowDay){
                days[d].appendChild(makeli(i,true));
            }else{
                days[d].appendChild(makeli(i,false));
            }
        }
    }
}

function SendSchedule(schedule) {
    this.schedule = schedule;
}

function prepareScheduleView(){
    clearElement("s_list");
    var ul = $("s_list");
    try {
        for(var i=0; i<scheduleList[currentDate.getDate()].length; i++) {

            var li = document.createElement("li");
            var s = scheduleList[currentDate.getDate()][i];

            li.appendChild(document.createTextNode(s.name));
            li.setAttribute("class", "w3-hover-green w3-border");

            var modify = document.createElement("span");
            modify.schedule = s;
            modify.index = i;
            modify.appendChild(document.createTextNode("✎"));
            modify.setAttribute("class", "w3-button");
            modify.observe("click", modifyAdder);
            li.appendChild(modify);

            var remove = document.createElement("span");
            remove.setAttribute("index", i);
            remove.setAttribute("class", "w3-button");
            remove.appendChild(document.createTextNode("×"));
            remove.index = i;
            remove.observe("click", removeSchedule);
            li.appendChild(remove);
            ul.appendChild(li);
        }
    }catch(e){
        alert("error on prepare : date =" + currentDate.getDate());
        alert(e.message);
    }
}

function refreshAdder(){
    $("s_add_name").value = "";
    $("s_add_location").value = "";
    $("s_add_text").value = "";
    $("s_add_timeStart").value = "00:00";
    $("s_add_timeEnd").value = "00:00";
}

function openAdder(event) {
    if(mode === 0) {
        refreshAdder();
        var popup = $("s_popup");
        popup.style.setProperty("display", "block");
        mode = 1;
    }
}

function closeAdder(event) {
    refreshAdder();
    var popup = $("s_popup");
    popup.style.setProperty("display","none");
    mode = 0;
}

function modifyAdder() {
    refreshAdder();
    $("s_add_name").value = this.schedule.name;
    $("s_add_location").value = this.schedule.location;
    $("s_add_text").value = this.schedule.text;
    $("s_add_timeStart").value = this.schedule.scheduleTime.getStart();
    $("s_add_timeEnd").value = this.schedule.scheduleTime.getEnd();
    target = this.index;
    targetID = this.schedule.id;
    var popup = $("s_popup");
    popup.style.setProperty("display", "block");
    mode = 2;
}

function reportAdder(event) {
    var popup = $("s_popup");
    var name = $("s_add_name").value;
    var location = $("s_add_location").value;
    var text = $("s_add_text").value;
    var startHour = $("s_add_timeStart").valueAsDate.getUTCHours();
    var startMinute = $("s_add_timeStart").valueAsDate.getMinutes();
    var endHour = $("s_add_timeEnd").valueAsDate.getUTCHours();
    var endMinute = $("s_add_timeEnd").valueAsDate.getMinutes();
    var schedule = new IrregularSchedule(name,location,text,new ScheduleTime(startHour,startMinute,endHour,endMinute),currentDate.toISOString(),null,null);

    var method = "N/A";
    if(mode === 1) {
        scheduleList[currentDate.getDate()].push(schedule);
        method = 'add';
    }
    else if(mode === 2){
        schedule.id = targetID;
        scheduleList[currentDate.getDate()][target] = schedule;
        method = 'update';
    }
    prepareScheduleView();
    var send = new SendSchedule(schedule);
    doAjax("ajax/calendar",method,send,true,postSuccess,null);
    popup.style.setProperty("display","none");
    makeCalendar(currentDate);
    mode = 0;
}

function removeSchedule(event) {
    if (mode === 0 && confirm("정말로 삭제하시겠습니까?")){
        var schedule = scheduleList[currentDate.getDate()][this.index];
        scheduleList[currentDate.getDate()].splice(this.index, 1);
        prepareScheduleView();
        doAjax("ajax/calendar","remove",{id:schedule.id},false,postSuccess,null);
    }
}

function postSuccess(ajax) {
    alert("성공적으로 요청되었습니다.");
    var id = ajax.responseJSON.data;
    if(id !== undefined && id !== null){
    	var scheduleListNow = scheduleList[currentDate.getDate()];
    	scheduleListNow[scheduleListNow.length-1].id = id;
    }
    makeCalendar(currentDate);
}

function init(){
    doAjax("ajax/calendar","get",{date:currentDate.toISOString()},false,initSchedules,null);
}

function ajaxFaulure(ajax, exception) {
    alert("Error : \n[Server_status]:" +  ajax.status + "\n[message]:" + ajax.responseText);
    if(exception){
        throw exception;
    }
}

function initSchedules(ajax) {
    var json = ajax.responseJSON;
    var jscheduleList = json.data;
    for(var i=1; i<=31; i++)
        scheduleList[i] = [];
    for(var i=0; i<jscheduleList.length; i++){
        var s = jscheduleList[i];
        var schedule = buildInstance(s, IrregularSchedule);
        scheduleList[schedule.date.getDate()].push(schedule);
    }
}

document.observe('dom:loaded', function() {
    currentDate = new Date();
    var prevs = $$(".prev");
    var nexts = $$(".next");
    for(var i=0; i<prevs.length; i++){
        prevs[i].observe("click", prevCalendar);
    }
    for(var i=0; i<nexts.length; i++){
        nexts[i].observe("click", nextCalendar);
    }
    $("s_add").observe("click",openAdder);
    $("s_add_cancel").observe("click",closeAdder);
    $("s_add_ok").observe("click",reportAdder);
    init();
    makeCalendar(currentDate);
});


