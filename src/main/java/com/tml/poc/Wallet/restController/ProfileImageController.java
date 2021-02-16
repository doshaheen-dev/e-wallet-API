package com.tml.poc.Wallet.restController;

import com.tml.poc.Wallet.s3Config.S3Wrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tml.poc.Wallet.config.MyBlobService;
import com.tml.poc.Wallet.exception.ResourceNotFoundException;
import com.tml.poc.Wallet.models.utilsmodels.FileUploadModelReq;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.repository.UserRepository;
import com.tml.poc.Wallet.utils.DataReturnUtil;
import com.tml.poc.Wallet.utils.Fileutils;

import java.io.*;
import java.util.List;
import java.util.Optional;

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


	@Autowired
	private S3Wrapper s3Wrapper;

//	@Autowired
//	private ApplicationProperties applicationProperties;


	@GetMapping("/")
	public List<String> blobitemst() {
		return myBlobService.listFiles();
	}

	@GetMapping("/download/{filename}")
	public Object download(@PathVariable String filename) throws IOException {
//		return myBlobService.downloadFile(filename).toByteArray();

		try {
			return  s3Wrapper.download(filename);
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.notFound();
		}
	}

	@PostMapping("/upload")
	public Object uploadFile(@Valid @RequestBody FileUploadModelReq fileReq) throws FileNotFoundException, IOException,ResourceNotFoundException {
		
		

		Optional<UserModel> userOptional = userRepository.findAllById(fileReq.getUserid());
		if (userOptional.isPresent()) {
			UserModel userModel = userOptional.get();

			String base64String = fileReq.getBase64String();

			fileReq.setBase64String(
					s3Wrapper.uploadPrev("userID"+fileReq.getUserid(),
							base64String,
							fileReq.getExtension())
			);

			userModel.setProfile_image(fileReq.getBase64String());
			userModel=userRepository.save(userModel);


			return ResponseEntity.ok(dataReturnUtils.setDataAndReturnResponseForRestAPI(userModel));
			
			
		} else {
//			throw new ResourceNotFoundException("User Not found " + fileReq.getUserid());

			return ResponseEntity.notFound();
		}

		

	}
}