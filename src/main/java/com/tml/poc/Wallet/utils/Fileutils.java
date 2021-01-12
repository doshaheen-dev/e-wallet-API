package com.tml.poc.Wallet.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;


@Service
public class Fileutils {
	// Method which write the bytes into a file
	public File writeByte(byte[] bytes,String ext) throws IOException {

		File file = new File(UUID.randomUUID().toString()+"."+ext.replace(".", ""));

		// Initialize a pointer
		// in file using OutputStream
		OutputStream os = new FileOutputStream(file);

		// Starts writing the bytes in it
		os.write(bytes);
		System.out.println("Successfully" + " byte inserted");

		// Close the file
		os.close();
		return file;

	}
	
	
	public  long getFileSizeKiloBytes(File file) {
		return (long) file.length() /1024 ;
	}
	public  long getFileSizeBytes(File file) {
		return (long) file.length()  ;
	}

	
	
}
