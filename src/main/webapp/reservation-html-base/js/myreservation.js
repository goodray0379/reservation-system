
document.addEventListener("DOMContentLoaded", function() {
	
	//공통 변수,로직 싱글턴패턴
	var parameter = {
		
		email : "",
		priceReg : /(^[+-]?\d+)(\d{3})/,
		//url의 파라매터 email값 받는 함수
		getParameter : function(){
			frmUrl = window.location.href;
			cut = frmUrl.indexOf("=");
			this.email = frmUrl.substring(cut + 1);
			this.email = this.email.replace("#","");
		},
	
		//정규표현식 변경
		regExpPrice : function(price){
			while (this.priceReg.test(price)) {
				price +="";
				price = price.replace(this.priceReg, '$1' + ',' + '$2');
		    }
			return price;
		}
	}
	parameter.getParameter();
	
	
	
	//요약 정보 세팅
	function SetSummary(summaryBoard) {
		 this.url = "/api/reservations/count?reservationEmail=" + parameter.email;
		 this.summaryBoard = summaryBoard;
		 this.summaryAjax(this.url);
    }
	
	SetSummary.prototype = {
			
		summaryAjax : function(url){
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function() {
				var data = JSON.parse(oReq.responseText);
				this.makeSummaryTemplate(data);
			}.bind(this));
			oReq.open("GET", url);
			oReq.send();
		},
		
		//예약이용완료에 대해선 정확한 정의가 없으니 2개로 정적인 페이지 구성
		makeSummaryTemplate : function(data){
			var li = summaryBoard.querySelectorAll("li");
			li[0].querySelector(".figure").innerText = data.reservationCount+1;
			li[1].querySelector(".figure").innerText = data.reservationCount-data.cancelCount;
			li[2].querySelector(".figure").innerText = 1;
			li[3].querySelector(".figure").innerText = data.cancelCount;
		}
	}
	var summaryBoard = document.querySelector(".summary_board");
	var summary = new SetSummary(summaryBoard);
	
	
	//본문 정보 세팅
	function SetDetails(wrapMylist) {
		 this.url = "/api/reservations?reservationEmail=" + parameter.email;
		 this.wrapMylist = wrapMylist;
		 this.DetailsAjax(this.url);
		 this.confirmCount = 0;
		 this.cancelCount = 0;
		 
    }
	
	SetDetails.prototype ={
			
		DetailsAjax : function(url){
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function() {
				var data = JSON.parse(oReq.responseText);
				this.makeDetailTemplate(data);
			}.bind(this));
			oReq.open("GET", url);
			oReq.send();
		},
		
		makeDetailTemplate : function(data){
			var confirm = wrapMylist.querySelector(".card"+".confirmed");
			var cancel = wrapMylist.querySelector(".card"+".used"+".cancel");
			var items = data.reservations;
			for(var i=0; i<items.length; i++){
				items[i].sumPrice = parameter.regExpPrice(items[i].sumPrice);
			}
			for(var i=0; i<items.length; i++){
				if(!items[i].cancelFlag){
					var template = document.querySelector("#confirmCard").innerText;
	        		var bindTemplate = Handlebars.compile(template);
	        		var resultHTML = bindTemplate(items[i]);
	        		confirm.innerHTML += resultHTML;
	        		this.confirmCount++;
				}else{
					var template = document.querySelector("#cancelCard").innerText;
	        		var bindTemplate = Handlebars.compile(template);
	        		var resultHTML = bindTemplate(items[i]);
	        		cancel.innerHTML += resultHTML;
	        		this.cancelCount++
				}
			}
			
			if(this.confirmCount==0){
				var template = document.querySelector("#noList").innerHTML;
        		confirm.innerHTML += template;
			}
			if(this.cancelCount==0){
				var template = document.querySelector("#noList").innerHTML;
        		cancel.innerHTML += template;
			}
			
			var cancelReservation = new CancelReservation();
			
		}
	}
	var wrapMylist = document.querySelector(".wrap_mylist");
	var details = new SetDetails(wrapMylist);
	
	
	//취소 버튼
	function CancelReservation() {
		 this.url = "/api/reservations/cancel/";
		 this.cancel();
    }
	CancelReservation.prototype={
			
		cancelAjax : function(id){
			var oReq = new XMLHttpRequest();
			oReq.open("PUT", this.url+id);
			oReq.send();
		},
		
		cancel : function(){
			var cancelButtons = document.querySelectorAll("#cancel");
			for(var i =0; i<cancelButtons.length; i++ ){
				cancelButtons[i].addEventListener("click", function(evt){
					var div = evt.target.parentElement.parentElement;
					var id = evt.currentTarget.offsetParent.querySelector(".booking_number").innerText.substring(3);
					if(confirm("예약을 취소하시겠습니까?")){
						
						this.cancelAjax(id);

						details.confirmCount--; --document.querySelectorAll(".figure")[1].innerText;
						details.cancelCount++; ++document.querySelectorAll(".figure")[3].innerText;
						
						document.querySelector(".card"+".used"+".cancel").appendChild(evt.target.offsetParent);
						evt.currentTarget.offsetParent.querySelector("li:nth-child(2)").querySelector("em").innerText ="예약 취소";
						evt.target.parentElement.remove();
						if(details.confirmCount===0){
							var template = document.querySelector("#noList").innerHTML;
							document.querySelector(".card"+".confirmed").innerHTML += template;
						}
						if(details.cancelCount===1){
							var div = document.querySelector(".card"+".used"+".cancel"); 
							div.removeChild(div.querySelector(".err"));
						}
						
					}else{
						return;
					}
				}.bind(this))
			}
		}
		
	}
	
})
