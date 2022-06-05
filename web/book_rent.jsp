<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content="图书 java jsp"/>
    <meta http-equiv="author" content="phenix"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <script src="Js/jquery-3.3.1.min.js"></script>
    <script>
        //获取系统的当前时间
       function getCurrentDate(){
            var dateObj = new Date();
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth()+1;
            var date = dateObj.getDate();

            month=month<10?'0'+month:month;
            date=date<10?'0'+date:date;
            var dateStr=year+"-"+month+"-"+date;
            return dateStr;
       }
       // console.log(getCurrentDate());
       //获取归还时间
        function getBackDate(count){
            var dateObj = new Date();
            var mills = dateObj.getMilliseconds();
            mills+=count*24*60*60*1000;
            dateObj.setMilliseconds(mills);
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth()+1;
            var day = dateObj.getDay();
            month=month<10?'0'+month:month;
            day=day<10?'0'+day:day;
            var dateStr=year+"-"+month+"-"+day;
            return dateStr;
        }
        // console.log(getBackDate(30));
        $(function(){
            //按顺序执行
            //后两个按钮初始时禁用
            $("#btnQueryBook").prop("disabled", "disabled");
            $("#btnSubmit").prop("disabled", "disabled");

            var member =null;
            $("#btnQuery").click(function () {
                //1、获取用和的身份证号
                var content = $("#memberId").val();
                //防止内容为空就确定提交
                if(!content){
                    alert("请输入用户身份号!!!");
                    return;
                }
                //2、调用js-ajax()/post()/get  -->没有什么重要信息用get
                var url = "member.let?type=doajax&idn=" + content;
                $.get(url,function (data,status){
                    //1、json字符串转为json对象
                    member = JSON.parse(data);
                    // console.log(member.balance+","+member.type.name+","+member.type.amount+","+member.name);
                    //2、给组件赋值
                    $("#name").val(member.name);
                    $("#type").val(member.type.name);
                    $("#amount").val(member.type.amount);
                    $("#balance").val(member.balance);
                });

                //查询用户的功能关闭
                // $(this).prop("disabled", "disabled");  -->不能禁用，用户可能不止查询一次，应当让其在任何时候可以点击

                //开启了查询按钮的功能
                $("#btnQueryBook").removeAttr("disabled");
            });

            //用以保存已经用来展示的书籍,防止重复展示同一书籍
            var bookNameList = new Array();

            //查询书籍功能
            $("#btnQueryBook").click(function(){
                var name = $("#bookContent").val();
                var url = "book.let?type=doajax&name="+name;
                $.get(url,function (data,status){
                    // console.log(data);
                    //判断是否相等用===进行比较
                    //客户端查找的书籍不在数据库中
                    if(data==="{}"){
                        alert("当前书籍不存在，查找失败");
                        //表格清空
                        $("#bookContent").val("");
                        return;
                    }
                    //处理相同书名的问题
                    if(bookNameList.indexOf(name)>=0){
                        alert("当前书籍已经添加!!!");
                        return;
                    }
                    //向书籍列表中添加书籍名(从前端表单中获取的书籍名)
                    bookNameList.push(name);
                    //将JSON转换为book对象
                    var book = JSON.parse(data);


                    //1、创建行  部分字符进行转义
                    var tr = $("<tr align=\"center\" class=\"d\">");
                    //2、创建多个列
                    var tdCheck=$("<td><input id=\"ckAll\" type=\"checkbox\" value=\""+book.id+"\" class=\"ck\" checked/></td>");
                    var tdName=$("<td>"+book.name+"</td>");
                    //借阅日期-->系统当前的日期
                    var tdRentDate=$("<td>"+getCurrentDate()+"</td>");
                    //归还日期-->借阅日期+会员等级所限定的借阅时长-->从上述的json字符串中获取
                    var tdBackDate=$("<td>"+getBackDate(member.type.keepDay)+"</td>");
                    var tdPublish=$("<td>"+book.publish+"</td>");
                    var td1Address=$("<td>"+book.address+"</td>");
                    var tdPrice=$("<td>"+book.price+"</td>");

                    //3、行加列,以追加的方式添加
                    tr.append(tdCheck);
                    tr.append(tdName);
                    tr.append(tdRentDate);
                    tr.append(tdBackDate);
                    tr.append(tdPublish);
                    tr.append(td1Address);
                    tr.append(tdPrice);

                    //4、表加行
                    $("#tdBook").append(tr);

                    //点击查询图书确定按钮后-->输入框内容清除，完成借阅按钮解除禁用
                    $("#bookContent").val("");
                    $("#btnSubmit").removeAttr("disabled");
                });
            });

            //全选、全不选
            $("#ckAll").click(function(){
                $(".ck").prop("checked",$(this).prop("checked"));
            });

            //submit借阅功能  将用户选取的书籍bookId整合成array,传到后台servlet
            $("#btnSubmit").click(function(){
                var count=0;//记录用户所选的书籍数量
                var ids = new Array();
                //1.获取用户选择的书籍编号
                $(".ck").each(function(){
                    if($(this).prop("checked")){
                        //获取checked对应的表单中的bookId的值
                        ids.push($(this).val());
                        count++;
                    }

                });
                if(count===0){
                    alert("请选择借阅书籍！！！");
                    return;
                }
                if(count>member.type.amount){
                    alert("借阅的书籍数量超出，请重新选择书籍！！！");
                    return;
                }
                //ids.join('_');
                //请求servlet  同步请求
                location.href="record.let?type=add&mid="+member.id+"&ids="+ids.join('_');
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
                                <td valign="bottom"><h3 style="letter-spacing:1px;">常用功能 > 图书借阅 </h3></td>
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
                            <table width="100%" border="1" class="cont">
                                <tr>
                                    <td width="8%" class="run-right"> 会员编号</td>
                                    <td colspan="7">
                                        <input class="text" type="text" id="memberId" name="memberId"/>
                                        <input type="button" id="btnQuery" value="确定" style="width: 80px;"/>
                                    </td>

                                    </td>

                                </tr>
                                <tr>
                                    <td width="8%" class="run-right">会员名称</td>
                                    <td width="17%"><input class="text" type="text" id="name" disabled/></td>
                                    <td width="8%" class="run-right">会员类型:</td>
                                    <td width="17%"><input class="text" type="text" id="type" disabled/></td>
                                    <td width="8%" class="run-right">可借数量</td>
                                    <td width="17%"><input class="text" type="text" id="amount" disabled/></td>
                                    <td width="8%" class="run-right">账户余额</td>
                                    <td width="17%"><input class="text" type="text" id="balance" disabled/></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>

                <!--空行-->
                <tr>
                    <td height="40" colspan="3">
                    </td>
                </tr>

                <!--图书搜索条-->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <fieldset>
                            <legend>查询图书</legend>
                            <table width="100%" border="1" class="cont">
                                <tr>
                                    <td colspan="8">
                                        请输入:&nbsp;&nbsp;<input class="text" type="text" id="bookContent" name="bookContent" placeholder="输入书名查询"/>
                                        <input type="button" id="btnQueryBook" value="确定" style="width: 80px;"/>
                                        <input type="button" id="btnSubmit" value="完成借阅" style="width: 80px;"/>
                                    </td>

                                </tr>

                            </table>
                        </fieldset>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <tr>
                    <td height="20" colspan="3"></td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="" method="">
                                        <table width="100%" class="cont tr_color" id="tdBook">
                                            <tr align="center" class="d">
                                                <th>
                                                    <input id="ckAll" type="checkbox" value="" checked/>全选/全不选
                                                </th>
                                                <th>书籍名</th>
                                                <th>借阅时间</th>
                                                <th>应还时间</th>
                                                <th>出版社</th>
                                                <th>书架</th>
                                                <th>定价(元)</th>
                                            </tr>

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