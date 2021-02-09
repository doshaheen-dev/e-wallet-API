package com.tml.poc.Wallet.restController;

import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.tml.poc.Wallet.exception.InvalidInputException;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.services.WebUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tml.poc.Wallet.models.rolePrevilage.WebUserRoleModel;
import com.tml.poc.Wallet.repository.WebUserRoleRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;

import javax.validation.Valid;

@RequestMapping("/webuser/role")
@RestController
public class WebUserRoleController {

	
	@Autowired
	private WebUserRoleRepository webUserRoleRepository;

	@Autowired
	private WebUserRoleService webUserRoleService;

	@PostMapping("/add")
	public Object addWebUserRole(@Valid @RequestBody WebUserRoleModel webUserRoleModel ) throws InvalidInputException {
		return webUserRoleService.addWebUserRole(webUserRoleModel);
	}

	@PutMapping("/update")
	public Object updateWebUserRole(@Valid @RequestBody WebUserRoleModel webUserRoleModel) throws InvalidInputException {
		return webUserRoleService.updateWebUserRole(webUserRoleModel);
	}

	@GetMapping("/get")
	public Object getWebUserRole(@QueryParam(value = "id") long id){
		return webUserRoleService.getWebUserRole(id);
	}

	@DeleteMapping("/delete")
	public Object deleteWebUserRole(@RequestParam(name = "id") long id) throws ResourceNotFoundException {
		return webUserRoleService.deleteWebUserRole(id);
	}
	
}
