package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.exception.TransactionFailedException;
import com.tml.poc.Wallet.models.mpin.MPINModel;
import com.tml.poc.Wallet.models.transaction.SendMoneyModel;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.transaction.UserBallanceModel;
import com.tml.poc.Wallet.serviceImpl.TransactionImplService;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private MPinServices mPinServices;

    @Autowired
    private MobileUserBallanceService mobileUserBallanceService;


    @Autowired
    private TransactionImplService transactionImplService;

    @Autowired
    private SendMoneyService sendMoneyService;



    /**
     * send money from Wallet to Wallet
     * @param sendMoneyModel
     * @return
     * @throws ResourceNotFoundException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     * @throws TransactionFailedException
     */
    public ResponseEntity sendMoneyTransaction(SendMoneyModel sendMoneyModel) throws
            ResourceNotFoundException, BadPaddingException,
            InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            IllegalBlockSizeException, NoSuchPaddingException,
            InvalidKeyException, InvalidKeySpecException, TransactionFailedException {

        try {
            isSenderUserPresent(sendMoneyModel.getSenderuserID());
            isReceiverUserPresent(sendMoneyModel.getSenderuserID());

            isMPINCorrect(sendMoneyModel.getSenderuserID(), sendMoneyModel.getMpin());
            float availableBalance = senderBalanceIsSufficient(sendMoneyModel.getSenderuserID(), sendMoneyModel.getTransactionAmount());
            TransactionModel transactionModelDebited = debitAmount(sendMoneyModel);
            TransactionModel transactionModelCredited = creditAmount(sendMoneyModel);

            UpdateBallanceOfDebitUser(transactionModelDebited);
            UpdateBallanceOfCreditUser(transactionModelCredited);

            sendMoneyModel.setStatusRespMessage(Constants.TRANSACTION_COMPLETED_SUCCESSFULLY);
            return ResponseEntity.ok(new DataReturnUtil()
                    .setDataAndReturnResponseSuccess(sendMoneyService.sendMoneyToTransaction(sendMoneyModel)
                            , Constants.TRANSACTION_COMPLETED_SUCCESSFULLY));

        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            /**
             * fail transaction
             */
            sendMoneyModel.setStatusRespMessage(e.getMessage());
            sendMoneyService.sendMoneyToTransaction(sendMoneyModel);
            throw new ResourceNotFoundException(e.getMessage());
        } catch (TransactionFailedException e) {
            e.printStackTrace();
            /**
             * fail Trasnaction
             */
            sendMoneyModel.setStatusRespMessage(e.getMessage());
            sendMoneyService.sendMoneyToTransaction(sendMoneyModel);
            throw new TransactionFailedException(e.getMessage());

        }

    }

    public boolean isSenderUserPresent(long senderUserID) throws ResourceNotFoundException {
        return userService.isGetUserById(senderUserID);

    }

    public boolean isReceiverUserPresent(long receiverUserID) throws ResourceNotFoundException {

        return userService.isGetUserById(receiverUserID);
    }

    public boolean isMPINCorrect(long senderUserId, String mpinOfSenderUser) throws BadPaddingException, ResourceNotFoundException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
        MPINModel mpinModel = new MPINModel();
        mpinModel.setmPin(mpinOfSenderUser);
        mpinModel.setUserID(senderUserId);
        return mPinServices.isMPINVerified(mpinOfSenderUser,mpinModel);
    }

    public float senderBalanceIsSufficient(long senderUserID, float transactionAmount) throws ResourceNotFoundException {
        UserBallanceModel userBallanceModel = mobileUserBallanceService.getUserBallanceByUserID(senderUserID);
        if (transactionAmount < userBallanceModel.getAvailableBalance()) {
            return userBallanceModel.getAvailableBalance();
        } else {
            throw new ResourceNotFoundException(Constants.INSUFFICIENT_BALANCE);
        }
    }

    public TransactionModel debitAmount(SendMoneyModel sendMoneyModel) throws TransactionFailedException {
        TransactionModel transactionModelDebit = transactionImplService.debitTransaction(sendMoneyModel);
        return transactionModelDebit;
    }

    public TransactionModel creditAmount(SendMoneyModel sendMoneyModel) throws TransactionFailedException {
        TransactionModel transactionModelDebit = transactionImplService.creditTransaction(sendMoneyModel);
        return transactionModelDebit;
    }

    public UserBallanceModel UpdateBallanceOfDebitUser(TransactionModel transactionModel) {

        UserBallanceModel userBallanceModel = mobileUserBallanceService.getUserBallanceByUserID(transactionModel.getUserID());
        userBallanceModel.setAvailableBalance(transactionModel.getAvailableBalance());
        mobileUserBallanceService.saveNewBallance(userBallanceModel);
        return userBallanceModel;
    }

    public UserBallanceModel UpdateBallanceOfCreditUser(TransactionModel transactionModel) {
        UserBallanceModel userBallanceModel = mobileUserBallanceService.getUserBallanceByUserID(transactionModel.getUserID());
        userBallanceModel.setAvailableBalance(transactionModel.getAvailableBalance());
        mobileUserBallanceService.saveNewBallance(userBallanceModel);
        return userBallanceModel;
    }

    /**
     * TODO:send notification to debitor
     */
    public Object SendNotificatioTODebitUser() {

        return "";
    }

    /**
     * TODO:send notification to receiver
     */
    public Object SendNotificatioTOCreditUser() {
        return "";
    }


}
