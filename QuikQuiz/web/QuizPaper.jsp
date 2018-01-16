<%-- Document: QuizPaper || Date : Jan 14, 2018, 6:50:42 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}"><jsp:forward page="Login.jsp"/></c:if>
<jsp:include flush="false" page="./0.jsp"/>


<script>
   $(document).ready(function () {
      $.ajax({
         url: 'GetQuestionsIntoQuizPaper?UUID=${param.UUID}&quizNo=${param.quizNo}',
         type: 'get',
         beforeSend: function (data) {
         },
         complete: function (cD) {
         },
         success: function (data) {
            $('#idDivRow').hide(1, function () {
               $(this).html(data).fadeIn(333);
            });
         },
         error: function (xhr, ajaxOptions, thrownError) {
            eNote("Ajax Failed:<br>Status: " + xhr.status + "<br>Error: " + thrownError + "<br>Message: " + xhr.responseText);
         }
      });
      return false;
   });
</script>

<div class='container'>
   <div class='row my-3' id='idDivRow'>

      <div class='col-12'>
         <p class='p-5 m-0 bg-white text-danger text-center rounded' style='font-size:1.5em'>
            <i class='fa fa-3x fa-spin fa-refresh'></i><br>
            Fetching data please wait..
         </p>
      </div>

   </div>
</div>

<jsp:include flush="false" page="./1.jsp"/>
