package com.tml.poc.Wallet.config;

import com.azure.storage.blob.BlobClient; 
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * BLOB Services to for Azure
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MyBlobService {
	@Autowired
    private  AzureBlobProperties azureBlobProperties;

    private BlobContainerClient containerClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(azureBlobProperties.getConnectionstring()).buildClient();
        BlobContainerClient container = serviceClient.getBlobContainerClient(azureBlobProperties.getContainer());
        return container;
    }

    /**
     * Get list of File of BLOB
     * @return
     */
    public List<String> listFiles() {
        BlobContainerClient container = containerClient();
        List<String> list = new ArrayList<String>();
        for (BlobItem blobItem : container.listBlobs()) {
            list.add(blobItem.getName());
        }
        return list;
    }

    /**
     * Download FIle and get into ByteAray Stram
     * @param blobitem
     * @return
     */
    public ByteArrayOutputStream downloadFile(String blobitem) {
        BlobContainerClient containerClient = containerClient();
        BlobClient blobClient = containerClient.getBlobClient(blobitem);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        blobClient.download(os);
        
        return os;
    }

    /**
     * Store File into AZURE Storage
     * @param filename
     * @param content
     * @param length
     * @return
     */
    public String storeFile(String filename, InputStream content, long length) {
        BlobClient client = containerClient().getBlobClient(filename);
        if (client.exists()) {
        } else {
            client.upload(content, length);
        }

        return "File uploaded with success!";
    }

}