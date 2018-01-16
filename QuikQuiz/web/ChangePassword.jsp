<%-- Document: ChangePassword || Date : Jan 12, 2018, 12:31:55 AM || @author : SKR --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty username}">
   <jsp:forward page="Login.jsp"/>
</c:if>

<jsp:include flush="false" page="./0.jsp"/>

<script src="${stuffs}js/changePassword.js"></script>

<div class='container'>
   <div class='row my-4'>
      <div class='col-lg-6 col-md-8 col-sm-10 d-block mx-auto'>

         <!--Password Changing form-->
         <div class="card" id='idDivRegistrationForm'>
            <div class="card-header">
               <h5 class="mb-0"><i class='fa fa-key'></i> Change Your Password</h5>
            </div>
            <div class="card-body">
               <form name='formChangePassword' action="<c:url value="/ChangePassword"/>" method="post" class='m-0'>
                  <input type='hidden' name='passHash' value='${password}'>
                  <input type='hidden' name='username' value='${username}'>
                  <div class='row mb-3'>
                     <div class='col-12'>
                        <label class='m-0 text-muted'>Current or Old Password</label>
                        <input type='password' name='oldPassword' class='form-control' autofocus="true">
                     </div>
                  </div>

                  <div class='row mb-4'>
                     <div class='col-6'>
                        <label class='m-0 text-muted'>New Password</label>
                        <input type='password' name='newPassword' class='form-control'>
                     </div>
                     <div class='col-6'>
                        <label class='m-0 text-muted'>Confirm New Password</label>
                        <input type='password' name='rePassword' class='form-control'>
                     </div>
                  </div>

                  <button type='button' onclick='return doChangePassword(this)' class='btn btn-dark d-block mx-auto' style='width: 60%'>
                     <i class='fa fa-shield'></i> Update New Password
                  </button>

               </form>
            </div>
         </div>
         <!--End Of Password Changing form-->

      </div>
      <!--end of col-->

      <div class='col-sm-12'>
         <p class='p-0 m-0 mt-5 text-light text-right'>
            Please contact <b>@suchitaj</b>
         </p>
      </div>
   </div>
   <!--end of row-->
</div>

<jsp:include flush="false" page="./1.jsp"/>