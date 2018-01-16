<%-- Document: quizList || Date : Jan 12, 2018, 7:47:18 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${username != 'admin'}"><jsp:forward page="/index.jsp"/></c:if>
<jsp:include flush="false" page="./0-admin.jsp"/>

<div class='col-md-8 col-sm-12 mb-3'>
   <div class="card ">
      <div class="card-header">
         <h5 class="m-0 p-0"><i class='fa fa-list-ol'></i> Available Quiz</h5>
      </div>
      <div class="card-body" id='divCardBodyQuizList'>

         <c:choose>
            <c:when test="${not empty reqQuizDetails}">
               <table style='width: 100%'>
                  <c:forEach var="quizObj" items="${reqQuizDetails}">
                     <tr>
                        <td style='width:70%'>
                           <input type='text' value='${quizObj.quizName}' class='form-control' disabled='true'>
                        </td>
                        <td style='width:25%'>
                           <a href='<c:url value="/admin/EditQuizDetails?"/>quizNo=${quizObj.quizID}' class='btn btn-success' style='width:100%'>
                              <i class='fa fa-pencil-square-o'></i> Edit
                           </a>
                        </td>
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
                           Something went wrong.
                           <br><small class='text-muted m-0 p-0'>May be you have not added any quiz yet.</small>
                        </c:otherwise>
                     </c:choose>
                  </p>
               </div>
            </c:otherwise>
         </c:choose>
      </div>
   </div><!--end of card-->
</div><!--end of col-->

<div class='col-md-4 col-sm-12'>
   <div class='card'>
      <div class='card-body'>
         <p class="m-0 text-muted text-right"><i class='fa fa-2x fa-paperclip'></i></p>
         <p class='m-0 p-0'>
            Click on edit button in order to edit/add/delete question or edit quiz details.
         </p>
      </div>
   </div>
</div>

<jsp:include flush="false" page="./1-admin.jsp"/>