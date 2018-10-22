<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>答题页面</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <script type="text/javascript" src="/js/popper.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/jquery.slim.min.js"></script>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
</head>
<body class="text-align：middle">
<div class="bg-info">
    <h2 class="text-center text-default" style="color: #ffffff; padding:1%"><strong>编程规范闯关</strong></h2>
</div>
<div class="row" style="margin-top: 1%; ">
    <div class=" col-md-3 font-weight-bold" align="center">
        <c:if test="${user.name!=null }">
        当前用户：${user.name }
    </div>
    <div class="col-md-3 font-weight-bold " align="center">
        得分：${user.score }
    </div>
    </c:if>
    <div class=" col-md-3 font-weight-bold" align="center">当前人数：
        <span id="count" class=" font-weight-bold text-success">
            <c:out value="${userCount}"/></span>
    </div>

    <div class=" col-md-3 font-weight-bold" align="center">倒计时：
        <span id="ant" class="text-warning"></span>
    </div>
</div>
<div  style="margin-top:1%">
    <div style="border:2px solid #17a2b8"></div>
</div>
</div>
<div class="" id="top">
    <form class="" name="questionForm" action="${pageContext.request.contextPath }/question/judge" method="post">

        <div class="row" style="margin-top:10%">
            <div class="offset-3 col-md-8">
                <h4 class="text-info"><c:out value="${content}"/></h4>
            </div>
        </div>
        <div class="row" style="margin-top:1%">
            <div class="offset-3 col-md-8">
                <c:forEach items="${options }" var="option">
                    <%--<h5 class="custom-radio row" style="margin-top:1%">--%>
                    <%--<div class="col-md-1" align="center">--%>
                    <%--<input type="radio" name="answer" value="${option.getKey()}"/>&nbsp;${option.getKey()}--%>
                    <%--</div>--%>
                    <%--<div class="col-md-10"><span class="text-primary" id="${option.getKey()}">--%>
                    <%--${option.getValue()}</span>--%>
                    <%--</div>--%>
                    <%--</h5>--%>
                    <div class="custom-radio">
                        <label>
                            <input type="radio" name="answer"
                                   value="${option.getKey()}"/>
                                ${option.getKey()}<span class="col-md-6 text-primary font-weight-bold" id="${option.getKey()}">
                                ${option.getValue()}
                        </span>
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>
    </form>
</div>

</div>
</body>
<script>
    window.onload = function () {
        var odiv = document.getElementById("ant");
        var count = 10;
        odiv.innerHTML = count + " S";
        var timer = null;
        timer = setInterval(function () {
            if (count > 0) {
                count = count - 1;
                odiv.innerHTML = count + " S";
            }
            else {
                clearInterval(timer);
                submitAnswer();
            }
        }, 1000);
        // var counts = document.getElementById("count");
        // counts.innerHTML = 67 + "/90";

    }

    //倒计时时间到开始提交答案，没选择默认不做答
    function submitAnswer() {
        $.ajax({
            type: "POST",//发送请求类型
            url: "${pageContext.request.contextPath }/question/judge",//请求路径
            //async:false,//默认为true,是异步请求
            //传参给后台的形式(两种方式)
            data: $("form").serialize(),
            dataType: "json",//返回类型
            cache: false,//不使用当前浏览器的缓存
            success: function (msg) {//请求成功后调用的回调函数
                debugger
                if (msg.result=="correct") {
                    document.getElementById(msg.userAnswer).setAttribute("class", "col-md-3 text-success font-weight-bold");
                } else {
                    document.getElementById(msg.answer).setAttribute("class", "col-md-3 text-success font-weight-bold");
                    if(msg.userAnswer != null){
                        document.getElementById(msg.userAnswer).setAttribute("class", "col-md-3 text-danger font-weight-bold");
                    }
                }
                setTimeout("window.location.href='${pageContext.request.contextPath }/question/getQuestion'", 5000);
            },
            error: function () {
                //Exception
                alert("error");
                <%--document.getElementById("A").setAttribute("class", "badge-success");--%>
                <%--document.getElementById("C").setAttribute("class", "badge-danger");--%>
                <%--setTimeout("window.location.href='${pageContext.request.contextPath }/question/getQuestion'", 5000);--%>
            }
        });
    }
</script>
</html>