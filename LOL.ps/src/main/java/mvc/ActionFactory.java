package mvc;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	public ActionFactory() { }
	public static ActionFactory getInstance() {
		return instance;
	}
	
	Action getAction(String command) {
		Action action = null;
		switch(command) {
		case "search" :
			action = new SearchAction();
			break;
		case "statistics" :
			action = new StatisticsAction();
			break;
		case "signin" :
			action = new SigninAction();
			break;
		case "signinCheck" :
			action = new SigninCheckAction();
			break;
		case "kakaoLogin" :
			action = new KakaoLoginAction();
			break;
		case "naverLogin" :
			action = new NaverLoginAction();
			break;
		case "googleLogin" :
			action = new GoogleLoginAction();
			break;
		case "login" :
			action = new LoginAction();
			break;
		case "introduceSave" :
			action = new IntroduceSaveAction();
			break;
		case "profileImage" :
			action = new ProfileImageAction();
			break;
		case "passwordChange" :
			action = new PasswordChangeAction();
			break;
		case "secession" :
			action = new SecessionAction();
			break;
		case "write":
			action = new WriteAction();
			break;
		case "communitySearch" :
			action = new CommunitySearchAction();
			break;
		case "comment" :
			action = new CommentAction();
			break;
		case "reply" :
			action = new ReplyAction();
			break;
		case "like" :
			action = new LikeAction();
			break;
		case "bad" :
			action = new BadAction();
			break;
		case "updateBoard" :
			action = new updateBoardAction();
			break;
		case "deleteBoard" :
			action = new deleteBoardAction();
			break;
		case "findId" :
			action = new FindIdAction();
			break;
		case "findPw" :
			action = new FindPwAction();
			break;
		case "deleteComment" :
			action = new DeleteComment();
			break;
				
		}
		return action;
	}
}
