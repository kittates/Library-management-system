<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/2 0002
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="description" content=""/>
    <meta name="keywords" content="/>
    <meta name="Author" content="phenix"/>
    <meta name="CopyRight" content="享学科技"/>
</head>
    <frameset rows="40,*" frameborder="no" border="0" framespacing="0">
        <!--头部-->
        <frame src="./top.jsp" name="top" noresize="noresize" frameborder="0" scrolling="no" marginwidth="0" marginheight="0"/>
        <!--主体部分-->
        <frameset cols="185,*">
            <!--主体左部分-->
            <frame src="./left.jsp" name="left" noresize="noresize" frameborder="0" scrolling="no" marginwidth="0" marginheight="0"/>
            <!--主体右部分-->
            <frame src="./main.jsp" name="main" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0"/>
    </frameset>
</html>
