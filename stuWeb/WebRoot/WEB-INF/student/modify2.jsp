<%@ page language="java" import="java.util.*,entity.Student"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'add.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">
<style type="text/css">
#main {
	width: 800px;
	margin: 10px auto;
}
#form{
width: 260px;
}


#form label {
	float: left;
	width: 60px;
}

#form.value {
	float: left;
	width: 200px;
}

#form div {
	clear: both;
	margin-top: 30px;
	overflow: hidden;
}

#btn {
	text-align: center;
}

#form span {
	margin: 0 10px;
	font-size: 14px;
	font-weight: blod;
}
#main ul li{
list-style: none;
float:left;
margin-left: 60px;
}
</style>

</head>

<body>

	<div id="main">
		<ul >
			<c:forEach items="${stus }" var="stu">
				<li>
				<form id="form" action="stu" method="post">
					<input type="hidden" name="type" value="modify" /> <input
						type="hidden" name="id" value="${stu.id }" />
					<div>
						<label>姓名：</label> <input type="text" name="name"
							class="form-control value" placeholder="请输入姓名"
							value="${stu.name }" />
					</div>
					<div>
						<label>性别：</label> <input type="radio" name="sex" value="男"
							<c:if test="${stu.sex eq '男' }">checked</c:if> /> <span>男</span>
						<input type="radio" name="sex" value="女"
							<c:if test="${stu.sex eq '女' }">checked</c:if> /> <span>女</span>
					</div>
					<div>
						<label>年龄：</label> <input type="text" name="age"
							class="form-control value" value="${stu.age }" />
					</div>
					<div id="btn">
						<input type="submit" value="保存" class="btn btn-danger" />
					</div>

				</form>
				</li>
			</c:forEach>
		</ul>
	</div>




</body>
</html>
