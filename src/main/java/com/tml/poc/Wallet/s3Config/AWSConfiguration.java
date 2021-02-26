package com.tml.poc.Wallet.s3Config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * S3 Bucket client Configuration
 */
@Configuration
public class AWSConfiguration {

    /**
     * Access key for S3
     */
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    /**
     * SecretKey Of S3 bucket
     */
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    /**
     * Region for S3 Bucket
     */
    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public BasicAWSCredentials basicAWSCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }

    /**
     *
     * @param awsCredentials
     * @return
     */
    @Bean
    public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials) {
        AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
        amazonS3Client.setRegion(Region.getRegion(Regions.fromName(region)));
        return amazonS3Client;
    }
}