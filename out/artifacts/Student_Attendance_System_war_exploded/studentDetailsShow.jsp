<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/5/8
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>广东财经大学考勤系统-教师课程展示</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap-4.2.1.css" rel="stylesheet">
    <link href="css/glyphicon.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap-4.2.1.js"></script>
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
        function exportWeekCountDetails(get_studentid) {
            var url = "teacherExport?method=exportStudentWeekCountDetails&StudentID=" + get_studentid;
            window.location = url;
        }
    </script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navba r-brand">广财大</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span
            class="navbar-toggler-icon"></span></button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active"><a class="nav-link" href="..\index.jsp">主页 <span
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

<section>
    <div class="jumbotron">
        <div class="container">
            <div class="row">
                <div class="col-6">
                    <div class="row">
                        <div class="col-4">
                            <img class="rounded-circle" alt="120x120" style="width: 120px; height: 120px;"
                                 src="..\images\headmodle.png"
                                 data-holder-rendered="true">
                        </div>
                        <div class="col-8">
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info"><span
                                        class="glyphicon glyphicon-user"></span> 个人信息
                                </li>
                                <li class="list-group-item">学号：${studentInfo.studentid}</li>
                                <li class="list-group-item">姓名：${studentInfo.studentname}</li>
                                <li class="list-group-item">学院：${studentInfo.college}</li>
                                <li class="list-group-item">年级：${studentInfo.grade}</li>
                                <li class="list-group-item">班级：${studentInfo.collegeclassname}</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="row">
                        <div class="col-6">
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info"><span
                                        class="glyphicon glyphicon-tasks"></span> 本学期课程数
                                </li>
                                <li class="list-group-item">数目：${studentInfo.coursenumber}</li>
                            </ul>
                            <hr>
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info"><span
                                        class="glyphicon glyphicon-list-alt"></span> 考勤情况 <span
                                        class="badge badge-pill badge-primary" data-toggle="collapse"
                                        data-target="#attendeced" style="cursor: pointer;">查看</span></li>
                                <li class="list-group-item">班级考勤总数：${studentInfo.attendencecount}</li>
                                <li class="list-group-item">班级出勤总数：${studentInfo.signincount}</li>
                            </ul>
                        </div>
                        <div class="col-6">
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info"><span
                                        class="glyphicon glyphicon-warning-sign"></span> 学业情况
                                </li>
                                <li class="list-group-item">绩点：${studentInfo.gpa}</li>
                                <li class="list-group-item">学业预警：
                                    <c:choose>
                                        <c:when test="${studentInfo.gpa >= 2.5}">
                                            <span class="badge badge-pill badge-success">无风险</span>
                                        </c:when>
                                        <c:when test="${studentInfo.gpa < 2.5 && studentInfo.gpa >= 2.0}">
                                            <span class="badge badge-pill badge-warning">中风险</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-pill badge-danger">高风险</span>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </ul>
                            <hr>
                            <ul class="list-group">
                                <li class="list-group-item list-group-item-info"><span
                                        class="glyphicon glyphicon-book"></span> 出入图书馆总数
                                </li>
                                <li class="list-group-item">数目：${studentInfo.countLibrary}</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-10">
                    <div class="row">
                        <div class="col-4">
                            <button type="button" class="btn btn-outline-info" data-toggle="collapse"
                                    data-target="#demo">详情
                            </button>
                            <br>
                            <div id="demo" class="collapse show">
                                <br>
                                <button class="btn btn-success">出勤比例</button>
                                <button class="btn btn-danger">缺勤比例</button>
                                <button class="btn btn-warning">绩点</button>
                            </div>
                            <br>
                            <button type="button" class="btn btn-info" rel="external nofollow"
                                    onclick="exportWeekCountDetails(${studentInfo.studentid})">
                                <span class="glyphicon glyphicon-save"></span> 导出考勤详情
                            </button>
                        </div>
                        <div class="col-8">
                            <div class="container">
                                <div class="progress" style="background-color: #ffffff; height: 20px;">
                                    <div class="progress-bar bg-success"
                                         style="width:${studentInfo.signinRatio}%"></div>
                                </div>
                                <br>
                                <div class="progress" style="background-color: #ffffff; height: 20px;">
                                    <div class="progress-bar bg-danger"
                                         style="width:${studentInfo.signoutRatio}%"></div>
                                </div>
                                <br>
                                <div class="progress" style="background-color: #ffffff; height: 20px;">
                                    <div class="progress-bar bg-warning" style="width:${studentInfo.gpaRatio}%"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div id="attendeced" class="collapse show">
                <div class="row">

                    <table class="table table-bordered table-hover text-center">
                        <thead>
                        <tr>
                            <td>姓名</td>
                            <td>教学班级</td>
                            <td>周、节次</td>
                            <td>考勤情况</td>
                        </tr>
                        </thead>
                        <c:forEach items="${list}" var="studentWeekCount">
                            <tr>
                                <td>${studentWeekCount.studentname}</td>
                                <td>${studentWeekCount.classname}</td>
                                <td>${studentWeekCount.weekcount}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${studentWeekCount.signinsituation eq '已签到'}">
                                            <button type="button" class="btn btn-success disabled"
                                                    rel="external nofollow">
                                                    ${studentWeekCount.signinsituation}
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-danger disabled"
                                                    rel="external nofollow">
                                                    ${studentWeekCount.signinsituation}
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="jumbotron"></div>
        </div>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col-12 col-md-8 mx-auto">

        </div>
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

</body>

</html>

