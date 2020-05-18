<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/4/22
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录状态检查</title>
</head>
<body>
    <%
        String user = (String) session.getAttribute("curUsername");
        Integer userIdentity = (Integer) session.getAttribute("curIdentity");
        if(user==null)
        {
    %>
    <script language="JavaScript">
        alert('尊敬的用户，由于您未登录，所以无法使用该平台，请先登录，谢谢合作！');
        window.location="login.jsp";
    </script>
    <%
        } else if(userIdentity == 0) {
            response.sendRedirect("../showClass.jsp");
        } else if(userIdentity == 1) {
            response.sendRedirect("../studentClass?method=findAll");
        } else if(userIdentity == 2) {
            response.sendRedirect("../collegeIndex.jsp");
        }
    %>
</body>
</html>
