package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import com.tml.poc.Wallet.repository.WebUserRoleRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@RequestMapping("/webuser/role")
@RestController
public class WebUserRoleController {

	
	@Autowired
	private WebUserRoleRepository webUserRoleRepository;

	@PostMapping("/add")
	private Object addWebUserRole(@RequestBody WebUserRoleModel emlRoleModel ) {

		return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
				webUserRoleRepository.save(emlRoleModel),"Role Added Successfully"));
	}
	
	
}
