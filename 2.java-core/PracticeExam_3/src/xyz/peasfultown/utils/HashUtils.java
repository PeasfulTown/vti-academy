package xyz.peasfultown.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import xyz.peasfultown.entity.UserHashException;

public class HashUtils {
	// EXCEPTIONS 
	private static final String HASH_NO_SUCH_ALGO_EXCPT = "Error generating hash, specified secure random generator algorithm not found";
	private static final String HASH_KEY_FACT_EXCPT = "Error generating hash, no provider supports the specified algorithm secret key factory implementation";
	private static final String HASH_KEYSPEC_EXCPT= "Error generating hash, key spec not appropriate for secret key factory";
	
	private static short SIZE = 128;
	private static final String RANDOM_ALGORITHM = "SHA1PRNG";
	private static final String SECRET_ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final int ITERATIONS = 100;

	public static String generateHash(char[] in) throws UserHashException {
		try {
			SecureRandom rand = SecureRandom.getInstance(RANDOM_ALGORITHM);
			byte[] salt = new byte[SIZE / 8];
			rand.nextBytes(salt);
			byte[] sc = pbkdf2(in, salt);
			byte[] out = new byte[salt.length + sc.length];
			System.arraycopy(salt, 0, out, 0, salt.length);
			System.arraycopy(sc, 0, out, salt.length, sc.length);
			Base64.Encoder enc = Base64.getUrlEncoder();
			return enc.encodeToString(out);			
		} catch (NoSuchAlgorithmException nsae) { 
			throw new UserHashException(HASH_NO_SUCH_ALGO_EXCPT, nsae);
		}
	}
	
	public static String generateHash(String in) throws UserHashException {
		return generateHash(in.toCharArray());
	}
	
	public static boolean auth(char[] in, String token) throws UserHashException {
		byte[] tkbt = Base64.getUrlDecoder().decode(token);
		byte[] salt = new byte[SIZE / 8];
		System.arraycopy(tkbt, 0, salt, 0, SIZE / 8);
		byte[] hash = new byte[tkbt.length - salt.length];
		System.arraycopy(tkbt, salt.length, hash, 0, tkbt.length - salt.length);
		byte[] out = pbkdf2(in, salt);
		
		return Arrays.equals(out, hash);
	}
	
	public static boolean auth(String in, String token) throws UserHashException {
		return auth(in.toCharArray(), token);
	}
	
	public static byte[] pbkdf2(char[] pass, byte[] salt) throws UserHashException {
		try {
			KeySpec ks = new PBEKeySpec(pass, salt, ITERATIONS, SIZE);
			SecretKeyFactory skf = SecretKeyFactory.getInstance(SECRET_ALGORITHM);
			return skf.generateSecret(ks).getEncoded();
		} catch (NoSuchAlgorithmException nsae) {
			throw new UserHashException(HASH_KEY_FACT_EXCPT, nsae);
		} catch (InvalidKeySpecException ivkse) {
			throw new UserHashException(HASH_KEYSPEC_EXCPT, ivkse);
		}
	}

}
