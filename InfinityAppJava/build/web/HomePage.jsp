
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<%@page import="org.json.simple.JSONArray"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery-1.10.2.min.js"></script>
        <script src="js/knockout-3.0.0.js"></script>
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script src="js/globalize.min.js"></script>
        <script src="js/dx.chartjs.js"></script>
        <title>Home Page</title>
        <link rel="stylesheet" type="text/css" href="styles/commonstyles.css">
        <script type="text/javascript">
            $(document).ready(function() {
                $("#type").change(function() {
                    console.log("here");
                    if ($(this).val()) {
                        window.open($(this).val(), '_blank');
                    }
                });
                $(".subfolderstyle1").mouseover(function() {               
                    $(".showsubfolderstyle1").hide();
                    $(this).siblings().show();
                   
                });

                $(".subfolderstyle1").mouseout(function() {
                    $(".showsubfolderstyle1").hide();
                });

                $(".showsubfolderstyle1").mouseover(function() {
                    $(this).show();

                });

                $(".showsubfolderstyle1").mouseout(function() {
                    $(this).hide();

                });

                $("#systemconfig").click(function() {
                    console.log("here");
                    $.ajax({
                        url: "http://localhost:8080/dattabapp/index.jsp",
                        type: "POST",
                        data: { username: "admin", password: "admin" },
                        context: document.body
                    }).done(function() {
                        window.open("http://localhost:8080/dattabapp/Login.do", '_blank');
                    });
                });
                $(".child").mouseover(function() {
                    $(".subchild").hide();
                    $(this).siblings().show();

                });
                $(".childs").mouseover(function() {
                    //$(".subchild").hide();
                    $(this).siblings().show();

                });
                $(".logout").mouseover(function() {
                    $(".showsubfolderstyle1").hide();

                });
            });
        </script>
        <script type="text/javascript">

            var jsonarray = '${jsonarray}';
            console.log("jsonarray " + jsonarray);
            var jsonChartArrayObject = $.parseJSON(jsonarray);

            $(function()
            {
                $("#chart1").dxChart({
                    dataSource: jsonChartArrayObject[0],

                    commonSeriesSettings: {
                        argumentField: "name",
                        valueField: "value",
                        type: "bar",
                        hoverMode: "allArgumentPoints",
                        selectionMode: "allArgumentPoints",
                        color: '#ffa500'

                    },
                    commonAxisSettings:{
                        label:{
                            font:{
                                size:10
                            }
                        }
                    },
                    
                    legend:{
                        visible:false
                    },

                    valueAxis: {
                        title :{
                            text:jsonChartArrayObject[1].graph1,
                            margin:10,
                            font:{
                                size:12
                            }
                        }
                    },
                    series: {
                        type: "bar",
                        color: '#ffa500'
                    },
                    pointClick: function(point) {
                        point.showTooltip();
                        clearTimeout(timer);
                        timer = setTimeout(function() { point.hideTooltip(); }, 200);
                        $("select option:contains(" + point.argument + ")").prop("selected", true);
                    }
                });
            });
            $(function()
            {

                $("#chart2").dxChart({
                    dataSource: jsonChartArrayObject[2],

                    commonSeriesSettings: {
                        argumentField: "name",
                        valueField: "value",
                        type: "bar",
                        hoverMode: "allArgumentPoints",
                        selectionMode: "allArgumentPoints",
                        color: '#ffa500'

                    },
                    commonAxisSettings:{
                        label:{
                            font:{
                                size:10
                            }
                        }
                    },
                    legend:{
                        visible:false
                    },

                    valueAxis: {
                        title :{
                            text:jsonChartArrayObject[3].graph2,
                            margin:10,
                            font:{
                                size:12
                            }
                        }
                    },
                    series: {
                        type: "bar",
                        color: '#ffa500'
                    },
                    pointClick: function(point) {
                        point.showTooltip();
                        clearTimeout(timer);
                        timer = setTimeout(function() { point.hideTooltip(); }, 200);
                        $("select option:contains(" + point.argument + ")").prop("selected", true);
                    }
                });
            }

        );
            $(function()
            {
                $("#chart3").dxChart({
                    dataSource: jsonChartArrayObject[4],
                    commonSeriesSettings: {
                        argumentField: "name",
                        valueField: "value",
                        type: "bar",
                        hoverMode: "allArgumentPoints",
                        selectionMode: "allArgumentPoints",
                        color: '#ffa500'

                    },
                    commonAxisSettings:{
                        label:{
                            font:{
                                size:10
                            }
                        }
                    },
                    legend:{
                        visible:false
                    },

                    valueAxis: {
                        title :{
                            text:jsonChartArrayObject[5].graph3,
                            margin:10,
                            font:{
                                size:12
                            }
                        }
                    },
                    series: {
                        type: "bar",
                        color: '#ffa500'
                    },
                    pointClick: function(point) {
                        point.showTooltip();
                        clearTimeout(timer);
                        timer = setTimeout(function() { point.hideTooltip(); }, 200);
                        $("select option:contains(" + point.argument + ")").prop("selected", true);
                    }
                });
            }

        );
            $(function()
            {
                $("#chart4").dxChart({
                    dataSource: jsonChartArrayObject[6],
                    commonSeriesSettings: {
                        argumentField: "name",
                        valueField: "value",
                        type: "bar",
                        hoverMode: "allArgumentPoints",
                        selectionMode: "allArgumentPoints",
                        color: '#ffa500'

                    },
                    commonAxisSettings:{
                        label:{
                            font:{
                                size:10,
                                color:'#000'
                            }
                        }
                    },
                    legend:{
                        visible:false
                    },

                    valueAxis: {
                        title :{
                            text:jsonChartArrayObject[7].graph4,
                            margin:10,
                            font:{
                                size:12
                            }
                        }
                    },
                    series: {
                        type: "bar",
                        color: '#ffa500'
                    },
                    pointClick: function(point) {
                        point.showTooltip();
                        clearTimeout(timer);
                        timer = setTimeout(function() { point.hideTooltip(); }, 200);
                        $("select option:contains(" + point.argument + ")").prop("selected", true);
                    }
                });
            }

        );

        </script>

    </head>
    <body>

        <div id="masterdiv">

            <div id="headerdiv">
                <jsp:include page="header.jsp">
                    <jsp:param name="bankname" value="${bankname}"/>

                    <jsp:param name="username" value="${username}"/>

                    <jsp:param name="sysdate" value="${sysdate}"/>

                </jsp:include>
            </div>
            <div id="maindiv">
                <div id="submaindiv">
                    <div id="menudiv">
                        <jsp:include  page="menu.jsp"/>
                    </div>
                    <div id="workspacediv">
                        <jsp:include page="graphs.jsp"/>
                    </div>

                    <div id="taskdiv">
                        <jsp:include page="tasks.html"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
