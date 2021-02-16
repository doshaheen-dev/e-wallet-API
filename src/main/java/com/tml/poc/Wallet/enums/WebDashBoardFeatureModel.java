package com.tml.poc.Wallet.enums;

import java.util.ArrayList;

public class WebDashBoardFeatureModel {


    public static final String[] FEATURES_MODULE_LIST={
            "Login",
            "WRITE_WEB_USER",
            "WRITE_KYC_CENTER",
            "WRITE_MOBILE_USER",
            "WRITE_ROLE",
            "WRITE_E_KYC",


            "REPORT_MOBILE_USER",

            "REPORT_E_KYC",
            "REPORT_USER_TRANSACTIONS",
            "REPORT_USER_LOGIN_LOGOUT",
            "REPORT_WEB_USER",
            "REPORT_KYC_CENTER",
            "REPORT_ROLE",


            "DELETE_ROLE",
            "DELETE_WEB_USER",
            "DELETE_ROLE",
            "BLOCK_MOBILE_USER"
    };

    public String[] getFEATURES_MODULE_LIST() {
        return FEATURES_MODULE_LIST;
    }

}
