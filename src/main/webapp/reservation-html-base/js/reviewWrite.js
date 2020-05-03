

document.addEventListener("DOMContentLoaded", function() {
	
	//전역변수 구간
	var score = 0;
	var count = document.querySelector(".guide_review").querySelector("span"); //글자 수
	
	//공통 변수,로직 싱글턴패턴
	var parameter = {
		
		email : "",
		//url의 파라매터 email값 받는 함수
		getParameter : function(){
			frmUrl = window.location.href;
			cut = frmUrl.indexOf("=");
			this.email = frmUrl.substring(cut + 1);
			this.email = this.email.replace("#","");
			document.querySelector(".btn_back").setAttribute("href",
					"/myreservation?reservationEmail=" + this.email);
		}
	}
	parameter.getParameter();
	
	
	//별점 기능 
	function SetScore(star) {
		this.starButton = star.getElementsByTagName("input");
		this.clickStar();
    }
	
	SetScore.prototype = {
		clickStar : function(){
			for(var i=0; i<this.starButton.length; i++){
				this.starButton[i].addEventListener("click", function(evt){
					var target = evt.target;
					for(var j=0; j<parseInt(target.value); j++){
						this.starButton[j].className = "rating_rdo checked";
					}
					for(var j=this.starButton.length-1; j>=parseInt(target.value); j--){
						this.starButton[j].className = "rating_rdo";
					}
					score = parseInt(target.value);
					var starLank = star.querySelector(".star_rank");
					starLank.innerText = score;
					starLank.className = "star_rank";
				}.bind(this))
			}
		}
	}
	
	var star = document.querySelector(".rating");
	var setScore = new SetScore(star);
	
	
	//리뷰 설정
	function WriteReview(reviewArea){
		this.reviewArea = reviewArea;
		this.clickReviewArea();
		this.writeReview();
	}
	
	
	WriteReview.prototype = {
		clickReviewArea : function(){
			var a = this.reviewArea.querySelector(".review_write_info");
			var textarea = this.reviewArea.querySelector(".review_textarea");

			a.addEventListener("click", function(){
				a.style.display = "none";
				textarea.focus();
			});

			textarea.addEventListener("blur", function(){
				if(textarea.value==0){
					a.style.display = "block";
				}
			});
		},
	
		writeReview : function(){
			
			var textarea = this.reviewArea.querySelector(".review_textarea");
			
			//실시간 글자 수 세기
			textarea.addEventListener('input', function() {
				count.innerText = textarea.value.length;
			});
		}
	}
	
	var reviewArea = document.querySelector(".review_contents" +".write");
	var writeReview = new WriteReview(reviewArea);
	
	//사진 등록
	function UploadPhoto(){
		this.item = document.querySelector(".item");
		this.elImage = document.querySelector("#reviewImageFileOpenInput");
		this.deleteButton = document.querySelector(".spr_book");
		this.findPhoto();
	}
	
	UploadPhoto.prototype = {
			
		findPhoto : function(){
			this.elImage.addEventListener("change", function(evt){
				//탐색기에서 선택한 사진 파일 검사
				const image = evt.target.files[0];
				if(!this.validImageType(image)) { 
					console.warn("invalide image file type");
					return;
				}
				//썸네일 표시
				const elImage = document.querySelector(".item_thumb");
	            elImage.src = window.URL.createObjectURL(image);
	            this.item.style.display = "inline-block";
	            //삭제 이벤트 발동
	            this.deleteImage();
			}.bind(this))
		},
		
		validImageType : function(image){
			const result = ([ 'image/jpeg',
				  'image/png',
				  'image/jpg' ].indexOf(image.type) > -1);
			return result;
		},
		
		deleteImage : function(){
			this.deleteButton.addEventListener("click", function(){
				this.item.style.display = "none";
			}.bind(this))
		}
	
	}
	
	var uploadPhto = new UploadPhoto();
	
	//리뷰 등록
	function SendReview(){
		this.button = document.querySelector(".bk_btn");
		this.send();
	}
	
	SendReview.prototype = {
		send : function(){
			this.button.addEventListener("click", function(){
				if(score>0 && parseInt(count.innerText)>=5){
					alert("리뷰가 등록됐습니다.(실제 등록 x)");
				}else if(score===0 && parseInt(count.innerText)>=5){
					alert("점수를 골라주세요.");
				}else{
					alert("리뷰를 5글자 이상 남겨주세요.")
				}
			}.bind(this))
		}
	}
	
	var sendReview = new SendReview();
	
})
