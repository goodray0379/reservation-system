

document.addEventListener("DOMContentLoaded", function() {
	
	var json; //DisplayInfo 데이터 전역변수
	var prev = document.querySelector(".prev");
	var nxt = document.querySelector(".nxt");
	var photo = document.querySelector(".visual_img");
	var num = document.querySelector(".num");
	
	var initPage = {
		
		url : "/api/products/",
		
		//url의 파라매터 id값 받는 함수
		getParameter : function(){
			frmUrl = window.location.href;
			cut = frmUrl.indexOf("=");
			id = parseInt(frmUrl.substring(cut + 1));
			//한줄평 더보기 버튼
			document.querySelector(".btn_review_more").setAttribute("href", "/review?id=" + id); 
			//예매하기 버튼
			document.querySelector(".reserve").setAttribute("href", "/reserve?id=" + id);
			return id;
		},
		
		ajax : function() {
			 this.url += this.getParameter();
			 var oReq = new XMLHttpRequest(this.url);
			 oReq.addEventListener("load", function() { 
				 json = JSON.parse(this.responseText);
				 promotionCount = json.productImages.length;
				 document.querySelector(".num" + ".off").firstElementChild.innerHTML = promotionCount;
				 //프로모션이 1개뿐일경우 prev next 버튼 삭제
				 if(promotionCount==1){
					 document.querySelector(".click").style.display = "none";
				 }
				 //템플릿작업 초기화
				 template.initTemplate();
				 tabmenu = document.querySelector(".info_tab_lst");
				 tabmenu.addEventListener("click", tap.initTap);
				 //이벤트 등록
				 prev.addEventListener("click", banner.movePrev.bind(banner));
				 nxt.addEventListener("click", banner.moveNxt.bind(banner));
			 });
			 oReq.open("GET", this.url);
			 oReq.send();
		},
		
		//펼쳐보기 접기 Jquery
		expand : $(".bk_more").click(function(){
	        if($(".bk_more"+ "._open").is(":visible")){
	        	$(".store_details").removeClass("close3");
	            $(".bk_more"+ "._open").css("display", "none");
	            $(".bk_more"+ "._close").css("display", "block");
	        }else{
	        	$(".store_details").addClass("close3");
	        	$(".bk_more"+ "._close").css("display", "none");
	            $(".bk_more"+ "._open").css("display", "block");
	        }
	    })
	}
	
	//배너 버튼 작업
	var banner = {
		now : 1,
		width : 414,
		
		movePrev : function(){
			photo.style.transition = 'all 0.1s';
			this.now--;
			if(num.innerText === "1"){
				num.innerText = "2";
				photo.style.transform = 'translateX(' +  ((-this.now)*this.width) + 'px)';
				if(this.now===0){
					this.now = 2;
					setTimeout(function(){
						photo.style.transition = 'all 0s';
						photo.style.transform = 'translateX(' +  ((-this.now)*this.width) + 'px)';
					}.bind(this),100);
				}
			}
			else{
				num.innerText = "1";
				photo.style.transform = 'translateX(' +  ((-this.now)*this.width) + 'px)';
			}
		},
	
		moveNxt : function(){
			photo.style.transition = 'all 0.1s';
			this.now++;
			if(num.innerText === "2"){
				num.innerText = "1";
				photo.style.transform = 'translateX(' + ((-this.now)*this.width) + 'px)';
				if(this.now===3){
					this.now = 1;
					setTimeout(function(){
						photo.style.transition = 'all 0s';
						photo.style.transform = 'translateX(' +  ((-this.now)*this.width) + 'px)';
					}.bind(this),100);
				}
			}
			else{
				num.innerText = "2";
				photo.style.transform = 'translateX(' + ((-this.now)*this.width) + 'px)';
			}
		}
	}
	
	var tap = {
			//탭 작업
			initTap : function (evt) {
				
				var target = evt.target;
				var tagname = target.tagName;
				text = target.innerText;
				document.querySelector(".anchor" + ".active").classList.remove("active");
				
				if(tagname === 'LI'){
					target = target.firstElementChild;
				}
				else if(tagname === 'SPAN'){
					target = target.parentElement;
				}
				target.classList.add('active');
				
				
				if(text == "상세정보"){
					document.querySelector('.detail_location').classList.add('hide');
					document.querySelector('.detail_area_wrap').classList.remove('hide');
					template.inIntroTemplate();
				}
				else{
					document.querySelector('.detail_area_wrap').classList.add('hide');
					document.querySelector('.detail_location').classList.remove('hide');
					template.locationTemplate();
				}	
			}	
		}
	
	var template = {
		//템플릿 작업들
		initTemplate :function(){
			this.promotionTemplate();
			this.introTemplate();
			this.inIntroTemplate();
		},
		
		promotionTemplate: function (){
			var template = document.querySelector("#promotionTemplate").innerText;
			var bindTemplate = Handlebars.compile(template);
			
			object={};
			object.description = json.product.description;
			
			var resultHTML = json.productImages.reduce( function(prev, next){
				object.saveFileName = next.saveFileName;
				return prev + bindTemplate(object);
			}, "");
			
			var show = document.querySelector(".visual_img" +".detail_swipe");
			show.innerHTML += resultHTML + resultHTML;
			photo.style.transform = 'translateX(' + ((-banner.now)*banner.width) + 'px)';
		},
		
		introTemplate: function(){
			var template = document.querySelector("#introTemplate").innerText;
			var bindTemplate = Handlebars.compile(template);
			var resultHTML = bindTemplate(json.product);
			var show = document.querySelector(".store_details" +".close3");
			show.innerHTML += resultHTML;
		},
		
		//프로젝트4에선 아직 미구현
		commentsTemplate: function(){
			
		},
		
		inIntroTemplate: function(){
			var template = document.querySelector("#inIntroTemplate").innerText;
			var bindTemplate = Handlebars.compile(template);
			var resultHTML = bindTemplate(json.product);
			var show = document.querySelector(".detail_area_wrap");
			show.innerHTML = resultHTML;
		},
		
		locationTemplate: function(){
			var template = document.querySelector("#locationTemplate").innerText;
			var bindTemplate = Handlebars.compile(template);
			
			var object = json.product;
			object.saveFileName = json.displayInfoImages[0].saveFileName;
			
			var resultHTML = bindTemplate(object);
			var show = document.querySelector(".detail_location");
			show.innerHTML = resultHTML;
		}
	}
	
	initPage.ajax();
})
