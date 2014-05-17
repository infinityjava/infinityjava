<%-- 
    Document   : header
    Created on : 19 Mar, 2014, 1:10:00 PM
    Author     : pavani
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
    <html:form action="search" method="post">
        <table id="fontcolor"  width="100%">
            <tr align="left">
                <td >Welcome ${param.username} !</td>

                <td id="headingcolor" align="center">${param.bankname}</td>
                <td align="right">Search<input type="text" name="search"/></td>
                <td align="right"><html:link forward="logout" ><img width="25" height="25" src="image/logout.gif"/></html:link></td>

            </tr>
        </table>
        <table id="fontcolor"  width="100%">
            <tr align="left">
                <td align="left">${param.sysdate}</td>
                <td>

                </td>
                <td>

                </td>
            </tr>

        </table>
    </html:form>
</body>
</html>
