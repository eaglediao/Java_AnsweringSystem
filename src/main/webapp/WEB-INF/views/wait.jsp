<%--
  Created by IntelliJ IDEA.
  User: cWX560850
  Date: 2018/9/4
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>答题即将开始</title>

    <link rel="stylesheet" type="text/css" href="/css/default.css">

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/popper.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <script src="/js/jquery.min.js"></script>
    <script src="/js/jquery.knob.js"></script>
    <script src="/js/jquery.throttle.js"></script>
    <script src="/js/jquery.classycountdown.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/jquery.classycountdown.css"/>

    <style>
        .ClassyCountdownDemo {
            margin: 0 auto 30px auto;
            max-width: 800px;
            width: calc(100%);
            padding: 30px;
            display: block
        }

        #countdown10 {
            background: #3498db
        }
    </style>
    <!--[if IE]>
    <script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->


    <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
    <script type="text/javascript">

        $("#title_countdown").hide();
        $("#inner_countdown").hide();
        var time = 10;
        var first_load = true;

        var websocket = null;

        websocket = new SockJS("http://10.169.225.207:8088/sockjs/socketServer.do");

        websocket.onOpen = function () {

        }
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;


        function onMessage(evt) {


            if (evt.data == "startCount") {
                if (first_load == true) {
                    countDown();}

            }else if (evt.data == "startGetQuestion") {
                window.location.href = "/question/getQuestion";
            }

        }

        function onError() {
        }

        function onClose() {
        }

        window.close = function () {
            websocket.onclose();
        }

        function countDown() {
            first_load = false;
            $("#title_wait").hide();
            $("#title_countdown").show();
            $("#inner_countdown").show();
            $('#countdown18').ClassyCountdown({
                theme: "flat-colors-black",
                end: $.now() + time
            });
            setInterval("locationToQuestion()", 1000);

        };

        function locationToQuestion() {
            --time;
            // if (time == 0) {
            //     window.location.href = "/question/getQuestion";
            // }

        }


    </script>

</head>

<body>
<%--<img style="position:absolute;left:0px;top:0px;width:100%;height:100%;z-Index:-1; border:1px solid blue"
     src="/images/timg.jpg"/>背景图片--%>

<div class="container-fluid" style="margin-top:20%">

    <div id="title_wait" class="text-center">
        <h1 class="text-info text-center"><strong>欢迎来到TD-Tech极客训练营</strong></h1>
        <h5 style="color:gray()"><strong>答题环节即将开始，请等待其他玩家加入...</strong></h5>
    </div>
    <div id="title_countdown" class="text-center" style="display:none">
        <h1 class="text-info text-center"><strong>答题将在倒计时结束后开始</strong></h1>
    </div>
    <div id="inner_countdown" class="text-center">
        <div id="countdown18" class="ClassyCountdownDemo"></div>
    </div>


</div>


</body>
</html>

