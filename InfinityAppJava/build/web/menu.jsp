<%--
    Document   : menu
    Created on : 10 Feb, 2014, 10:53:43 AM
    Author     : kareem
--%>
<%@page import="com.ist.infinity.resources.login.LoginDisplayBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.lang.*" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="org.json.simple.JSONArray"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <script type="text/javascript" >
            $(document).ready(function() {

         
                $(function() {
                   
                  

                    // just to be sure that it is submitting, remove this code
                    $("#formElement").submit(function() {
                        alert('submitting ... ');
                    });
                });

        </script>






    </head>
    <body>
        <div id="menudropdown">
            <select id="type" name="type">
                <option>Select...</option>

                <logic:iterate scope="session" name="modules" id="homePageBean">
                    <option value="<bean:write name="homePageBean" property="redirectedto"/>"><bean:write name="homePageBean" property="modulename"/></option>
                </logic:iterate>
            </select>

        </div>
        <div id="ibody_x">

            <div id="xbody_x">
                <div id="header_x">

                    <div class="midbody_bk">
                        <div id="midbody_x">
                            <div id="midbody_x_x">
                                <div id="leftbody_x">
                                    <div id="sub_body">
                                        <div class="pecsmenudiv">
                                            <ul id="pecsmenutree1">
                                                <%
                                                            List<LoginDisplayBean> optionsList = (List<LoginDisplayBean>) request.getAttribute("parentList");
                                                            int pastLevel = 0;
                                                            for (LoginDisplayBean ldb : optionsList) {
                                                                int level = ldb.getLevel();
                                                                if (level == 0) {
                                                                    if (level < pastLevel) {
                                                %>
                                                </li></ul>
                                                <%pastLevel = level;
                                                                                                                    }%>
                                            <li>
                                                <a href="<%=ldb.getParent_param1()%>" class="subfolderstyle1">
                                                    <%=ldb.getParent_menu()%>
                                                </a>
                                                <%
                                                                                                                } else if (level == 1) {
                                                                                                                    if (pastLevel < level) {
                                                %>
                                                <ul  class="showsubfolderstyle1" id="showchilds">
                                                    <% pastLevel = level;
                                                                                                                                                                        } else if (level == pastLevel) {
                                                    %>
                                            </li>
                                            <%} else if (level < pastLevel) {%>
                                            </li></ul>
                                            <%}%>
                                            <li>
                                                <a href="<%=ldb.getParent_param1()%>" class="child">
                                                    <%=ldb.getParent_menu()%>
                                                </a>
                                                <%
                                                                                                                    pastLevel = level;
                                                                                                                } else if (level > 1) {
                                                                                                                    if (pastLevel < level) {
                                                                                                                        pastLevel = level;
                                                %>
                                                <ul class="subchild"  id="showsubchilds">
                                                    <%} else if (level == pastLevel) {%>
                                            </li>
                                            <%} else if (level < pastLevel) {%>
                                            </li></ul>
                                            <%}%>
                                            <li>
                                                <a href="<%=ldb.getParent_param1()%>" class="childs">
                                                    <%=ldb.getParent_menu()%>
                                                </a>
                                                <%pastLevel = level;
                                                                }
                                                            }%>
                                            </li></ul>
                                            <%if (pastLevel > 1) {%>
                                            </ul>
                                            <%}%>

                                            </ul>
                                            <ul >
                                                <%String user = String.valueOf(session.getAttribute("username"));
                                                            System.out.println("USER " + user);
                                                            if (user.equalsIgnoreCase("superuser")) {%>
                                                <li class="extraul">
                                                    <a href="#" id="systemconfig" >System configuration</a>
                                                </li>
                                                <%}%>
                                                <li class="extraul">
                                                     <a href="masterHome.jsp" id="systemconfig" >Masters Info</a>
                                                </li>
                                                <li class="extraul">
                                                    <html:link forward="logout" >Logout</html:link>
                                                </li>


                                            </ul>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <logic:present name="datanotFound" scope="request">
            <div  id="error">
                No data present. Please login other name.
                <html:errors />
            </div>
        </logic:present>
    </body>
</html>