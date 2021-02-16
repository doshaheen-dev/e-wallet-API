package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.webuser.WebUserModel;
import com.tml.poc.Wallet.services.WebUserService;
import com.tml.poc.Wallet.utils.DataReturnUtil;

@RequestMapping("/webuser")
@RestController
public class WebUserController {

	
	@Autowired
	private WebUserService emplService;
	
	@Autowired
	private DataReturnUtil dataReturnUtil;
		
	@PostMapping("/add")
	private ResponseEntity addWebUser(@Valid @RequestBody WebUserModel webUserModel)
			throws ResourceNotFoundException {

		return emplService.addWebUser(webUserModel);
	}

	@PutMapping("/update")
	private ResponseEntity updateWebUser(@Valid @RequestBody WebUserModel webUserModel)
			throws ResourceNotFoundException {
		
		return emplService.updateWebUser(webUserModel);
	}
	
	@DeleteMapping("/{id}/delete")
	private ResponseEntity deleteWebUser(@PathVariable long id )
			throws ResourceNotFoundException {
		
		return emplService.deleteWebUser(id);
	}

	@GetMapping("/{id}/get")
	private ResponseEntity getWebUser(@PathVariable long id) throws ResourceNotFoundException {
		return emplService.getWebUser(id);
	}

	@GetMapping("/getAll")
	private ResponseEntity getAllWebUser() throws ResourceNotFoundException {
		return emplService.getAllWebUser();
	}



}
