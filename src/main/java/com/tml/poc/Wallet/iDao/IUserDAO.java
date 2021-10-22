package com.tml.poc.Wallet.iDao;

import com.tml.poc.Wallet.dao.user.SearchCriteria;
import com.tml.poc.Wallet.models.usermodels.UserModel;

import java.util.Date;
import java.util.List;

/**
 * User DAO Interface for Repository Search Query
 */
public interface IUserDAO {
    List<UserModel> searchUser(List<SearchCriteria> params,
                               Date fromdate,
                               Date toDate,
                               int pageSize,
                               int pageNo
                               );

    void save(UserModel entity);
}
