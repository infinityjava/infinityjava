<%-- 
    Document   : graphs
    Created on : 19 Mar, 2014, 2:22:27 PM
    Author     : pavani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Graphs</title>
    </head>
    <body>
        <div id="dropdowndiv">

            <html:select name="HomePageActionForm" property="division" >
                <html:options name="HomePageActionForm" property="divisionlist"/>
            </html:select>
            <html:select name="HomePageActionForm" property="region" >
                <html:options name="HomePageActionForm" property="regionlist"/>
            </html:select>
            <html:select name="HomePageActionForm" property="area" >
                <html:options name="HomePageActionForm" property="areaslist"/>
            </html:select>
            <html:select name="HomePageActionForm" property="branch" >
                <html:options name="HomePageActionForm" property="brancheslist"/>
            </html:select>
        </div>
        <div class="graphsdiv">
            <div id="chart1"></div>
            <div id="chart2"></div>
            <div id="chart3"></div>
            <div id="chart4"></div>
        </div>
        <div id="configurediv">
           <button type="button">Configure</button>
        </div>
    </body>
</html>
