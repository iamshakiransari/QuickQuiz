<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty username}">
   <jsp:forward page="index.jsp"/>
</c:if>

<jsp:include flush="false" page="./0.jsp"/>
<script src="${stuffs}js/entry-point.js"></script>

<!--start of container-->
<div class='container'>
   <div class='row my-4'>
      <div class='col-lg-6 col-md-8 col-sm-10 d-block mx-auto'>

         <!--Sign in form-->
         <div class="card" id='idDivSigninForm'>
            <div class="card-header">
               <h5 class="mb-0"><i class='fa fa-sign-in'></i> Log in</h5>
            </div>
            <div class="card-body">
               <form name='formSignIn' action="<c:url value="/EntryPage"/>" method="post" class='m-0'>
                  <input type='hidden' name='entryType' value='login'>
                  <input type='hidden' name='afterLogin' value='<c:url value="/index.jsp" />'>

                  <label class='m-0 text-muted'>Amazon&CloseCurlyQuote;s username</label>
                  <input type='text' name='username' class='form-control mb-3' autofocus="true">

                  <label class='m-0 text-muted'>Password</label>
                  <input type='password' name='password' class='form-control mb-3'>

                  <button type='button' onclick='return doLogin(this)' class='btn btn-dark d-block mx-auto mb-3' style='width: 60%'>
                     <i class='fa fa-lg fa-sign-in'></i> Log in
                  </button>

                  <hr class="m-2">

                  <div class='m-0 p-0 text-right'>
                     <a href='#' onclick='return showRegForm();'>Registration</a>
                     &nbsp; / &nbsp;
                     <a href='#' onclick='return needHelp()'>Need help?</a>
                  </div>
               </form>
            </div>
         </div>
         <!--end of Sign in card-->

         <!--Registration form-->
         <div class="card" id='idDivRegistrationForm' style='display: none'>
            <div class="card-header">
               <h5 class="mb-0"><i class='fa fa-sign-in'></i> Registration</h5>
            </div>
            <div class="card-body">
               <form name='formRegistration' action="<c:url value="/EntryPage"/>" method="post" class='m-0'>
                  <input type='hidden' name='entryType' value='registration'>

                  <div class='row mb-3'>
                     <div class='col-12'>
                        <label class='m-0 text-muted'>Amazon&CloseCurlyQuote;s username</label>
                        <input type='text' name='username' class='form-control' autofocus="true">
                     </div>
                  </div>

                  <div class='row mb-3'>
                     <div class='col-6'>
                        <label class='m-0 text-muted'>Password</label>
                        <input type='password' name='password' class='form-control'>
                     </div>
                     <div class='col-6'>
                        <label class='m-0 text-muted'>Confirm Password</label>
                        <input type='password' name='rePassword' class='form-control'>
                     </div>
                  </div>

                  <!--                  <div class='row mb-3'>
                                       <div class='col-12'>
                                          <label class='m-0 text-muted'>Your team</label>
                                          <select name='team' class='form-control'>
                                             <option value='1'>PSS US</option>
                                          </select>
                                       </div>
                                    </div>-->

                  <button type='button' onclick='return doRegistration(this)' class='btn btn-dark d-block mx-auto mb-3' style='width: 60%'>
                     <i class='fa fa-lg fa-sign-in'></i> Register
                  </button>

                  <hr class="m-2">

                  <div class='m-0 p-0 text-right'>
                     <a href='#' onclick='return showSigForm();'>Sign in</a>&nbsp; / &nbsp;
                     <a href='#' onclick='return needHelp()'>Need help?</a>
                  </div>
               </form>
            </div>
         </div>
         <!--end of Registration form-->


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
<!--end of container-->

<jsp:include flush="false" page="./1.jsp"/>