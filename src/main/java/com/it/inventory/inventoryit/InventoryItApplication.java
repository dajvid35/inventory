package com.it.inventory.inventoryit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.it.itinventory.inventoryit.fileUpload.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class InventoryItApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryItApplication.class, args);
	}

}
