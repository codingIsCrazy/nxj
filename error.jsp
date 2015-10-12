<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/commons/taglibs.jsp"%>
<html>
<head>
<%@include file="/commons/header.jsp"%>
</head>
<body>
	<c:if test="${tip != null && tip != ''}">
		<script>alert("${tip}");</script>
	</c:if>
</body>
</html>
