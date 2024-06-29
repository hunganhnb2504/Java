
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/11/2024
  Time: 10:44 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Expression</title>
    <title>JSP Scriptlet</title>
</head>

<body>
<h1>expression demo</h1>
<h1>JSP Scriptlet</h1>
<h3>Hello World of Java</h3>
<%
    for (int i = 1; i <= 5; i++) {
        System.out.println("<br/>I love T1909M <3 " + i);
    }

%>
Converting a string to uppercase: <%= new String("Hello World").toUpperCase() %>
<br/><br/>
multiplied by 4 equals <%= 25 * 4 %>
<br/><br/>
Is 75 less than 69? <%= 75 < 69 %>
</body>
</html>