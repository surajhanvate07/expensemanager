$(function() {

	const $loginForm = $("#loginForm");

	if($loginForm.length) {
		$loginForm.validate({
			rules: {
				username:{
					required: true
				},
				password:{
					required: true
				}
			},
			messages: {
				username: {
					required: 'Please enter the email'
				},
				password: {
					required : 'Please enter the password'
				}
			},
			errorElement:'em',
			errorPlacement: function(error, element) {
				error.addClass('help-block');
				error.insertAfter(element);
			}
		})
	}
})
