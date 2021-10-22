package com.tml.poc.Wallet.services;


import com.tml.poc.Wallet.dao.user.SearchCriteria;
import com.tml.poc.Wallet.models.transaction.TransactionModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.models.utilsmodels.LoginHistoryModel;
import com.tml.poc.Wallet.models.webuser.WebUserModel;
import com.tml.poc.Wallet.repository.LoginHistoryRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginHistoryService {

    @Autowired
    private LoginHistoryRepository loginHistoryRepository;


    /**
     * get All Login History
     * @param pageSize
     * @param pageNo
     * @param sort
     * @return
     */
    public ResponseEntity searchAllLoginHistory(int pageSize, int pageNo, String sort)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());

        Page<LoginHistoryModel> pagedResult = loginHistoryRepository.findAll(paging);
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(
                pagedResult));
    }

    /**
     * get All Mobile Login History
     */
    public ResponseEntity searchMobileAllLoginHistory(int pageSize, int pageNo, String sort)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());

        Page<LoginHistoryModel> pagedResult = loginHistoryRepository.findAllByWebUserId(new WebUserModel(0),paging);

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(
                pagedResult));
    }

    /**
     * get All WebLogin History
     * @param pageSize
     * @param pageNo
     * @param sort
     * @return
     */
    public ResponseEntity searchWebAllLoginHistory(int pageSize, int pageNo,String sort)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sort).descending());

        Page<LoginHistoryModel> pagedResult = loginHistoryRepository.findAllByMobileUserId(new UserModel(0),paging);

        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseForRestAPI(
                pagedResult));
    }
}
