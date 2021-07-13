<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/28
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        img{
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
    <h4>用户数据</h4>
    <c:forEach items="${users}" var="u">
        <p>姓名:${u.uname}</p>
        <img src="/img/${u.uimg}/">
        <hr/>
    </c:forEach>
</body>
</html>
