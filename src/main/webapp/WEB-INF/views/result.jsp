<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: cWX560850
  Date: 2018/9/5
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <!--
	<link rel="stylesheet" href="/css/style.css"/>
    -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/js/popper.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery.slim.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
    <script type="text/javascript">
        var websocket = null;

        websocket = new SockJS("http://10.169.225.207:8088/sockjs/socketServer.do");

        websocket.onopen = function () {

        }
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;

        function onMessage(evt) {

            window.location.href = evt.data;
        }

        function onError() {
        }

        function onClose() {
        }

        window.close = function () {
            websocket.onclose();
        }
    </script>

</head>
<body>


<div class="row" style="margin-top: 10%;">
    <div class="col-sm-2 col-md-2">
    </div>
    <div class="col-sm-8 col-md-8 table-responsive">
        <h2 class="text-center"><strong>积分榜</strong></h2>
        <table class="table">
            <caption></caption>
            <thead>
            <tr>
                <th class="text-center">姓名</th>
                <th class="text-center">分数</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${scoreMap }" var="option">
                <c:forEach items="${option.value }" var="employeeid">
                    <tr class="success">
                        <td class="text-center"><c:out value="${employeeid}"/></td>
                        <td class="text-center"><c:out value="${option.key}"/></td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-sm-2 col-md-2">
    </div>
</div>
</body>
</html>
