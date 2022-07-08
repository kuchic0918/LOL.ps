$(window).on('load',function() {
		$('.loader').fadeOut();
});
/*const navbar = document.querySelector('.header-mainnav');*/
const navbar = $('.header-mainnav');

/*document.addEventListener('scroll', () => {
    if(window.scrollY > 160) {					// scrollY : 현재 위치 Y값
        navbar.style.background = '#172b65';
    }else if(window.scrollY < 160) {
        navbar.style.background = 'none';
    }
})*/
$(document).scroll(function () {
	if(window.scrollY > 160) {
		navbar.css({background:'#172b65'});
	}else if(window.scrollY < 160) {
		navbar.css({background:'none'});
	}
});

/*const goTop = document.querySelector('.top-button');*/
const goTop = $('.top-button');

/*if(goTop!==null) {
	goTop.addEventListener('click', () => {
	    window.scrollTo({ top:0, behavior: 'smooth' })		// scrollTo( '여기로 스크롤해라' )
	})
}*/
if(goTop!==null) {
	goTop.click(function() {
		window.scrollTo({top:0, behavior:'smooth'});
	});
}


/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/
/*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*//*sign*/

<<<<<<< HEAD
//const signInForm = $('[name=signInForm]');
//const submitBtn = $('#summit-button');
//
//if(signInForm!==null) {
//	const emailInput = $('input[name=email]');
//	const passwordInput = $('input[name=password]');
//	const passwordConfirmInput = $('input[name=password-confirm]')
//
//	const emailPattern = new RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
//	const passwordPattern = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/)
//
//	const emailPtag = $('#email-p');
//	const passwordPtag = $('#password-p');
//	const passwordConfirmPtag = $('#password-confirm-p');
//	
//	function validationTest(pattern, input) {	// pattern : RegExp(객체)의 파라미터, input : eamilInput, passwordInput, passwodrdConfirmInput
//	    if(pattern.test(input.value)) {		// test : RegExp가 제공하는 메소드
//	        return true;
//	    }else {
//	        return false;
//	    }
//	}
//
//	emailInput.change(function() {
//	    if(validationTest(emailPattern, emailInput)) {
//	        emailPtag.html("");
//	    }else{
//	        emailPtag.html('올바른 이메일 형식을 입력하세요!!')
//	        emailInput.focus();
//	        return;
//	    }
//	});
//
//	passwordInput.change(function() {
//	    if(validationTest(passwordPattern, passwordInput)) {
//	        passwordPtag.html("");
//	    }else{
//	        passwordPtag.html('숫자, 알파벳, 특수문자를 포함하셔야 합니다!!');
//	        passwordInput.focus();
//	        return;
//	    }
//	});
//
//	passwordConfirmInput.change(function() {
//	    if(passwordInput.val() === passwordConfirmInput.val()) {
//	        passwordConfirmPtag.html("");
//	    }else {
//	        passwordConfirmPtag.html("비밀번호와 일치하지 않습니다!!");
//	    }
//	});
//
//	submitBtn.click(function() {
//	    if(validationTest(emailPattern, emailInput) && validationTest(passwordPattern, passwordInput)) {
//	    	/*
//	    	  패턴 넘기면 서버에 데이터 넘기기.
//	    	  ★★★★★★★★★★★★★★★★★★★★★★★★★★★폼태그로 넘길것인지, 스크립트로 넘길것인지 생각★★★★★★★★★★★★★★★★★★★★★★★★★★★
//	    	*/
//	    }else {
//	        console.log('적절한 이메일, 비밀번호가 아닙니다.')
//	    }
//	});
//}
=======
/*const signInForm = $('[name=signInForm]');
const submitBtn = $('#summit-button');

if(signInForm!==null) {
	const emailInput = $('input[name=email]');
	const passwordInput = $('input[name=password]');
	const passwordConfirmInput = $('input[name=password-confirm]')

	const emailPattern = new RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
	const passwordPattern = new RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/)

	const emailPtag = $('#email-p');
	const passwordPtag = $('#password-p');
	const passwordConfirmPtag = $('#password-confirm-p');
	
	function validationTest(pattern, input) {	// pattern : RegExp(객체)의 파라미터, input : eamilInput, passwordInput, passwodrdConfirmInput
	    if(pattern.test(input.value)) {		// test : RegExp가 제공하는 메소드
	        return true;
	    }else {
	        return false;
	    }
	}

	emailInput.change(function() {
	    if(validationTest(emailPattern, emailInput)) {
	        emailPtag.html("");
	    }else{
	        emailPtag.html('올바른 이메일 형식을 입력하세요!!')
	        emailInput.focus();
	        return;
	    }
	});

	passwordInput.change(function() {
	    if(validationTest(passwordPattern, passwordInput)) {
	        passwordPtag.html("");
	    }else{
	        passwordPtag.html('숫자, 알파벳, 특수문자를 포함하셔야 합니다!!');
	        passwordInput.focus();
	        return;
	    }
	});

	passwordConfirmInput.change(function() {
	    if(passwordInput.val() === passwordConfirmInput.val()) {
	        passwordConfirmPtag.html("");
	    }else {
	        passwordConfirmPtag.html("비밀번호와 일치하지 않습니다!!");
	    }
	});

	submitBtn.click(function() {
	    if(validationTest(emailPattern, emailInput) && validationTest(passwordPattern, passwordInput)) {
	    	
	    	  패턴 넘기면 서버에 데이터 넘기기.
	    	  ★★★★★★★★★★★★★★★★★★★★★★★★★★★폼태그로 넘길것인지, 스크립트로 넘길것인지 생각★★★★★★★★★★★★★★★★★★★★★★★★★★★
	    	
	    }else {
	        console.log('적절한 이메일, 비밀번호가 아닙니다.')
	    }
	});
}*/
>>>>>>> branch 'main' of https://github.com/kuchic0918/LOL.ps.git

