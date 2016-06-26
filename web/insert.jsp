<!--实现查找功能-->
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="checkvalid.jsp" %>
<%@page import="java.sql.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="ArticleAddition.ArticleIfExist" %>
<%@ page import="Service.DataBaseService.QueryArticle" %>
<%@ page import="ArticleAddition.AddArticle" %>
<%@ page import="log4j.Log4j" %>

<%

	response.setCharacterEncoding("UTF-8");
	request.setCharacterEncoding("UTF-8");
	//要执行的 sql 查询

	String username=(String)session.getAttribute("username");

	String insertName=(String)request.getParameter("insertName");

%>
<html>
<head>
	<title>Search page</title>
	<meta http-equiv="content-Type" content="text/html;charset=UTF-8"> 
</head>

<body  align="center" style="width:700" bgcolor="#FFFF99">
	<div align="center">
	<table>
	<tr>
	<td style="width:300">
	<form action="insert.jsp" method="post">
		<input type="text" name="insertName" maxlength="20" style="width:120"/>
		<input type="submit" value="插入新文章" />
		<input type="button" value="退出登录" onclick="location.href='logout.jsp'" />
	</form>
	</td>
	</tr>
	</table>
	</div>
	<hr  style="width:700" />
	<div align="center" style="width:700" >
		<li>
	<%
		if (insertName!=null&&insertName.length()!=0){
			if(ArticleIfExist.ifExistArticle(insertName)){
				out.print("文章已经存在，请不要重复插入。");
			}else{
				if(AddArticle.addArticle(insertName))
					out.print("文章插入成功！美编大大辛苦辣~");
				else{
					out.print("文章插入失败！请及时联系管理员~");
				}
			}
		}else{
			out.print("输入错误！");
		}
	%>
		</li>
	</div>


</body>
</html>