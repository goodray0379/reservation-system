

document.addEventListener("DOMContentLoaded", function() {
	
	var start = 0; //버튼 클릭시 상품요청할 때 사용할 정보
	var nowCategory = document.querySelector(".active"); //현재 active된 메뉴의 요소(category 이름)
	var categoryId = "0"; //현재 보여지고있는 category의 ID값
	var arrCount;        //카테고리마다의 상품개수
	var allCount=0;      //모든 상품개수
	
	var promotionCount;  //프로모션 총 개수
	var slideCount = 0;  //프로모션 현재 번호
	
	
	var productUrl = "/api/products";
	var categoryUrl = "/api/categories";
	var promotionUrl = "/api/promotions";
	
	var btn = document.getElementsByClassName("btn");
	var tabmenu = document.querySelector(".event_tab_lst");
	
	var promotion = document.querySelector(".visual_img");
	
	
	categoryAjax(categoryUrl); //메인페이지 로드될 때 처음에 한번만 요청
	productAjax(productUrl);  //더보기 누르거나 탭 변경시 계속 요청할 것
	promotionAjax(promotionUrl); //메인페이지 로드될 때 처음에 한번만 요청
	
	
	//카테고리 변경시 템플릿 내부에 보여줄 상품들 넣는 함수
	function makeTemplate(json){
		var html = document.querySelector("#itemList").innerHTML;
		var result = "";

		for(var i=0; i<json.items.length; i++){
			result = html.replace("{displayInfoId}", json.items[i].displayInfoId)
						.replace("{description}", json.items[i].description)
						.replace("{id}", json.items[i].id)
						.replace("{description}", json.items[i].description)
						.replace("{placeName}", json.items[i].placeName)
						.replace("{content}", json.items[i].content);
			if(i%2==0){
				document.querySelector(".lst_event_box").innerHTML += result;
			}else{
				document.querySelector(".lst_event_box:nth-of-type(2)").innerHTML += result;
			}
		}
	}
	
	
	//슬라이딩 함수
	function sliding(){
	    if(slideCount===promotionCount){
	    	slideCount = 0;
	    	promotion.style.transition = 'all 0s';
		    promotion.style.transform = 'translateX(0px)';
		    setTimeout(() => {
		    	sliding()
		    }, 200);
	    }
	    else{
			const trans = slideCount*414;
			promotion.style.transition = 'all 0.5s';
			promotion.style.transform = 'translateX(-' + trans +'px)';
			slideCount++;
		    setTimeout(() => {
		    	sliding()
		    }, 2000);
	    }
    }
	
	
	//프로모션 만드는 함수
	function makePromotion(json){
		var html = document.querySelector("#promotionItem").innerHTML;
		var result = "";

		for(var i=0; i<json.items.length; i++){
			result = html.replace("{productId}", json.items[i].productId)
						.replace("{productImageId}", json.items[i].productImageId)
			document.querySelector(".visual_img").innerHTML += result;
		}
		promotionCount = json.size;
		sliding();
	}
	
	//상품 정보(Product) 가져오기
	function productAjax(url) {
		 var oReq = new XMLHttpRequest(url);
		 oReq.addEventListener("load", function() {
			 
			 json = JSON.parse(this.responseText);
			 makeTemplate(json);
			 
		 });
		 oReq.open("GET", url);
		 oReq.send();
	}
	
	//상품 숫자(Count) 가져오기
	function categoryAjax(url) {
		 var oReq = new XMLHttpRequest(url);
		 oReq.addEventListener("load", function() {
			 
			 arrCount = JSON.parse(this.responseText);
			 
			 for(var i=0; i<arrCount.items.length; i++)
			 	allCount += arrCount.items[i].count;
			 
			 changeCount(categoryId);
			 
		 });
		 oReq.open("GET", url);
		 oReq.send();
	}
	
	//프로모션 가져오기
	function promotionAjax(url) {
		 var oReq = new XMLHttpRequest(url);
		 oReq.addEventListener("load", function() {
			 
			 json = JSON.parse(this.responseText);
			 makePromotion(json);
			 
		 });
		 oReq.open("GET", url);
		 oReq.send();
	}
	
	//active된 카테고리 프로덕트 수 표시 함수 
	function changeCount(categoryId){
		
		 //count 변경	    
	    var evtCount = document.querySelector(".pink");
	    if(categoryId==="0")
	    	evtCount.innerHTML = allCount + "개";
	    else
	    	evtCount.innerHTML = arrCount.items[categoryId-1].count + "개";
	    
	}
	
	
	//탭 변경시 초기화 되는 것 설정
	function init(target, category){
		var ebox = document.querySelectorAll(".lst_event_box");
		ebox[0].innerHTML="";
		ebox[1].innerHTML="";
		categoryId = category;
	    start = 0;
	    
	    //.active 적용 변경
	    nowCategory.classList.remove('active');
	    target.firstElementChild.classList.add("active");
	    nowCategory = target.firstElementChild;
	    
	    changeCount(categoryId);
	    
		//버튼이 히든상태면 다시 보여줌
		if(btn[0].style.display === "none"){
			btn[0].style.display = "inline";
		}
	    
	    productAjax(productUrl + "?categoryId=" + category);
	}
	
	tabmenu.addEventListener("click", function (evt) {
		
		var target = evt.target;
		var tagname = target.tagName;
		if(tagname === 'LI'){
			var category = target.dataset.category;
			init(target, category);
		}
		else if(tagname === 'A'){
			target = target.parentElement;
			var category = target.dataset.category;
			init(target, category);
		}
		else if(tagname === 'SPAN'){
			target = target.parentElement.parentElement;
			var category = target.dataset.category;
			init(target, category);
		}
    });
	
	
	btn[0].addEventListener("click", function (evt) {
	      start += 4;
	      productUrl += "?categoryId="+categoryId + "&start=" + start;
	      productAjax(productUrl);
	      if(categoryId==="0"){
	    	  if(start>=allCount-4)
	    		  this.style.display="none";
	      }
	      else if(start>=arrCount.items[categoryId-1].count-4){
	    	  this.style.display="none";
	      }
	      productUrl  = "/api/products";
	});
	

})