/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/
/*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*//*statistics*/

/*
const summaryBtn1 = document.querySelectorAll('.statistics-summary-button')[0];      // statistics-summary-button의 첫번쨰
const summaryBtn2 = document.querySelectorAll('.statistics-summary-button')[1];      // statistics-summary-button의 두번쨰
*/
const summaryBtn1 = $('.statistics-summary-button').eq(0);
const summaryBtn2 = $('.statistics-summary-button').eq(1);

if(summaryBtn1!==undefined) {
   summaryBtn1.click(function() {
      summaryBtn1.removeClass('statistics-summary-button-active');
      summaryBtn2.removeClass('statistics-summary-button-active');
      //summaryBtn들의 클래스 ...active를 삭제 후
      summaryBtn1.addClass('statistics-summary-button-active');
      //summaryBtn1에 ...active클래스 추가
   });
   summaryBtn2.click(function() {
      summaryBtn1.removeClass('statistics-summary-button-active');
      summaryBtn2.removeClass('statistics-summary-button-active');
      //summaryBtn들의 클래스 ...active를 삭제 후
      summaryBtn2.addClass('statistics-summary-button-active');
      //summaryBtn2에 ...active클래스 추가
   });
}

/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/
/*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*//*rank*/

const position = document.querySelector('.rank-position');
const topBtn = document.querySelectorAll('.rank-position-button')[0];
const jungleBtn = document.querySelectorAll('.rank-position-button')[1];
const midBtn = document.querySelectorAll('.rank-position-button')[2];
const adBtn = document.querySelectorAll('.rank-position-button')[3];
const supBtn = document.querySelectorAll('.rank-position-button')[4];
const allBtn = document.querySelectorAll('.rank-position-button')[5];

