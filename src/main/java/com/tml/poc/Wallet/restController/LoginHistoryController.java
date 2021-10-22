package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.repository.LoginHistoryRepository;
import com.tml.poc.Wallet.services.LoginHistoryService;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * For Login History this Controller works
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login/history")
public class LoginHistoryController {

    @Autowired
    private LoginHistoryService loginHistoryService;

    /**
     * get all Login History
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return
     */
    @GetMapping("/all")
    private ResponseEntity getAll(@RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
                                  @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
                                  @RequestParam(defaultValue = "id", name = "sort") String sort) {
        return loginHistoryService.searchAllLoginHistory(pageSize, pageNo, sort);
    }

    /**
     * get All mobile Users LoginHistory
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return
     */
    @GetMapping("/mobile/user")
    private ResponseEntity getAllByMobileUserId(@RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
                                                @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
                                                @RequestParam(defaultValue = "id", name = "sort") String sort) {
        return loginHistoryService.searchMobileAllLoginHistory(pageSize, pageNo, sort);
    }

    /**
     * get All web User Login History
     * @param pageNo
     * @param pageSize
     * @param sort
     * @return
     */
    @GetMapping("/web/user")
    private ResponseEntity getAllByWebUserId(@RequestParam(defaultValue = "0", name = "pageNo") int pageNo,
                                             @RequestParam(defaultValue = "20", name = "pageSize") int pageSize,
                                             @RequestParam(defaultValue = "id", name = "sort") String sort) {
        return loginHistoryService.searchWebAllLoginHistory(pageSize, pageNo, sort);
    }
}
