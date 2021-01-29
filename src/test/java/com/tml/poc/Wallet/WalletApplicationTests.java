package com.tml.poc.Wallet;

import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.utils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@SpringBootTest
class WalletApplicationTests {


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

//	@Test
//	void givenString_whenEncrypt_thenSuccess()
//			throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
//			BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
//
//		String input = "baeldung";
//		SecretKey key = AESUtils.generateKey(128);
//
//		System.out.println("Key "+key.getEncoded());
//		IvParameterSpec ivParameterSpec = AESUtils.generateIv();
//		String algorithm = "AES/CBC/PKCS5Padding";
//		String cipherText = AESUtils.encrypt(algorithm, input, key, ivParameterSpec);
//		String plainText = AESUtils.decrypt(algorithm, cipherText, key, ivParameterSpec);
//		System.out.println("input "+input+" cipherText "+cipherText);
//		Assertions.assertEquals(input, plainText);
//	}
//
//	@Test
//	void givenPassword_whenEncrypt_thenSuccess()
//			throws InvalidKeySpecException, NoSuchAlgorithmException,
//			IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
//			InvalidAlgorithmParameterException, NoSuchPaddingException {
//
//		String plainText = "www.baeldung.com";
//		String password = "baeldung";
//		String salt = "12345678";
//		IvParameterSpec ivParameterSpec = AESUtils.generateIv();
//		SecretKey key = AESUtils.getKeyFromPassword(password,salt);
//		String cipherText = AESUtils.encryptPasswordBased(plainText, key, ivParameterSpec);
//		String decryptedCipherText = AESUtils.decryptPasswordBased(
//				cipherText, key, ivParameterSpec);
//		Assertions.assertEquals(plainText, decryptedCipherText);
//	}

	/**
	 * key will be our Encryption one
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws IllegalBlockSizeException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchPaddingException
	 */
	@Test
	void givenPassword_whenEncrypt_thenSuccess()
			throws InvalidKeySpecException, NoSuchAlgorithmException,
			IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
			InvalidAlgorithmParameterException, NoSuchPaddingException {
//
		String plainText = "www.baeldung.com";
		String password = "baeldung";
		String salt = "1234567890123456";
		IvParameterSpec ivParameterSpec = AESUtils.generateIv();

		String cipherText = AES.encrypt(plainText, salt) ;
		String decryptText = AES.decrypt(cipherText, salt) ;

		Assertions.assertEquals(plainText, decryptText);
	}
}
