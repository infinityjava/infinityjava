/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*

Name: jsDate
Desc: VBScript date functions recreated for Javascript
Author: Rob Eberhardt, Slingshot Solutions - http://slingfive.com/
History:
	2004-11-26	v0.91		fixed datePart/ww bug, added weekdayName() & monthName()
	2004-08-30	v0.9		brand new

*/


function isDate(p_Expression){
	return !isNaN(new Date(p_Expression));
}


// REQUIRES: isDate()
function dateAdd(p_Interval, p_Number, p_Date){
	if(!isDate(p_Date)){return "invalid date: '" + p_Date + "'";}
	if(isNaN(p_Number)){return "invalid number: '" + p_Number + "'";}

	p_Number = new Number(p_Number);
	var dt = new Date(p_Date);
	switch(p_Interval.toLowerCase()){
		case "yyyy": {// year
			dt.setFullYear(dt.getFullYear() + p_Number);
			break;
		}
		case "q": {		// quarter
			dt.setMonth(dt.getMonth() + (p_Number*3));
			break;
		}
		case "m": {		// month
			dt.setMonth(dt.getMonth() + p_Number);
			break;
		}
		case "y":		// day of year
		case "d":		// day
		case "w": {		// weekday
			dt.setDate(dt.getDate() + p_Number);
			break;
		}
		case "ww": {	// week of year
			dt.setDate(dt.getDate() + (p_Number*7));
			break;
		}
		case "h": {		// hour
			dt.setHours(dt.getHours() + p_Number);
			break;
		}
		case "n": {		// minute
			dt.setMinutes(dt.getMinutes() + p_Number);
			break;
		}
		case "s": {		// second
			dt.setSeconds(dt.getSeconds() + p_Number);
			break;
		}
		case "ms": {		// second
			dt.setMilliseconds(dt.getMilliseconds() + p_Number);
			break;
		}
		default: {
			return "invalid interval: '" + p_Interval + "'";
		}
	}
	return dt;
}



// REQUIRES: isDate()
// NOT SUPPORTED: firstdayofweek and firstweekofyear (defaults for both)
function dateDiff(p_Interval, p_Date1, p_Date2, p_firstdayofweek, p_firstweekofyear){
	if(!isDate(p_Date1)){return "invalid date: '" + p_Date1 + "'";}
	if(!isDate(p_Date2)){return "invalid date: '" + p_Date2 + "'";}

	var dt1 = new Date(p_Date1);
	var dt2 = new Date(p_Date2);

	//get ms between dates (UTC) and make into "difference" date
	var iDiffMS = dt2.valueOf() - dt1.valueOf();
	var dtDiff = new Date(iDiffMS);

	// calc various diffs
	var nYears = dtDiff.getUTCFullYear()-1970;
	var nMonths =	dtDiff.getUTCMonth() + (nYears!=0 ? nYears*12 : 0);
	var nQuarters = parseInt(nMonths/3);
	var nWeeks = parseInt(iDiffMS/1000/60/60/24/7);
	var nDays	= parseInt(iDiffMS/1000/60/60/24);
	var nHours = parseInt(iDiffMS/1000/60/60);
	var nMinutes = parseInt(iDiffMS/1000/60);
	var nSeconds= parseInt(iDiffMS/1000);
	var nMilliseconds = iDiffMS;

	// return requested difference
	var iDiff = 0;
	switch(p_Interval.toLowerCase()){
		case "yyyy": return nYears;
		case "q": return nQuarters;
		case "m": return nMonths;
		case "y": 		// day of year
		case "d": return nDays;
		case "w": return nDays;
		case "ww":return nWeeks;		// week of year	// <-- inaccurate, WW should count calendar weeks (# of sundays) between
		case "h": return nHours;
		case "n": return nMinutes;
		case "s": return nSeconds;
		case "ms":return nMilliseconds;	// millisecond	// <-- extension for JS, NOT available in VBScript
		default: return "invalid interval: '" + p_Interval + "'";
	}
}



// REQUIRES: isDate(), dateDiff()
// NOT SUPPORTED: firstdayofweek and firstweekofyear (defaults for both)
function datePart(p_Interval, p_Date, p_firstdayofweek, p_firstweekofyear){
	if(!isDate(p_Date)){return "invalid date: '" + p_Date + "'";}

	var dtPart = new Date(p_Date);
	switch(p_Interval.toLowerCase()){
		case "yyyy": return dtPart.getFullYear();
		case "q": return parseInt(dtPart.getMonth()/3)+1;
		case "m": return dtPart.getMonth()+1;
		case "y": return dateDiff("y", "1/1/" + dtPart.getFullYear(), dtPart);			// day of year
		case "d": return dtPart.getDate();
		case "w": return dtPart.getDay();	// weekday
		case "ww":return dateDiff("ww", "1/1/" + dtPart.getFullYear(), dtPart);		// week of year
		case "h": return dtPart.getHours();
		case "n": return dtPart.getMinutes();
		case "s": return dtPart.getSeconds();
		case "ms":return dtPart.getMilliseconds();	// millisecond	// <-- extension for JS, NOT available in VBScript
		default: return "invalid interval: '" + p_Interval + "'";
	}
}


// REQUIRES: isDate()
function weekdayName(p_Date, p_abbreviate){
	if(!isDate(p_Date)){return "invalid date: '" + p_Date + "'";}
	var dt = new Date(p_Date);
	var retVal = dt.toString().split(' ')[0];
	var retVal = Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday')[dt.getDay()];
	if(p_abbreviate==true){retVal = retVal.substring(0, 3)}	// abbr to 1st 3 chars
	return retVal;
}
// REQUIRES: isDate()
function monthName(p_Date, p_abbreviate){
	if(!isDate(p_Date)){return "invalid date: '" + p_Date + "'";}
	var dt = new Date(p_Date);
	var retVal = Array('January','February','March','April','May','June','July','August','September','October','November','December')[dt.getMonth()];
	if(p_abbreviate==true){retVal = retVal.substring(0, 3)}	// abbr to 1st 3 chars
	return retVal;
}




// bootstrap different capitalizations
function IsDate(p_Expression){
	return isDate(p_Expression);
}
function DateAdd(p_Interval, p_Number, p_Date){
	return dateAdd(p_Interval, p_Number, p_Date);
}
function DateDiff(p_interval, p_date1, p_date2, p_firstdayofweek, p_firstweekofyear){
	return dateDiff(p_interval, p_date1, p_date2, p_firstdayofweek, p_firstweekofyear);
}
function DatePart(p_Interval, p_Date, p_firstdayofweek, p_firstweekofyear){
	return datePart(p_Interval, p_Date, p_firstdayofweek, p_firstweekofyear);
}
function WeekdayName(p_Date){
	return weekdayName(p_Date);
}
function MonthName(p_Date){
	return monthName(p_Date);
}