$(document).ready(function(){
				
				// user1 목록1
				$('.user1.list1').click(function(){
					$.ajax({
						url:'/Ch09/user1',
						method:'GET',
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				
				$('.user1.list2').click(function(){
					
					let uid = 'j101';
					
					$.ajax({
						url:'/Ch09/user1/'+uid,
						method:'GET',
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				
				$('.user1.register').click(function(){
					
					let jsonData = {
							"uid":"a101",
							"name":"김하나",
							"hp":"010-1234-1101",
							"age":19,
					};
					
					$.ajax({
						url:'/Ch09/user1/',
						method:'POST',
						data: jsonData,
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				$('.user1.modify').click(function(){
					let jsonData = {
							"uid":"a101",
							"name":"김하나",
							"hp":"010-1234-1222",
							"age":29,
					};
					
					$.ajax({
						url:'/Ch09/user1/',
						method:'PUT',
						data: jsonData,
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				$('.user1.delete').click(function(){
					let uid = 'A102';
					
					$.ajax({
						url:'/Ch09/user1/'+uid,
						method:'DELETE',
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				
				
});