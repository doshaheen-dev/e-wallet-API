package com.tml.poc.Wallet.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("azure.myblob")
public class AzureBlobProperties {
    private String connectionstring;
    private String container;
	public String getConnectionstring() {
		return connectionstring;
	}
	public void setConnectionstring(String connectionstring) {
		this.connectionstring = connectionstring;
	}
	public String getContainer() {
		return container;
	}
	public void setContainer(String container) {
		this.container = container;
	}
    
    
}