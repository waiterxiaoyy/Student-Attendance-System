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
    <title>广东财经大学考勤系统-教师课程详情展示</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap-4.2.1.css" rel="stylesheet">
    <link href="css/glyphicon.css" rel="stylesheet">

    <script type="text/javascript">
        var url;
        window.onload = function () {

            var add_btn = document.getElementById("add_btn");
            var add_back = document.getElementById("addModal");
            var add_close_all = document.getElementById("add_close_all");
            var add_submit = document.getElementById("add_submit");
            var remove_back = document.getElementById("removeModal");
            var remove_close_all = document.getElementById("remove_close_all");
            var remove_fail = document.getElementById("remove_fail");
            var remove_success = document.getElementById("remove_success");
            var outlogin_btn = document.getElementById("outlogin_btn");
            var outlogin_back = document.getElementById("outloginModal");
            var outlogin_close_all = document.getElementById("outlogin_close_all");
            var outlogin_success = document.getElementById("outlogin_success");
            var outlogin_fail = document.getElementById("outlogin_fail");

            add_btn.onclick = function () {
                addModal.style.display = "block";
            };
            add_close_all.onclick = function () {
                addModal.style.display = "none";
            };
            add_submit.onclick = function () {
                addModal.style.display = "none";
            };
            remove_close_all.onclick = function () {
                removeModal.style.display = "none";
            };
            remove_fail.onclick = function () {
                removeModal.style.display = "none";
            };
            remove_success.onclick = function () {
                removeModal.style.display = "none";
                location.href = url;
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

        function remove_ask(get_remove_id) {
            url = "/WeekCount?method=deleteByWeekCountID&WeekCountID=" + get_remove_id;
            var removeModal = document.getElementById("removeModal");
            removeModal.style.display = "block";
        };

        function open_weekcountDetail(get_open_weekcountid) {
            var url = "/WeekCount?method=findByWeekCountID&WeekCountID=" + get_open_weekcountid +"&page=1";
            window.location = url;
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
                <button type="button" id="add_btn" class="btn btn-primary" rel="external nofollow" data-toggle="Modal"
                        data-target="#myModal">
                    <span class="glyphicon glyphicon-plus"></span> 添加考勤课程
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <div class="navbar-nav mr-auto"></div>
                    <form class="form-inline my-2 my-lg-0" method="post" action="/classShow?method=findByWeekCount">
                        <input class="form-control mr-sm-2" type="search" placeholder="查询周/节次" aria-label="Search"
                               name="searchWeekCount" id="searchWeekCount">
                        <input type="hidden" name="findclassid" value="${classid}">
                        <button class="btn btn-success my-2 my-sm-0" type="submit"><span
                                class="glyphicon glyphicon-search"></span> 查询
                        </button>
                    </form>

                </div>
            </nav>
            <p>&nbsp;</p>
            <table class="table table-bordered table-hover text-center">
                <thead>
                <tr>
                    <th>年级</th>
                    <th>课程名</th>
                    <th>教学班级</th>
                    <th>周/节次</th>
                    <th>班级总人数</th>
                    <th>已签到人数</th>
                    <th>详情</th>
                    <th>删除</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="classshow">
                    <tr>
                        <td>${classshow.grade}</td>
                        <td>${classshow.coursename}</td>
                        <td>${classshow.classname}</td>
                        <td>${classshow.weekcount}</td>
                        <td>${classshow.studentnumber}</td>
                        <td>${classshow.attdencednumber}</td>
                        <td>
                            <button type="button" id="${classshow.weekcountid}" name="open_btn" class="btn btn-info"
                                    rel="external nofollow"
                                    value="/WeekCountShow?WeekCountID=${classshow.weekcountid}"
                                    onclick="open_weekcountDetail(this.id)">展开
                            </button>
                        </td>
                        <td>
                            <button type="button" id="${classshow.weekcountid}" name="remove_btn"
                                    class="btn btn-outline-danger"
                                    rel="external nofollow"
                                    value="method=deleteByWeekCountID&WeekCountID=${classshow.weekcountid}"
                                    data-toggle="Modal" data-target="removeModal" onclick="remove_ask(this.id)">
                                <span class="glyphicon glyphicon-remove"></span> 删除
                            </button>
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
                            <select class="form-control" name="week">
                                <option>第01周</option>
                                <option>第02周</option>
                                <option>第03周</option>
                                <option>第04周</option>
                                <option>第05周</option>
                                <option>第06周</option>
                                <option>第07周</option>
                                <option>第08周</option>
                                <option>第09周</option>
                                <option>第10周</option>
                                <option>第11周</option>
                                <option>第12周</option>
                                <option>第13周</option>
                                <option>第14周</option>
                                <option>第15周</option>
                                <option>第16周</option>
                                <option>第17周</option>
                                <option>第18周</option>
                            </select>
<%--                            <input type="text" name="week" class="form-control" placeholder="请填写:第xx周">--%>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">节次</span>
                            </div>
<%--                            <input type="text" name="count" class="form-control" placeholder="请填写:第xx节课">--%>
                            <select class="form-control" name="count">
                                <option>第01节课</option>
                                <option>第02节课</option>
                                <option>第03节课</option>
                                <option>第04节课</option>
                                <option>第05节课</option>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">开始时间</span>
                            </div>
                            <input type="text" id="starttime" name="starttime" class="form-control"
                                   placeholder="请选择:2020-10-01 10:00:00">
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">结束时间</span>
                            </div>
                            <input type="text" id="endtime" name="endtime" class="form-control"
                                   placeholder="请选择:2020-10-01 10:00:00">
                        </div>
<%--                        <div class="btn-group" data-toggle="buttons">--%>
<%--                            <label class="btn btn-info">--%>
<%--                                <input type="radio" name="way" id="way"> 签到--%>
<%--                            </label>--%>
<%--                        </div>--%>
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
<div class="modal" id="removeModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title">删除确认</h4>
                <button type="button" id="remove_close_all" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- 模态框主体 -->
            <div class="modal-body">
                <div>
                    <a>您确认要删除这条记录吗？</a><br><br>
                    <a>请注意：删除此条记录后具体考勤信息将一并删除！</a>
                </div>
                <p></p>
                <!-- 模态框底部 -->
                <div class="modal-footer">
                    <button type="button" id="remove_success" class="btn btn-success" data-dismiss="modal">确认</button>&nbsp;&nbsp;&nbsp;
                    <button type="button" id="remove_fail" class="btn btn-primary" data-dismiss="modal">取消</button>
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
<script src="js/laydate/laydate.js"></script>
<script>
    //执行一个laydate实例
    laydate.render({
        elem: '#starttime' //指定元素
        , type: 'datetime'
    });
    laydate.render({
        elem: '#endtime' //指定元素
        , type: 'datetime'
    });
</script>
</body>
</html>
