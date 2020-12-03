package com.tml.poc.Wallet.restController;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.tml.poc.Wallet.config.MyBlobService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blobfile")
@RequiredArgsConstructor
@Slf4j
public class MyBlobRestController {
	@Autowired
    private  MyBlobService myBlobService;

    @GetMapping("/")
    public List<String> blobitemst() {
        return myBlobService.listFiles();
    }


    @GetMapping("/download/{filename}")
    public byte[] download(@PathVariable String filename) {
         return myBlobService.downloadFile(filename).toByteArray();
    }
    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) throws IOException {
       System.out.println("found file"+ file);
        myBlobService.storeFile(file.getOriginalFilename(),file.getInputStream(), file.getSize());
        return file.getOriginalFilename() + " Has been saved as a blob-item!!!";

    }
}