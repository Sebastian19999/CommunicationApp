/**
 * 
 */

$('document').ready(function(){	
	$('.table .btn').on('click',function(event){	
		
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		console.log("ajuns in edit_student")
		
		$.get(href, function(student, status){
			$('#idEdit').val(student.id_student);
			$('#nameEdit').val(student.name_student);
			$('#ageEdit').val(student.age);
			$('#addressEdit').val(student.address);
			$('#majorEdit').val(student.major);
			$('#yearEdit').val(student.year);
			$('#phoneEdit').val(student.student_phone);
			$('#balanceEdit').val(student.balance);
		});
		
		$('#editSModal').modal();				
	});	
	
	
	
	
	
	
	
	
	$('table #deleteButton').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();
	});
});