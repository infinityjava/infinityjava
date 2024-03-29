/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


// JScript File

         // how reliable is this test?
         isIE = (document.all ? true : false);
	 isDOM = (document.getElementById ? true : false);

         // Initialize arrays.
         var months = new Array("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
	 "Aug", "Sep", "Oct", "Nov", "Dec");
         var daysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31,
            30, 31, 30, 31);

	 //change by mayank dhingra
	 var displayMonth = new Date().getMonth();
	 var displayYear = new Date().getFullYear();

	 //var displayMonth = <%=month(date)%>;
	 //var displayYear = new Date().getFullYear();
	 var displayDivName;
	 var displayElement;

         function getDays(month, year) {
            // Test for leap year when February is selected.
            if (1 == month)
               return ((0 == year % 4) && (0 != (year % 100))) ||
                  (0 == year % 400) ? 29 : 28;
            else
               return daysInMonth[month];
         }

         function getToday() {
            // Generate today's date.
            this.now = new Date();
            this.year = this.now.getFullYear();
            this.month = this.now.getMonth();
            this.day = this.now.getDate();
         }

         // Start with a calendar for today.
         today = new getToday();

         function newCalendar(eltName,attachedElement) {
	    if (attachedElement) {
	       if (displayDivName && displayDivName != eltName) hideElement(displayDivName);
	       displayElement = attachedElement;
	    }
	    displayDivName = eltName;
            today = new getToday();
            var parseYear = parseInt(displayYear + '');
            var newCal = new Date(parseYear,displayMonth,1);
            var day = -1;
            var startDayOfWeek = newCal.getDay();
            if ((today.year == newCal.getFullYear()) &&
                  (today.month == newCal.getMonth()))
	    {
               day = today.day;
            }
            var intDaysInMonth =
               getDays(newCal.getMonth(), newCal.getFullYear());
            var daysGrid = makeDaysGrid(startDayOfWeek,day,intDaysInMonth,newCal,eltName)
	    if (isIE) {
	       var elt = document.all[eltName];
	       elt.innerHTML = daysGrid;
            } else if (isDOM) {
	       var elt = document.getElementById(eltName);
	       elt.innerHTML = daysGrid;
	    } else {
	       var elt = document.layers[eltName].document;
	       elt.open();
	       elt.write(daysGrid);
	       elt.close();
	    }
	 }

	 function incMonth(delta,eltName) {
	   displayMonth += delta;
	   if (displayMonth >= 12) {
	     displayMonth = 0;
	     incYear(1,eltName);
	   } else if (displayMonth <= -1) {
	     displayMonth = 11;
	     incYear(-1,eltName);
	   } else {
	     newCalendar(eltName);
	   }
	 }

	 function incYear(delta,eltName) {
	   displayYear = parseInt(displayYear + '') + delta;
	   newCalendar(eltName);
	 }

	 function makeDaysGrid(startDay,day,intDaysInMonth,newCal,eltName) {
	    var daysGrid;
	    var month = newCal.getMonth();
	    var year = newCal.getFullYear();
	    var isThisYear = (year == new Date().getFullYear());
	    var isThisMonth = (day > -1)
	    daysGrid = '<table border=1 bordercolor=navy  cellspacing=0 cellpadding=2><tr><td bgcolor=#ffffff nowrap>';
	    daysGrid += '<font face="courier new, courier" size=2>';
	    daysGrid += '<a href="javascript:hideElement(\'' + eltName + '\')">x</a>';
	    daysGrid += '&nbsp;&nbsp;';
	    daysGrid += '<a href="javascript:incMonth(-1,\'' + eltName + '\')">&laquo; </a>';

	    daysGrid += '<b>';
	    if (isThisMonth) { daysGrid += '<font color=red>' + months[month] + '</font>'; }
	    else { daysGrid += months[month]; }
	    daysGrid += '</b>';

	    daysGrid += '<a href="javascript:incMonth(1,\'' + eltName + '\')"> &raquo;</a>';
	    daysGrid += '&nbsp;&nbsp;&nbsp;';
	    daysGrid += '<a href="javascript:incYear(-1,\'' + eltName + '\')">&laquo; </a>';

	    daysGrid += '<b>';
	    if (isThisYear) { daysGrid += '<font color=red>' + year + '</font>'; }
	    else { daysGrid += ''+year; }
	    daysGrid += '</b>';

	    daysGrid += '<a href="javascript:incYear(1,\'' + eltName + '\')"> &raquo;</a><br>';
	    daysGrid += 'Su|Mo|Tu|We|Th|Fr|Sa<br>&nbsp;';
	    var dayOfMonthOfFirstSunday = (7 - startDay + 1);
	    for (var intWeek = 0; intWeek < 6; intWeek++) {
	       var dayOfMonth;
	       for (var intDay = 0; intDay < 7; intDay++) {
	         dayOfMonth = (intWeek * 7) + intDay + dayOfMonthOfFirstSunday - 7;
		 if (dayOfMonth <= 0) {
	           daysGrid += "&nbsp;&nbsp; "
		 } else if (dayOfMonth <= intDaysInMonth) {
		   var color = "blue";
		   if (day > 0 && day == dayOfMonth) color="red";
		   daysGrid += '<a href="javascript:setDay(';
		   daysGrid += dayOfMonth + ',\'' + eltName + '\')" '
		   daysGrid += 'style="color:' + color + '">';
		   var dayString = dayOfMonth + "</a> ";
		   if (dayString.length == 6) dayString = '0' + dayString;
		   daysGrid += dayString;
		 }
	       }
	       if (dayOfMonth < intDaysInMonth) daysGrid += "<br>&nbsp;";
	    }
	    return daysGrid + "</td></tr></table>";
	 }
	 function setDay(day,eltName)
	 {
	   var objmonth, objday;
	   objmonth = new String(displayMonth + 1)
	   if (objmonth.length == 1)
			objmonth = "0" + objmonth
	   objday = new String(day )
	   if (objday.length == 1)
			objday = "0" + objday
	   displayElement.value =  objday+ "/" + objmonth + "/" + displayYear;
	   hideElement(eltName);
	 }