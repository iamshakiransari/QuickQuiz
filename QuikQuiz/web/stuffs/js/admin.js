/*!
 * @version  : 1.0.1
 * @date     : Jan 12, 2018, 3:20:42 PM
 * @licensed : Private
 * @author   : SKR
 */


$().ready(function () {
   //------------------
   $(document.formAddNewQuizAdmin.lastDate).onlyFutureDates();

   //$('#inputLastDate').datepicker({minDate: new Date(),maxDate: "+30D",dateFormat: 'd MM, y'});

});

//-- RESET PASSWORD SCRIPT -------------------------------------------------------------------------------------------------------
var resetPasswordURL = $('#urlForResetPassword').val();
function loadUserData() {
   $.ajax({
      url: resetPasswordURL,
      type: 'get',

      beforeSend: function (data) {
      },
      complete: function (cD) {
      },
      success: function (data) {
         $('#divCardBodyResetPassword').html(data);
      },
      error: function (xhr, ajaxOptions, thrownError) {
         eNote("Ajax Failed:<br>Status: " + xhr.status + "<br>Error: " + thrownError + "<br>Message: " + xhr.responseText);
      }
   });
   return false;
}
function resetThisUser(THIS, user) {

   if (!confirm('Are you sure??')) {
      return false;
   }

   $.ajax({
      url: resetPasswordURL,
      type: 'post',
      data: {username: user},
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
      },
      success: function (data) {
         if (data === 'DONE') {
            $('#idTableRowReset' + user).fadeOut(333);
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

//-- NEW QUIZ SCRIPT -------------------------------------------------------------------------------------------------------------
function addNewQuiz(THIS) {
   var form = document.formAddNewQuizAdmin;

   var quizTitle = form.quizTitle.value;
   if (quizTitle.length < 5 || quizTitle.length > 100) {
      form.quizTitle.focus();
      eNote("Quiz title should be 5 to 100 char long");
      return false;
   }

   var level = form.level;
   if (level.value < 1 || level.value > 4) {
      eNote("Please select a valid quiz level");
      level.focus();
      return false;
   }

//   var attempts = form.attempts;
//   if (attempts.value.length < 1 || attempts.value.length > 4) {
//      eNote("Please enter a valid attempt number");
//      attempts.focus();
//      return false;
//   }
//
   var lastDate = form.lastDate;
   if (lastDate.value === '') {
      eNote("Please enter a valid quiz end date");
      lastDate.focus();
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
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Your quiz has been added. Please add some quiestions into.');
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

//-- EDIT Quiz Details AND Questions SCRIPT --------------------------------------------------------------------------------------------------------
//var UrlEditAndViewQuizDetails = $('#idInputUrlEditAndViewQuizDetails').val();

function updateQuizDetails(THIS) {

   var form = document.formUpdateQuizDetails;

   var quizTitle = form.quizTitle.value;
   if (quizTitle.length < 5 || quizTitle.length > 100) {
      form.quizTitle.focus();
      eNote("Quiz title should be 5 to 100 char long");
      return false;
   }

   var level = form.level;
   if (level.value < 1 || level.value > 4) {
      eNote("Please select a valid quiz level");
      level.focus();
      return false;
   }

   var lastDate = form.lastDate;
   if (lastDate.value === '') {
      eNote("Please enter a valid quiz end date");
      lastDate.focus();
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
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Your quiz has been updated.');
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
function deleteQuiz(THIS, url, quizNo) {
   if (!confirm('Are you sure??')) {
      return false;
   }

   $.ajax({
      url: url,
      type: 'post',
      data: {quizNo: quizNo},
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Your quiz has been DELETED.');
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

function addNewQuestion(THIS) {
   var form = document.formAddNewQuestion;

   if (form.question.value.trim().length < 1) {
      form.question.focus();
      eNote("Question cannot be empty!!!");
      return false;
   }

   if (form.opt1.value.trim().length < 1) {
      form.opt1.focus();
      eNote("Option 1 cannot be empty!!!");
      return false;
   }
   if (form.opt2.value.trim().length < 1) {
      form.opt2.focus();
      eNote("Option 2 cannot be empty!!!");
      return false;
   }
   if (form.opt3.value.trim().length < 1) {
      form.opt3.focus();
      eNote("Option 3 cannot be empty!!!");
      return false;
   }
   if (form.opt4.value.trim().length < 1) {
      form.opt4.focus();
      eNote("Option 4 cannot be empty!!!");
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
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Your question has been added.');
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
function saveQuestion(THIS, elemID) {

   var form = document.getElementById("formQuestion" + elemID);

   var question = $(form.question);
   if (question.val().trim().length < 1) {
      question.focus();
      eNote("Question cannot be empty!!!");
      return false;
   }

   var opt1 = $(form.opt1);
   if (opt1.val().trim().length < 1) {
      opt1.focus();
      eNote("Option 1 cannot be empty!!!");
      return false;
   }

   var opt2 = $(form.opt2);
   if (opt2.val().trim().length < 1) {
      opt2.focus();
      eNote("Option 2 cannot be empty!!!");
      return false;
   }

   var opt3 = $(form.opt3);
   if (opt3.val().trim().length < 1) {
      opt3.focus();
      eNote("Option 3 cannot be empty!!!");
      return false;
   }

   var opt4 = $(form.opt4);
   if (opt4.val().trim().length < 1) {
      opt4.focus();
      eNote("Option 4 cannot be empty!!!");
      return false;
   }

   $form = $(form);
   $.ajax({
      url: $form.attr('action'),
      type: 'post',
      data: $form.serialize(),
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Your question has been Updated.');
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
function deleteQuestion(THIS, url, questionID) {
   if (!confirm('Are you sure??')) {
      return false;
   }

   $.ajax({
      url: url,
      type: 'post',
      data: {questionID: questionID},
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('Your question has been DELETED.');
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

//-- RESET USER ATTEMPT ----------------------------------------------------------------------------------------------------------
function resetUserAtempt(THIS) {
   var form = document.formResetQuizAttempt;

   if (form.quizNo.value === '0') {
      eNote("Please select a quiz");
      form.quizNo.focus();
      return false;
   }

   if (form.user.value === '') {
      eNote("username cannot be empty");
      form.user.focus();
      return false;
   }

   if (!confirm('Are you sure??')) {
      return false;
   }

   var $form = $(form);
   $.ajax({
      url: $form.attr('action'),
      type: 'post',
      data: $form.serialize(),
      beforeSend: function (data) {
         disableButton(THIS);
         hideNote();
      },
      complete: function (cD) {
         enableButton(THIS);
      },
      success: function (data) {
         if (data === 'DONE') {
            iNote('User attempt has been reset.');
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
