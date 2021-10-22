package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.dao.user.SearchCriteria;
import com.tml.poc.Wallet.iDao.IUserDAO;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.services.UserSearchService;
import com.tml.poc.Wallet.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Search Mobile USer
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("user/search")
public class UserSearchController {

    @Autowired
    private IUserDAO api;

    @Autowired
    private UserSearchService userSearchService;

    /**
     * Mobile User search By Mobile Number
     * @param mobileNumber
     * @return
     */
    @GetMapping("/mobile/{mobileNumber}")
    private ResponseEntity getUserByMobileNumber(@PathVariable(name = "mobileNumber")String mobileNumber){
        return userSearchService.searchUserByMobile(mobileNumber);
    }

    /**
     * mobile user By QRCode
     * @param qrCode
     * @return
     */
    @GetMapping("/qrcode/{qrCode}")
    private ResponseEntity getUserByQRCode(@PathVariable(name = "qrCode")String qrCode){
        return userSearchService.searchUserByUUID(qrCode);
    }

    /**
     * get All Mobil Users
     * @return
     */
    @GetMapping("/getAll")
    private ResponseEntity getAllUser(){
        return userSearchService.getAllUser();
    }


    /**
     * Search Mobile User
     * @param search: Search Quary here <SearchKey> (:/</>) <SearchValue>    :<> are oparators
     * @param fromDdate
     * @param toDate
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseBody
    public List<UserModel> findAll(@RequestParam(value = "search", required = false) String search,
                                   @RequestParam(value = "fromDate", required = false)
                                   @DateTimeFormat(pattern= Constants.TIME_DATE) Date fromDdate,
                                   @RequestParam(value = "toDate", required = false)
                                       @DateTimeFormat(pattern= Constants.TIME_DATE) Date toDate,
                                   @RequestParam(value = "pageSize",defaultValue ="20", required = false) int pageSize,
                                   @RequestParam(value = "pageNo",defaultValue ="0",required = false) int pageNo) {
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1),
                        matcher.group(2), matcher.group(3)));

            }
        }

        return api.searchUser(params,fromDdate,toDate,pageSize,pageNo);
    }
}
