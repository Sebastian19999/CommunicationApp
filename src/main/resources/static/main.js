/**
 * 
 */

$('document').ready(function(){	
	$('.table .btn').on('click',function(event){	
		
		event.preventDefault();
		
		var href= $(this).attr('href');
		
		$.get(href, function(client, status){
			$('#idEdit').val(client.id);
			$('#nameEdit').val(client.name);
			$('#addressEdit').val(client.address);
			$('#phoneEdit').val(client.phone);
			$('#balanceEdit').val(client.balance);
		});
		
		$('#editModal').modal();				
	});	
	
	
	
	
	
	
	
	
	$('table #deleteButton').on('click',function(event){
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();
	});
});