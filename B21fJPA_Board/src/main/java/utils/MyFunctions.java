package utils;

public class MyFunctions {
	public static String stringCut(String s) {
		if(s==null || s.length()==0) {
			return "작성일없음";
		}
		else {
			return s.substring(0,10);
		}
	}
}
