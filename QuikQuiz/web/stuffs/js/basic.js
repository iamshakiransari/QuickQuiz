/*!
 * @version  : 1.0
 * @date     : Jan 11, 2018, 1:21:26 PM
 * @licensed : Private
 * @author   : SKR
 */

//-----------------------------------------------
var usernameReg = new RegExp(/^[A-Za-z]{3,100}$/);
//-----------------------------------------------
$().ready(function () {
   $(document).on('click', '.card-header.tog', function () {
      $(this).next('.card-body').toggle("slow");
   });
});
//================================================================================================================================
$.fn.readonly = function () {
   return this.each(function () {
      //$(this).prop("readonly", true);
      $(this).focusin(function () {
         $(this).prop("readonly", true);
      }).focusout(function () {
         $(this).prop("readonly", false);
      });
   });
};
$.fn.onlyPastDates = function () {
   return this.each(function () {
      var today = document.getElementById('serverDate').value;

      $(this).readonly();
      $(this).datepicker({
         maxDate: new Date(today),
         dateFormat: 'MM dd, yy',
         showAnim: 'slideDown'
      }).datepicker("setDate", "" + today);
   });
};
$.fn.onlyFutureDates = function () {
   return this.each(function () {
      var today = document.getElementById('serverDate').value;

      $(this).readonly();
      $(this).datepicker({
         minDate: new Date(),
         dateFormat: 'MM dd, yy',
         showAnim: 'slideDown'
      }).datepicker();
   });
};
//================================================================================================================================
function disableThis(obj) {
   $(obj).prop("disabled", true);
}
function enableThis(obj) {
   $(obj).prop("disabled", false);
}
function disableButton(obj) {
   var buttonValue = $(obj).val();
   disableThis(obj);
   $(obj).addClass("disabled").val("Wait..");
   return buttonValue;
}
function enableButton(obj) {
   $(obj).removeClass("disabled");
   enableThis(obj);
}
//================================================================================================================================
function md5(obj) {
   obj.value = calcMD5(obj.value);
}
//================================================================================================================================
