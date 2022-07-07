package yg_ac_java;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainCard {
	String name;
	String line;
	Double win;
	Double winBefore;
	Double winVari;
	Double pick;
	Double pickBefore;
	Double pickVari;
	Double ban;
	Double banBefore;
	Double banVari;
	
	ArrayList<MainCard> mainCard;
	
	public MainCard() {
		
	}
	
	public MainCard(String name, String line, Double win, Double winBefore, Double winVari, Double pick,
			Double pickBefore, Double pickVari, Double ban, Double banBefore, Double banVari) {
		this.name = name;
		this.line = line;
		this.win = win;
		this.winBefore = winBefore;
		this.winVari = winVari;
		this.pick = pick;
		this.pickBefore = pickBefore;
		this.pickVari = pickVari;
		this.ban = ban;
		this.banBefore = banBefore;
		this.banVari = banVari;
	}
	
	@Override
	public String toString() {
		return  name + "(" + line + ") \n"  + "    이전패치" + "\t현재패치" + 
				"\n승률 " + win + "% ---> " + winBefore + "%\t" + winVari +
				"%\n픽률 " + pick + "% ---> " + pickBefore + "% " + pickVari + "%\n밴률 " + ban + "% --->  " + banBefore +
				"% " + banVari + "%"; 
	}

	// 카드정보 ArrayList에 담고 return
	public ArrayList<MainCard> getCard(ResultSet rs) throws SQLException {
		ArrayList<MainCard> mainCard = new ArrayList<MainCard>();
		int row = 0;
		
		while(rs.next()) {
			this.name = rs.getString("name");
			this.line = rs.getString("line");
			this.win = rs.getDouble("w");
			this.winBefore = rs.getDouble("w_before");
			this.winVari = rs.getDouble("win_vari");
			this.pick = rs.getDouble("p");
			this.pickBefore = rs.getDouble("p_before");
			this.pickVari = rs.getDouble("pick_vari");
			this.ban = rs.getDouble("b");
			this.banBefore = rs.getDouble("b_before");
			this.banVari = rs.getDouble("ban_vari");
			
			row++;
			
			if(row < 10 || row > 220) {
				mainCard.add(new MainCard(name, line, win, winBefore, winVari, pick, pickBefore, pickVari, ban, banBefore, banVari));
			}
			
		}
		
		return mainCard;
	}
	
	//click한 카드의 정보만 불러오기
	public void onClick(ArrayList<MainCard> mainCard, String champName) {  
		for(int i=0; i<=mainCard.size()-1; i++) {
			if(mainCard.get(i).name.equals(champName)) {
				System.out.println(mainCard.get(i));
				break;
			}
		}
	}
	
	//오라클에서 주목해야 할 챔피언 목록 가져오기
	public void cardExecute(Connection conn, PreparedStatement pstmt, ResultSet rs, ArrayList<MainCard> mainCard) throws SQLException {
		try {
			String sql = "select tier.name, tier.line,  tier.win_rate w, tier_before.win_rate w_before, (tier_before.win_rate - tier.win_rate) win_vari,\r\n"
					+ "tier.pick_rate p, tier_before.pick_rate p_before, ( tier_before.pick_rate - tier.pick_rate ) pick_vari,\r\n"
					+ "tier.ban_rate b, tier_before.ban_rate b_before, ( tier_before.ban_rate - tier.ban_rate ) ban_vari\r\n"
					+ "from c_champ_tier tier, c_champ_tier_before tier_before\r\n"
					+ "where tier.name = tier_before.name\r\n"
					+ "and tier.line = tier_before.line\r\n"
					+ "order by win_vari desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			this.mainCard = getCard(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pstmt.close();
			conn.close();
			rs.close();
		}
	}
	
	// 웹에서 카드 클릭하는 연출
	public void clickExecute(ArrayList<MainCard> mainCard, Scanner sc, MainCard mc) {
		boolean exit = true;
		String yesOrNo;
		while(exit) {
			System.out.println("< 주목해야 할 챔피언 >");
			System.out.println();
			for(int i = 0; i < this.mainCard.size(); i++) {
				System.out.print(this.mainCard.get(i).name + "(" + this.mainCard.get(i).line + ") ");
			}
			System.out.println();
			System.out.println();
			System.out.print("클릭할 챔피언을 고르세요 : ");
			
			String champName = sc.nextLine();
			System.out.println();
			
			mc.onClick(this.mainCard, champName);
			
			System.out.println();
			System.out.print("창닫으려면 yes를 입력하세요");
			yesOrNo = sc.next();
			if(yesOrNo.equals("yes")) {
				exit = false;
			}else {
				exit = true;
			}
			sc.nextLine();
			System.out.println();
		}
		sc.close();
	}
}