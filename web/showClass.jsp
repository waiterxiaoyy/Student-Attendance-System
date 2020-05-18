<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/4/22
  Time: 18:36
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
        function open_classDetail(get_open_classid) {
            var url = "/classShow?method=findClassByClassid&classid=" + get_open_classid;
            window.location=url;
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
<%--            <li class="nav-item dropdown">--%>
<%--                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"--%>
<%--                   aria-haspopup="true" aria-expanded="false"> 教师查看系统 </a>--%>
<%--                <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">任课教师</a>--%>
<%--                    <div class="dropdown-divider"></div>--%>
<%--                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">辅导员</a>--%>
<%--                </div>--%>
<%--            </li>--%>
<%--            <li class="nav-item dropdown">--%>
<%--                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"--%>
<%--                   aria-haspopup="true" aria-expanded="false"> 考勤 </a>--%>
<%--                <div class="dropdown-menu" aria-labelledby="navbarDropdown">--%>
<%--                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">签到</a>--%>
<%--                    <div class="dropdown-divider"></div>--%>
<%--                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">签退</a>--%>
<%--                    <div class="dropdown-divider"></div>--%>
<%--                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">补签</a>--%>
<%--                </div>--%>
<%--            </li>--%>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <button type="button" class="btn btn-default btn-sm">
                <span class="glyphicon glyphicon-user" > ${curUsername}</span>
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
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 年级</a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="/course?method=findAll">所有</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/course?method=findByGrade&Grade=2016">2016级</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/course?method=findByGrade&Grade=2017">2017级</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/course?method=findByGrade&Grade=2018">2018级</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="/course?method=findByGrade&Grade=2019">2019级</a>
                            </div>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0" method="post" action="/course">
                        <input class="form-control mr-sm-2" type="search" placeholder="查询班级" aria-label="Search"
                               name="searchClassName" id="searchClassName" value="${searchClassName}">
                        <button class="btn btn-success my-2 my-sm-0" type="submit"><span class="glyphicon glyphicon-search"></span> 查询</button>
                    </form>
                </div>
            </nav>
            <br>
            <p>&nbsp;<span class="label label-info">当前展示：${curGrade}</span></p>
            <table class="table table-bordered table-hover text-center">
                <thead>
                <tr>
                    <th>教师</th>
                    <th>年级</th>
                    <th>课程号</th>
                    <th>课程名</th>
                    <th>教学班级</th>
                    <th>人数</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="courseshow">
                    <tr>
                        <td>${courseshow.teacher}</td>
                        <td>${courseshow.grade}</td>
                        <td>${courseshow.courseid}</td>
                        <td>${courseshow.coursename}</td>
                        <td>${courseshow.classname}</td>
                        <td>${courseshow.studentnumber}</td>
                        <td>
                            <button type="button" id="${courseshow.classid}" name="open_btn" class="btn btn-info"
                                    rel="external nofollow"
                                    value="/classShow?method=findClassByClassid&classid=${courseshow.classid}"
                                    onclick="open_classDetail(this.id)">展开
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="jumbotron">
            <div class="container">

            </div>
        </div>
    </div>
    <div class="row"></div>
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
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-3.3.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap-4.2.1.js"></script>
</body>

</html>
