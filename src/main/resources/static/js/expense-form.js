let validNumber = new RegExp(/^(?!0)\d*\.?\d{0,2}$/);
let amountValid = $("#amount").val();
const validateNumber = function(elem) {
	if(validNumber.test(elem.value)) {
		amountValid = elem.value;
	} else {
		elem.value = amountValid;
	}
}
$(function() {
	$('#createdAt').datepicker({
		dateFormat: "dd/mm/yy",
		changeMonth: true,
		changeYear: true,
		maxDate: new Date()
	});
})

const $expenseForm = $("#expenseForm");

if($expenseForm.length) {
	$expenseForm.validate({
		rules: {
			name:{
				required: true,
				minlength: 3
			},
			amount:{
				required: true
			},
			dateString:{
				required: true
			}
		},
		messages: {
			name: {
				required: 'Please enter the expense name',
				minlength: 'Expense name should be minimum 3 characters'
			},
			amount: {
				required: 'Please enter the amount'
			},
			dateString: {
				required: 'Please enter the date'
			}
		}
	})
}