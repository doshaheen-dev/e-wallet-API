package com.tml.poc.Wallet;

import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.utils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.UUID;

import static com.tml.poc.Wallet.utils.Constants.ENCRYPTION_ALGO;
import static com.tml.poc.Wallet.utils.Constants.ENCRYPTION_SECRETKEY;

@SpringBootTest
class WalletApplicationTests {

	@Autowired
	private AESUtils aesUtils;

	private String SecretKey;
	@Test
	void contextLoads() {
		System.out.println( CommonMethods.getDate(Constants.TIME_DATE));
	}
//	@Test
//	void givenObject_whenEncrypt_thenSuccess()
//			throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
//			InvalidAlgorithmParameterException, NoSuchPaddingException, IOException,
//			BadPaddingException, ClassNotFoundException {
//
//		MPINModel student = new MPINModel();
//		student.setActive(true);
//		student.setmPin("101010");
//		student.setRequestID(UUID.randomUUID().toString());
//		student.setUserID(2);
//		SecretKey key = AESUtils.generateKey(128);
//		IvParameterSpec ivParameterSpec = AESUtils.generateIv();
//		String algorithm = "AES/CBC/PKCS5Padding";
//		SealedObject sealedObject = AESUtils.encryptObject(
//				algorithm, student, key, ivParameterSpec);
//		MPINModel object = (MPINModel) AESUtils.decryptObject(
//				algorithm, sealedObject, key, ivParameterSpec);
//		assertThat(student).isEqualToComparingFieldByField(object);
//	}

	/**
	 * tested
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 */
	@Test
	void givenString_whenEncrypt_thenSuccess1()
			throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
			BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		String input = "{\"mpin\":\"111111\",\"receiveruserID\":4,\"senderuserID\":7,\"transactionAmount\":\"100\"}";
//		String cipherText = "0lcBrBOs9wqgnFTLYVI0Pb4XMMEzuc1DVfI4Wrlvw0Cd5GpVIKpiTuKHKTOlZps1DtwIrAaz+OsupqoMeig2GmlArMHWaVLFnke8QrET+i0=";
		SecretKey key = aesUtils.secretKeyToString(ENCRYPTION_SECRETKEY);
		IvParameterSpec ivParameterSpec = aesUtils.generateIvPreloaded();
		System.out.println("Key "+aesUtils.stringToSecretKey(key));
		System.out.println("ivParameterSpec "+ Base64.getEncoder().encodeToString(ivParameterSpec.getIV()));
		String algorithm = ENCRYPTION_ALGO;
		String cipherText = aesUtils.encrypt(algorithm, input, key, ivParameterSpec);
		String plainText = aesUtils.decrypt(algorithm, cipherText, key, ivParameterSpec);
		System.out.println("input "+input+" plainText "+plainText);
		System.out.println("input "+input+" cipherText "+cipherText);
		Assertions.assertEquals(input, plainText);
	}

	@Test
	void decryptText(){
		String input="";
		String ciphertext="";
		String secureKey="";
		String iv="";

//		SecretKey key = AESUtils.generateKey(128);

	}


	@Test
	void givenPassword_whenEncrypt_thenSuccess()
			throws InvalidKeySpecException, NoSuchAlgorithmException,
			IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
			InvalidAlgorithmParameterException, NoSuchPaddingException {

//		String plainText = "www.baeldung.com";
//		String password = "baeldung";
//		String salt = "12345678";
//		IvParameterSpec ivParameterSpec = AESUtils.generateIv();
//		SecretKey key = AESUtils.getKeyFromPassword(password,salt);
//		String cipherText = AESUtils.encryptPasswordBased(plainText, key, ivParameterSpec);
//		String decryptedCipherText = AESUtils.decryptPasswordBased(
//				cipherText, key, ivParameterSpec);
//		Assertions.assertEquals(plainText, decryptedCipherText);
	}

//	/**
//	 * key will be our Encryption one
//	 * @throws InvalidKeySpecException
//	 * @throws NoSuchAlgorithmException
//	 * @throws IllegalBlockSizeException
//	 * @throws InvalidKeyException
//	 * @throws BadPaddingException
//	 * @throws InvalidAlgorithmParameterException
//	 * @throws NoSuchPaddingException
//	 */
//	@Test
//	void givenPassword_whenEncrypt_thenSuccess()
//			throws InvalidKeySpecException, NoSuchAlgorithmException,
//			IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
//			InvalidAlgorithmParameterException, NoSuchPaddingException {
////
//		String plainText = "100";
//		String cipher = "eAZYKFhOOI3nK6RuAp5k2Q==";
////		String password = "baeldung";
//		String salt = "R1rCQ5DqMpLhXSgQRAInbsgYydUJbShFEugUE8Aub2I=";
//		IvParameterSpec ivParameterSpec = AESUtils.generateIv();
//
//		String cipherText = AES.encrypt(plainText, salt) ;
//		String decryptText = AES.decrypt(cipher, salt) ;
//
//		Assertions.assertEquals(plainText, decryptText);
//	}

//	@Test
//	void testAES(){
//		KeyGenerator keyGenerator;
//		SecretKey secretKey;
//
//		try {
//			keyGenerator = KeyGenerator.getInstance("AES");
//			keyGenerator.init(128);
//			secretKey = keyGenerator.generateKey();
//
//			byte[] IV = {12,34,54,78,95,90,34,32,36,24,10,40,38,42,06,03};
//			SecureRandom random;
//			random = new SecureRandom();
//			random.nextBytes(IV);
//			try {
//				byte[] encrypt = encrypt("I am plain text".getBytes(), secretKey, IV);
//				System.out.println("Secretkey "+AESUtils.stringToSecretKey(secretKey));
//				String encryptText = new String(encrypt, "UTF-8");
//				System.out.println("encryptText "+encryptText);
//
//				String decrypt = decrypt(encrypt, secretKey, IV);
//				System.out.println("decrypt "+decrypt);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(IV);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		byte[] cipherText = cipher.doFinal(plaintext);
		return cipherText;
	}

	public static String decrypt(byte[] cipherText, SecretKey key, byte[] IV) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(IV);
			cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
			byte[] decryptedText = cipher.doFinal(cipherText);
			return new String(decryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



}
