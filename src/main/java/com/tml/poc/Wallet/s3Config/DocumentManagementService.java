package com.tml.poc.Wallet.s3Config;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Chamith
 */
public interface DocumentManagementService {
    void uploadMultipleFiles(List<MultipartFile> multipartFiles);
}
