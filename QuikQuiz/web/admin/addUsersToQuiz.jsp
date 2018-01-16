<%-- Document: addUsersToQuiz || Date : Jan 13, 2018, 11:46:51 PM || @author : SKR --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${username != 'admin'}"><jsp:forward page="/index.jsp"/></c:if>

<jsp:include flush="false" page="./0-admin.jsp"/>

<!--Start of container-->
<div class='container'>
   <div class='row'>

      <div class='col-md-8 col-sm-12 mb-3'>
         <div class="card ">
            <div class="card-header">
               <h4 class="m-0 p-0"><i class='fa fa-puzzle-piece'></i> Add New Quiz</h4>
               <input type='hidden' id='urlForResetPassword' value='<c:url value="/ResetPassword"/>'>
            </div>

            <div class="card-body">
               <div class='row'>

                  <form name='formAddNewQuizAdmin' method="post" action='<c:url value="/AddNewQuiz"/>' class="m-0 p-0">

                     <div class='col-12 mb-3'>
                        <label class='m-0 text-muted mb-1'>Quiz Title</label>
                        <select name='level' class='form-control'>
                           <option value='0'>--select--</option>
                           <option value='1'>Low</option>
                           <option value='2'>Medium</option>
                           <option value='3'>High</option>
                           <option value='4'>Very High</option>
                        </select>
                     </div>

                     <div class='col-12 mb-3'>
                        <div class='row'>
                           <div class='col-4'>
                              <label class='m-0 text-muted mb-1'>Quiz level</label>
                              <select name='level' class='form-control'>
                                 <option value='0'>--select--</option>
                                 <option value='1'>Low</option>
                                 <option value='2'>Medium</option>
                                 <option value='3'>High</option>
                                 <option value='4'>Very High</option>
                              </select>
                           </div>
                           <div class='col-3'>
                              <label class='m-0 text-muted mb-1'>Attempts</label>
                              <input type='text' name='attempts' value='1' readonly='true' class='form-control'>
                           </div>
                           <div class='col-5'>
                              <label class='m-0 text-muted mb-1'>Last date</label>
                              <input type="text" name="lastDate" id='inputLastDate' class="form-control"><br>
                           </div>
                        </div>
                     </div>

                     <div class='col-12 mt-3 text-right'>
                        <button type='button' onclick='addNewQuiz(this)' class='btn btn-dark' style='width: 50%'>
                           <i class='fa fa-lg fa-puzzle-piece'></i> Add to database
                        </button>
                     </div>
                  </form>

               </div>
            </div>

         </div><!--end of card-->
      </div><!--end of col-->

      <div class='col-md-4 col-sm-12'>
         <div class='card'>
            <div class='card-body'>
               <p class="m-0 text-muted text-right"><i class='fa fa-2x fa-paperclip'></i></p>
               <p class='m-0 p-0'>
                  Please add your quiz to database with requested details and change them later if required.
                  <br>
                  <br>
                  You may add/edit/delete question(s) in "Edit existing quiz."
               </p>
            </div>
         </div>
      </div>

   </div><!--end of row-->
</div>
<!--end of container-->

<jsp:include flush="false" page="./1-admin.jsp"/>