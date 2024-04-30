<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content="图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <script src="Js/echarts.js"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('char1'));
        console.log(myChart)

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data: ['销量']
            },
            xAxis: {
                data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
            },
            yAxis: {},
            series: [
                {
                    name: '销量',
                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);


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
        <td width="16" valign="top" background="./Images/mail_right_bg.gif">
            <img src="./Images/nav_right_bg.gif" width="16" height="29"/></td>
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
                <!--**********这里是内容**********-->
                <!--**********这里是内容**********-->
                <!--**********这里是内容**********-->
                <!--**********这里是内容**********-->
                <tr>
                    <!--左边内容-->
<%--                    <td colspan="2" valign="top">--%>
<%--                        <marquee><h3 style="margin:20px 0 10px 10px;"></h3></marquee>--%>
<%--                    </td>--%>
                </tr>

                <!--第二行-->
                <tr>
                    <td>&nbsp;</td>
                    <td valign="top">
                        <table width="100%" height="230" border="0" cellpadding="0" cellspacing="0"
                               style="border: 1px solid #CCCCCC;">
                            <tr>
                                <td width="7%" background="./Images/news_title_bg.gif">
                                    <img src="./Images/news_title_bg.gif" width="2" height="27">
                                </td>
                                <td width="93%" height="27" background="./Images/news_title_bg.gif"
                                    class="left_bt"></td>
                            </tr>
                            <tr>
                                <td>
                                    <img src="Images/interface/test1.png" alt="error" height="500px" width="1360px" >
                                </td>


<%--                                <td height="186" valign="top"--%>
<%--                                    style="font-size: 20px; font-family:'隶书'; font-weight: bolder;">--%>
<%--                                </td>--%>

<%--                                <td height="102" valign="top">--%>
<%--                                    <img src="Images/echars.png" width="700px" height="500px"/>--%>

<%--                                </td>--%>
<%--                                <td>&nbsp;&nbsp;</td>--%>
<%--                                <td height="102" valign="top">--%>
<%--                                    <img src="Images/echars2.png" width="700px" height="500px"/>--%>
<%--                                </td>--%>
                            </tr>
                            <tr>
                                <td height="5" colspan="2">&nbsp;</td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr>
                                <td>

                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="51%" class="left_txt">
<%--                        <img src="." width="16" height="11"> 客户服务邮箱：xxx@qq.com<br/>--%>

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