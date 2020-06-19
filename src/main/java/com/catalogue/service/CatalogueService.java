package com.catalogue.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.catalogue.exceptionhandling.CatalogueServiceException;
import com.catalogue.model.MyCatalogue;

public interface CatalogueService {
	public MyCatalogue addData(MyCatalogue catalogueObj);
	
	public List<MyCatalogue> getAllCatalogueDetails();
	
	public List<MyCatalogue> getByName(String productName) throws CatalogueServiceException;
	
	public String delByIndex1(int index1);
	
	//public MultipartFile uploadImage(MultipartFile file); 
	
	public ResponseEntity<Map<String, String>> getImages();
}
