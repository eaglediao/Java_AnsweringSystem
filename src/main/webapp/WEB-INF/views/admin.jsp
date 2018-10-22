<%--
  Created by IntelliJ IDEA.
  User: cWX560850
  Date: 2018/9/4
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/webSocket/send2All">
    <button>send2All</button>
</form>
<form action="${pageContext.request.contextPath }/webSocket/nextTurn">
    <button>nextTurn</button>
</form>
</body>
</html>