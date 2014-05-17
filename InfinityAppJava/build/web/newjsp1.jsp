<%--
    Document   : branchModifyPage
    Created on : Feb 12, 2014, 4:15:01 PM
    Author     : murthy.p
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <script>
            function searchcode()
            {
                var str=$("#codPrefix").val()
                alert(str)
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
                        document.getElementById("div1").innerHTML = xmlhttp.responseText;
                        //document.getElementById("div1").style.display = "block";
                        document.getElementById("div2").innerHTML = "<tr><td><input type='submit' name='method' value='update'/> </td>  <td><input type='submit' name='method' value='delete'/> </td> </tr>"
                    }
                }
                xmlhttp.open("GET", "PrefixesDetailsAction.do?code=" + str + "&status=modify", true);
                xmlhttp.send();
            }
        </script>

    </head>
    <body>
        <center>
            <form action="Login">


                Select Branch   <select property="selected_branch" onchange = "showCustomer(this.value)">
                    <option value="select">please select</option>
                    <option value="ram">ram</option>

                </select> <br>

<input type="button" value="Search" onclick="searchcode()"></input>


                <div id="div1" >
                </div>
                <div id="div2"></div>



            </form>

        </center>
    </body>
</html>