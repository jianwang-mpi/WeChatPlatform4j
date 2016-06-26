<%@page language="java" pageEncoding="UTF-8"%>

<%@ page import="ArticleAddition.UserInformation" %>
<%@ page import="ArticleAddition.QueryUser" %>
<%
    int ok=0;
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
    if(session.getAttribute("login")!=null && session.getAttribute("login").equals("ok")){
        ok=1;
    }
    else if(request.getParameter("username")!=null && request.getParameter("passwd")!=null){
        String username=request.getParameter("username");
        String passwd=request.getParameter("passwd");


        /** 链接数据库参数 **/

        //取得结果
        UserInformation userInformation = QueryUser.queryUsername(username);
        if (userInformation!=null){
            if (userInformation.getPasswd().equals(passwd)){
                ok=1;
                session.setAttribute("login","ok");
                session.setAttribute("username",username);
                session.setMaxInactiveInterval(-1);
            }
        }
    }
    if (ok==1){
        String content=0+";URL="+"main.jsp";
        response.setHeader("REFRESH",content);
    }else{
        out.println("<p>用户名或密码错误！5秒后返回<a href=\"index.jsp\">登录页面</a></p>");
        String content=5+";URL="+"index.jsp";
        response.setHeader("REFRESH",content);
    }
%>