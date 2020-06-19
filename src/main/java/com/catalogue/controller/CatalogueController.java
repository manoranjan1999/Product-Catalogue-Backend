package com.catalogue.controller;

import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.catalogue.service.CatalogueService;
import com.catalogue.exceptionhandling.CatalogueServiceException;
import com.catalogue.model.MyCatalogue;

//'CatalogueController' class having certain methods that manage all the request and response coming from client side(http://localhost:4200/).

@CrossOrigin(origins = "*")
@RestController
public class CatalogueController {
	@Autowired
	private CatalogueService serviceObj;

	// adding product detail one by one to database.
	@Transactional
	@PostMapping("/addProduct")
	public MyCatalogue addAllDetails(@RequestBody MyCatalogue catalogueObj) {
		return serviceObj.addData(catalogueObj);
	}

	// Retrive all the products from database and send it on respective request.
	@Transactional
	@GetMapping("/displayProduct")
	public List<MyCatalogue> displayProduct() {
		List<MyCatalogue> catalogueObj = serviceObj.getAllCatalogueDetails();
		return catalogueObj;
	}

	// Save the image comes as request, in local file system
	@PostMapping(value = "/uploadImage")
	public void uploadImage(@RequestParam("imageData") MultipartFile file1) throws IOException {
		System.out.println(file1.getOriginalFilename());
		final String folder = "/home/manoranjan/Desktop/projectImage/";
		Path path = Paths.get(folder + file1.getOriginalFilename());
		
		Files.copy(file1.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		//Files.write(path, file1.getBytes());
		
	}

	// Retrive all Image from folder and return it as response
	@Transactional
	@GetMapping("/getAllImage")
	public ResponseEntity<Map<String, String>> getAllImage() {
		return serviceObj.getImages();
	}

	// Search products details by product name
	@Transactional
	@GetMapping("/searchProduct/{productName}")
	public List<MyCatalogue> searchProduct(@PathVariable String productName) throws CatalogueServiceException {
		return serviceObj.getByName(productName);
	}

	// delete product details by product name
	@Transactional
	@DeleteMapping("/deleteProduct/{index1}")
	public String deleteByName(@PathVariable int index1) {
		return serviceObj.delByIndex1(index1);
	}
	
}
