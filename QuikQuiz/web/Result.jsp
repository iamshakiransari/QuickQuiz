<%-- Document: Result || Date : Jan 15, 2018, 4:29:31 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}"><jsp:forward page="Login.jsp"/></c:if>

<jsp:include flush="false" page="./0.jsp"/>

<div class='container'>
   <div class='row my-3'>

      <c:choose>
         <c:when test="${status}">
            <div class='col-md-4 col-sm-12 mb-2'>
               <div class='card'>
                  <div class='card-body'>
                     <p class="m-0 text-dark fa-2x"><i class='fa fa-newspaper-o'></i> Quiz Summary</p>
                     <table class='table table-bordered my-2'>
                        <tr><td>Correct Answers</td>   <td>${totalCorrects}</td></tr>
                        <tr><td>Incorrect or Unanswered</td> <td>${totalIncorrects}</td></tr>
                        <tr><td>Your score</td>             <td>${scorePer}</td></tr>
                     </table>
                     <hr class='my-2'>
                     <p class='p-0 m-0 text-danger text-right'>Please contact <b>${suchi}</b> for further clarification or help.</p>
                  </div>
               </div>
            </div>

            <div class='col-md-8 col-sm-12 mb-3'>
               <div class="card ">
                  <div class="card-body">
                     <h5 class="m-0 p-0">Quiz Results &nbsp;<span class='text-dark'><i class="fa fa-lg fa-bullhorn"></i></span></h5>
                     <table class='table table-bordered table-striped mt-3 mb-0'>
                        <c:forEach var="resObj" items="${reqResult}">
                           <c:choose>
                              <c:when test="${resObj.correct}">
                                 <tr title='Question ${resObj.questionCountNo} was correct' >
                                    <td>Question ${resObj.questionCountNo}</td>
                                    <td class='text-success'><i class='fa fa-lg fa-check'></i></td>
                                 </tr>
                              </c:when>
                              <c:otherwise>
                                 <tr title='Question ${resObj.questionCountNo} was incorrect' >
                                    <td>Question ${resObj.questionCountNo}</td>
                                    <td class='text-danger'><i class='fa fa-lg fa-times'></i></td>
                                 </tr>
                              </c:otherwise>
                           </c:choose>
                        </c:forEach>
                     </table>
                  </div>
               </div><!--end of card-->
            </div>
         </c:when>
         <c:otherwise>
            <div class='col-12'>
               <p class='p-5 m-0 bg-white text-danger text-center rounded' style='font-size:1.5em'>
                  <i class='fa fa-5x fa-exclamation-circle'></i>
                  <br>
                  Something went wrong <br> ${reqMSG}
               </p>
            </div>
         </c:otherwise>
      </c:choose>

   </div><!--end of row-->
</div><!--end of container-->

<jsp:include flush="false" page="./1.jsp"/>
