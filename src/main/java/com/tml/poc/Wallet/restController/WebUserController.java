package com.tml.poc.Wallet.restController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.WebUserModel;
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
		
		return ResponseEntity.ok(new DataReturnUtil().setDataAndReturnResponseSuccess(
				emplService.addWebUser(webUserModel),"Web User Added"));
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
	
	
	
	
}
