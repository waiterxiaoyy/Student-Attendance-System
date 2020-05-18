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
  </head>
  <body>
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand">广财大</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span
            class="navbar-toggler-icon"></span> </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active"> <a class="nav-link" href="../index.jsp">主页 <span
                class="sr-only">(current)</span></a> </li>
        <li class="nav-item"> </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
             aria-haspopup="true" aria-expanded="false"> 用户 </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">任课教师</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">辅导员</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">学生</a>
          </div>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
             aria-haspopup="true" aria-expanded="false"> 考勤 </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">签到</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="..\login\loginStatusCheck.jsp">签退</a>
          </div>
        </li>
      </ul>
      <form class="form-inline my-2 my-lg-0">
      </form>
    </div>
    <img class="rounded" alt="18x2" style="width: 170px; height:40px;" src="..\images\logo.png"
         data-holder-rendered="true">
  </nav>
  <header>
    <div class="jumbotron">
      <div class="container">
        <div class="row">
          <div class="col-12">
            <h1 class="text-center">欢迎进入学生考勤系统</h1>
            <p>&nbsp;</p>
            <p class="text-center"><a class="btn btn-primary btn-lg" href="..\login\loginStatusCheck.jsp" role="button">点击进入</a>
            </p>
          </div>
        </div>
      </div>
    </div>
  </header>
  <section>
    <div class="container">
      <div class="row">
        <div class="col-lg-12 mb-4 mt-2 text-center">
          <h3>广财大学生考勤系统特色功能</h3>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
          <img class="rounded" alt="140x140" style="width: 140px; height: 140px;" src="..\images\attendenced.png"
               data-holder-rendered="true">
          <h4>发布考勤信息</h4>
          <p>各课程科任老师可以发布教学班级各周各节次的考勤信息，提供给该教学班级的学生进行考勤</p>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
          <img class="rounded" alt="140x140" style="width: 140px; height: 140px;" src="..\images\signin.png"
               data-holder-rendered="true">
          <h4>学生考勤</h4>
          <p>每个学生可以进入自己所选课程的教学班级进行签到签退，数据与教师端同步更新</p>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
          <img class="rounded" alt="140x140" style="width: 140px; height: 140px;" src="..\images\export.png"
               data-holder-rendered="true">
          <h4>导出数据到本地</h4>
          <p>科任老师可以将班级考勤具体数据导出并保存在本地，便于统计该班级学生出勤情况；辅导员可导出学生数据，便于更好掌握学生学习情况</p>
        </div>
      </div>
      <p></p>
      <div class="col-lg-12 mb-4 mt-2 text-center">
        <h3>广财大学生学业监督机制</h3>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
          <img class="rounded" alt="140x140" style="width: 140px; height: 140px;" src="..\images\learnwarning.png"
               data-holder-rendered="true">
          <h4>学业预警机制</h4>
          <p>学业预警是通过对学生学习和学业情况进行分析，对出现学习问题、完成学业困难的学生进行警示，帮助学生顺利完成学业的一种干预制度。</p>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
          <img class="rounded" alt="140x140" style="width: 140px; height: 140px;" src="..\images\monthdata.png"
               data-holder-rendered="true">
          <h4>月度跟踪机制</h4>
          <p>系统每月生成一次各学院各年级考勤月报，辅导员可根据月报掌握学生的出勤情况，及时跟踪学生的学业状况。</p>
        </div>
        <div class="col-lg-4 col-md-6 col-sm-12 text-center">
          <img class="rounded" alt="140x140" style="width: 140px; height: 140px;" src="..\images\times.png"
               data-holder-rendered="true">
          <h4>入馆次数反馈机制</h4>
          <p>辅导员可查看学生进出图书馆以及借还图书的次数，反馈学生的自学情况，让辅导员掌握学生的基本学习情况。</p>
        </div>
      </div>
      <div class="row"> </div>
    </div>
    <div class="jumbotron"> </div>
    <div class="row"> </div>
    </div>
  </section>
  <div class="container">
    <div class="row">
      <div class="col-12 col-md-8 mx-auto"> </div>
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
