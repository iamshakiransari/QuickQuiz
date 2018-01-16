<%-- Document: profile || Date : Jan 9, 2018, 11:26:24 PM || @author : SKR --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}">
   <jsp:forward page="Login.jsp"/>
</c:if>

<c:if test="${empty param.visitor}">
   <jsp:forward page="index.jsp"/>
</c:if>

<c:if test="${empty param.visitor == 'admin'}">
   <jsp:forward page="/admin/Admin.jsp"/>
</c:if>

<jsp:include flush="false" page="./0.jsp"/>

<h1 class='text-white text-center'>Profile page for: ${param.visitor}</h1>

<jsp:include flush="false" page="./1.jsp"/>