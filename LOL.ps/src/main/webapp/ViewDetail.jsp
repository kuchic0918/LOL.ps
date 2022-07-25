<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<!DOCTYPE html>
<%
	response.setCharacterEncoding("UTF-8");
	int bno = Integer.parseInt(request.getParameter("bno"));
	BoardDao bDao = new BoardDao();
	BoardDto dto = bDao.getDetail(bno);
	MemberDTO writer = bDao.getWriter(dto.getMemberkey());
	String introduce = writer.getIntroduce();
	MemberDTO member = (MemberDTO) session.getAttribute("memberInfo");	
	if(writer.getIntroduce()==null){
		introduce = "";
	}
	ArrayList<Integer> getBno = bDao.getFirstLastBno(dto.getCategory());
	int nextBno = 0;
	int beforeBno = 0;
	for(int i=0; i<getBno.size(); i++) {
		if(bno==getBno.get(i)){
			if(getBno.get(i)==getBno.get(getBno.size()-1)){
				nextBno = 0;
				beforeBno = getBno.get(i-1);
			}else if(getBno.get(i)==getBno.get(0)){
				nextBno = getBno.get(i+1);
				beforeBno = 0;
			}else {
				nextBno = getBno.get(i+1);
				beforeBno = getBno.get(i-1);
			}
		}
	}
	ArrayList<CommentDto> cDto = bDao.getCommnet(bno);
	ArrayList<MemberDTO> commentWriter = new ArrayList<MemberDTO>();
%>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title><%=dto.getCategory() %></title>
	<link rel="stylesheet" href="Css/all.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>    
    <%
    if(member != null){
    %>
    <script>
    	$(function() {
    		$('.replybtn').click(function() {
				var form = $(this).parent().parent().parent().parent().find('.reply');
    			$(form).toggle();
    		});
    		const url = new URL($(location).attr("href"));
    		<%
    		if((MemberDTO) session.getAttribute("memberInfo")!=null) {
    			int memberkey = member.getMemberkey();
   			%>
    		
    		$('#good_btn').click(function(){
    			$.ajax ({
    				type : 'get',
    				dataType : 'JSON',
    				contentType : 'application/json',
    				url : 'Controller',
    				data : {
    					command : 'like' ,
    					bno : '<%=bno%>' ,
    					memberkey : '<%=memberkey%>',
    				},
    				success : function(data,x,status){
    					console.log(data);
    					if(status.status == 201) { 
    						alert("이미 추천한 게시글 입니다.");
    					}else {
							$('.recommend').addClass('recommend-on');    							
	  						$('.unrecommend').removeClass('recommend-on');
	   						$('#good').text(data);
	   						$('#bad').text(<%=bDao.likeCount(bno)%>);
    					}
    				},
    				error:function(){
//     					alert("중복");
						alert("이미 추천한 게시글 입니다.");
    					console.log('error');
    				}
    			});
    		});
    		$('#bad_btn').click(function(){
    			$.ajax ({
    				type : 'get',
    				url : 'Controller',
    				data : {
    					command : 'bad' ,
    					bno : '<%=bno%>' ,
    					memberkey : '<%=memberkey%>',
    				},
    				success : function(data,x,status){
    					if(status.status == 201) { 
    						alert("이미 비추천한 게시글입니다.");
    					}else {
							$('.unrecommend').addClass('recommend-on');
	    					$('.recommend').removeClass('recommend-on');
	   						$('#bad').text(data);
	   						$('#good').text(<%=bDao.likeCount(bno)%>);
    					}
    				},
    				error:function(){
//     					alert("중복");
    					console.log('error');
    				}
    			});
    		});
    		$('#board_delete').click(function(){
    			if(confirm('정말 삭제하시겠습니까 ?') == true) {
    				$.ajax({
    					type : 'POST',
    					url : 'Controller' , 
    					data : {
    						command : 'deleteBoard' ,
    						bno : '<%=bno%>',
    					},
    					success : function() {
    						alert("삭제 완료");
    						location.href = "community.jsp?category=<%=dto.getCategory()%>";
    					}
    				});
    				
    			}  			
    		});
    		if(<%=bDao.likeCheck(memberkey, bno)%> == 1) {
    			$('.recommend').addClass('recommend-on');
    		}
    		else if(<%=bDao.badCheck(memberkey, bno)%> == 1) {
    			$('.unrecommend').addClass('recommend-on');
    		}
    		else {
    			return;
    		}
    		
    		$('#board_update').click(function(){
    			location.href = "writeUpdate.jsp?category=<%=dto.getCategory()%>&bno=<%=bno%>";
					
    		});
    		
    		<%
    		}
    		%>
    	});
    </script>
    <%
    }
    %>
