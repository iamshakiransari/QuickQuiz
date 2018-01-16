/*!
 * @version  : 1.0
 * @date     : Jan 11, 2018, 1:23:15 PM
 * @licensed : Private
 * @author   : SKR
 */

//-----------------------------------------------

function needHelp() {
   eNote('Please contact <i>@suchitraj</i> for any help or assistance !!!');
   return false;
}
function showRegForm() {
   $('#idDivSigninForm').hide(1);
   $('#idDivRegistrationForm').show(1);
   return false;
}
function showSigForm() {
   $('#idDivRegistrationForm').hide(1);
   $('#idDivSigninForm').show(1);
   return false;
}

function doLogin(THIS) {
   var form = document.formSignIn;

   var username = form.username.value;
   if (username.length < 3 || username.length > 100) {
      form.username.focus();
      eNote("Username should be 3 to 100 char long");
      return false;
   }
   if (username.match(usernameReg) === null) {
      form.username.focus();
      eNote("Please enter valid username..");
      return false;
   }

   var pass = form.password;
   if (pass.value.length < 5 || pass.value.length > 30) {
      eNote("Password length should be 5 to 30 char long");
      pass.focus();
      return false;
   }
   md5(pass);

   var $form = $(form);
   $.ajax({
      url: $form.attr('action'),
      type: $form.attr('method'),
      data: $form.serialize(),
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
         pass.value = "";
      },
      success: function (data) {
         if (data === 'DONE') {
            window.location.replace('' + form.afterLogin.value);
         } else {
            eNote('Error: ' + data);
         }
      },
      error: function (xhr, ajaxOptions, thrownError) {
        eNote("Ajax Failed:<br>Status: " + xhr.status + "<br>Error: " + thrownError + "<br>Message: " + xhr.responseText);
      }
   });
   return false;
}

function doRegistration(THIS) {
   var form = document.formRegistration;

   var username = form.username.value;

   if (username.length < 3 || username.length > 100) {
      form.username.focus();
      eNote("Username should be 3 to 100 char long");
      return false;
   }

   if (username.match(usernameReg) === null) {
      form.username.focus();
      eNote("Please enter valid username..");
      return false;
   }

   var pass = form.password;
   var rePass = form.rePassword;

   if (pass.value.length < 5 || pass.value.length > 30) {
      eNote("Password length should be 5 to 30 char long");
      pass.focus();
      return false;
   }

   if (pass.value !== rePass.value) {
      rePass.focus();
      eNote("Password and repeated password should be same");
      return false;
   }

   md5(pass);
   md5(rePass);

   var $form = $(form);
   $.ajax({
      url: $form.attr('action'),
      type: $form.attr('method'),
      data: $form.serialize(),
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
         pass.value = "";
         rePass.value = "";
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Registration has been completed. Please do login');
         } else {
            eNote(data);
         }
      },
      error: function (xhr, ajaxOptions, thrownError) {
         eNote("Ajax Failed:<br>Status: " + xhr.status + "<br>Error: " + thrownError + "<br>Message: " + xhr.responseText);
      }
   });

   return false;
}
