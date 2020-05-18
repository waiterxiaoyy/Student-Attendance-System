<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/4/22
  Time: 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>广东财经大学考勤系统</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap-4.2.1.css" rel="stylesheet">
    <link href="css/glyphicon.css" rel="stylesheet">
    <script>
        window.onload = function () {

            var outlogin_btn = document.getElementById("outlogin_btn");
            var outlogin_back = document.getElementById("outloginModal");
            var outlogin_close_all = document.getElementById("outlogin_close_all");
            var outlogin_success = document.getElementById("outlogin_success");
            var outlogin_fail = document.getElementById("outlogin_fail");

            outlogin_btn.onclick = function () {
                outloginModal.style.display = "block";
            };
            outlogin_close_all.onclick = function () {
                outloginModal.style.display = "none";
            };
            outlogin_success.onclick = function () {
                outloginModal.style.display = "none";
                window.location = "/outlogin"
            };
            outlogin_fail.onclick = function () {
                outloginModal.style.display = "none";
            };
        };
        function open_collegeClass() {
            location.href = "/collegeClass?method=findCollegeClassByGrade";
        };
        function open_xueye() {
            location.href = "/collegeClass?method=findCollegeClassByGradeForLearn";
        };
        function open_library() {
            location.href = "/collegeClass?method=findCollegeClassByGradeForLibrary";
        };
    </script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand">广财大</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span
            class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link" href="../index.jsp">主页 <span
                    class="sr-only">(current)</span></a></li>
            <li class="nav-item"></li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-user"> ${curUsername}</span>
            </button>
            &nbsp;&nbsp;
            <a class="btn btn-primary" href="#" role="button" style="text-shadow: black 5px 3px 3px;">${curCollege}</a>
            &nbsp;&nbsp;
            <button type="button" id="outlogin_btn" class="btn btn-secondary" rel="external nofollow" data-toggle="Modal"
                    data-target="#outloginModal">退出</button>
        </form>
    </div>

</nav>
<header>
    <div class="jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-12 text-center" role="button" style="cursor: pointer;" onclick="open_collegeClass()">
                    <img class="rounded" alt="120x120" style="width: 120px; height: 120px;" src="..\images\class.png"
                         data-holder-rendered="true">
                    <p></p>
                    <h4>班级考勤详情</h4>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 text-center" style="cursor: pointer;" onclick="open_xueye()">
                    <img class="rounded" alt="120x120" style="width: 120px; height: 120px;"
                         src="..\images\learnwarning.png"
                         data-holder-rendered="true">
                    <p></p>
                    <h4>查看具有学业风险学生</h4>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 text-center" style="cursor: pointer;" onclick="open_library()">
                    <img class="rounded" alt="120x120" style="width: 120px; height: 120px;" src="..\images\times.png"
                         data-holder-rendered="true">
                    <p></p>
                    <h4>查看图书馆入馆反馈</h4>
                </div>
            </div>
            <div class="row">
                <div class="jumbotron"></div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-6 col-sm-12 text-center" style="cursor: pointer;">
                    <img class="rounded" alt="120x120" style="width: 120px; height: 120px;" src="..\images\more.png"
                         data-holder-rendered="true">
                    <p></p>
                    <h4>更多功能正在探索中......</h4>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 text-center" style="cursor: pointer;" onclick="open_xueye()">

                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 text-center" style="cursor: pointer;">

                </div>

            </div>
            <div class="row"></div>
        </div>
    </div>
</header>

<div class="container">
    <div class="row">
        <div class="col-12 col-md-8 mx-auto"></div>
    </div>
</div>
<!-- 退出登录模态框 -->
<div class="modal" id="outloginModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">退出登录确认</h4>
                <button type="button" id="outlogin_close_all" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- 模态框主体 -->
            <div class="modal-body">
                <div>
                    <a>您确认要退出登录吗？</a><br><br>
                </div>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" id="outlogin_success" class="btn btn-success" data-dismiss="modal">确认</button>&nbsp;&nbsp;&nbsp;
                    <button type="button" id="outlogin_fail" class="btn btn-danger" data-dismiss="modal">取消</button>
                </div>
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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.2.1.js"></script>
</body>
</html>
