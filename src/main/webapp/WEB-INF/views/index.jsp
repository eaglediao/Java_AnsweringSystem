<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cWX560850
  Date: 2018/8/22
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<c:if test="${user.name==null }">
    <c:redirect url="${pageContext.request.contextPath }/user/doLogin"/>
</c:if>
<c:if test="${user.name!=null }">
    <c:redirect url="${pageContext.request.contextPath }/question/getQuestion"/>
</c:if>
</body>
</html>
