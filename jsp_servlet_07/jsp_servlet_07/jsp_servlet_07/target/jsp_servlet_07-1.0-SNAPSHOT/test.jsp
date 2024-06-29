<%--
  Created by IntelliJ IDEA.
  User: thidk
  Date: 29/05/2021
  Time: 09:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:query var="rs" dataSource="jdbc/TestDB">
    select id, foo, bar from testdata
</sql:query>

<html>
<head>
    <title>DB Test</title>
    <link type="text/css" rel="stylesheet" href="css/test.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
</head>
<body>

<div id="container">
    <div id="content">
        <h2 class="page-title">Results</h2>

        <c:forEach var="row" items="${rs.rows}">
            <div class="row">
                <p class="row-label">Foo:</p>
                <p class="row-value">${row.foo}</p>
            </div>
            <div class="row">
                <p class="row-label">Bar:</p>
                <p class="row-value">${row.bar}</p>
            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>
