<%@ page contentType="text/html;charset=UTF-8" language="java"  import="java.util.*"%>
<%@ page import="com.enjoy.book.bean.Record" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content="图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <script src="Js/jquery-3.3.1.min.js"></script>
    <script>
        $(function(){
            $("#btnQuery").click(function(){
                //拿到idn
                var idn = $("#idNumber").val();
                //发送请求
                location.href="record.let?type=queryback&idn="+idn;
            });

            //批量归还按钮事件
            $("#btnReturn").click(function (){

                //用户编号为空
                if(!$("#memberId").val()){
                    alert("请输入会员编号！！！");
                    return;
                }
                //1、获取所有选中的编号-->集合
                var idList = new Array();
                $(".ck").each(function(){
                   if($(this).prop("checked")){
                       idList.push($(this).val());
                   }
                });
                if(idList.length==0){
                    alert("请选择要归还的书籍！！！");
                }

                //2、将idList连接成一个字符串
                var idStr = idList.join("_");

                //3、发送请求
                var path = "record.let?type=back&mid="+$("#memberId").val()+"&ids="+idStr;
                location.href=path


            });

            //全选、全不选
            $("#ckAll").click(function(){
                $(".ck").prop("checked",$(this).prop("checked"));
            });
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
        <td width="16" valign="top" background="./Images/mail_right_bg.gif">
            <img src="./Images/nav_right_bg.gif" width="16" height="29"/>
        </td>
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
                                <td valign="bottom"><h3 style="letter-spacing:1px;">常用功能 > 图书归还 </h3></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 一条线 -->
                <tr>
                    <td height="20" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 会员信息开始 -->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <fieldset>
                            <legend>查询会员</legend>
                            <c:if test="${member!=null}">
                                <table width="100%" border="1" class="cont">
                                    <tr>
                                        <td width="8%" class="run-right"> 会员编号</td>
                                        <td colspan="7">
                                            <input class="text" type="text" id="idNumber" value="${member.idNumber}" />
                                            <input type="button" id="btnQuery" value="确定"  style="width: 80px;"/>
                                            <input type="button" id="btnReturn" value="批量归还" style="width: 80px;"/>
                                        </td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="8%" class="run-right">会员名称</td>
                                        <td width="17%"><input class="text" type="text"  name="memberId" value="${member.name}" disabled/>
                                            <!--将返回来的信息给value赋值-->
                                            <input type="hidden" id="memberId" value="${member.id}"/>
                                        </td>
                                        <td width="8%" class="run-right">会员类型:</td>
                                        <td width="17%"><input class="text" type="text"  name="memberId" value="${member.type.name}" disabled/></td>
                                        <td width="8%" class="run-right">已借数量</td>
                                        <%
                                            List<Record> records = (List<Record>) request.getAttribute("records");
                                        %>
                                        <td width="17%"><input class="text" type="text"  name="memberId" value="<%=records.size()%>" disabled/></td>
                                        <td width="8%" class="run-right">账户余额</td>
                                        <td width="17%"><input class="text" type="text"  name="memberId" value="${member.balance}" disabled/></td>
                                    </tr>
                                </table>
                            </c:if>
                            <c:if test="${member==null}">
                                <table width="100%" border="1" class="cont">
                                    <tr>
                                        <td width="8%" class="run-right"> 会员编号</td>
                                        <td colspan="7">
                                            <input class="text" type="text" id="idNumber" />
                                            <input type="button" id="btnQuery" value="确定" style="width: 80px;"/>
                                            <input type="button" id="btnReturn" value="批量归还" style="width: 80px;"/>
                                        </td>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="8%" class="run-right">会员名称</td>
                                        <td width="17%"><input class="text" type="text"  disabled/>
                                            <input type="hidden" id="memberId" value=""/>
                                        </td>
                                        <td width="8%" class="run-right">会员类型:</td>
                                        <td width="17%"><input class="text" type="text"  disabled/></td>
                                        <td width="8%" class="run-right">可借数量</td>
                                        <td width="17%"><input class="text" type="text"  disabled/></td>
                                        <td width="8%" class="run-right">账户余额</td>
                                        <td width="17%"><input class="text" type="text"  disabled/></td>
                                    </tr>
                                </table>
                            </c:if>
                        </fieldset>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>

                <!--空行-->
                <tr>
                    <td height="20" colspan="3">
                    </td>
                </tr>

                <!--详细信息-->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="" method="">
                                        <table width="100%" class="cont tr_color">
                                            <tr>
                                                <th>
                                                    <input id="ckAll" type="checkbox" value="" />全选/全不选
                                                </th>
                                                <th>书籍名</th>
                                                <th>借阅时间</th>
                                                <th>应还时间</th>
                                                <th>出版社</th>
                                                <th>书架</th>
                                                <th>押金</th>
                                                <th>操作</th>
                                            </tr>
                                            <!--展示书籍借阅记录-->
                                            <c:if test="${member==null}">
                                                <tr align="center" class="d">
                                                    <td colspan="8" align="center">
                                                        <h2>暂无借阅信息</h2>
                                                    </td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${member!=null}">
                                                <c:forEach items="${records}" var="r">
                                                    <!--逾期归还的处理-->
                                                    <%
                                                        Record record = (Record) pageContext.getAttribute("r");
                                                        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                                                        if(record.getBackDate().before(date)){
                                                         %>
                                                        <tr align="center" class="d" style="background: lightcoral">
                                                            <td>
                                                                <input class="ck" type="checkbox" value="${r.id}"  checked/>
                                                            </td>
                                                            <td>${r.book.name}</td>
                                                            <td>${r.rentDate}</td>
                                                            <td>${r.backDate}</td>
                                                            <td>${r.book.publish}</td>
                                                            <td>${r.book.address}</td>
                                                            <td>${r.deposit}</td>
                                                            <td>
                                                                <!--归还功能由上述批量归还操作-->
                                                                逾期请先归还
                                                            </td>
                                                        </tr>
                                                    <%
                                                        }else{
                                                    %>
                                                        <tr align="center" class="d">
                                                            <td>
                                                                <input class="ck" type="checkbox" value="${r.id}" checked/>
                                                            </td>
                                                            <td>${r.book.name}</td>
                                                            <td>${r.rentDate}</td>
                                                            <td>${r.backDate}</td>
                                                            <td>${r.book.publish}</td>
                                                            <td>${r.book.address}</td>
                                                            <td>${r.deposit}</td>
                                                            <td>
                                                                <!--归还功能由上述批量归还操作-->
                                                                <a href="record.let?type=keep&id=${r.id}">续借</a>
                                                            </td>
                                                        </tr>
                                                    <%
                                                    }
                                                    %>

                                                </c:forEach>
                                            </c:if>

                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <!-- 产品列表结束 -->
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
<%--                        <img src="./Images/icon_mail.gif" width="16" height="11"> 客户服务邮箱：xxx@qq.com<br/>--%>
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