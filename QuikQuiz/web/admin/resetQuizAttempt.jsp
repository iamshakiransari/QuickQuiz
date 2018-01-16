<%-- Document: resetQuizAttempt || Date : Jan 16, 2018, 5:36:08 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${username != 'admin'}">
   <jsp:forward page="/index.jsp"/>
</c:if>

<jsp:include flush="false" page="./0-admin.jsp"/>

<div class='col-md-9 col-sm-12 mb-3'>
   <div class="card ">
      <div class="card-header">
         <h5 class="m-0 p-0"><span class='text-danger'><i class="fa fa-lg fa-trash"></i></span> Reset quiz attempts</h5>
      </div>

      <div class="card-body" id='divCardBodyResetPassword'>
         <form name='formResetQuizAttempt' action='<c:url value="/admin/ResetQuizAttempt"/>' method='post'>
            <div class='row'>
               <div class='col-md-6 col-sm-12'>
                  <label class='text-muted m-0'> Please pick a quiz</label>
                  <select class='form-control' name='quizNo'>
                     <option value='0'>--select a quiz--</option>
                     <c:if test="${not empty quizDetailList}">
                        <c:forEach var="quizDetailObj" items="${quizDetailList}">
                           <option value='${quizDetailObj.quizID}'>${quizDetailObj.quizName}</option>
                        </c:forEach>
                     </c:if>
                  </select>
               </div>
               <div class='col-md-6 col-sm-12'>
                  <label class='text-muted m-0'> Enter username</label>
                  <input type="text" name='user' class="form-control"/>
               </div>
            </div>

            <div class='row my-2'>
               <div class='col-md-6 col-sm-12'>
               </div>
               <div class='col-md-6 col-sm-12'>
                  <button type='button' onclick='resetUserAtempt(this)' class='btn btn-danger' style='width:100%'><i class='fa fa-lg fa-search'></i> Reset</button>
               </div>
            </div>
         </form>
      </div>
   </div><!--end of card-->
</div><!--end of col-->

<div class='col-md-3 col-sm-12'>
   <div class='card'>
      <div class='card-body'>
         <p class="m-0 text-muted text-center"><i class='fa fa-2x fa-newspaper-o'></i></p>
         <p class='p-0 m-0'>
            After a successful reset, user will be able to take quiz again..
            <br>
            You cannot undo changes after reset.
         </p>
      </div>
   </div>
</div>

<jsp:include flush="false" page="./1-admin.jsp"/>