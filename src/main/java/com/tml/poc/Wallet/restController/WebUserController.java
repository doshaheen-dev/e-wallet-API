package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.webuser.WebUserModel;
import com.tml.poc.Wallet.services.WebUserService;
import com.tml.poc.Wallet.utils.DataReturnUtil;

/**
 * Web User Crud
 */
@RequestMapping("/webuser")
@RestController
public class WebUserController {

	
	@Autowired
	private WebUserService emplService;
	
	@Autowired
	private DataReturnUtil dataReturnUtil;

	/**
	 * Add Web USer
	 * @param webUserModel
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PostMapping("/add")
	private ResponseEntity addWebUser(@Valid @RequestBody WebUserModel webUserModel)
			throws ResourceNotFoundException {

		return emplService.addWebUser(webUserModel);
	}

	/**
	 * WebUser Update
	 * @param webUserModel
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/update")
	private ResponseEntity updateWebUser(@Valid @RequestBody WebUserModel webUserModel)
			throws ResourceNotFoundException {
		
		return emplService.updateWebUser(webUserModel);
	}

	/**
	 * delete Webuser by Id
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping("/{id}/delete")
	private ResponseEntity deleteWebUser(@PathVariable long id )
			throws ResourceNotFoundException {
		
		return emplService.deleteWebUser(id);
	}

	/**
	 * Web USer get By User ID
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/{id}/get")
	private ResponseEntity getWebUser(@PathVariable long id) throws ResourceNotFoundException {
		return emplService.getWebUser(id);
	}

	/**
	 * get All Web-User
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/getAll")
	private ResponseEntity getAllWebUser() throws ResourceNotFoundException {
		return emplService.getAllWebUser();
	}



}
