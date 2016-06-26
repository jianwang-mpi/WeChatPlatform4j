<!--实现用户登出功能-->
<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.ResultSetMetaData"%>

<body bgcolor="#FFFF99">
<%
	int ok=0;
	if(session.getAttribute("login")!=null && session.getAttribute("login").equals("ok")){
		session.setAttribute("login",null);
		session.setAttribute("userID",null);
		ok=1;
	}
	out.println("<p>退出登录！！</p>");
	out.println("<p>5秒后返回<a href=\"index.jsp\">登陆</a>页面！</p>");
	String content=5+";URL="+"index.jsp";
	response.setHeader("REFRESH",content); 
%>
</body>