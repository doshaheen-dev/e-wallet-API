package com.tml.poc.Wallet.enums;

import java.util.ArrayList;

public class WebDashBoardFeatureModel {


    public static final String[] FEATURES_MODULE_LIST={
            "Login",
            "WRITE_WEB_USER",
            "DELETE_WEB_USER",
            "REPORT_WEB_USER",

            "WRITE_ROLE",
            "DELETE_ROLE",
            "REPORT_ROLE",

            "WRITE_MOBILE_USER",
            "BLOCK_MOBILE_USER",
            "REPORT_MOBILE_USER",

            "REPORT_E_KYC",
            "WRITE_E_KYC",

            "REPORT_USER_TRANSACTIONS",
            "REPORT_USER_LOGIN_LOGOUT",

            "READ_KYC_CENTER",
            "WRITE_KYC_CENTER"
    };

    public String[] getFEATURES_MODULE_LIST() {
        return FEATURES_MODULE_LIST;
    }

}
