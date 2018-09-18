/**
 * joinForm.jsp 검증(jquery validation plugin 사용)
 */
$(function() {
	$("#joinform").validate({
		errorPlacement:function(error,element){
			$(element).closest("form")
			.find("small[id='"+element.attr("id")+"']")
			.append(error);
		},
		rules:{
			userid:{
				required:true,
				validId:true,
				remote:{
					url:"joinProcess2.jsp",
					type:"post",
					data:{
						userid:function(){
							return $("#userid").val();
						}
					}
				}
			},
			password:{
				required:true,
				validPwd:true,
				validPwdLetters:true
			},
			confirm_password:{
				required:true,
				validPwd:true,
				validPwdLetters:true,
				equalTo:"#password"
			},
			gender:{
				required:true,
				minlength:1
			},
			email:{
				required:true,
				validEmail:true
			},
			name:{
				required:true,
				minlength:2
			}
			
		},
		messages:{
			userid:{
				required:"아이디는 필수 입력사항입니다",
				remote:"아이디가 중복되었습니다."

			},
			password:{
				required:"비밀번호는 필수 입력사항입니다",

			},
			confirm_password:{
				required:"비밀번호 재입력은 필수 입력사항입니다",
				equalTo:"이전 비밀번호랑 다르잖아 흑두루말이야"
			},
			gender:{
				required:"성별은 필수 입력사항입니다",

			},
			email:{
				required:"이메일은 필수 입력사항입니다",

			},
			name:{
				required:"이름은 필수 입력사항입니다",
				minlength:"이름은 최소 2자"
			}
			

		}
	});
});
$.validator.addMethod("validId",function(value){
	var regId=/^[a-zA-Z0-9]{5,12}$/;
	return regId.test(value);
},"필수 영문자 숫자포함 5~12자"
);
$.validator.addMethod("validPwd",function(value){
	var regPwd=/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
	return regPwd.test(value);
},"비밀번호는 숫자, 영대소문자, 특수문자 조합으로 8~15자리를 사용해야 합니다"
);
$.validator.addMethod("validPwdLetters",function(value){
	var regPwd=/(\w)\1\1\1/;
	return !regPwd.test(value);
},"동일한 숫자와 문자를 연속으로 사용할수 없습니다."
);
$.validator.addMethod("validEmail",function(value){
	var regEmail=/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return regEmail.test(value);
},'이메일 형식을 확인해주세요');
$.validator.addMethod("validTel",function(value){
	var regTel=/^\d{3}\d{3,4}\d{4}$/;
	return regTel.test(value);
},'-를 제외한 번호만 입력해주세요');