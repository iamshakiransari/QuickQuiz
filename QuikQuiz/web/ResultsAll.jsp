<%-- Document: ResultsAll || Date : Jan 15, 2018, 6:30:56 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}"><jsp:forward page="Login.jsp"/></c:if>

<jsp:include flush="false" page="./0.jsp"/>

<div class='col-md-5 col-sm-12 mb-2'>
   <div class='card'>
      <div class='card-header'>
         <h5 class="m-0 text-right text-dark fa-lg"><i class='fa fa-lg fa-puzzle-piece'></i> Available Quiz(s)</h5>
      </div>
      <div class='card-body'>
         <form class='m-0 p-0' action="<c:url value="/ResultsAll"/>" method="post">
            <input type='hidden' name='user' value='${username}'/>
            <div class='row'>
               <div class='col-12'>
                  <select class='form-control' name='quizNo'>
                     <option vlaue='0'>--select a quiz--</option>
                     <c:if test="${not empty quizDetailList}">
                        <c:forEach var="quizDetailObj" items="${quizDetailList}">
                           <option value='${quizDetailObj.quizID}'>${quizDetailObj.quizName}</option>
                        </c:forEach>
                     </c:if>
                  </select>
               </div>
               <div class='col-12 mt-3 text-right'>
                  <button class='btn btn-dark' style='width:60%'>
                     <i class='fa fa-lg fa-search'></i> Search Result
                  </button>
               </div>
            </div>
         </form>
      </div>
   </div>
</div>

<div class='col-md-7 col-sm-12 mb-3'>
   <div class="card">
      <div class="card-header">
         <h5 class="m-0 p-0">Quiz Results &nbsp;<span class='text-dark'><i class="fa fa-lg fa-bullhorn"></i></span></h5>
      </div>
      <div class="card-body">
         <c:choose>
            <c:when test="${not empty reqResultsAll}">
               <table class='table table-bordered table-striped mt-3 mb-0'>
                  <table class='table table-bordered my-2'>
                     <tr>
                        <td>Total questions</td>
                        <td>${reqResultsAll.totalQuestions}</td>
                     </tr>
                     <tr>
                        <td>Correct Answers</td>
                        <td>${reqResultsAll.corrects}</td>
                     </tr>
                     <tr>
                        <td>Incorrect or Unanswered</td>
                        <td>${reqResultsAll.incorrects}</td>
                     </tr>
                     <tr>
                        <td>Your score</td>
                        <td>${reqResultsAll.percentage}</td>
                     </tr>
                  </table>
               </c:when>
               <c:otherwise>
                  <div class='col-12'>
                     <p class='p-4 m-0 bg-white text-danger text-center rounded' style='font-size:1.5em'>
                        <i class='fa fa-3x fa-exclamation-triangle' style='text-shadow: 1px 2px 3px #999'></i>
                        <br>
                        <c:choose>
                           <c:when test="${not empty reqMSG}">
                              ${reqMSG}
                           </c:when>
                           <c:otherwise>
                              Please select a quiz from dropdown menu
                           </c:otherwise>
                        </c:choose>
                     </p>
                  </div>
               </c:otherwise>
            </c:choose>
      </div>
   </div><!--end of card-->
</div>

<jsp:include flush="false" page="./1.jsp"/>