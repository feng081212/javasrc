<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/CustomTaglib"  prefix="CT"%>
<%@ page isELIgnored="false"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>欢迎页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    	hello world ！
    	<CT:formatTimestamp format="yyyy-MM-dd HH:mm:ss"></CT:formatTimestamp>
  </body>
</html>
