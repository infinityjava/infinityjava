
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@include file="masterHome.jsp" %>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery-1.10.2.min.js"></script>

        <script>
           function searchcode()
            {
                  
                 // alert(document.getElementById("codPrefix").value);
                  var str = $("#code").val();
                  alert(str);

                   var xmlhttp;
                if (str == "")
                {
                    document.getElementById("txtHint").innerHTML = "";
                    return;
                }
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp = new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                xmlhttp.onreadystatechange = function()
                {
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        //alert(xmlhttp.responseText);
                            document.getElementById("details").innerHTML = xmlhttp.responseText;
                        //document.getElementById("div1").style.display = "block";
                        //document.getElementById("div2").innerHTML = "<tr><td><input type='submit' name='method' value='modifyRec'/> </td>   </tr>"
                    }
                }
                xmlhttp.open("GET", "PrefixesDetailsAction.do?code=" + str + "&status=M", true);
                xmlhttp.send();



            } 
               
        </script>
    </head>
    <body>
        <center>
            <html:form action="MasterPrefixesAction">
                <%
                            String show = request.getParameter("mode").toString().trim();


                            //  If the parameter is 'addrec'  then add record details display.

                            if (show.equalsIgnoreCase("N")) {
                %>

                Prefix code    <html:text property="codPrefix"></html:text><html:errors property="codPrefix"/> <br>
                Prefix name    <html:text property="txtPrefixDesc"></html:text><html:errors property="txtPrefixDesc"/>
               
                </br>

                <html:submit property="method" value="addNewRec" />

                <% }%>

                <%


                            //  If the parameter is 'modifyrec'  then display modify  record details

                            if (show.equalsIgnoreCase("M")) {
                %>

                Prefix code    <html:text property="codPrefix" styleId="code"></html:text><html:errors property="codPrefix"/>
                <input type="button" value="Search" onclick="searchcode()"></input> <br>

                <div id="details">

                </div>
                
                <html:submit property="method" value="modifyRec" />
                <div id="div2">

                </div>

    

                <% }%>
                <%

                            //  If the parameter is 'deleterec'  then add delete record details display.

                            if (show.equalsIgnoreCase("C")) {
                %>

                <br>   Prefix code    <html:text property="codPrefix" styleId="code">></html:text><html:errors property="codPrefix"/> <br>

                 <input type="button" value="Search" onclick="searchcode()"></input> <br>

                <div id="details">

                </div>


                <html:submit property="method" value="deleteRec" />  <br>

                <% }%>
                <%

                            //  If the parameter is 'authorizedrec'  then authorized record details display.

                            if (show.equalsIgnoreCase("A")) {
                %>

                Prefix code    <html:text property="codPrefix" styleId="code"></html:text><html:errors property="codPrefix"/>
                <input type="button" value="Search" onclick="searchcode()"></input> <br>

                <div id="details">

                </div>
                <html:submit property="method" value="authorizedRec" />  <br>


                <% }%>
                <%


                            //  If the parameter is 'reauthorizedrec'  then reauthorized record details display.

                            if (show.equalsIgnoreCase("R")) {
                %>

                Prefix code    <html:text property="codPrefix" styleId="code">></html:text><html:errors property="codPrefix"/> <br>
                <input type="button" value="Search" onclick="searchcode()"></input> <br>

                <div id="details">

                </div>
                

                <html:submit property="method" value="reAuthorizedRec" />

                <% }%>






            </html:form>


        </center>

    </body>
</html>
