<%--
  Created by IntelliJ IDEA.
  User: Strogino
  Date: 28.07.2017
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>Meals</title>
    <style type="text/css">
        p {color:red;}
    </style>
</head>

<body>
<table border="1">
    <tr>
        <th>Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
        <c:forEach var="num" items="${list}">
            <tr>
                <td width="500">${num.dateTime}</td>
                <td width="500">${num.description}</td>
                <td width="500">${num.calories}</td>
            </tr>
        </c:forEach>
</table>
    <c:forEach var="num" items="${list}">
        <p>${num}</p>
    </c:forEach>
</body>