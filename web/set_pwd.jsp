<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content="图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <script src="Js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $("#newpwd2").blur(function () {
                if ($("#newpwd").val() != $(this).val()) {
                    $("#newpwd2_err").show();
                }

            }).focus(function () {
                $("#newpwd2_err").hide();
            })

        });

    </script>

</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <!-- 头部开始 -->
    <tr>
        <td width="17" valign="top" background="./Images/mail_left_bg.gif">
            <img src="./Images/left_top_right.gif" width="17" height="29"/>
        </td>
        <td valign="top" background="./Images/content_bg.gif">

        </td>
        <td width="16" valign="top" background="./Images/mail_right_bg.gif"><img src="./Images/nav_right_bg.gif"
                                                                                 width="16" height="29"/></td>
    </tr>
    <!-- 中间部分开始 -->
    <tr>
        <!--第一行左边框-->
        <td valign="middle" background="./Images/mail_left_bg.gif">&nbsp;</td>
        <!--第一行中间内容-->
        <td valign="top" bgcolor="#F7F8F9">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <!-- 空白行-->
                <tr>
                    <td colspan="2" valign="top">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table>
                            <tr>
                                <td width="100" align="center"><img src="./Images/mime.gif"/></td>
                                <td valign="bottom"><h3 style="letter-spacing:1px;">个人管理 > 修改密码</h3></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 一条线 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 添加产品开始 -->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="user.let?type=modifyPwd" method="post">
                                        <table width="100%" class="cont">
<%--                                            <tr>--%>
<%--&lt;%&ndash;                                                <td width="2%">&nbsp;</td>&ndash;%&gt;--%>
<%--                                                <td width="80px">原密码：</td>--%>
<%--                                                <td width="150px">--%>
<%--                                                    <input class="text" type="password" name="pwd" required/>--%>
<%--                                                </td>--%>
<%--                                                <td class="err">*原密码不匹配</td>--%>
<%--&lt;%&ndash;                                                <td width="2%">&nbsp;</td>&ndash;%&gt;--%>
<%--                                            </tr>--%>
                                            <tr>
<%--                                                <td width="2%">&nbsp;</td>--%>
                                                <td width="80px"> 新密码：</td>
                                                <td width="150px">
                                                    <input class="text" type="password" name="newpwd" required/>
                                                </td>
                                                <td></td>
<%--                                                <td width="2%">&nbsp;</td>--%>
                                            </tr>
<%--                                            <tr>--%>
<%--&lt;%&ndash;                                                <td width="2%">&nbsp;</td>&ndash;%&gt;--%>
<%--                                                <td width="80px">确认密码：</td>--%>
<%--                                                <td width="150px">--%>
<%--                                                    <input class="text" type="password" id="newpwd2" name="newpwd2" required/>--%>
<%--                                                </td>--%>
<%--                                                <td id="newpwd2_err" class="err">两次密码不相同</td>--%>
<%--&lt;%&ndash;                                                <td width="2%">&nbsp;</td>&ndash;%&gt;--%>
<%--                                            </tr>--%>
                                            <tr>
<%--                                                <td width="2%">&nbsp;</td>--%>
                                                <td width="80px"></td>
                                                <td width="150px"><input class="btn" type="submit" value="提交"/></td>
                                                <td></td>
<%--                                                <td width="2%">&nbsp;</td>--%>

                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <!-- 添加产品结束 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="51%" class="left_txt">
<%--                        <img src="./Images/icon_mail.gif" width="16" height="11"> 客户服务邮箱：2087924818@qq.com<br/>--%>
                    </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
        <td background="./Images/mail_right_bg.gif">&nbsp;</td>
    </tr>
    <!-- 底部部分 -->
    <tr>
        <td valign="bottom" background="./Images/mail_left_bg.gif">
            <img src="./Images/buttom_left.gif" width="17" height="17"/>
        </td>
        <td background="./Images/buttom_bgs.gif">
            <img src="./Images/buttom_bgs.gif" width="17" height="17">
        </td>
        <td valign="bottom" background="./Images/mail_right_bg.gif">
            <img src="./Images/buttom_right.gif" width="16" height="17"/>
        </td>
    </tr>
</table>
</body>
</html>