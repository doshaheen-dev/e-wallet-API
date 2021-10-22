package com.tml.poc.Wallet;

import com.google.gson.Gson;
import com.tml.poc.Wallet.dao.user.SearchCriteria;
import com.tml.poc.Wallet.models.kycCenter.KycCenterModel;
import com.tml.poc.Wallet.models.transaction.RequestMoneyModel;
import com.tml.poc.Wallet.repository.FirebaseRepository;
import com.tml.poc.Wallet.repository.KycCenterRepository;
import com.tml.poc.Wallet.services.PushNotificationService;
import com.tml.poc.Wallet.services.RequestMoneyService;
import com.tml.poc.Wallet.utils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tml.poc.Wallet.utils.Constants.ENCRYPTION_ALGO;
import static com.tml.poc.Wallet.utils.Constants.ENCRYPTION_SECRETKEY;

@SpringBootTest
class WalletApplicationTests {

    @Autowired
    private AESUtils aesUtils;

    private String SecretKey;

    @Autowired
    private FirebaseRepository firebaseRepository;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Test
    void contextLoads() {
        System.out.println(CommonMethods.getDate(Constants.TIME_DATE));
    }
    @Autowired
    private RequestMoneyService requestMoneyService;



    @Test
    void sendFirebaseNotification(){
//        List<FirebaseTokenModel> firebaseTokenModelOptional
//                =firebaseRepository.findAll();
//        List<String> tokenList=new ArrayList<>();
//        for(FirebaseTokenModel firebaseTokenModel: firebaseTokenModelOptional) {
//            tokenList.add(firebaseTokenModel.getFcmToken());
//
//
//        }
//        Map<String, String> data=new HashMap<String,String>();
//        data.put("requestFrom", "1");
//        data.put("requestTo", "2");
//        PushNotificationRequest pushNotificationRequest=new PushNotificationRequest(
//                "Request Money",
//                "You Have Money Request of 100 RM",
//                "",
//                tokenList,
//                data,
//                "Request Money"
//        );
//
//        pushNotificationService.sendPushNotificationToMultipleUsers(pushNotificationRequest);

//        RequestMoneyModel requestMoneyModel=new RequestMoneyModel();
//        requestMoneyModel.setRequesterUserId(20);
//        requestMoneyModel.setRequestToUserId(20);
//        requestMoneyModel.setTransactionAmount(100);
//        requestMoneyService.sendNotificationToRequestie(requestMoneyModel);

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
     *
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

        String input = "{\"requestToUserId\":4,\"requesterUserId\":4,\"transactionAmount\":2.0}";
		String cipherText = "MGlu7KYLaj9YcLkW/+IUf44qLCepls19EqT+MHGziyb5AO2LHusczJAXEDmg7PDBhnA/tG1ujNmCgwmZX9ZmIh9fudnwsIikckxu7wnw5Bo\u003d";
        SecretKey key = aesUtils.secretKeyToString(ENCRYPTION_SECRETKEY);
        IvParameterSpec ivParameterSpec = aesUtils.generateIvPreloaded();
        System.out.println("Key " + aesUtils.stringToSecretKey(key));
        System.out.println("ivParameterSpec " + Base64.getEncoder().encodeToString(ivParameterSpec.getIV()));
        String algorithm = ENCRYPTION_ALGO;
//        String cipherText = aesUtils.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = aesUtils.decrypt(algorithm, cipherText, key, ivParameterSpec);
        System.out.println("input " + input + " plainText " + plainText);
        System.out.println("input " + input + " cipherText " + cipherText);
        System.out.println(new BCryptPasswordEncoder().matches("670b14728ad9902aecba32e22fa4f6bd",
                "$2a$10$oWerIkUefGZOZQk9wR8nKeGvrVLB1avpm/APCurap7/HwYFSfDBwS"));
//        Assertions.assertEquals(input, plainText);
    }
    @Autowired
    private KycCenterRepository kycCenterRepository;


    @Test
    void distanceTest() {
        double distanceInKM=10000d;
        double lat=10d;
        double lon=10d;
        List<KycCenterModel> kycCenterModelListReturn=new ArrayList<>();
        List<KycCenterModel> kycCenterModelList=kycCenterRepository.findAll();

        for(KycCenterModel kycCenterModel:kycCenterModelList){
            if(distance(kycCenterModel.getLatitude(),kycCenterModel.getLongitude(),
                    lat,lon,"K")<distanceInKM){
                kycCenterModelListReturn.add(kycCenterModel);
            }
        }

        System.out.println("orig"+new Gson().toJson(kycCenterModelList));
        System.out.println("new list"+new Gson().toJson(kycCenterModelListReturn));
        System.out.println("Date "+Constants.TIME_DATE+" == "+CommonMethods.getDate(Constants.TIME_DATE));

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

    @Test
    void testAESCBC() {
        String privateSecretKey = "globalSecretKey+mpinModel.getSecretkey();";
        String cipherText = AES.encrypt("mpinModel.getmPin()", privateSecretKey);
        String plaintext = AES.decrypt(cipherText, privateSecretKey);
        System.out.println("cipherText = " + cipherText);
        System.out.println("plaintext = " + plaintext);
        Assertions.assertEquals("mpinModel.getmPin()", plaintext);
    }

    @Test
    void testBCRYPT() {
        BCryptPasswordEncoder beBCryptPasswordEncoder = new BCryptPasswordEncoder(12,null);
        /**
         * send it by Email
         */
        System.out.println(beBCryptPasswordEncoder.encode("i am Password"));
        System.out.println(
                beBCryptPasswordEncoder.matches("i am Password",
                        beBCryptPasswordEncoder.encode("i am Password")));



    }

    @Test
    void testRegEx(){
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        String search="emailID:vaibhavyopmailcom,email:abc ";
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));

                System.out.println("matcher boy comming here= "+matcher.group(1)
                        +" "+matcher.group(2)+" "+matcher.group(3));
            }
        }
    }

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

    private  double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }

            System.out.println("distt:"+dist);

            return (dist);
        }
    }
}
