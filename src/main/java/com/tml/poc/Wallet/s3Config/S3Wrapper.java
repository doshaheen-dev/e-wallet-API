package com.tml.poc.Wallet.s3Config;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.tml.poc.Wallet.utils.Fileutils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class S3Wrapper {

    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${app.awsServices.bucketName}")
    private String bucket;

    @Autowired
    private Fileutils fileutils;

    /**
     * any base64String will be save to s3 bucket
     * @param prefix
     * @param base64String
     * @param extension
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String uploadPrev(String prefix,String base64String, String extension)
            throws FileNotFoundException , IOException{

        File file = null;
            file = fileutils.writeByte(Base64.decodeBase64(base64String),
                    extension);
        InputStream targetStream = new FileInputStream(file);
        String filename=prefix+"-"+UUID.randomUUID().toString()+extension;
        upload(targetStream, filename);
        if(file.delete())                      //returns Boolean value
        {
            System.out.println(file.getName() + " deleted");   //getting and printing the file name
        }
        return filename;
    }

    public PutObjectResult upload(InputStream inputStream, String uploadKey) {
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey,
                    inputStream, new ObjectMetadata());

            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

            PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);

            IOUtils.closeQuietly(inputStream);

            return putObjectResult;
        } catch (SdkClientException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<PutObjectResult> upload(MultipartFile[] multipartFiles) {
        List<PutObjectResult> putObjectResults = new ArrayList<>();

        Arrays.stream(multipartFiles)
                .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
                .forEach(multipartFile -> {
                    try {
                        putObjectResults.add(upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return putObjectResults;
    }

    public ResponseEntity<byte[]> download(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);

        S3Object s3Object = amazonS3Client.getObject(getObjectRequest);

        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

        byte[] bytes = IOUtils.toByteArray(objectInputStream);

        String fileName = URLEncoder.encode(key, "UTF-8").replaceAll("\\+", "%20");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentLength(bytes.length);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<>(bytes, httpHeaders, HttpStatus.OK);
    }

    public List<S3ObjectSummary> list() {
        ObjectListing objectListing = amazonS3Client.listObjects(new ListObjectsRequest().withBucketName(bucket));

        List<S3ObjectSummary> s3ObjectSummaries = objectListing.getObjectSummaries();

        return s3ObjectSummaries;
    }
}