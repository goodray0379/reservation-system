
document.addEventListener("DOMContentLoaded", function() {
	
	var data;
	var id; //display id
	var productId;
	
	//페이지 초기화(상품사진,정보 설정)
	function Init() {
		 this.productUrl = "/api/products/";
		 this.getParameter();
         this.productAjax(this.productUrl);
     }
		 
	 Init.prototype = {
			 
		getParameter : function() {
			frmUrl = window.location.href;
			cut = frmUrl.indexOf("=");
			id = parseInt(frmUrl.substring(cut + 1));
			document.querySelector(".btn_back").setAttribute("href",
					"/detail?id=" + id);
		},
		
		productAjax : function(url) {
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function() {
				var data = JSON.parse(oReq.responseText);
				this.makeProductTemplate(data);
			}.bind(this));
			oReq.open("GET", url+id);
			oReq.send();
		},
		
		makeProductTemplate : function(data) {
			document.querySelector(".title").innerText = data.product.description;
			if(data.productImages.length===2){
				document.querySelector(".img_thumb").src = data.productImages[1].saveFileName;
			}else{
				document.querySelector(".img_thumb").src = data.productImages[0].saveFileName;
			}
            document.querySelector(".dsc").innerText += "장소 : "+ data.product.placeName;
            
			//날짜 세팅
			var today = new Date();
			var month = today.getUTCMonth() + 1; //months from 1-12
			var day = today.getUTCDate();
			var year = today.getUTCFullYear();
			today = year + "." + month + "." + day;
			var date = document.querySelector(".inline_txt"+ ".selected").innerHTML;
			document.querySelector(".inline_txt"+ ".selected").innerHTML = date.replace("date", today);
        },
		
	}

	var init = new Init();

	 
	//티켓 관련
	function SelectTicket() {
		
		this.priceUrl = "/api/products/prices/";
		this.priceData = []; //정규표현식 가격과 정보들이 담겨있음
		this.priceReg = /(^[+-]?\d+)(\d{3})/;
        this.priceAjax(this.priceUrl);
		
	}
	
	SelectTicket.prototype = {
			
		priceAjax : function(url) {
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function() {
				var data = JSON.parse(oReq.responseText);
				this.setPriceData(data);
				this.makeTicketTemplate();
				this.countEvents();
			}.bind(this));
			oReq.open("GET", url + id);
			oReq.send();
			
		},
		
		setPriceData : function(data){
			for(var i=0; i<data.price.length; i++){
				var temp;
				
				/*성인(M), 청소년(Y), 유아(C), 장애인(I), 지역주민(L)
				  VIP(V), R석(R), A석(A), B석(B), S석(S), 평일(D), 어얼리버드(E)*/
				switch (data.price[i].priceTypeName) {
					case "M": 
						temp = {"type" : "성인"};
						break;
					case "Y": 
						temp = {"type" : "청소년"};
						break;
					case "C": 
						temp = {"type" : "유아"};
						break;
					case "I": 
						temp = {"type" : "장애인"};
						break;
					case "L": 
						temp = {"type" : "지역주민"};
						break;
					case "V": 
						temp = {"type" : "VIP석"};
						break;
					case "R": 
						temp = {"type" : "R석"};
						break;
					case "S": 
						temp = {"type" : "S석"};
						break;
					case "A": 
						temp = {"type" : "A석"};
						break;
					case "B": 
						temp = {"type" : "B석"};
						break;
					case "D": 
						temp = {"type" : "평일"};
						break;
					case "E": 
						temp = {"type" : "얼리버드"};
						break;
				 	default    : 
				 		console.log('해당 숫자가 없습니다.<br />');
				 		break;
				};
				
				//상품 가격 정규표현식으로 쉽표(",") 구분
				temp["productPriceId"] = data.price[i].id;
				temp["price"] = data.price[i].price;
				temp["discountRate"] = data.price[i].discountRate;
				temp["count"] = 0;
				temp["regExpPrice"] = this.regExpPrice(data.price[i].price);
				this.priceData.push(temp);
			}
		},
		
		//상품 가격 정규표현식으로 쉽표(",") 구분하는 함수
		regExpPrice : function(price){
			while (this.priceReg.test(price)) {
				price +="";
				price = price.replace(this.priceReg, '$1' + ',' + '$2');
	        }
			return price;
		},
        
        makeTicketTemplate : function(){
        	for(var i=0; i<this.priceData.length; i++){
        		
        		var price = this.priceData[i].regExpPrice;
        		//간략한 요금 정보
        		document.querySelector("#tit").innerHTML += this.priceData[i].type + " : " + price + "원";
        		if(this.priceData[i].discountRate===0.0){
        			document.querySelector("#tit").innerHTML += "<br>";
        		}else{
        			document.querySelector("#tit").innerHTML += " / " + this.priceData[i].discountRate + "% 할인가" + "<br>";
        		}
        		
        		//티켓 수 선택 UI 템플릿
        		var template = document.querySelector("#inIntroTemplate").innerText;
        		var bindTemplate = Handlebars.compile(template);
        		var resultHTML = bindTemplate(this.priceData[i]);
        		resultHTML = resultHTML.replace("countId", "c"+i);
        		var show = document.querySelector(".ticket_body");
        		show.innerHTML += resultHTML.replace("(0% 할인가)", "");

        	}
        
        },
		
        
        //티켓 개수 선택
		countEvents : function() {
			var ticket = document.querySelectorAll(".count_control");
			for(var i=0; i<ticket.length; i++){
				ticket[i].addEventListener("click", function(evt){
					if(evt.target.tagName==="A"){
						
						var target = evt.target;	
						var clearfix = target.parentElement;
						var targetCount = clearfix.getElementsByTagName("input")[0];
						var totalCount = document.querySelector("#totalCount");
						var countControl = clearfix.parentElement;
						var index = parseInt(countControl.id[1]);
						
						if(target.title==="빼기"){
							if(targetCount.value != 0){
								
								targetCount.value = parseInt(targetCount.value)-1;
								var totalPrice = this.priceData[index].price*parseInt(targetCount.value);
								countControl.getElementsByClassName("total_price")[0].innerText= this.regExpPrice(totalPrice);
								
								if(targetCount.value==0){
									clearfix.querySelectorAll(".btn_plus_minus")[0].className += " disabled";
									clearfix.querySelector(".count_control_input").className += " disabled";
									countControl.querySelector(".individual_price").className += "individual_price";
								}else if(targetCount.value==9){
									clearfix.querySelectorAll(".btn_plus_minus")[1].className = "btn_plus_minus spr_book2 ico_plus3"; 
								}
								totalCount.innerText = parseInt(totalCount.innerText) - 1;
								
							}
						}else{
							if(targetCount.value != 10){
								
								targetCount.value = parseInt(targetCount.value)+1;
								var totalPrice = this.priceData[index].price*parseInt(targetCount.value);
								countControl.getElementsByClassName("total_price")[0].innerText= this.regExpPrice(totalPrice);
								
								if (targetCount.value == 10) {
									clearfix.querySelectorAll(".btn_plus_minus")[1].className += " disabled";
								}else if(targetCount.value==1){
									clearfix.querySelectorAll(".btn_plus_minus")[0].className = "btn_plus_minus spr_book2 ico_minus3";
									clearfix.querySelector(".count_control_input").className += "count_control_input";
									countControl.querySelector(".individual_price").className += " on_color";
								}
								totalCount.innerText = parseInt(totalCount.innerText) + 1;
							}	
						}
						
						this.priceData[index].count = parseInt(targetCount.value);
					}
				}.bind(this))
			}
        }
	
	}

	var selectTicket = new SelectTicket();
	
	
	//예매자 정보 입력 관련
	function ReserveInfo(name, tel, email, button){
		this.regExpTel = /01[0789]-\d{4}-\d{4}/; 
		this.regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.button = button;
		this.postData;
		this.submit();
	}
	
	ReserveInfo.prototype = {
		
		setData : function(){
			var data ={};
			var priceData = selectTicket.priceData;
			data.productId = id;
			data.reservationName = this.name.value;
			data.reservationTel = this.tel.value;
			data.reservationEmail = this.email.value;
			data.reservationYearMonthDay = document.querySelector(".inline_txt"+".selected").innerText.substring(0,10);
			data.prices = [];
			for(var i=0; i<priceData.length; i++){
				var temp = {};
				temp.productPriceId = priceData[i].productPriceId;
				temp.count = priceData[i].count;
				data.prices.push(temp);
			}
			this.postData = data;
		},
		
		reserveAjax : function() {
			
			var oReq = new XMLHttpRequest();
			oReq.open("POST", "/api/reservations/");
			oReq.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
			oReq.send(JSON.stringify(reserveInfo.postData));
			
		},
			
		submit : function(){
			this.button.addEventListener("click", function(){
				var isValid = this.checkInfo(this.checkTel(), this.checkEmail());
				if(isValid){
					this.setData();
					this.reserveAjax();
					window.location.href = '/';
					alert("예약되었습니다.");
				}
			}.bind(this))
		},
	
		checkTel : function(){
			if(this.regExpTel.exec(this.tel.value)==null)
				return false;
			else
				return true;
		},
		
		checkEmail : function(){
			if(this.regExpEmail.exec(this.email.value)==null)
				return false;
			else
				return true;
		},
		
		checkInfo : function(checkTel, checkEmail){
			if(document.querySelector("#totalCount").innerText>0){
				if(this.name.value.length>0){
					if(checkTel && (this.tel.value.length<14)){
						if(checkEmail){
							return true;
						}else{
							alert("이메일 형식이 잘못됐습니다.");
						}
					}else{
						alert("휴대폰 번호 형식이 잘못됐습니다.");
					}
				}else{
					alert("예매자 이름을 입력하세요.");
				}
				return false;
			}else{
				alert("표를 1장 이상 구매하셔야 합니다.")
			}
		}
		
	}
	
	var name = document.querySelector("#name");
	var tel = document.querySelector("#tel");
	var email = document.querySelector("#email");
	var button = document.querySelector(".bk_btn");
	var reserveInfo = new ReserveInfo(name, tel, email, button);
	
	
	//약관 관련, 회원정보 값 여부
	function CheckTerms(){
		this.check = false;
		this.expandCheck = [false, false];	
		this.onOffButton();
		this.expand();
	}
	
	CheckTerms.prototype ={
		onOffButton : function(){
			var checkButton = document.querySelector(".chk_agree");
			var name = document.querySelector("#name");
			var tel = document.querySelector("#tel");
			var email = document.querySelector("#email");
			var buttonWrap = document.querySelector(".bk_btn_wrap");
			var submitButton = buttonWrap.firstElementChild;
			
			name.addEventListener("input", function(){
				if(this.isValid(name, tel, email)){
					buttonWrap.className = "bk_btn_wrap";
					submitButton.disabled = false;
				}else{
					buttonWrap.className = "bk_btn_wrap disable";
					submitButton.disabled = true;
				}
			}.bind(this));
			
			tel.addEventListener("input", function(){
				if(this.isValid(name, tel, email)){
					buttonWrap.className = "bk_btn_wrap";
					submitButton.disabled = false;
				}else{
					buttonWrap.className = "bk_btn_wrap disable";
					submitButton.disabled = true;
				}
			}.bind(this));
			
			email.addEventListener("input", function(){
				if(this.isValid(name, tel, email)){
					buttonWrap.className = "bk_btn_wrap";
					submitButton.disabled = false;
				}else{
					buttonWrap.className = "bk_btn_wrap disable";
					submitButton.disabled = true;
				}
			}.bind(this));
			
			checkButton.addEventListener("click", function() {
				
				if(this.check===false){
					this.check = true;
					if(this.isValid(name, tel, email)){
						buttonWrap.className = "bk_btn_wrap";
						submitButton.disabled = false;
					}
				}else{
					this.check = false;
					buttonWrap.className = "bk_btn_wrap disable";
					submitButton.disabled = true;
				}
				
			}.bind(this));
		},
		
		isValid : function(name, tel, email){		

			if(name.value.length>0 && tel.value.length>0 && email.value.length>0 && this.check===true){
				return true;
			}else{
				return false;
			}
		},
		
		expand : function(){
			var agreement = document.querySelectorAll(".btn_agreement");
			var contents = document.querySelectorAll(".agreement.part");
			var expandButton = document.querySelectorAll('.btn_agreement');
			var i;
			
			for(var i=0; i<2; i++){
				agreement[i].addEventListener("click", function(evt) {
					if(evt.currentTarget.id === "expand1")
						i=0;
					else
						i=1;
					
					if(this.expandCheck[i] === false){
						contents[i].className += " open";
						expandButton[i].firstElementChild.innerText= "접기";
						expandButton[i].lastElementChild.className = "fn fn-up2"
						this.expandCheck[i] = true;
					}else{
						contents[i].className = "agreement";
						expandButton[i].firstElementChild.innerText= "보기";
						expandButton[i].lastElementChild.className = "fn fn-down2"
						this.expandCheck[i] = false;
					}
				}.bind(this));
			}
		}
	}
	
	var checkTerms = new CheckTerms();
	
})
