package com.tml.poc.Wallet.services;

import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import com.tml.poc.Wallet.repository.WebUserRoleRepository;
import com.tml.poc.Wallet.utils.Constants;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tml.poc.Wallet.utils.Constants.ROLE_ADDED_SUCCESSFULLY;
import static com.tml.poc.Wallet.utils.Constants.ROLE_DELETED_SUCCESSFULLY;

@Service
public class WebUserRoleService {

    @Autowired
    private WebUserRoleRepository webUserRoleRepository;

    /**
     * add WebRole
     * @param webUserRoleModel
     * @return
     * @throws InvalidInputException
     */
    public Object addWebUserRole(WebUserRoleModel webUserRoleModel) throws InvalidInputException {
        Optional<WebUserRoleModel> webUserRoleModelOptional=
                webUserRoleRepository.findByRoleCodeAndIsActive(webUserRoleModel.getRoleCode(),true);
        if(webUserRoleModelOptional.isPresent()){
            throw new InvalidInputException(Constants.ROLE_ALREADY_ADDED);
        }
        return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                webUserRoleRepository.save(webUserRoleModel),ROLE_ADDED_SUCCESSFULLY));
    }

    /**
     * update WebUser Role
     * @param webUserRoleModel
     * @return
     * @throws InvalidInputException
     */
    public Object updateWebUserRole(WebUserRoleModel webUserRoleModel) throws InvalidInputException {
        Optional<WebUserRoleModel> webUserRoleModelOptional=
                webUserRoleRepository.findByRoleCodeAndIsActive(webUserRoleModel.getRoleCode(),true);
        if(webUserRoleModelOptional.isPresent()){
            WebUserRoleModel webUserRoleModelFromDB=webUserRoleModelOptional.get();
            webUserRoleModelFromDB.setRoleName(webUserRoleModel.getRoleName());
            webUserRoleModelFromDB.setAccessLayerModule(webUserRoleModel.getAccessLayerModule());
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                    webUserRoleRepository.save(webUserRoleModel),ROLE_ADDED_SUCCESSFULLY));
        }
        throw new InvalidInputException(Constants.ROLE_ALREADY_ADDED);
    }

    /**
     * get webRole by id
     * else get all
     * @param id
     * @return
     */
    public Object getWebUserRole(long id){
        if(id==0){
            List<WebUserRoleModel> webUserRoleModelList=webUserRoleRepository.findAllByIsActive(true);
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                    webUserRoleModelList,ROLE_ADDED_SUCCESSFULLY));
        }else {
            List<WebUserRoleModel> webUserRoleModelList=webUserRoleRepository.findAllByIdAndIsActive(id,true);
            return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                    webUserRoleModelList,ROLE_ADDED_SUCCESSFULLY));
        }
    }

    public Object deleteWebUserRole(long id) throws ResourceNotFoundException {
        Optional<WebUserRoleModel> webUserRoleModelOptional=
                webUserRoleRepository.findByIdAndIsActive(id,true);
        if(!webUserRoleModelOptional.isPresent()){
            throw new ResourceNotFoundException(Constants.ROLE_NOT_FOUND);
        }
        WebUserRoleModel webUserRoleModel=webUserRoleModelOptional.get();
        webUserRoleModel.setActive(false);
        return  ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
                webUserRoleRepository.save(webUserRoleModel),ROLE_DELETED_SUCCESSFULLY));
    }
}
