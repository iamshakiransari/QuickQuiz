<%-- Document: editQuiz || Date : Jan 12, 2018, 11:20:55 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${username != 'admin'}"><jsp:forward page="/index.jsp"/></c:if>
<c:if test="${empty param.quizNo}"><jsp:forward page="/admin/Admin.jsp"/></c:if>
<jsp:include flush="false" page="./0-admin.jsp"/>

<script>
   $().ready(function () {
      $(document.getElementById('idInputLastDate')).onlyFutureDates();
   });
</script>


<div class='col-md-4 col-sm-12'>
   <div class='card'>
      <div class='card-header'>
         <h5 class="m-0 p-0">
            <span class='text-danger'><i class='fa fa-lg fa-puzzle-piece'></i></span> Quiz details
         </h5>
      </div>

      <div class='card-body'>
         <c:choose>
            <c:when test="${not empty reqQuizDetailObj}">
               <form name='formUpdateQuizDetails' action='<c:url value="/admin/UpdateQuizDetails"/>' method='post'>
                  <div class='row'>
                     <div class='col-sm-12 mb-3'>
                        <label class='m-0 text-muted mb-1'>Quiz Title</label>
                        <input type='hidden' name='quizNo' value='${reqQuizDetailObj.quizID}'/>
                        <input type='text' name='quizTitle' value='${reqQuizDetailObj.quizName}' class='form-control' autofocus='true'>
                     </div>
                     <div class='col-sm-12 mb-3'>
                        <label class='m-0 text-muted mb-1'>Quiz level</label>
                        <select name='level' class='form-control'>
                           <option value='${reqQuizDetailObj.level}'>${reqQuizDetailObj.levelByName}</option>
                           <option value='1'>Low</option>
                           <option value='2'>Medium</option>
                           <option value='3'>High</option>
                           <option value='4'>Very High</option>
                        </select>
                     </div>
                     <div class='col-sm-12 mb-3'>
                        <label class='m-0 text-muted mb-1'>Last date</label>
                        <input type='text' value='${reqQuizDetailObj.sqlDateFormatted}' name='lastDate' id='idInputLastDate' class='form-control'>
                     </div>
                     <div class='col-sm-12 text-right'>
                        <button type='button' onclick='updateQuizDetails(this)' class='btn btn-dark'>
                           <i class='fa fa-lg fa-puzzle-piece'></i> Save new values
                        </button>
                        <button type='button' onclick='deleteQuiz(this, "<c:url value="/admin/DeleteQuiz"/>", ${reqQuizDetailObj.quizID})' class='btn btn-danger' title='Delete this Quiz'>
                           <i class='fa fa-lg fa-trash'></i>
                        </button>
                     </div>
                  </div>
               </form>
            </c:when>
            <c:otherwise>
               <div class='col-12'>
                  <p class='p-4 m-0 bg-white text-danger text-center rounded' style='font-size:1.5em'>
                     <i class='fa fa-3x fa-exclamation-triangle' style='text-shadow: 1px 2px 3px #999'></i>
                     <br>
                     <c:choose>
                        <c:when test="${not empty detailErrorMSG}">
                           ${detailErrorMSG}
                        </c:when>
                        <c:otherwise>
                           Something went wrong.
                           <br><small class='text-muted m-0 p-0'>Please the quiz no again..</small>
                        </c:otherwise>
                     </c:choose>
                  </p>
               </div>
            </c:otherwise>
         </c:choose>
      </div>
   </div>
</div>

