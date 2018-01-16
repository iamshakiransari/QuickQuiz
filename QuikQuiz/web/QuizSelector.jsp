<%-- Document: QuizList || Date : Jan 14, 2018, 12:04:17 AM || @author : SKR --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}"><jsp:forward page="Login.jsp"/></c:if>

<jsp:include flush="false" page="./0.jsp"/>

<div class='col-md-5 col-sm-12 mb-3'>
   <div class='card'>
      <div class='card-body'>
         <p class="m-0 text-dark text-right"><i class='fa fa-2x fa-paperclip'></i></p>
         <h5>Please read the below instruction carefully.</h5>
         <hr>
         <ol style='list-style: decimal-leading-zero'>
            <li>Once you click on [start] quiz will start and your one attempt will be gone.</li>
            <li>Do not close/refresh your browser tab/window, once quiz is on.</li>
            <li>Contact <b>@suchitaj</b>, if you need help or assistance.</li>
         </ol>
      </div>
   </div>
</div>

<div class='col-md-7 col-sm-12'>
   <div class="card ">
      <div class="card-header">
         <h5 class="m-0 p-0"><i class='fa fa-lg fa-puzzle-piece'></i> Available quiz</h5>
      </div>
      <div class="card-body">
         <div class='row'>
            <div class='col-12'>
               <c:choose>
                  <c:when test="${not empty reqQuizDetails}">
                     <table style='width: 100%'>
                        <c:forEach var="quizObj" items="${reqQuizDetails}">
                           <tr>
                              <td style='width:70%'><input type='text' value='${quizObj.quizName}' class='form-control disabled text-center' disabled='true'></td>
                              <td style='width:25%'><a href='<c:url value="/QuizPaper.jsp"/>?UUID=${quizObj.quizUUID}&quizNo=${quizObj.quizID}' class='btn btn-dark' style='width:100%'><i class='fa fa-lg fa-clock-o'></i> Start</a></td>
                           </tr>
                        </c:forEach>
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
                                 Something went wrong, please contact ${suchi}
                              </c:otherwise>
                           </c:choose>
                        </p>
                     </div>
                  </c:otherwise>
               </c:choose>
            </div>
         </div>
      </div><!--end of card-->

   </div><!--end of col-->

</div><!--end of col-->

<jsp:include flush="false" page="./1.jsp"/>