if(position!==null) {
   position.addEventListener('click', function(e){
       if(e.target === topBtn) {                  //내가 클릭한 것이 topBtn이면 실행
           topBtn.classList.remove('rank-active');
           jungleBtn.classList.remove('rank-active');
           midBtn.classList.remove('rank-active');
           adBtn.classList.remove('rank-active');
           supBtn.classList.remove('rank-active');
           allBtn.classList.remove('rank-active');
   
           topBtn.classList.add('rank-active');
           
           $('.rank-champ-list').load('loadRank.jsp #rank-topRank');
       } 
       if(e.target === jungleBtn) {               //내가 클릭한 것이 jungleBtn이면 실행
           topBtn.classList.remove('rank-active');
           jungleBtn.classList.remove('rank-active');
           midBtn.classList.remove('rank-active');
           adBtn.classList.remove('rank-active');
           supBtn.classList.remove('rank-active');
           allBtn.classList.remove('rank-active');
   
           jungleBtn.classList.add('rank-active');
           
           $('.rank-champ-list').load('loadRank.jsp #rank-jungleRank');
       } 
       if(e.target === midBtn) {                  //내가 클릭한 것이 midBtn이면 실행
           topBtn.classList.remove('rank-active');
           jungleBtn.classList.remove('rank-active');
           midBtn.classList.remove('rank-active');
           adBtn.classList.remove('rank-active');
           supBtn.classList.remove('rank-active');
           allBtn.classList.remove('rank-active');
   
           midBtn.classList.add('rank-active');
           
           $('.rank-champ-list').load('loadRank.jsp #rank-midRank')
       }
       if(e.target === adBtn) {                  //내가 클릭한 것이 adBtn이면 실행
           topBtn.classList.remove('rank-active');
           jungleBtn.classList.remove('rank-active');
           midBtn.classList.remove('rank-active');
           adBtn.classList.remove('rank-active');
           supBtn.classList.remove('rank-active');
           allBtn.classList.remove('rank-active');
   
           adBtn.classList.add('rank-active');
           
           $('.rank-champ-list').load('loadRank.jsp #rank-botRank')
       } 
       if(e.target === supBtn) {                  //내가 클릭한 것이 supBtn이면 실행
           topBtn.classList.remove('rank-active');
           jungleBtn.classList.remove('rank-active');
           midBtn.classList.remove('rank-active');
           adBtn.classList.remove('rank-active');
           supBtn.classList.remove('rank-active');
           allBtn.classList.remove('rank-active');
   
           supBtn.classList.add('rank-active');
           
           $('.rank-champ-list').load('loadRank.jsp #rank-supRank')
       }
       if(e.target === allBtn) {                  //내가 클릭한 것이 allBtn이면 실행
           topBtn.classList.remove('rank-active');
           jungleBtn.classList.remove('rank-active');
           midBtn.classList.remove('rank-active');
           adBtn.classList.remove('rank-active');
           supBtn.classList.remove('rank-active');
           allBtn.classList.remove('rank-active');
   
           allBtn.classList.add('rank-active');
           
           $('.rank-champ-list').load('loadRank.jsp #rank-allRank')
       }
   })
}
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/
/*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*//*main*/

const realtime = $('#realtime_search');

if(realtime!==null) {
   let count = 1;
   const slideRealtime = setInterval(() => {
       realtime.css({transform : `translate(0%, ${count * -29}px)`});
       count++;
   
       if(count == 9) {
           realtime.css({transform : 'translate(0%, 0%)'})
           count = 1;
       }
   
   }, 3000);
   
   function callFunction(obj) {
      $('#realtime_search').addClass('main-background-white');
      $('.main-realtime a').removeClass('main-realtime-color-black');
      $('.main-realtime a').addClass('main-realtime-color-black');
      $('.main-word_wrapper').removeClass('main-overflow');
      $('.main-searchbar').addClass('main-searchbar-padding-bottom');
       realtime.css({transform : 'translate(0%, 0%)'});
       clearInterval(slideRealtime);
   };
}
      
const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: true,
    slidesPerView : 'auto', // 한 슬라이드에 보여줄 갯수
    centeredSlides: true,
    autoplay:true,
    autoplay: {
        delay: 3000,
    },
});


