package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.models.utilsmodels.DashboardModel;
import com.tml.poc.Wallet.repository.TransactionRepository;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @GetMapping("/getDashboard")
    private Object getDashboard(@RequestParam(value = "transactionDateFrom",required = false)@DateTimeFormat(pattern= Constants.TIME_DATE) Date transDate){

        DashboardModel dashboardModel=new DashboardModel();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date date = cal.getTime();
        dashboardModel.setLastWeekuserCount(userRepository.countAllByCreatedAtAfter(date));
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
         date = cal.getTime();
        dashboardModel.setLastMonthuserCount(userRepository.countAllByCreatedAtAfter(date));
        dashboardModel.setTotalUserCount(userRepository.count());
        dashboardModel.setTransactionCount(transactionRepository.countAllByCreatedAtAfter(transDate));

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(dashboardModel));

    }
}