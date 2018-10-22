<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="/js/jquery-3.2.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            background: url(/images/background.jpg) repeat;
        }

        #login-box {
            /*border:1px solid #F00;*/
            padding: 35px;
            border-radius: 15px;
            background: #dae0e5;
            color: #fff;
        }

    </style>
</head>
<body>
<div class="container" id="top">

    <div class="row" style="margin-top: 280px; ">

        <div class="col-md-4"></div>

        <div class="col-md-4" id="login-box">

            <form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/user/login" id="from1"
                  method="post">
                <h4>
                    <p class="text-center text-info ">编程规范闯关</p>
                </h4>

                <div class="form-group">
                    <div class="col-sm-12">
                        <input type="text" class="form-control" id="employeeID" placeholder="用户名" name="employeeID">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-12">
                        <input type="password" class="form-control" id="password" placeholder="密码" name="password">
                    </div>
                </div>

                <div class="form-group pull-right" style="margin-right: 15px;">
                    <div class="offset-9">
                        <button type="submit" class="btn defaultbtn- btn-info">登录</button>
                    </div>
                </div>

            </form>

        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>