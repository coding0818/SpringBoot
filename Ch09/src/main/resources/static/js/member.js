$(document).ready(function(){
	// member
				$('.member.list1').click(function(){
					$.ajax({
						url:'/Ch09/member',
						method:'GET',
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				
				$('.member.list2').click(function(){
					
					let uid = 'a101';
					
					$.ajax({
						url:'/member/'+uid,
						method:'GET',
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				
				$('.member.register').click(function(){
					
					let jsonData = {
							"uid":"a111",
							"name":"김하나",
							"hp":"010-1234-1222",
							"pos":"사원",
							"dep":106
					};
					
					$.ajax({
						url:'/Ch09/member/',
						method:'POST',
						data: jsonData,
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				$('.member.modify').click(function(){
					let jsonData = {
							"uid":"a111",
							"name":"김하나",
							"hp":"010-1234-1222",
							"pos":"대리",
							"dep":107
					};
					
					$.ajax({
						url:'/Ch09/member/',
						method:'PUT',
						data: jsonData,
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
				$('.member.delete').click(function(){
					let uid = 'a102';
					
					$.ajax({
						url:'/Ch09/member/'+uid,
						method:'DELETE',
						dataType:'json',
						success:function(data){
							console.log(data);
						}
					});
				});
});