</head>
<body class="community-body">
	<header class="all-header-mainnav header-mainnav">
        <div class="header-container">
            <a href="main.jsp">
                <img src="Images/header-logo.webp" alt="LOL.PS">
            </a>
            <div class = "nav-item-container">
            	<a class="nav-items" href="community.jsp?category=공지사항">공지사항</a>
                <a class="nav-items" href="ChampRank.jsp">챔피언 랭킹</a>
                <a class="nav-items" href="community.jsp?category=빌드 연구소">빌드 연구소</a>
                <a class="nav-items" href="community.jsp?category=자유 게시판">자유 게시판</a>
            </div>
            <div class="sign-login">
				<%
            		if(session.getAttribute("memberInfo") == null) {
            			
           	 	%>
		                <a class="signin" href="signin.jsp">회원가입</a>
		                <a class="login" href="login.jsp">로그인</a>           	 		
           	 	<% 
            		}else {
            	%>
            			<form action= "my-page.jsp" >
            				<input class="signin" type="submit" value="마이페이지"/>
            			</form>
		                <form action = "Controller" >
		                	<input type="hidden" name="command" value="login"/>
		                	<input class="login" type="submit" value="로그아웃"/>
	                	</form>  
            	<%
            		}
            	%>
			</div>
        </div>
    </header>

    <div class="all-main">
    	<%
    	if(dto.getCategory().equals("공지사항")){
    		%>
    		<div class="notice-first-title">공지사항
	       	<a class="nav-items notice-post-list-up" href="community.jsp?category=공지사항">목록</a>
	        </div>
    		<%
    	}else{
    	%>
        <div class="first-title"><%=dto.getCategory() %></div>
        <div class="second-title">
        	<a class="main-button" href="write.jsp?category=<%=dto.getCategory()%>&url="+ ${url}>✎게시물 쓰기</a>
        	<form action="Controller" method="get" id="search_form" autocomplete="off">
				<input class="main-input" type="text" name="name" placeholder="챔피언 이름을 입력하세요">
				<button style="opacity:0;" type="submit" name="command" value="search"></button>
			</form>
        </div>
        <div style="clear: both;"></div>
        <%
    	}
        %>
    </div>
	
	<!-- 중단 -->
    <main class="community-main">
		<div class="whiteDiv"></div>
      	<!-- 포스트 -->  	
      	<%
         String titleCss = "title";
         String shadow = "";
         if(dto.getCategory().equals("공지사항")){
            titleCss = "notice-post-title";
            shadow = "style='box-shadow:none'";
         }else{
            titleCss = "title";
            shadow = "";
         }
         %>
      	<div class="community-post-post-detail" <%=shadow %>>
      		<!-- 포스트제목 -->
      		<div class="<%=titleCss%>">
      			<div style="font-size:15px; color:#7e9bff; float:left "><b><%=dto.getCategory() %></b></div>
      			<% 
      			if(member != null){
      				if(member.getMemberkey() == dto.getMemberkey()) {      			
      			%>
      					<button class = "updateDelete_btn" id ="board_delete" style = "float :right;">게시물 삭제</button>
      					<button class = "updateDelete_btn" id = "board_update" style = "float :right; margin-right:3px;">게시물 수정</button>
      			<%
      				}
      			}
      				if(dto.getCategory().equals("자유 게시판")) {
      			%>
      					<h3 style="padding-top: 15px;"><%=dto.getTitle() %></h3>
      			<%	
      			} else if(dto.getCategory().equals("빌드 연구소")){
      			%>
     					<h3 style="padding-top: 15px;">[<%=dto.getChampName() %>] <%=dto.getTitle() %></h3>
      			<%	
      			}else{
      			%>
      			<%=dto.getTitle() %>
       			<div class="notice-post-title2">
       				<span>조회 <%=dto.getCount() %></span>
       				<span class="notice-post-pre">     |     <%=dto.getWritedate() %></span>
       			</div>
      			<%
      				}
      			%>
      		</div>
      		<!-- 포스트내용 -->
      		<%
      		if(dto.getCategory().equals("공지사항")){
      			%>
      			<div class="notice-post-content">
           		<%=dto.getContent() %>
           		</div>
      			<%
      		}else{
      		%>
      		<div class="write">
			<%=dto.getContent() %>
			</div>
      		<!-- 포스트글쓴이 -->
      		<div class="writer">
      			<div>
      				<img class="writer-img" src="Images/profile/<%=writer.getImage() %>"/>
      			</div>
      			
      			<div class="writer-mid">
      				<div style="padding:5px 10px; font-size:12px; color:darkgray;">글쓴이</div>
      				<div style="padding:5px 10px;"><b><%=writer.getNickname() %></b></div>
      				<div style="padding:5px 10px; font-size:15px; color:gray;"><%=introduce %></div>
      			</div>
      			
      			<div class="writer-bot">
      				<span>댓글 <%=cDto.size() %> |</span>
      				<span> 조회수 <%=dto.getCount() %></span>
      			</div>
      		</div>
      		<%
        	}
        	%>
      		
      		<!-- 포스트하단 -->
           	<div class="content-function">
           		<%
           		if(beforeBno==0) {
           		%>
           		<a style="padding-left:30px;" class="prev-next-none" href="ViewDetail.jsp?bno=<%=beforeBno%>">←이전</a>
           		<%
           		}else {
           		%>
           		<a style="padding-left:30px;" class="prev-next" href="ViewDetail.jsp?bno=<%=beforeBno%>">←이전</a>
           		<%
           		}
           		%>
           		<div class="justify-content-start">
           			<button style="white-space:pre;" id = "good_btn" class="recommend" type="button"><span class="pre">&uarr;    </span ><span id = "good"><%=bDao.likeCount(bno)%></span></button>
           			<button style="white-space:pre;" id = "bad_btn" class="unrecommend" type="button"><span class="pre">&darr;    </span><span id = "bad"><%=bDao.badCount(bno)%></span></button>
           		</div>
           		
           		<%
           		if(nextBno==0) {
           		%>
           		<a style="padding-right:30px;" class="prev-next-none" href="ViewDetail.jsp?bno=<%=nextBno%>">다음→</a>
           		<%
           		}else {
           		%>
           		<a style="padding-right:30px;" class="prev-next" href="ViewDetail.jsp?bno=<%=nextBno%>">다음→</a>
           		<%
           		}
           		%>
           		
           	</div>
        </div>
      	<div style="height:40px;"></div>
      	
      	<!-- 댓글 -->
      	<%
      	for(int i=0; i<cDto.size(); i++){
      		commentWriter.add(i, bDao.getWriter(cDto.get(i).getMemberkey()));
      		String comments = "comments";
      		commentWriter.add(i, bDao.getWriter(cDto.get(i).getMemberkey()));
      		if(i==0){
      			comments = "comments";
      		}else if(cDto.get(i).getCno()==cDto.get(i-1).getCno()){
      			comments = "comments-comment";
      		}else{
      			comments = "comments";
      		}
      		%>
	   		<div>
				<div class="<%=comments%>">
		      		<img class="comments-img" src="Images/profile/<%=commentWriter.get(i).getImage()%>"/>
		      		<div style="padding-left: 20px; width : 100%;">
		      			<%if (member != null) {
		      				if(cDto.get(i).getMemberkey() == dto.getMemberkey()) {
		      			%>
		      			<span class = "comments-owner" style = "float:left;">글쓴이</span>
		      			<% 		
		      				}
	      				}
		      			%>
		      			<div style="padding-bottom: 5px; color:rgb(69, 76, 107); font-size:14px;"><b><%=commentWriter.get(i).getNickname()%></b></div>		      			
		      			<div style="font-size:14px;">
		      			<%=cDto.get(i).getContent()%>
		      			</div>
		      			<div style="padding-top:5px">
		      				<span style="font-size: 14px; color:gray;"><%=cDto.get(i).getWritedate()%></span>
	      					<button class="write-comment replybtn">↳댓글</button>
	      					<%if (member != null) {
		      					if(cDto.get(i).getMemberkey() == member.getMemberkey()) {
		      				%>
		      						<form action="Controller" method="POST">
		      							<input type="hidden" name="cno" value="<%=cDto.get(i).getCno()%>"/>
		      							<input type="hidden" name="bno" value="<%=cDto.get(i).getBno()%>"/>
		      							<span style = "float:right;">
		      							<button type="submit" name="command" value="updateComment" class = "updateDelete_btn" id = "commentEdit" >내 댓글 수정</button>
		      							<button type="submit" name="command" value="deleteComment" class = "updateDelete_btn" id = "commentDelete">삭제</button>
		      							</span>
	      							</form>
		      				<% 		
		      					}
	      					}
		      				%>
		      			</div>
		      		</div>
	      		</div> 
      		<%	
      		if((MemberDTO) session.getAttribute("memberInfo")!=null){
        		
       		%>
	        	<form action="Controller" method="POST" style="display:none;" class="reply">
	            	<div class="comment">
	               		<div class="comment-name">댓글쓰기</div>
	               		<textarea class="comment-space" name="content"></textarea>
	               		<input type="hidden" name="memberkey" value="<%=member.getMemberkey()%>"/>
	               		<input type="hidden" name="bno" value="<%=bno%>"/>
	               		<input type="hidden" name="cno" value="<%=cDto.get(i).getCno()%>"/>
	               		<button class="comment-regist" type="submit" name="command" value="reply">등록</button>
	               	</div>
	            </form>
	        </div>
       		<%
        	}
      	}
    	%>
		<!-- 댓글쓰기 -->
        <div class="bottom-comment">
        	<%
        	if((MemberDTO) session.getAttribute("memberInfo")!=null){
        		
        	%>
        	<form action="Controller" method="POST">
	        	<div class="comment">
	           		<div class="comment-name">댓글쓰기</div>
	           		<textarea class="comment-space" name="content"></textarea>
	           		<input type="hidden" name="memberkey" value="<%=member.getMemberkey()%>"/>
	           		<input type="hidden" name="bno" value="<%=bno%>"/>
	           		<button class="comment-regist" type="submit" name="command" value="comment">등록</button>
	           	</div>
            </form>
        	<%
        	}
        	if(dto.getCategory().equals("공지사항")){
        		
        	}else {
        	%>
           	<div class="list-under-div">
           		<a style="border-radius:6%;" class="list-under" href="write.jsp?category=<%=dto.getCategory()%>">게시물 쓰기</a>
           		<a style="border-radius:10%;" class="list-under" href="community.jsp?category=<%=dto.getCategory()%>">목록</a>
     		</div>
			<%	
        	}
        	%>
     	</div>
    </main>
	
	<!-- 하단고정 -->
	<div class="top-button">
        <span style="color: #6c757d;">UP</span>
    </div>
	
   	<footer class="footer">

        <div class="footer-left">
            <span class="footer-left-item">공지사항</span>
            <span class="footer-left-item">버그리포팅</span>
            <span class="footer-left-item">파트너 신청</span>
            <div style="margin-bottom: 10px;"></div>
            <span class="footer-left-item">이용약관</span>
            <span class="footer-left-item">개인정보처리방침</span>
        </div>

        <div class="footer-right">
            <h5>PS Analytics, Inc. © 2020</h5>
            <p>
                lol.ps is hosted by PS Analytics, Inc. lol.ps isn’t endorsed by Riot Games and doesn’t reflect the views or opinions of Riot Games or anyone officially involved in 
                producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. 
                League of Legends © Riot Games, Inc.
            </p>
        </div>

    </footer>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<script src="Js/all.js"></script>
</body>
</html>