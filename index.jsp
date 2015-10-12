<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="util.StatisticsUtil" %>
<%@page import="com.gwb.title.dto.TitleOneBean" %>
<%@page import="com.gwb.title.dto.TitleTwoBean" %>
<%@include file="/commons/taglibs.jsp"%>
<% 
	//增加pv
	StatisticsUtil.updatePageViewPv(request);
	StatisticsUtil.queryAll(request);
 %>
<html>

<head>
<style type="text/css">
	body{
		algin:center;
		background-color:#00fa9a;
		font-size:15px;
	}
	font{
		color:#0000FF;
		font-weight: 600;
		font-size:17px;
	}
	
</style>
<%@include file="/commons/header.jsp"%>

</head>
<body>
<!-- 
<div>
<center>
	<font>名称:&nbsp;&nbsp;&nbsp;</font><input name="name" value="${value}" style="width:200px;height:25px;font-size:19px;"/>
		&nbsp;&nbsp;
	 <a href="javascript:void(0)" class="easyui-linkbutton"  onclick="submitForm()">查询</a></center>
</div>
 -->
 <div style="margin-left:20%;">
<br>
<br>
	<c:forEach items="${titleOneList}" var="titleOne">
		<font style="font-size:20px;color:#DC143C;"><br><br><br>${titleOne.name}</font>
		<c:forEach items="${titleOne.titleTwoList}" var="titleTwo" varStatus="status">
			<c:if test="${status.index%7 == 0}">
			<br><br>
			</c:if>
			<a href="${titleTwo.href}"  target="_blank" style="text-decoration:none" onclick="updateClickCount(${titleTwo.id});">
			<font style="width:13%;display:inline-block">${titleTwo.name}&nbsp;&nbsp;</font>
			</a>
		</c:forEach>
	</c:forEach>
<br>
</div>
<script>
function updateClickCount(id){
	$.ajax({
   		type: "POST",
   		url: "${path}/titleController/updateClickCount.gwb?id="+id,
  	    success: function(msg){
  		}
	});
}
</script>
</body>
</html>