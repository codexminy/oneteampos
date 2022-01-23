package oneteampos.menu.etc;

public class ChangeString {
	
	private ChangeString() {
		
	}
	
	public static String setCashMark(int text) {
		return String.format("￦ %,d", text);
	}
	
	public static int setErase(String text) {
		return Integer.parseInt(text.replaceAll("\\D", ""));
	}
	
}
