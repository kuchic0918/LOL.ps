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
		case "kakaoLogin" :
			action = new KakaoLoginAction();
			break;
		case "naverLogin" :
			action = new NaverLoginAction();
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
		}
		return action;
	}
}
