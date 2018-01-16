<%-- Document: top || Date : Jan 9, 2018, 1:40:49 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" scope="session" value="${pageContext.request.contextPath}/"/>
<c:set var="stuffs" scope="session" value="${contextPath}stuffs/"/>
<c:set var="suchi" scope="session" value="@suchitaj"/>
<!DOCTYPE html>
<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>QQ: Quick Quiz</title>
      <link rel="stylesheet" href="<c:url value="/stuffs/vendor/bootstrap/css/bootstrap.min.css"/>">
      <link rel="stylesheet" href="<c:url value="/stuffs/vendor/fa-4.0.7/src/fa-4.7.0.min.css"/>">
      <link rel="stylesheet" href="<c:url value="/stuffs/vendor/jquery.ui/jquery-ui.min.css"/>">
      <!--<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">-->
      <link rel="stylesheet" href="<c:url value="/stuffs/css/basic.css"/>">
      <link rel="stylesheet" href="<c:url value="/stuffs/vendor/nn-notifications/nn-notifications.css"/>">

      <script src="<c:url value="/stuffs/js/jquery.v3.2.1.min.js"/>"></script>
      <script src="<c:url value="/stuffs/js/jquery.sticky.js"/>"></script>
      <script src="<c:url value="/stuffs/vendor/jquery.ui/jquery-ui.min.js"/>"></script>
      <script src="<c:url value="/stuffs/vendor/popper/popper.min.js"/>"></script>
      <script src="<c:url value="/stuffs/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
      <script src="<c:url value="/stuffs/js/basic.js"/>"></script>
      <script src="<c:url value="/stuffs/js/md5.min.js"/>"></script>
      <script src="<c:url value="/stuffs/vendor/nn-notifications/nn-notifications.js"/>"></script>
   </head>

   <body>

      <div id="nn-notification" style='display: none'></div>
      <input type="hidden" value="<%= new skr.core.DateTime().getDate("MM/dd/YYYY")%>" id="serverDate">

      <!-- Navigation -->
      <nav class="navbar navbar-expand-sm navbar-dark bg-dark fixed-top" style="background: rgba(0,0,0,.8) !important; border-bottom: 2px solid green;">
         <!--<nav class="navbar fixed-top navbar-expand-sm navbar-light" style="background-color: #e3f2fd;">-->
         <div class="container">

            <a class="navbar-brand" href="<c:url value="/index.jsp"/>">
               <span class='fa fa-lg fa-amazon'></span>
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
               <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarResponsive">
               <c:if test="${not empty username}">
                  <ul class="navbar-nav ml-auto">
                     <!--<li class="nav-item"><a class="nav-link" href="<c:url value="/Home"/>">Home<span class="sr-only">(current)</span></a></li>-->
                     <li class="nav-item dropdown">
                        <a href='#' class="nav-link dropdown-toggle" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${username}</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">

                           <c:if test="${username == 'admin'}">
                              <a class="dropdown-item" href="<c:url value="/admin/Admin.jsp"/>">
                                 <i class='fa fa-lg fa-get-pocket'></i> Admin Panel
                              </a>
                           </c:if>
                           <a class="dropdown-item" href="<c:url value="/QuizSelector"/>">
                              <i class='fa fa-lg fa-puzzle-piece'></i> Quiz(s)
                           </a>
                           <a class="dropdown-item" href="<c:url value="/ResultsAll"/>">
                              <i class='fa fa-lg fa-bullhorn'></i> Results
                           </a>
                           <a class="dropdown-item" href="<c:url value="/ChangePassword.jsp"/>">
                              <i class='fa fa-lg fa-shield'></i> Change Password
                           </a>
                           <a class="dropdown-item" href="<c:url value="/Logout.jsp"/>">
                              <i class='fa fa-lg fa-power-off'></i> Logout
                           </a>
                        </div>
                     </li>
                  </ul>
               </c:if>
            </div>

         </div>
      </nav>

      <div class="container-fluid">
            <div class='row my-3'>

