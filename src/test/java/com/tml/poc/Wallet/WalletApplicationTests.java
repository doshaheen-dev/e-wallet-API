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

   
}
