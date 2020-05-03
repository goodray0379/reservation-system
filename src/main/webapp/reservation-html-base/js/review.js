

document.addEventListener("DOMContentLoaded", function() {
	
	getParameter();
	
	//url의 파라매터 id값 받는 함수
	function getParameter(){
		frmUrl = window.location.href;
		cut = frmUrl.indexOf("=");
		id = frmUrl.substring(cut+1);
		document.querySelector(".btn_back").setAttribute("href", "/detail?id=" + id); 
	}

})
