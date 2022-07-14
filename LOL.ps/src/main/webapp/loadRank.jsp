<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.yg_ac.dao.*" %>
<%@ page import="com.yg_ac.dto.*" %>
<%
	Y_DBmanager db = new Y_DBmanager();

	ResultSet rs = null;
	Connection conn = db.getConnection();
	PreparedStatement pstmt = null;
	
	ChampRankDao champRankDao = new ChampRankDao();
	
	ArrayList<ChampRankDto> rankAll = champRankDao.getChampRankAll(conn, pstmt, rs);
	ArrayList<ChampRankDto> rankTopLine = champRankDao.getChampRankByLine(conn, pstmt, rs, "탑");
	ArrayList<ChampRankDto> rankJungleLine = champRankDao.getChampRankByLine(conn, pstmt, rs, "정글");
	ArrayList<ChampRankDto> rankMidLine = champRankDao.getChampRankByLine(conn, pstmt, rs, "미드");
	ArrayList<ChampRankDto> rankBotLine = champRankDao.getChampRankByLine(conn, pstmt, rs, "원딜");
	ArrayList<ChampRankDto> rankSupLine = champRankDao.getChampRankByLine(conn, pstmt, rs, "서폿");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
        <div class="rank-champ-list" id="rank-topRank">
        <%
        	for(int i = 0; i < rankTopLine.size(); i++) {
        	
        %>
	            <a class="rank-list" href="statistics.jsp?name=<%=rankTopLine.get(i).getName() %>&line=<%=rankTopLine.get(i).getLine() %>" style="text-decoration: none; color: black;">
	                <span style="width:30px;"><%=i+1%></span>
	                <span style='width: 40px; padding: 0;'>
	                    <img class="rank-list-image" src="Images/champion/head/<%=rankTopLine.get(i).getImage() %>" alt="img">
	                </span>
	                <span style="width:250px;"><%=rankTopLine.get(i).getName() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getLine() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getPsScore() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getHoneyScore() %></span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getWinRate() %>%</span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getPickRate() %>%</span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getBanRate() %>%</span>
	                <span class="rank-list-number"><%=rankTopLine.get(i).getCount2() %></span>
	            </a>
        <%
        	}
        %>
            
      	</div>
      	
      	<div class="rank-champ-list" id="rank-jungleRank">
        <%
        	for(int i = 0; i < rankJungleLine.size(); i++) {
        	
        %>
	            <a class="rank-list" href="statistics.jsp?name=<%=rankJungleLine.get(i).getName() %>&line=<%=rankJungleLine.get(i).getLine() %>" style="text-decoration: none; color: black;">
	                <span style="width:30px;"><%=i+1%></span>
	                <span style='width: 40px; padding: 0;'>
	                    <img class="rank-list-image" src="Images/champion/head/<%=rankJungleLine.get(i).getImage() %>" alt="img">
	                </span>
	                <span style="width:250px;"><%=rankJungleLine.get(i).getName() %></span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getLine() %></span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getPsScore() %></span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getHoneyScore() %></span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getWinRate() %>%</span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getPickRate() %>%</span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getBanRate() %>%</span>
	                <span class="rank-list-number"><%=rankJungleLine.get(i).getCount2() %></span>
	            </a>
            
        <%
        	}
        %>
       </div>
       
       
      	
      	
      	<div class="rank-champ-list" id="rank-midRank">
        <%
        	for(int i = 0; i < rankMidLine.size(); i++) {
        	
        %>
            <a class="rank-list" href="statistics.jsp?name=<%=rankMidLine.get(i).getName() %>&line=<%=rankMidLine.get(i).getLine() %>" style="text-decoration: none; color: black;">
                <span style="width:30px;"><%=i+1%></span>
                <span style='width: 40px; padding: 0;'>
                    <img class="rank-list-image" src="Images/champion/head/<%=rankMidLine.get(i).getImage() %>" alt="img">
                </span>
                <span style="width:250px;"><%=rankMidLine.get(i).getName() %></span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getLine() %></span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getPsScore() %></span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getHoneyScore() %></span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getWinRate() %>%</span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getPickRate() %>%</span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getBanRate() %>%</span>
                <span class="rank-list-number"><%=rankMidLine.get(i).getCount2() %></span>
            </a>
        <%
        	}
        %>
        </div> 
        
        <div class="rank-champ-list" id="rank-botRank">
        <%
        	for(int i = 0; i < rankBotLine.size(); i++) {
        	
        %>
            <a class="rank-list" href="statistics.jsp?name=<%=rankBotLine.get(i).getName() %>&line=<%=rankBotLine.get(i).getLine() %>" style="text-decoration: none; color: black;">
                <span style="width:30px;"><%=i+1%></span>
                <span style='width: 40px; padding: 0;'>
                    <img class="rank-list-image" src="Images/champion/head/<%=rankBotLine.get(i).getImage() %>" alt="img">
                </span>
                <span style="width:250px;"><%=rankBotLine.get(i).getName() %></span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getLine() %></span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getPsScore() %></span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getHoneyScore() %></span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getWinRate() %>%</span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getPickRate() %>%</span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getBanRate() %>%</span>
                <span class="rank-list-number"><%=rankBotLine.get(i).getCount2() %></span>
            </a>
        <%
        	}
        %>
        </div>
        
         <div class="rank-champ-list" id="rank-supRank">
        <%
        	for(int i = 0; i < rankSupLine.size(); i++) {
        	
        %>
	            <a class="rank-list" href="statistics.jsp?name=<%=rankSupLine.get(i).getName() %>&line=<%=rankSupLine.get(i).getLine() %>" style="text-decoration: none; color: black;">
	                <span style="width:30px;"><%=i+1%></span>
	                <span style='width: 40px; padding: 0;'>
	                    <img class="rank-list-image" src="Images/champion/head/<%=rankSupLine.get(i).getImage() %>" alt="img">
	                </span>
	                <span style="width:250px;"><%=rankSupLine.get(i).getName() %></span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getLine() %></span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getPsScore() %></span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getHoneyScore() %></span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getWinRate() %>%</span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getPickRate() %>%</span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getBanRate() %>%</span>
	                <span class="rank-list-number"><%=rankSupLine.get(i).getCount2() %></span>
	            </a>
        <%
        	}
        %>
        </div>
        
          <div class="rank-champ-list" id="rank-allRank">
        <%
        	for(int i = 0; i < rankAll.size(); i++) {
        	
        %>
            <a class="rank-list" href="statistics.jsp?name=<%=rankAll.get(i).getName() %>&line=<%=rankAll.get(i).getLine() %>" style="text-decoration: none; color: black;">
                <span style="width:30px;"><%=i+1%></span>
                <span style='width:40px; padding: 0;'>
                    <img class="rank-list-image" src="Images/champion/head/<%=rankAll.get(i).getImage() %>" alt="img">
                </span>
                <span style="width:250px;"><%=rankAll.get(i).getName() %></span>
                <span class="rank-list-number"><%=rankAll.get(i).getLine() %></span>
                <span class="rank-list-number"><%=rankAll.get(i).getPsScore() %></span>
                <span class="rank-list-number"><%=rankAll.get(i).getHoneyScore() %></span>
                <span class="rank-list-number"><%=rankAll.get(i).getWinRate() %>%</span>
                <span class="rank-list-number"><%=rankAll.get(i).getPickRate() %>%</span>
                <span class="rank-list-number"><%=rankAll.get(i).getBanRate() %>%</span>
                <span class="rank-list-number"><%=rankAll.get(i).getCount2() %></span>
            </a>
        <%
        	}
        %>            
		</div>
</body>
</html>