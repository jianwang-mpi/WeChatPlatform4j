<%@ page import="Mail.MailSender" %>
<%@ page import="Mail.MailInformation" %>
<%@ page contentType="text/html; charset=UTF-8"%>
发送成功！
<%
    String name = request.getParameter("name");
    String content = request.getParameter("content");
    MailInformation mailInformation = new MailInformation();
    MailSender mailSender = new MailSender("smtp.126.com",mailInformation.getUsername(),mailInformation.getPassword());
    mailSender.send("pkuyouthvision@163.com","北大青年联系消息","姓名："+name+"<br>内容："+content);
%>