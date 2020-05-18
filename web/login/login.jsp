<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/4/22
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>广东财经大学考勤系统登录界面</title>
    <!-- Bootstrap -->
    <link href="../css/bootstrap-4.2.1.css" rel="stylesheet">
    <link href="../css/glyphicon.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="../js/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../js/popper.min.js"></script>
    <script src="../js/bootstrap-4.2.1.js"></script>
    <script type="text/javascript">
        //首先判断输入的是否是空值，不是则提交
        function checkstr() {
            if (document.getElementById("username").value == "") {
                alert("用户名不能为空！");
                return false;
            }
            if (document.getElementById("password").value == "") {
                alert("密码不能为空！");
                return false;
            }
            return true;
        }
    </script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">广财大</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span
            class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link" href="..\index.jsp">主页 <span
                    class="sr-only">(current)</span></a></li>
            <li class="nav-item"></li>
            <!-- <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 教师查看系统 </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">任课教师</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="fudaoteacher01 .html">辅导员</a>
              </div>
            </li> -->
            <!-- <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 考勤 </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="#">签到</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">签退</a>
                <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">补签</a>
            </div>
          </li> -->
        </ul>

    </div>
    <img class="rounded" alt="18x2" style="width: 170px; height:40px;" src="..\images\logo.png"
         data-holder-rendered="true">
</nav>
<header>

</header>
<div class="jumbotron">
    <div class="container">

        <div class="container">

            <p>&nbsp;</p>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">


                    <h2 class="text-center">用户登录界面</h2>
                    <p>&nbsp;</p>
                    <form class="text-center" method="post" action="/loginCheck">

                        <div class="input-group mb-5">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><span class="glyphicon glyphicon-user"></span></span>
                            </div>
                            <input type="text" id="username" name="username" class="form-control" placeholder="请输入账号">
                        </div>

                        <div class="input-group mb-5">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><span class="glyphicon glyphicon-lock"></span></span>
                            </div>
                            <input type="password" id="password" name="password" class="form-control" placeholder="请输入密码">
                        </div>
                        <input type="submit" class="btn btn-primary btn-lg" value="登录"
                               onclick="return checkstr()">
                    </form>
                </div>
                <div class="col-4"></div>
            </div>
        </div>

    </div>
</div>

<footer class="text-center">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <p>Copyright © GDUFE@WaiterXiaoYY. All rights reserved.</p>
            </div>
        </div>
    </div>
</footer>

</body>
</html>
