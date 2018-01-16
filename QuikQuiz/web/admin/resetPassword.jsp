<%-- Document: resetPassword || Date : Jan 10, 2018, 6:40:48 PM || @author : SKR --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${username != 'admin'}">
   <jsp:forward page="/index.jsp"/>
</c:if>

<jsp:include flush="false" page="./0-admin.jsp"/>

<div class='container'>
   <div class='row'>

      <div class='col-md-9 col-sm-12 mb-3'>
         <div class="card ">
            <div class="card-header">
               <h5 class="m-0 p-0"><span class='text-dark'><i class="fa fa-lg fa-user-times"></i></span> Reset user account</h5>
               <input type='hidden' id='urlForResetPassword' value='<c:url value="/ResetPassword"/>'>
            </div>
            <div class="card-body" id='divCardBodyResetPassword'>
               <h5 id='idParaSpinner' class='bg-transparent p-0 text-center'><i class='fa fa-spin fa-spinner'></i> Please wait..</h5>
            </div>
         </div><!--end of card-->
      </div><!--end of col-->

      <div class='col-md-3 col-sm-12'>
         <div class='card'>
            <div class='card-body'>
               <p class="m-0 text-muted text-center"><i class='fa fa-2x fa-newspaper-o'></i></p>
               <p class='p-0 m-0'>After a successful reset, user has to register him/herself again.</p>
            </div>
         </div>
      </div>

   </div><!--end of row-->
</div><!--end of container-->

<script>$().ready(function(){ loadUserData(); });</script>
<jsp:include flush="false" page="./1-admin.jsp"/>