<div class='col-md-8 col-sm-12 mb-3'>
   <div class='row'>

      <!--Add New Question-->
      <div class='col-md-12 col-sm-12 mb-1'>
         <div class="card">
            <div class="card-header tog">
               <h5 class="m-0 p-0"><span class='text-danger'><i class='fa fa-plus-square'></i></span> Add new question here</h5>
            </div>
            <div class="card-body" style='display: none'>
               <form name='formAddNewQuestion' method="post" action='<c:url value="/AddNewQuestion"/>' class="m-0 p-0">
                  <input type='hidden' name='quizNo' value='${param.quizNo}'/>
                  <div class='row'>

                     <div class='col-12 mb-2'>
                        <label class='m-0 text-muted'>Question</label>
                        <textarea type='text' name='question' class='form-control'></textarea>
                     </div>
                     <div class='col-12 mb-2'>
                        <div class='row'>
                           <div class='col-6'>
                              <label class='m-0 text-muted'>Option 1 (Answer)</label>
                              <textarea type='text' name='opt1' class='form-control'></textarea>
                           </div>
                           <div class='col-6'>
                              <label class='m-0 text-muted'>Option 2</label>
                              <textarea type='text' name='opt2' class='form-control'></textarea>
                           </div>
                        </div>
                     </div>
                     <div class='col-12 mb-4'>
                        <div class='row'>
                           <div class='col-6'>
                              <label class='m-0 text-muted'>Option 3</label>
                              <textarea type='text' name='opt3' class='form-control'></textarea>
                           </div>
                           <div class='col-6'>
                              <label class='m-0 text-muted'>Option 4</label>
                              <textarea type='text' name='opt4' class='form-control'></textarea>
                           </div>
                        </div>
                     </div>
                     <div class='col-12 text-right'>
                        <button type='button' onclick='addNewQuestion(this)' class='btn btn-dark'>
                           <i class='fa fa-lg fa-plus-square'></i> Add to database
                        </button>
                     </div>

                  </div>
               </form>
            </div>
         </div>
      </div>
      <!--Add New Question-->

      <!--List Of Old Question-->
      <div class='col-md-12 col-sm-12 mb-3'>
         <c:choose>
            <c:when test="${not empty reqQuestionsList}">
               <c:set var="srNo" value="0" scope="page"/>
               <c:forEach var="que" items="${reqQuestionsList}">
                  <c:set var="srNo" value="${srNo + 1}" scope="page"/>
                  <form id='formQuestion${que.questionID}' action='<c:url value="/admin/UpdateQuestion"/>' method='post'>
                     <input type='hidden' name='questionID' value='${que.questionID}'/>
                     <div class='card mb-1'>
                        <div class='card-header tog'>
                           <h5 class='m-0 p-0'> ${srNo}.) Question ID: ac${que.questionID}b</h5>
                        </div>
                        <div class='card-body' style='display: none'>
                           <div class='row'>
                              <div class='col-12 mb-2'>
                                 <label class='m-0 text-muted'>Question</label>
                                 <textarea name='question' class='form-control'>${que.question}</textarea>
                              </div>
                              <div class='col-12 mb-2'>
                                 <div class='row'>
                                    <div class='col-6'>
                                       <label class='m-0 text-muted'>Option 1 (Answer)</label>
                                       <textarea name='opt1' class='form-control'>${que.opt1}</textarea>
                                    </div>
                                    <div class='col-6'>
                                       <label class='m-0 text-muted'>Option 2</label>
                                       <textarea name='opt2' class='form-control'>${que.opt2}</textarea>
                                    </div>
                                 </div>
                              </div>
                              <div class='col-12 mb-4'>
                                 <div class='row'>
                                    <div class='col-6'>
                                       <label class='m-0 text-muted'>Option 3</label>
                                       <textarea name='opt3' class='form-control'>${que.opt3}</textarea>
                                    </div>
                                    <div class='col-6'>
                                       <label class='m-0 text-muted'>Option 4</label>
                                       <textarea name='opt4' class='form-control'>${que.opt4}</textarea>
                                    </div>
                                 </div>
                              </div>
                              <div class='col-12 text-right'>
                                 <button type='button' onclick='saveQuestion(this, ${que.questionID})' class='btn btn-dark mr-2'>
                                    <i class='fa fa-lg fa-folder'></i> Save to database
                                 </button>
                                 <button type='button' onclick='deleteQuestion(this, "<c:url value="/admin/DeleteQuestion"/>", ${que.questionID})' class='btn btn-danger' title='Delete this Quiz'>
                                    <i class='fa fa-lg fa-trash'></i>
                                 </button>
                              </div>
                           </div>
                        </div>
                     </div>
                  </form>
               </c:forEach>
            </c:when>
            <c:otherwise>
               <div class='col-12'>
                  <p class='p-4 m-0 bg-white text-danger text-center rounded' style='font-size:1.5em'>
                     <i class='fa fa-3x fa-exclamation-triangle' style='text-shadow: 1px 2px 3px #999'></i>
                     <br>
                     <c:choose>
                        <c:when test="${not empty questionErrorMSG}">
                           ${questionErrorMSG}
                        </c:when>
                        <c:otherwise>
                           Something went wrong.
                           <br><small class='text-muted m-0 p-0'>Please the quiz no again..</small>
                        </c:otherwise>
                     </c:choose>
                  </p>
               </div>
            </c:otherwise>
         </c:choose>
      </div>
      <!--List Of Old Question-->
   </div>
</div>
<jsp:include flush="false" page="./1-admin.jsp"/>