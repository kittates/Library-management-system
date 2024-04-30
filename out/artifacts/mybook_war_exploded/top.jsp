<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content="图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <link rel="stylesheet" href="./Style/buttons.css">
    <script type="text/javascript">
        function logout() {
            if (window.confirm('您确定要退出吗？')) {
                top.location = 'login.html';
            }
        }
    </script>
</head>
<body>
<%--background="./Images/top_top_bg.gif"--%>
<table cellpadding="0" width="100%" height="40" bgcolor="#737c7b">
    <tr valign="top">
        <td width="70%"><a href="javascript:void(0)"></a></td>
        <td width="30%" style="padding-top:13px;padding-left: 200px;font:15px Arial,SimSun,sans-serif;color:#FFF">
            当前用户:&nbsp;&nbsp;<b>${user.name}</b>&nbsp;
           

            <a style="color:white" onclick="return confirm('确认退出');" href="user.let?type=exit">&nbsp;&nbsp;安全退出</a>
        </td>
<%--        <td width="30%" style="padding-top:13px;font:15px Arial,SimSun,sans-serif;color:#FFF">--%>
<%--&lt;%&ndash;            El表达式:直接从session中取得user的信息，调用反射机制，实际上调用的是getName方法&ndash;%&gt;--%>
<%--            当前用户:<b>${user.name}</b>&nbsp;--%>
<%--&lt;%&ndash;            return confirm('')增加了一条误操作机制，二次确认退出&ndash;%&gt;--%>
<%--            <a style="color:white" onclick="return confirm('确认退出');" href="user.let?type=exit">安全退出</a>--%>
<%--        </td>--%>
    </tr>
</table>
</body>
</html>