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
    <title>广东财经大学考勤系统-班级学生展示</title>
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
        function exportCollegeClassDetails(get_collegeclassid) {
            location.href="/teacherExport?method=exportCollegeClass&collegeclassid="+get_collegeclassid;
        };
        function openStudentDetails(get_studentid) {
            location.href="/studentDetails?method=findStudentDetailsByStudentID&StudentID="+get_studentid;
        };
        function next(get_collegeclassid, get_currentpage, get_pages) {
            if (get_currentpage == get_pages)
                return;
            get_currentpage++;
            location.href = "/studentDetails?method=findCollegeClassStudentLibraryByCollegeClassid&CollegeClassID=" + get_collegeclassid + "&page=" + get_currentpage;
        };
        function previous(get_collegeclassid, get_currentpage) {
            if (get_currentpage == 1)
                return;
            get_currentpage--;
            location.href = "/studentDetails?method=findCollegeClassStudentLibraryByCollegeClassid&CollegeClassID=" + get_collegeclassid + "&page=" + get_currentpage;
        };
        function first(get_collegeclassid) {
            location.href = "/studentDetails?method=findCollegeClassStudentLibraryByCollegeClassid&CollegeClassID=" + get_collegeclassid + "&page=1";
        };
        function last(get_collegeclassid, get_pages) {
            location.href = "/studentDetails?method=findCollegeClassStudentLibraryByCollegeClassid&CollegeClassID=" + get_collegeclassid + "&page=" + get_pages;
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
                <form class="form-inline my-2 my-lg-0" method="post" action="/studentDetails?method=findStudentLibraryInCollegeGrade">
                    <label class="form-control">班级:</label>
                    <input class="form-control mr-sm-4" type="search" placeholder="通过班级检索" aria-label="Search"
                           name="searchClassName" id="searchClassName" value="">
                    <label class="form-control">学号:</label>
                    <input class="form-control mr-sm-4" type="search" placeholder="通过学号检索" aria-label="Search"
                           name="searchStudentid" id="searchStudentid" value="">
                    <label class="form-control">姓名:</label>
                    <input class="form-control mr-sm-4" type="search" placeholder="通过姓名检索" aria-label="Search"
                           name="searchStudentName" id="searchStudentName" value="">
                    <button class="btn btn-success my-2 my-sm-0" type="submit"><span class="glyphicon glyphicon-search"></span> 查询</button>
                </form>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

            </nav>
            <br>
            <p>&nbsp;<span class="label label-info">当前展示：${curShow}</span></p>
            <table class="table table-bordered table-hover text-center">
                <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>班级</th>
                    <th>入馆数目</th>

                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${list}" var="collegeClassSLibrarytudent">
                        <tr>
                            <td>${collegeClassSLibrarytudent.studentid}</td>
                            <td><button id="${collegeClassSLibrarytudent.studentid}" type="button" class="btn btn-info" onclick="openStudentDetails(this.id)">${collegeClassSLibrarytudent.studentname}</button></td>
                            <td>${collegeClassSLibrarytudent.collegeclassname}</td>
                            <td>${collegeClassSLibrarytudent.countlibrary}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <hr class="hr"/>
            <input value="${collegeclassid}" type="hidden" id="collegeclassid">
            <ul class="pagination">
                <li class="page-item" onclick="first(${collegeclassid})"><a class="page-link"id="first" style="color: #007bff"><<</a></li>
                <li class="page-item" onclick="previous(${collegeclassid}, ${currentPage})"><a class="page-link" id="previous" style="color: #007bff">上一页</a></li>
                <li class="page-item" id="currentPage1" ><a class="page-link" style="color: #007bff"><font id="currenPage" >${currentPage}</font>/<font id="pages">${pages}</font></a></li>
                <li class="page-item" onclick="next(${collegeclassid}, ${currentPage}, ${pages})"><a class="page-link" style="color: #007bff">下一页</a></li>
                <li class="page-item" onclick="last(${collegeclassid}, ${pages})"><a class="page-link" id="last" style="color: #007bff">>></a></li>
            </ul>
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

</body>

</html>
