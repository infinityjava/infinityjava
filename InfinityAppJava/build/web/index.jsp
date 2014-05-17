<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/SiteStyle.css"/>
        <link rel="stylesheet" type="text/css" href="styles/Leftmenu.css"/>
        <title><bean:message key="welcome.title"/></title>
        <html:base/>
        <style type="text/css">
            body{
                background-position: center;
            }
            table{
                align:center;
            }
        </style>

    </head>
    <body >
<%String username=request.getParameter("username");
       String password=request.getParameter("password");

%>
        <center>
            <html:form action="Login" method="post">

                <table align="center">
                    <tr><td><font face="verdana,arial" size=-1><bean:message key="welcome.user"/></font></td><td><html:text name="LoginForm" property="userName"  /></td></tr>

                    <tr><td></td><td><font face="verdana,arial" size=-1><html:submit value="Login" /></font></td></tr>

                </table>
            </html:form>
        </center>


        <logic:present name="errorFound" scope="request">
            <div id="error">
                User name /password entered wrong or cannot be left empty
                <html:errors />
            </div>
        </logic:present>
        <logic:present name="datanotFound" scope="request">
            <div id="error">
                No data present. Please login other name.
                <html:errors />
            </div>
        </logic:present>

    </body>
</html:html>
