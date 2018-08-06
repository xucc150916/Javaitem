<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<jsp:include page="common/base.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>陕西科技大学学生信息管理系统主界面</title>
    <%
        // 权限验证
        if (session.getAttribute("currentUser") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
    %>
    <jsp:include page="common/resource.jsp"/>
    <script type="text/javascript">
        $(function () {
            // 数据
            var treeData = [{
                text: "根",
                children: [{
                    text: "班级信息管理",
                    attributes: {
                        url: "grade/grade_info_manage.jsp"
                    }
                }, {
                    text: "学生信息管理",
                    attributes: {
                        url: "student/student_info_manage.jsp"
                    }
                }]
            }];

            // 实例化树菜单
            $("#tree").tree({
                data: treeData,
                lines: true,
                onClick: function (node) {
                    if (node.attributes) {
                        openTab(node.text, node.attributes.url);
                    }
                }
            });

            // 新增Tab
            function openTab(text, url) {
                if ($("#tabs").tabs('exists', text)) {
                    $("#tabs").tabs('select', text);
                } else {
                    var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src=" + url + "></iframe>";
                    $("#tabs").tabs('add', {
                        title: text,
                        closable: true,
                        content: content
                    });
                }
            }
        });
    </script>
</head>
<body class="easyui-layout">
<div region="north" style="height: 80px;background-color: #E0EDFF">

    <div align="left" style="width: 80%;float: left">
        <img src="assets/images/main.jpg"></div>

    <div style="padding-top: 50px;padding-right: 20px;">
        当前用户：<font color="red">${currentUser.userName}</font>
        &nbsp;&nbsp;
        当前人数：<font color="red">${onlineNumber}</font>
        &nbsp;&nbsp;
        <a href="logout">退出登录</a>
    </div>

    </div>
    <div region="center">
        <div class="easyui-tabs" fit="true" border="false" id="tabs">
            <div title="首页">
                <div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用</font></div>
            </div>
        </div>
    </div>
    <div region="west" style="width: 150px;" title="导航菜单" split="true">
        <ul id="tree"></ul>
    </div>
    <div region="south" style="height: 25px;" align="center">版权所有<a href="http://www.bittech.in">Bit Tech</a></div>
</body>
</html>