<%-- Document: getQuestionsIntoQuizPaper || Date : Jan 15, 2018, 2:06:13 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}">
   <jsp:forward page="Login.jsp"/>
</c:if>

<c:choose>
   <c:when test="${reqPaperList.status}">
      <style>
         .spanStyle{color: #666;font-size: 1.3em;}
         label { cursor: pointer; min-height: 80px; max-height: 200px; overflow-y: auto }
      </style>
      <script>
         $(document).ready(function () {
            $("#idDivCardTimer").sticky({topSpacing: 70});
         });

         var mins = ${reqPaperList.questionCount}, secs = 0, counter = null;
         function updateClock() {
            if (mins >= 0) {

               if (secs < 1) {
                  mins--;
                  secs = 60;
               }
               secs--;

               var strSec = (secs < 10) ? '0' + secs : secs;
               var strMin = (mins < 10) ? '0' + mins : mins;
               $('#idSpanTimer').html(strMin + ':' + strSec);

               if (secs < 1 && mins < 1) {
                  //eNote('Done');
                  submitQuizPaper();
                  clearInterval(counter);
               }
            }
         }
         function startQuiz(THIS) {
            $('#idDivColQuestions').fadeIn(333, function () {
               $(THIS).hide(1);
               $('#idButtonSaveQuiz').show(1, function () {
                  counter = setInterval(updateClock, 1000);
               });
               $('#idDivColQuestionTimeNote').hide(0, function () {
                  $(this).remove();
               });
            })
         }

         function submitQuizPaper() {
            var quizForm = document.formQuizPaper.submit();
         }
      </script>

      <!-- Timer and save button col-->
      <div class='col-md-3 col-sm-12 mb-3' style='z-index: 9'>
         <div class='card' id='idDivCardTimer'>
            <div class='card-body'>
               <div class='row text-dark text-center'>
                  <div class='col-md-12 col-sm-5'>
                     <i class='fa fa-2x fa-clock-o'></i>&nbsp;
                     <span class='m-0' style='font-size:200%' class='m-0 p-0 text-center' id='idSpanTimer'>00:00</span>
                  </div>
                  <div class='col-md-12 col-sm-1'>&nbsp;</div>
                  <div class='col-md-12 col-sm-5'>
                     <button class='btn btn-lg btn-primary' onclick='startQuiz(this)'><i class='fa fa-lg fa-puzzle-piece'></i> Start Quiz</button>

                     <button class='btn btn-lg btn-success' onclick='submitQuizPaper()' id='idButtonSaveQuiz' style='display:none'>
                        <i class='fa fa-lg fa-archive'></i> Save & Submit
                     </button>
                  </div>
                  <div class='col-md-12 col-sm-12' id='idDivColQuestionTimeNote'>
                     <hr class='my-3'>
                     <p class='p-0 m-0 text-left text-muted'>This quiz contains ${reqPaperList.questionCount} questions(s) and you have 1(one) minute for each question. </p>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!--end Timer and save button col-->

      <!-- Question col-->
      <div class='col-md-9 col-sm-12' style='display:none' id='idDivColQuestions'>
         <form name='formQuizPaper' action="<c:url value="/Result"/>" method='post'>
            <input type='hidden' name='user' value='${username}'/>
            <input type='hidden' name='totalQuestions' value='${reqPaperList.questionCount}'/>
            <input type='hidden' name='UUID' value='${param.UUID}'/>
            <input type='hidden' name='quizNo' value='${param.quizNo}'/>

            <c:forEach var="questionObj" items="${reqPaperList.list}">
               <div class="card mb-2">
                  <div class="card-body">
                     <div class='row'>
                        <div class='col-md-12 col-sm-12'>
                           <b class='spanStyle'>Question: </b>${questionObj.question}<hr class='my-3'>
                        </div>
                        <div class='col-md-6 col-sm-12'>
                           <span class='spanStyle'><input type='radio' name='que${questionObj.questionID}' id='idRadioOptA${questionObj.questionID}' value='1'/> Option A</span>
                           <label for="idRadioOptA${questionObj.questionID}" class='form-control rounded border bg-light'>${questionObj.opt1}</label>
                        </div>
                        <div class='col-md-6 col-sm-12'>
                           <span class='spanStyle'><input type='radio' name='que${questionObj.questionID}' id='idRadioOptB${questionObj.questionID}' value='2'/> Option B</span>
                           <label for="idRadioOptB${questionObj.questionID}" class='form-control rounded border bg-light'>${questionObj.opt2}</label>
                        </div>
                        <div class='col-md-6 col-sm-12'>
                           <span class='spanStyle'><input type='radio' name='que${questionObj.questionID}' id='idRadioOptC${questionObj.questionID}' value='3'/> Option C</span>
                           <label for="idRadioOptC${questionObj.questionID}" class='form-control rounded border bg-light'>${questionObj.opt3}</label>
                        </div>
                        <div class='col-md-6 col-sm-12'>
                           <span class='spanStyle'><input type='radio' name='que${questionObj.questionID}' id='idRadioOptD${questionObj.questionID}' value='4'/> Option D</span>
                           <label for="idRadioOptD${questionObj.questionID}" class='form-control rounded border bg-light'>${questionObj.opt4}</label>
                        </div>
                        <input type='hidden' name='qa_us${questionObj.questionID}' value='${questionObj.anwser}'/>
                     </div>
                  </div>
               </div>
            </c:forEach>
         </form>
      </div>
   </c:when>
   <c:otherwise>
      <div class='col-12 p-4 m-0 bg-white text-danger text-center rounded' style='font-size:1.5em'>
         <p class='p-0 m-0'><i class='fa fa-5x fa-exclamation-circle'></i>
         <h1 class='p-0 mt-4 text-dark'>${reqPaperList.statusMessage}</h1>
      </div>
   </c:otherwise>
</c:choose>
<!--end Question col-->