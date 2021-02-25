package com.tml.poc.Wallet.dao;

import com.tml.poc.Wallet.dao.user.SearchCriteria;
import com.tml.poc.Wallet.dao.user.UserSearchQueryCriteriaConsumer;
import com.tml.poc.Wallet.iDao.IUserDAO;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * This DAO is using override Search User function
 * actual Data shared here with page, from-to Date and parameter
 */
@Repository
public class UserModelDAO implements IUserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserModel> searchUser(List<SearchCriteria> params,
                                      Date fromdate,
                                      Date toDate,
                                      int pageSize,
                                      int pageNo) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserModel> query = builder.createQuery(UserModel.class);
        Root r = query.from(UserModel.class);

        Predicate predicate = builder.conjunction();

        UserSearchQueryCriteriaConsumer searchConsumer =
                new UserSearchQueryCriteriaConsumer(predicate, builder, r);
        params.stream().forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();


        query.where(predicate);

        if (toDate != null)
//            predicate
            query.where(builder.lessThanOrEqualTo(r.<Date>get("createdAt"), toDate));
        if (fromdate != null)
            query.where(builder.greaterThanOrEqualTo(r.<Date>get("createdAt"), fromdate));
        // Added orderBy clause
        TypedQuery typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult(pageNo);
        typedQuery.setMaxResults(pageSize);


        List<UserModel> result = typedQuery.getResultList();
        return result;
    }

    @Override
    public void save(UserModel entity) {
        entityManager.persist(entity);
    }
}