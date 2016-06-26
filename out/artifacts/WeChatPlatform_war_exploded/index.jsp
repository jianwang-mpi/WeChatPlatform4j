<!--实现用户登录功能-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%

    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
    if(session.getAttribute("login")!=null && session.getAttribute("login").equals("ok")){
        String content=0+";URL="+"main.jsp";
        response.setHeader("REFRESH",content);
    }
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>北大青年文章插入系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <meta http-equiv="keywords" content="social network,login">
    <meta http-equiv="description" content="This is the login page.">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body bgcolor="#FFFF99" width="300" height="300">
<center>

    <div align="center" class="style1 style2">系 统 登 录
    </div>
    <form action="loginResponse.jsp" method="post">
        <table border="2" bordercolor="black" bgcolor="">
            <tbody>
            <tr>
                <td height="28"><span class="style5">用户名</span> <input type="text" name="username" maxlength="20" style="width:150"></td>
            </tr>
            <tr>
                <td><span class="style5">密&nbsp;&nbsp;码</span> <input type="password" name="passwd" maxlength="20" style="width:150"></td>
            </tr>
            </tbody>
        </table>
        <div align="center">
            <input type="submit" value="登录" />
        </div>
    </form>
    <p>&nbsp;</p>
    <div align="right">
        <p>出品人：王健 等 </p>
    </div>
</center>
</body>
</html>