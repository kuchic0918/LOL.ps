$(document).ready(function() {
	    	//여기 아래 부분
	    	$("#summernote").summernote({
	    		  height: 300,                 // 에디터 높이
		  		  minHeight: null,             // 최소 높이
		  		  maxHeight: null,  
		  		  
	    		  toolbar: [
	    			    // [groupName, [list of button]]
	    			    ['fontname', ['fontname']],
	    			    ['fontsize', ['fontsize']],
	    			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
	    			    ['color', ['forecolor','color']],
	    			    ['table', ['table']],
	    			    ['para', ['ul', 'ol', 'paragraph']],
	    			    ['height', ['height']],
	    			    ['insert',['picture','link','video']],
	    			    ['view', ['fullscreen', 'help']]
	    			    ['para', ['ul']]
	    			  ],
	    			fontNames: ['맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
	    			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
	    			
	    	    focus: true,
	    	    disableResizeEditor: true
	    	});
	    });
