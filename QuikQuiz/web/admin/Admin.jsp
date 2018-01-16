<%-- Document: admin || Date : Jan 9, 2018, 4:52:02 PM || @author : SKR --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${username != 'admin'}">
   <jsp:forward page="/index.jsp"/>
</c:if>
<jsp:include flush="false" page="./0-admin.jsp"/>

<div class="card ">
   <div class="card-body">
      <h1>Welcome admin,</h1>
      <h2><small>Click any quick link to do more..</small></h2>
      <p class='pt-3 m-0'>
         Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
      </p>
   </div>
</div>

<jsp:include flush="false" page="./1-admin.jsp"/>