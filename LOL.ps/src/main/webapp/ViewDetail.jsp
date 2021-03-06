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
    <link href='//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css' rel='stylesheet' type='text/css'>
    <script src="Js/jquery-3.6.0.min.js"></script>
    <%
    if(member != null){
    %>
    <script>
       $(function() {
          $('.replybtn').click(function() {
            var form = $(this).parent().parent().parent().parent().find('.reply');
             $(form).toggle("normal");
          });
          $('.comment-update-btn').click(function() {
             var form = $(this).parent().parent().parent().find('.comment-update');
             $(form).toggle("normal");
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
                      alert("?????? ????????? ????????? ?????????.");
                   }else {
                     $('.recommend').addClass('recommend-on');                         
                       $('.unrecommend').removeClass('recommend-on');
                        $('#good').text(data);
                        $('#bad').text(<%=bDao.badCount(bno)%>);
                   }
                },
                error:function(){
//                    alert("??????");
                  alert("?????? ????????? ????????? ?????????.");
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
                      alert("?????? ???????????? ??????????????????.");
                   }else {
                     $('.unrecommend').addClass('recommend-on');
                      $('.recommend').removeClass('recommend-on');
                        $('#bad').text(data);
                        $('#good').text(<%=bDao.likeCount(bno)%>);
                   }
                },
                error:function(){
//                    alert("??????");
                   console.log('error');
                }
             });
          });
          <%
             if(cDto != null) {
          %>          
          $('#board_delete').click(function(){
             if(confirm('?????? ???????????????????????? ?') == true) {
                $.ajax({
                   type : 'POST',
                   url : 'Controller' , 
                   data : {
                      command : 'deleteBoard' ,
                      bno : '<%=bno%>',
                   },
                   success : function() {
                      alert("?????? ??????");
                      location.href = "community.jsp?category=<%=dto.getCategory()%>";
                   }
                });
                
             }           
          });
          $('#board_update').click(function(){
              location.href = "writeUpdate.jsp?category=<%=dto.getCategory()%>&bno=<%=bno%>";
                
           }); 
          <%
             }
          %>
          if(<%=bDao.likeCheck(memberkey, bno)%> == 1) {
             $('.recommend').addClass('recommend-on');
          }
          else if(<%=bDao.badCheck(memberkey, bno)%> == 1) {
             $('.unrecommend').addClass('recommend-on');
          }
          else {
             return;
          }
          
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
                <a class="nav-items" href="community.jsp?category=????????????">????????????</a>
                <a class="nav-items" href="ChampRank.jsp">????????? ??????</a>
                <a class="nav-items" href="community.jsp?category=?????? ?????????">?????? ?????????</a>
                <a class="nav-items" href="community.jsp?category=?????? ?????????">?????? ?????????</a>
            </div>
            <div class="sign-login">
            <%
                  if(session.getAttribute("memberInfo") == null) {
                     
                  %>
                      <a class="signin" href="signin.jsp">????????????</a>
                      <a class="login" href="login.jsp">?????????</a>                     
                  <% 
                  }else {
               %>
                     <form action= "my-page.jsp" >
                        <input class="signin" type="submit" value="???????????????"/>
                     </form>
                      <form action = "Controller" >
                         <input type="hidden" name="command" value="login"/>
                         <input class="login" type="submit" value="????????????"/>
                      </form>  
               <%
                  }
               %>
         </div>
        </div>
    </header>

    <div class="all-main">
       <%
       if(dto.getCategory().equals("????????????")){
          %>
          <div class="notice-first-title">????????????
             <a class="nav-items notice-post-list-up" href="community.jsp?category=????????????">??????</a>
           </div>
          <%
       }else{
       %>
        <div class="first-title"><%=dto.getCategory() %></div>
        <div class="second-title">
           <a class="main-button" href="write.jsp?category=<%=dto.getCategory()%>&url=${url}">???????????? ??????</a>
           <form action="Controller" method="get" id="search_form" autocomplete="off">
            <input class="main-input" type="text" name="name" placeholder="????????? ????????? ???????????????">
            <button style="opacity:0;" type="submit" name="command" value="search"></button>
         </form>
        </div>
        <div style="clear: both;"></div>
        <%
       }
        %>
    </div>
   
   <!-- ?????? -->
    <main class="community-main">
      <div class="whiteDiv"></div>
         <!-- ????????? -->     
         <%
         String titleCss = "title";
         String shadow = "";
         if(dto.getCategory().equals("????????????")){
            titleCss = "notice-post-title";
            shadow = "style='box-shadow:none'";
         }else{
            titleCss = "title";
            shadow = "";
         }
         %>
         <div class="community-post-post-detail" <%=shadow %>>
            <!-- ??????????????? -->
            <div class="<%=titleCss%>">
               <div style="font-size:15px; color:#7e9bff; float:left "><b><%=dto.getCategory() %></b></div>
               <% 
               if(member != null){
                  if(member.getMemberkey() == dto.getMemberkey()) {               
               %>
                     <button class = "updateDelete_btn" id ="board_delete" style = "float :right;">????????? ??????</button>
                     <button class = "updateDelete_btn" id = "board_update" style = "float :right; margin-right:3px;">????????? ??????</button>
               <%
                  }
               }
                  if(dto.getCategory().equals("?????? ?????????")) {
               %>
                     <h3 style="padding-top: 15px;"><%=dto.getTitle() %></h3>
               <%   
               } else if(dto.getCategory().equals("?????? ?????????")){
               %>
                    <h3 style="padding-top: 15px;">[<%=dto.getChampName() %>] <%=dto.getTitle() %></h3>
               <%   
               }else{
               %>
               <h3 style="padding-top: 15px; width: 700px;"><%=dto.getTitle() %></h3>
                <div class="notice-post-title2">
                   <span>?????? <%=dto.getCount() %></span>
                   <span class="notice-post-pre"> | <%=dto.getWritedate() %></span>
                </div>
               <%
                  }
               %>
            </div>
            <!-- ??????????????? -->
            <%
            if(dto.getCategory().equals("????????????")){
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
            <!-- ?????????????????? -->
            <div class="writer">
               <div>
                  <img class="writer-img" src="Images/profile/<%=writer.getImage() %>"/>
               </div>
               
               <div class="writer-mid">
                  <div style="padding:5px 10px; font-size:12px; color:darkgray;">?????????</div>
                  <div style="padding:5px 10px;"><b><%=writer.getNickname() %></b></div>
                  <div style="padding:5px 10px; font-size:15px; color:gray;"><%=introduce %></div>
               </div>
               
               <div class="writer-bot">
                  <span>?????? <%=cDto.size() %> |</span>
                  <span> ????????? <%=dto.getCount() %></span>
               </div>
            </div>
            <%
           }
           %>
            
            <!-- ??????????????? -->
              <div class="content-function">
                 <%
                 if(beforeBno==0) {
                 %>
                 <a style="padding-left:30px;" class="prev-next-none" href="ViewDetail.jsp?bno=<%=beforeBno%>">?????????</a>
                 <%
                 }else {
                 %>
                 <a style="padding-left:30px;" class="prev-next" href="ViewDetail.jsp?bno=<%=beforeBno%>">?????????</a>
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
                 <a style="padding-right:30px;" class="prev-next-none" href="ViewDetail.jsp?bno=<%=nextBno%>">?????????</a>
                 <%
                 }else {
                 %>
                 <a style="padding-right:30px;" class="prev-next" href="ViewDetail.jsp?bno=<%=nextBno%>">?????????</a>
                 <%
                 }
                 %>
                 
              </div>
        </div>
         <div style="height:40px;"></div>
         
         <!-- ?????? -->
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
                     <span class = "comments-owner" style = "float:left;">?????????</span>
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
                        <button class="write-comment replybtn">?????????</button>
                        <%if (member != null) {
                           if(cDto.get(i).getMemberkey() == member.getMemberkey()) {
                        %>
                           <div>
                           <%
                           if("comments-comment".equals(comments)) {
                           %>
                           <form action="Controller" method="POST">
                                 <input type="hidden" name="rno" value="<%=cDto.get(i).getRno()%>"/>
                                 <input type="hidden" name="bno" value="<%=cDto.get(i).getBno()%>"/>
                                 <span style = "float:right;">
                                    <button type="button" class = "updateDelete_btn comment-update-btn" >??? ?????? ??????</button>
                                    <button type="submit" name="command" value="deleteCommentComments" class = "updateDelete_btn" >??????</button>
                                 </span>
                           </form>
                           <%
                           } else {
                           %>
                           <form action="Controller" method="POST">
                                 <input type="hidden" name="cno" value="<%=cDto.get(i).getCno()%>"/>
                                 <input type="hidden" name="bno" value="<%=cDto.get(i).getBno()%>"/>
                                 <span style = "float:right;">
                                    <button type="button" class = "updateDelete_btn comment-update-btn" >??? ?????? ??????</button>
                                    <button type="submit" name="command" value="deleteComment" class = "updateDelete_btn" >??????</button>
                                 </span>
                           </form>
                           <%    
                           }
                           %>
                              
                              <form action="Controller" method="POST" style="display:none;" class="comment-update">
                                 <div class="comment" style = "border-top : none; ">
                                       <div class="comment-name">????????????</div>
                                       <input type="hidden" name="rno" value="<%=cDto.get(i).getRno()%>"/>
                                    <input type="hidden" name="bno" value="<%=cDto.get(i).getBno()%>"/>
                                       <textarea class="comment-space" name="content"><%=cDto.get(i).getContent() %></textarea>
                                       <button class="comment-regist" type="submit" name="command" value="updateComment">??????</button>
                                    </div>
                              </form>
                           </div>
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
                        <div class="comment-name">????????????</div>
                        <textarea class="comment-space" name="content"></textarea>
                        <input type="hidden" name="memberkey" value="<%=member.getMemberkey()%>"/>
                        <input type="hidden" name="bno" value="<%=bno%>"/>
                        <input type="hidden" name="cno" value="<%=cDto.get(i).getCno()%>"/>
                        <button class="comment-regist" type="submit" name="command" value="reply">??????</button>
                     </div>
               </form>
           </div>
             <%
           }
         }
       %>
      <!-- ???????????? -->
        <div class="bottom-comment">
           <%
           if((MemberDTO) session.getAttribute("memberInfo")!=null){
              
           %>
           <form action="Controller" method="POST">
              <div class="comment">
                    <div class="comment-name">????????????</div>
                    <textarea class="comment-space" name="content"></textarea>
                    <input type="hidden" name="memberkey" value="<%=member.getMemberkey()%>"/>
                    <input type="hidden" name="bno" value="<%=bno%>"/>
                    <button class="comment-regist" type="submit" name="command" value="comment">??????</button>
                 </div>
            </form>
           <%
           }
           if(dto.getCategory().equals("????????????")){
              
           }else {
           %>
              <div class="list-under-div">
                 <a style="border-radius:6%;" class="list-under" href="write.jsp?category=<%=dto.getCategory()%>">????????? ??????</a>
                 <a style="border-radius:10%;" class="list-under" href="community.jsp?category=<%=dto.getCategory()%>">??????</a>
           </div>
         <%   
           }
           %>
        </div>
    </main>
   
   <!-- ???????????? -->
   <div class="top-button">
        <span style="color: #6c757d;">UP</span>
    </div>
   
      <footer class="footer">

        <div class="footer-left">
            <span class="footer-left-item">????????????</span>
            <span class="footer-left-item">???????????????</span>
            <span class="footer-left-item">????????? ??????</span>
            <div style="margin-bottom: 10px;"></div>
            <span class="footer-left-item">????????????</span>
            <span class="footer-left-item">????????????????????????</span>
        </div>

        <div class="footer-right">
            <h5>PS Analytics, Inc. ?? 2020</h5>
            <p>
                lol.ps is hosted by PS Analytics, Inc. lol.ps isn???t endorsed by Riot Games and doesn???t reflect the views or opinions of Riot Games or anyone officially involved in 
                producing or managing League of Legends. League of Legends and Riot Games are trademarks or registered trademarks of Riot Games, Inc. 
                League of Legends ?? Riot Games, Inc.
            </p>
        </div>

    </footer>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<script src="Js/all.js"></script>
</body>
</html>