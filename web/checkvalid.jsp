
<%
if(session.getAttribute("login")==null||!session.getAttribute("login").equals("ok")){
response.sendRedirect("login.jsp");
}
%>
