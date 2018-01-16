/*!
 * @version  : 1.0
 * @date     : Jan 12, 2018, 12:46:25 AM
 * @licensed : Private
 * @author   : SKR
 */

//-----------------------------------------------

function doChangePassword(THIS) {
   var form = document.formChangePassword;

   var oldPass = form.oldPassword;
   if (oldPass.value.length < 5 || oldPass.value.length > 30) {
      eNote("Old password length should be 5 to 30 char long..");
      oldPass.focus();
      return false;
   }

   var newPass = form.newPassword;
   if (newPass.value.length < 5 || newPass.value.length > 30) {
      eNote("New password length should be 5 to 30 char long..");
      newPass.focus();
      return false;
   }

   var reNewPass = form.rePassword;
   if (reNewPass.value !== newPass.value) {
      eNote("New password and Confirm password are not matching..");
      reNewPass.focus();
      return false;
   }

   md5(newPass);
   md5(reNewPass);
   md5(oldPass);

   if (form.passHash.value !== oldPass.value) {
      eNote("Current or old password was incorrect..");
      oldPass.focus();

      newPass.value = "";
      reNewPass.value = "";
      oldPass.value = "";

      return false;
   }




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
         newPass.value = "";
         reNewPass.value = "";
         oldPass.value = "";
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Password has changed. Please logout and login again.');
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