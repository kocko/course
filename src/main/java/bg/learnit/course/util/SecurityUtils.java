package bg.learnit.course.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.primefaces.util.Base64;

public final class SecurityUtils {

	/**
	 * A private constructor, so that the class can not be initialized.
	 */
	private SecurityUtils() {
		// empty block
	}

	public static String encryptToMD5(String input) {
		byte[] inputAsBytes = null;
		byte[] encrypted = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			inputAsBytes = input.getBytes("UTF-8");
			encrypted = md5.digest(inputAsBytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String result = null;
		if (encrypted != null) {
			result = Base64.encodeToString(encrypted, false);
		}

		return result;
	}

}