<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/27
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="statics/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("[type='submit']").attr("disabled","disabled");
            $("#regForm").submit(function(){
                //检查数据
                return true;
            });
            $("[name='uname']").blur(function(){
                var uname = $(this).val();
                ajaxUname(uname);
            });
        });
        function ajaxUname(uname){
            $.ajax({
                url:"valUname.shtml",
                type:"post",
                data:{
                    uname:uname
                },
                async:false,
                dataType:"json",
                success:function(data){
                    if (data.resultCode == "0000"){
                        $("[type='submit']").removeAttr("disabled");
                    }else {
                        alert("用户名重复了，请更换再注册")
                        $("[type='submit']").attr("disabled","disabled");
                    }
                }
            });
        }
    </script>
</head>
<body>
<form action="/addUser.shtml" method="post" enctype="multipart/form-data">
    <label>用户名:</label>
    <input type="text" name="uname"/>
    <br/>
    <label>密码:</label>
    <input type="password" name="upwd"/>
    <br/>
    <label>图像:</label>
    <input type="file" name="uimgFile"/>
    <br/>
    <label>注册时间:</label>
    <input type="text" name="ucreateTime"/>[yyyy-MM-dd]
    <br/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
