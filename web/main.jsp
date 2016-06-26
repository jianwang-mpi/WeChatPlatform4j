<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page import="java.sql.*"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
    if(session.getAttribute("login")==null||!session.getAttribute("login").equals("ok")){
        response.sendRedirect("login.jsp");
    }

%>
<html>
<head>
    <title>北大青年文章插入系统</title>
    <meta http-equiv="content-Type" content="text/html;charset=UTF-8">
    <style>
        .input_detail {
            width:500px;
            height: 80px;
            border: 1px solid #ccc;
            border-left-color: #9a9a9a;
            border-top-color: #9a9a9a;
            outline: none;
            word-wrap: break-word;
            font-size: 18px;
            overflow:auto;
        }
        .comment{
            border-style: dashed;
            border-width: 1px 0px 0px 0px;
            border-color: "#202020";
        }
    </style>
</head>
<body  align="center" style="width:700" bgcolor="#FFFF99">
<div align="center">

    <tr>

        <p>您现在想做的是？</p>
        <form action="insert.jsp" method="post">
            <input type="text" name="insertName" maxlength="20" style="width:120"/>
            <input type="submit" value="插入新文章" />
        </form>

        <input type="button" value="退出登录" onclick="location.href='logout.jsp'" />
</div>

</body>
</html>