<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/27
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/login.shtml" method="post">
        <label>用户名:</label>
        <input type="text" name="uname"/>
        <br/>
        <label>密码:</label>
        <input type="password" name="upwd"/>
        <br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
