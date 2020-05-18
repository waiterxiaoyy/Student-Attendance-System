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
    <title>广东财经大学考勤系统-考勤课程详情展示</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap-4.2.1.css" rel="stylesheet">
    <link href="css/glyphicon.css" rel="stylesheet">
    <script type="text/javascript">
        var url;
        window.onload=function () {
            var qiandao_back = document.getElementById("qiandaoModal");
            var qiandao_close_all = document.getElementById("qiandao_close_all");
            var qiandao_fail = document.getElementById("qiandao_fail");
            var qiandao_success = document.getElementById("qiandao_success");

            var outlogin_btn = document.getElementById("outlogin_btn");
            var outlogin_back = document.getElementById("outloginModal");
            var outlogin_close_all = document.getElementById("outlogin_close_all");
            var outlogin_success = document.getElementById("outlogin_success");
            var outlogin_fail = document.getElementById("outlogin_fail");

            qiandao_close_all.onclick=function () {
                qiandaoModal.style.display="none";
            };
            qiandao_fail.onclick=function() {
                qiandaoModal.style.display="none";
            };
            qiandao_success.onclick=function() {
                qiandaoModal.style.display="none";
                location.href=url;
            };
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
        function qiandao_ask(get_studentid, get_weekcountid) {
            url = "/studentWeekCount?method=updateSigninSituation&WeekCountID=" + get_weekcountid + "&StudentID=" + get_studentid;
            var qiandaoModal = document.getElementById("qiandaoModal");
            qiandaoModal.style.display="block";
        };
        function qiantui_ask(get_studentid, get_weekcountid) {
            url = "/studentWeekCount?method=updateSignoutSituation&WeekCountID=" + get_weekcountid + "&StudentID=" + get_studentid;
            var qiandaoModal = document.getElementById("qiandaoModal");
            qiandaoModal.style.display="block";
        };
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

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false"> 考勤 </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">签到</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">签退</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">补签</a>
                </div>
            </li>
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
            <nav class="navbar navbar-expand-lg navbar-light bg-light">

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="navbar-nav mr-auto"></div>
                    <form class="form-inline my-2 my-lg-0" method="post" action="/studentWeekCount?method=findWeekCount">
                        <input class="form-control mr-sm-2" type="search" placeholder="查询周/节次" aria-label="Search"
                               name="searchWeekCount" id="searchWeekCount">
                        <input type="hidden" name="findClassID" value="${findClassID}">
                        <button class="btn btn-success my-2 my-sm-0" type="submit"><span class="glyphicon glyphicon-search"></span> 查询</button>
                    </form>
                </div>
            </nav>
            <p>&nbsp;</p>
            <table class="table table-bordered table-hover text-center">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>教学班级</th>
                    <th>周/节次</th>
                    <th>签到情况</th>
                    <th>签退情况</th>
                    <th>考勤状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="weekcountshow">
                    <tr>
                        <td>${weekcountshow.studentid}</td>
                        <td>${weekcountshow.studentname}</td>
                        <td>${weekcountshow.classname}</td>
                        <td>${weekcountshow.weekcount}</td>
                        <c:choose>
                            <c:when test="${weekcountshow.signinsituation eq '已签到'}">
                                <td><button type="button" id="${weekcountshow.studentid}" name="singin_btn" class="btn btn-success disabled"
                                        rel="external nofollow" value="${weekcountshow.weekcountid}&&ClassID=${weekcountshow.classid}">${weekcountshow.signinsituation}
                                </button></td>
                            </c:when>
                            <c:when test="${weekcountshow.signinsituation eq '未签到' && weekcountshow.situation ne 'ing'}">
                                <td><button type="button" id="${weekcountshow.studentid}" name="singin_btn" class="btn btn-danger disabled"
                                            rel="external nofollow" value="${weekcountshow.weekcountid}&&ClassID=${weekcountshow.classid}">${weekcountshow.signinsituation}
                                </button></td>
                            </c:when>
                            <c:otherwise>
                              <td><button type="button" id="${weekcountshow.studentid}" name="singout_btn" class="btn btn-warning"
                                        rel="external nofollow" value="${weekcountshow.weekcountid}&&ClassID=${weekcountshow.classid}" onclick="qiandao_ask(this.id, this.value)">${weekcountshow.signinsituation}
                                </button></td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${weekcountshow.signoutsituation eq '已签退'}">
                                <td><button type="button" id="${weekcountshow.studentid}" name="singin_btn" class="btn btn-success disabled"
                                            rel="external nofollow" value="${weekcountshow.weekcountid}&&ClassID=${weekcountshow.classid}">${weekcountshow.signoutsituation}
                                </button></td>
                            </c:when>
                            <c:when test="${weekcountshow.signoutsituation eq '未签退' && weekcountshow.situation ne 'ing'}">
                                <td><button type="button" id="${weekcountshow.studentid}" name="singin_btn" class="btn btn-danger disabled"
                                            rel="external nofollow" value="${weekcountshow.weekcountid}&&ClassID=${weekcountshow.classid}">${weekcountshow.signoutsituation}
                                </button></td>
                            </c:when>
                            <c:otherwise>
                                <td><button type="button" id="${weekcountshow.studentid}" name="singout_btn" class="btn btn-warning"
                                            rel="external nofollow" value="${weekcountshow.weekcountid}&&ClassID=${weekcountshow.classid}" onclick="qiantui_ask(this.id, this.value)">${weekcountshow.signoutsituation}
                                </button></td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <c:choose>
                                <c:when test="${weekcountshow.situation eq 'ing'}">
                                    <button type="button"  class="btn btn-primary disabled" rel="external nofollow" >
                                        <span class="glyphicon glyphicon-time"></span> 考勤中...
                                    </button>
                                </c:when>
                                <c:when test="${weekcountshow.situation eq 'nostart'}">
                                    <button type="button"  class="btn btn-warning disabled" rel="external nofollow" >
                                        <span class="glyphicon glyphicon-off"></span> 考勤未开始
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button"  class="btn btn-danger disabled" rel="external nofollow" >
                                        <span class="glyphicon glyphicon-off"></span> 考勤已结束
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="jumbotron"></div>
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
<!-- 模态框 -->
<div class="modal" id="addModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">添加考勤信息</h4>
                <button type="button" id="add_close_all" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form method="post" action="/WeekCount?method=addWeekCount">
                    <div class="input-group mb-3">
<%--                        <div class="input-group mb-3">--%>
<%--                            <div class="input-group-prepend">--%>
<%--                                <span class="input-group-text">班级</span>--%>
<%--                            </div>--%>
<%--                            <input type="text" class="form-control" placeholder="班级名称(请填写全称）">--%>

<%--                        </div>--%>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">周次</span>
                            </div>
                            <input type="text" name="week" class="form-control" placeholder="请填写:第xx周">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">节次</span>
                            </div>
                            <input type="text" name="count" class="form-control" placeholder="请填写:第xx节课">
                        </div>
                        <div class="btn-group" data-toggle="buttons">
                            <label class="btn btn-info">
                                <input type="radio" name="way" id="way"> 签到
                            </label>
                        </div>
                    </div>
                    <!-- 模态框底部 -->
                    <div class="modal-footer">
                        <input type="hidden" name="classid" value="${classid}">
                        <button type="submit" id="add_submit" class="btn btn-primary" data-dismiss="modal">提交</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 删除模态框 -->
<div class="modal" id="qiandaoModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">考勤确认</h4>
                <button type="button" id="qiandao_close_all" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- 模态框主体 -->
            <div class="modal-body">
                <div>
                    <a>点击确认完成考勤！</a>
                </div>
                <p></p>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" id="qiandao_success" class="btn btn-success" data-dismiss="modal">确认</button>&nbsp;&nbsp;&nbsp;
                    <button type="button" id="qiandao_fail" class="btn btn-primary" data-dismiss="modal">取消</button>
                </div>
            </div>
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
