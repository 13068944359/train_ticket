package user.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class otherstest {
	public static void main(String[] args) {

		Date d = new Date();
		String s  = null;
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		s = sdf.format(d);
		System.out.println(s);
	}
}
