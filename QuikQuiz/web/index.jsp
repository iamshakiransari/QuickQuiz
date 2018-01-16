<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include flush="false" page="./0.jsp"/>

<div class="container">
   <div class="jumbotron jumbotron-fluid bg-transparent pb-3">
      <h1 class="display-1 text-light">
         <em>
            <span class='text-danger'>Q</span>uick
            <span class='text-danger'>Q</span>uiz
         </em>
      </h1>

      <h2 class="display-3 text-light">
         Do you know about your process??
         <br>
         Take a quick quiz to see..
      </h2>
   </div>

   <div class="p-0 my-2">
      <c:choose>
         <c:when test="${empty username}">
            <a class='btn btn-primary' style='width: 250px' href='<c:url value="/Login.jsp"/>'><i class='fa fa-lg fa-sign-in'></i> Login</a>
         </c:when>
         <c:otherwise>
            <a class='btn btn-primary' style='width: 250px' href='<c:url value="/QuizSelector"/>'><i class='fa fa-lg fa-puzzle-piece'></i> Take a quick quiz</a>
         </c:otherwise>
      </c:choose>

      <p class='text-right text-light p-1' style='border-right: 1px white solid'>Written and Developed by ${suchi}</p>
   </div>

</div>

<jsp:include flush="false" page="./1.jsp"/>