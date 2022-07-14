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
		}
		return action;
	}
}
