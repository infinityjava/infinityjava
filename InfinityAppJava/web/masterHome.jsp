<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script src="http://code.jquery.com/jquery-1.8.2.js" type="text/javascript"></script>
<script type="text/javascript">
$(function () {
$('.menu ul li').hover(
function () {
$('.sub_menu', this).stop(true, true).slideDown(); /*slideDown the subitems on mouseover*/
}, function () {
$('.sub_menu', this).stop(true, true).slideUp();  /*slideUp the subitems on mouseout*/
});
});
</script>
<style type="text/css">
.menu{
width:600px;
font-family: verdana, Segoe UI;
//background-color:#B34C00;
margin:0 auto;
height:38px;
border: 1px solid #B34C00;
border-radius: 4px; /*To make the corners rounded in IE*/
-moz-border-radius: 4px; /*this is for mozilla*/
-webkit-border-radius: 4px; /*chrome and other browsers*/
box-shadow: 0 1px 1px #dddddd inset;
-moz-box-shadow: 0 1px 1px #dddddd inset;
-webkit-box-shadow: 0 1px 1px #dddddd inset;
}
.menu ul{
padding:0px;
margin: 0px;
list-style: none;
}
.menu ul li{
display: inline-block;
float:left;
position: relative;
}
.menu ul li a{
//color:#ffffff;
text-decoration: none;
display: block;
padding:10px 15px;
}
.menu ul li a:hover{
background-color:#ffffff;
}
.sub_menu{
position: absolute;
//background-color: #666666;
width:200px;
top:38px;
left:0px;
display:none; /*hide the subitems div tag initially*/
border-bottom:4px solid #B34C00; /*just to add a little more good look*/
}
.sub_menu ul li{
width:200px;
}
.sub_menu ul li a{
//color:#ffffff;
text-decoration: none;
display: block;
padding:10px 15px;
}
.sub_items ul li a:hover{
//background-color: #777777;
}
</style>
</head>
<body>
<div class="menu">
<ul>

<li><a href="#">Master Bank Branch Info</a>
<div class="sub_menu">
<ul>
<li><a href="bankBranchInfo.jsp?mode=N">Add</a></li>
<li><a href="bankBranchInfo.jsp?mode=M">Modify</a></li>
<li><a href="bankBranchInfo.jsp?mode=C">Close</a></li>
<li><a href="bankBranchInfo.jsp?mode=A">Authorize</a></li>
<li><a href="bankBranchInfo.jsp?mode=R">Reopen</a></li>
</ul>
</div>

<li><a href="#">Master Prefix Info </a>
<div class="sub_menu">
<ul>
<li><a href="masterPrefixesInfo.jsp?mode=N">Add</a></li>
<li><a href="masterPrefixesInfo.jsp?mode=M">Modify</a></li>
<li><a href="masterPrefixesInfo.jsp?mode=C">Close</a></li>
<li><a href="masterPrefixesInfo.jsp?mode=A">Authorize</a></li>
<li><a href="masterPrefixesInfo.jsp?mode=R">Reopen</a></li>
</ul>
</div>
    
<li><a href="#">Master Occupation Info </a>
<div class="sub_menu">
<ul>
<li><a href="masterOccupationsInfo.jsp?mode=N">Add</a></li>
<li><a href="masterOccupationsInfo.jsp?mode=M">Modify</a></li>
<li><a href="masterOccupationsInfo.jsp?mode=C">Close</a></li>
<li><a href="masterOccupationsInfo.jsp?mode=A">Authorize</a></li>
<li><a href="masterOccupationsInfo.jsp?mode=R">Reopen</a></li>
</ul>
</div>
    
    
    
</ul>
    
</div>
</body>
</html>