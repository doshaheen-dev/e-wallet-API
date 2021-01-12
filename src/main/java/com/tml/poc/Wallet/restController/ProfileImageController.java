package com.tml.poc.Wallet.restController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tml.poc.Wallet.config.MyBlobService;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.FileUploadModelReq;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import com.tml.poc.Wallet.utils.Fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/blobfile")
@RequiredArgsConstructor
@Slf4j
public class ProfileImageController {
	@Autowired
	private MyBlobService myBlobService;

	@Autowired
	private Fileutils fileutils;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DataReturnUtil dataReturnUtils;


	@GetMapping("/")
	public List<String> blobitemst() {
		return myBlobService.listFiles();
	}

	@GetMapping("/download/{filename}")
	public Object download(@PathVariable String filename) {
//		return myBlobService.downloadFile(filename).toByteArray();
		
		return new ByteArrayResource(myBlobService.downloadFile(filename).toByteArray());
	}

	@PostMapping("/upload")
	public Object uploadFile(@Valid @RequestBody FileUploadModelReq fileReq) throws Exception {
		
		

		Optional<UserModel> userOptional = userRepository.findAllById(fileReq.getUserid());
		if (userOptional.isPresent()) {
			UserModel userModel = userOptional.get();

			String base64String = fileReq.getBase64String();

			File file = fileutils.writeByte(Base64.decodeBase64(base64String),
					fileReq.getExtension());

			InputStream targetStream = new FileInputStream(file);
			
//			myBlobService.storeFile(file.getName(), targetStream, fileutils.getFileSizeKiloBytes(file));

			fileReq.setBase64String(myBlobService.storeFile(file.getName(), targetStream, 
					fileutils.getFileSizeBytes(file)));
			
			userModel.setProfile_image(file.getName());
			userModel=userRepository.save(userModel);
			if(file.delete())                      //returns Boolean value  
			{  
			System.out.println(file.getName() + " deleted");   //getting and printing the file name  
			}  
			
			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userModel));
			
			
		} else {
			throw new ResourceNotFoundException("User Not found " + fileReq.getUserid());
		}

		

	}
}