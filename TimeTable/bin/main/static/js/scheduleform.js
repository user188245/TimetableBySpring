"use strict";
var Week = ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
var Month= ["January","February","March","April","May","June","July","August","September","October","November","December"];

(function() {

    function pad(number) {
        if (number < 10) {
            return '0' + number;
        }
        return number;
    }

    Date.prototype.toISOString = function() {
        return this.getFullYear() +
            '-' + pad(this.getMonth() + 1) +
            '-' + pad(this.getDate());
    };

}());



function getTime(hour,minute){
    hour = (hour + parseInt(minute / 60,10)).toString();
    minute = (minute % 60).toString();

    if(hour < 10) {
        hour = "0" + hour;
    }
    if(minute < 10) {
        minute = "0" + minute;
    }

    var string =  hour + ":" + minute;

    return string;
}

function getFormattedDate(date) {
    return Month[date.getMonth()] + "," + date.getDate() + "," + date.getFullYear() + "(" + Week[(date.getDay() + 6) % 7] + ")";
}

function clearElement(ElementId){
    var e = $(ElementId);
    while(e.firstChild)
        e.removeChild(e.firstChild);
}

function parseScheduleTime(instance){
    return new ScheduleTime(instance.startHour,instance.startMinute,instance.endHour,instance.endMinute);
}

function ScheduleTime(startHour,startMinute,endHour,endMinute){
    this.startHour = startHour;
    this.startMinute  = startMinute;
    this.endHour   = endHour;
    this.endMinute    = endMinute;
}

ScheduleTime.prototype = {
    toString : function() {
        return getTime(this.startHour,this.startMinute) + " ~ " + getTime(this.endHour,this.endMinute);
    },
    initiate : function () {

    },
    getStart: function () {
        return getTime(this.startHour,this.startMinute);
    },
    getEnd: function () {
        return getTime(this.endHour,this.endMinute);
    },
    getHour : function() {
        return this.endHour - this.startHour;
    },
    getMinute : function() {
        return this.endMinute - this.startMinute;
    },
    getFloatTime : function() {
        return this.getHour() + this.getMinute()/60;
    },
    getFloatStart : function() {
        return this.startHour + this.startMinute/60;
    },
    getFloatEnd : function() {
        return this.endHour + this.endMinute/60;
    }
};

function Lecture(name,instructor,homepage,separatingColor,id){
    this.name = name;
    this.instructor = instructor;
    this.homepage = homepage;
    this.separatingColor = separatingColor; // WARNNING!! this is private field, do not refer this value.;
    this.scheduleList = [];
    this.id = id;
    this.toJSON = function() {
        return {
            "id": this.id,
            "name": this.name,
            "instructor": this.instructor,
            "homepage": this.homepage,
            "scheduleList": this.scheduleList
        };
    };
}

Lecture.prototype = {
    toString:function () {
        return this.name + "," + this.instructor;
    },
    initiate : function () {

    },
    addRegularSchedule:function(regularSchedule){
        this.scheduleList.push(regularSchedule);
    },
    addRegularScheduleCustom:function(startHour,startMinute,endHour,endMinute,week,location,isInactive,lecture){
        this.scheduleList.push(new RegularSchedule(new ScheduleTime(startHour,startMinute,endHour,endMinute), week, location, isInactive, lecture));
    },
    setRegularScheduleList:function(regularScheduleList){
        this.scheduleList = regularScheduleList;
    }
};

function RegularSchedule(scheduleTime,week,location,isInactive,lecture){
    this.scheduleTime = scheduleTime;
    this.week = week;
    this.location = location;
    this.lecture = lecture;
    this.isCanceled = isInactive;
    this.initiate();
    this.toJSON = function() {
        return {
            "scheduleTime": this.scheduleTime,
            "week": this.week,
            "location": this.location
        };
    };
}

RegularSchedule.prototype = {
    toString : function(){
        return this.scheduleTime.toString() + "," + Week[this.week] + "," + this.location;
    },
    initiate : function () {
        this.scheduleTime = buildInstance(this.scheduleTime,ScheduleTime);
    }
};


function IrregularSchedule(name,location,text,scheduleTime,date,saperatingColor,id){
    this.name = name;
    this.location = location;
    this.text = text;
    this.scheduleTime = scheduleTime;
    this.date = date;
    this.saperatingColor = saperatingColor;
    this.id = id;
    this.initiate();
    this.toJSON = function() {
        return {
            "id":this.id,
            "name": this.name,
            "location": this.location,
            "text": this.text,
            "scheduleTime": this.scheduleTime,
            "date": this.date
        };
    };

}

IrregularSchedule.prototype = {
    toString : function () {
        return this.name + "," + getFormattedDate(this.date) + "," + this.scheduleTime.toString() + "," + this.location;
    },
    initiate : function () {
        this.scheduleTime = buildInstance(this.scheduleTime,ScheduleTime);
        this.date = new Date(this.date);
    }
};

function buildInstance(instance, Type) {
    var newInstance = Object.setPrototypeOf(instance, Type.prototype);
    newInstance.initiate();
    return newInstance;
}