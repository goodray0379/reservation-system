

document.addEventListener("DOMContentLoaded", function() {
	
	var emailInfo = {
		form : document.querySelector(".ng-pristine"),
		email : document.querySelector(".login_input"),
		regExpEmail : /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i,
		submit: function(){
			this.form.addEventListener("submit", function(evt){
				var isValid = this.checkEmail();
				if(!isValid){
					alert("이메일 형식이 잘못됐습니다.");
					evt.preventDefault();
				}
			}.bind(this))
		},
		checkEmail: function(){
			if(this.regExpEmail.exec(this.email.value)==null)
				return false;
			else
				return true;
		}	
	}
	
	emailInfo.submit();
	
	
})
