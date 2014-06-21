package bg.learnit.course.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class SecurityUtils {

	public static String encryptToMD5(String input) {
		byte[] inputAsBytes = null;
		byte[] encrypted = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			inputAsBytes = input.getBytes("UTF-8");
			encrypted = md5.digest(inputAsBytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = null;
		if (encrypted != null) {
			result = new String(encrypted);
		}
		
		return result;
	}
}