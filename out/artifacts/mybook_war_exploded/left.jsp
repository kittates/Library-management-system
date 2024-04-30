<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/jsp; charset=utf-8"/>
    <meta http-equiv="keywords" content="图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <script src="./Js/prototype.lite.js" type="text/javascript"></script>
    <script src="./Js/moo.fx.js" type="text/javascript"></script>
    <script src="./Js/moo.fx.pack.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <link rel="stylesheet" href="./Style/buttons.css">
    <script type="text/javascript">
        window.onload = function () {
            var contents = document.getElementsByClassName('content');
            var toggles = document.getElementsByClassName('type');

            var myAccordion = new fx.Accordion(
                toggles, contents, {opacity: true, duration: 400}
            );
            myAccordion.showThisHideOpen(contents[0]);
            for (var i = 0; i < document.getElementsByTagName("a").length; i++) {
                var dl_a = document.getElementsByTagName("a")[i];
                dl_a.onfocus = function () {
                    this.blur();
                }
            }
        }
    </script>

</head>

<body>
<table width="100%" height="200" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td width="182" valign="top">

            <div id="container">
                <h1 class="type"><a href="javascript:void(0)">会员管理</a></h1>
                <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><img src="./Images/menu_top_line.gif" width="182" height="5"/></td>
                        </tr>
                    </table>
                    <ul class="RM">
                        <li><a href="./member.let?type=addpre " target="main">会员开卡</a></li>
                        <li><a href="./member.let?type=query" target="main">会员列表</a></li>
                        <li><a href="./mem_recharge.jsp" target="main">会员充值</a></li>
                    </ul>
                </div>
                <h1 class="type"><a href="javascript:void(0)">图书管理</a></h1>
                <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><img src="./Images/menu_top_line.gif" width="182" height="5"/></td>
                        </tr>
                    </table>
                    <ul class="RM">
                        <li><a href="./book_add.jsp" target="main">添加图书</a></li>
                        <li><a href="./book.let?type=query&pageIndex=1" target="main">图书列表</a></li>
                    </ul>
                </div>
                <h1 class="type"><a href="javascript:void(0)">图书类型管理</a></h1>
                <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><img src="./Images/menu_top_line.gif" width="182" height="5"/></td>
                        </tr>
                    </table>
                    <ul class="RM">
                        <li><a href="./type_add.jsp" target="main">添加类型</a></li>
                        <li><a href="./type_list.jsp" target="main">类型列表</a></li>
                    </ul>
                </div>
                <h1 class="type"><a href="javascript:void(0)">借阅操作</a></h1>
                <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><img src="./Images/menu_top_line.gif" width="182" height="5"/></td>
                        </tr>
                    </table>
                    <ul class="RM">
                        <li><a href="./book_rent.jsp" target="main">图书借阅</a></li>
                        <li><a href="./return_list.jsp" target="main">图书归还</a></li>
<%--                        <li><a href="./rent_list.jsp" target="main">查看借阅历史信息</a></li>--%>
                    </ul>
                </div>
                <!-- *********** -->
                <h1 class="type"><a href="javascript:void(0)">个人中心</a></h1>
                <div class="content">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><img src="./Images/menu_top_line.gif" width="182" height="5"/></td>
                        </tr>
                    </table>
                    <ul class="RM">
                        <li><a href="set_pwd.jsp" target="main">修改密码</a></li>

                    </ul>
                </div>
                <h1 class="type"><a href="./main.jsp" target="main">主界面</a> </h1>



                <!-- *********** -->

<%--                <div class="content">--%>
<%--                    <table width="100%" border="0" cellspacing="0" cellpadding="0">--%>
<%--                        <tr>--%>
<%--                            <td><img src="./Images/menu_top_line.gif" width="182" height="5"/></td>--%>
<%--                        </tr>--%>
<%--                    </table>--%>
<%--                    <ul class="RM">--%>
<%--                        <li><a href="javascript:void(0)" target="main">友情连接</a></li>--%>
<%--                        <li><a href="javascript:void(0)" target="main">在线留言</a></li>--%>
<%--                        <li><a href="javascript:void(0)" target="main">网站投票</a></li>--%>
<%--                        <li><a href="javascript:void(0)" target="main">邮箱设置</a></li>--%>
<%--                        <li><a href="javascript:void(0)" target="main">图片上传</a></li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
                <!-- *********** -->
            </div>

        </td>
    </tr>


</table>

</body>
</html>
