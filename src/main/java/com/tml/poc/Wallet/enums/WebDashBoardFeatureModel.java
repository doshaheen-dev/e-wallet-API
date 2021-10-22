package com.tml.poc.Wallet.enums;

import java.util.ArrayList;

/**
 * User Feature shown for Web Pannel for Privilages
 */
public class WebDashBoardFeatureModel {


    public static final String[] FEATURES_MODULE_LIST={
            "WRITE_WEB_USER",
            "WRITE_ROLE",
            "WRITE_SETTING",

            "WRITE_MOBILE_USER",
            "WRITE_MOBILE_USER_TRANSACTION",
            "WRITE_E_KYC",
            "WRITE_KYC_CENTER",
            "WRITE_LOGIN_HISTORY",
            "WRITE_WALLET_RECHARGE",

            "REPORT_MOBILE_USER",
            "REPORT_MOBILE_USER_TRANSACTION",
            "REPORT_E_KYC",
            "REPORT_LOGIN_HISTORY",

            "BLOCK_MOBILE_USER",
            "BLOCK_MOBILE_USER_TRANSACTION",

            "DELETE_WEB_USER",
            "DELETE_ROLE"
    };

    public String[] getFEATURES_MODULE_LIST() {
        return FEATURES_MODULE_LIST;
    }

}
