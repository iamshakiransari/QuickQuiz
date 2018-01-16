<%-- Document: 0 || Date : Jan 10, 2018, 5:18:06 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include flush="false" page="../0.jsp"/>

<script src="${stuffs}js/admin.js"></script>

<div class='col-md-3 col-sm-12 mb-3 pr-3'>
   <div class="card">
      <div class="card-header">
         <h5 class="m-0 p-0"><span class='text-danger'><i class='fa fa-lg fa-get-pocket'></i></span> Quick links</h5>
      </div>
      <div class="card-body bg-w">

         Quiz Related
         <a class="btn btn-light mb-1" style='width:100%' href="<c:url value="/admin/addNewQuiz.jsp"/>">Add new quiz</a>
         <!--<a class="btn btn-light mb-1" style='width:100%' href="<c:url value="/admin/addUsersToQuiz.jsp"/>">Add users to quiz</a>-->
         <a class="btn btn-light mb-0" style='width:100%' href="<c:url value="/admin/EditQuizSelector"/>">Edit quiz</a>

         <br><br>
         Restore/Reset
         <a class="btn btn-light mb-1" style='width:100%' href="<c:url value="/admin/ResetQuizAttempt"/>">Reset Quiz Attempt</a>
         <a class="btn btn-light m-0" style='width:100%' href="<c:url value="/admin/resetPassword.jsp"/>">Reset User Account</a>

      </div>
   </div>
</div>

<div class='col-md-9 col-sm-12 mb-3'>
   <div class='row'>
