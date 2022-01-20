package oneteampos.menu.etc;

public class ChangeStr {
	
	private ChangeStr() {
		
	}
	
	public static String setCashMark(int text) {
		return String.format("￦ %,d", text);
	}
	
	public static String setErase(String text) {
		return text.replaceAll("\\D", "");
	}
	
}
