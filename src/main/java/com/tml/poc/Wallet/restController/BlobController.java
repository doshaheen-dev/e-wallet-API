package com.tml.poc.Wallet.restController;

import org.springframework.beans.factory.annotation.Value;  
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;


import antlr.ByteBuffer;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobErrorCode;
import com.azure.storage.blob.models.BlobStorageException;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
 
//@RestController
@RequestMapping("blob")
public class BlobController {

	@Value("${blob}")
	private Resource blobfiles;

	
	@GetMapping
	public String readBlobFile() throws IOException {
		
		return StreamUtils.copyToString(this.blobfiles.getInputStream(), Charset.defaultCharset());
	}

	@PostMapping
	public String writeBlobFile(@RequestBody String dataStr) throws IOException, Exception {
		
//		DefaultEndpointsProtocol=https;AccountName=tmlwallet;AccountKey=+vtsTAqOnh9dcV+jIkPG6mgRLsRTgpyMKlSrotUD3xznW2EvunXGsjVY+qoj96Q//A3DmhqAqQ0njj6VeIGyqQ==;EndpointSuffix=core.windows.net
		BlobContainerClient containerClient=null;
		String yourSasToken = 
				"+vtsTAqOnh9dcV+jIkPG6mgRLsRTgpyMKlSrotUD3xznW2EvunXGsjVY+qoj96Q//A3DmhqAqQ0njj6VeIGyqQ==";
		/* Create a new BlobServiceClient with a SAS Token */
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
		    .endpoint("https://tmlwallet.core.windows.net")
		    .sasToken(yourSasToken)
		    .buildClient();
		
		/* Create a new container client */
		try {
		    containerClient = blobServiceClient.createBlobContainer("image");
		} catch (BlobStorageException ex) {
		    // The container may already exist, so don't throw an error
		    if (!ex.getErrorCode().equals(BlobErrorCode.CONTAINER_ALREADY_EXISTS)) {
		        throw ex;
		    }
		}

		/* Upload the file to the container */
		BlobClient blobClient = containerClient.getBlobClient("my-remote-file.jpg");
		blobClient.uploadFromFile("my-local-file.jpg");
		return "file was updated";
	}
}