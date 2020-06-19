package com.catalogue.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.catalogue.exceptionhandling.CatalogueServiceException;
import com.catalogue.model.MyCatalogue;
import com.catalogue.repository.CatalogueRepository;

//'CatalogueServiceImpl' class contain all the business logic that can be send to client.

@Service
public class CatalogueServiceImpl implements CatalogueService {
	@Autowired
	private CatalogueRepository daoObj;

	// method to add data to repository
	@Override
	public MyCatalogue addData(MyCatalogue catalogueObj) {
		daoObj.save(catalogueObj);
		return catalogueObj;
	}

	// method to retrive information from repository.
	@Override
	public List<MyCatalogue> getAllCatalogueDetails() {
		List<MyCatalogue> list = daoObj.findAll();
		return list;
	}

	// method that retrive product detail based on particular productName
	@Override
	public List<MyCatalogue> getByName(String productName) throws CatalogueServiceException {

		List<MyCatalogue> searchedNameObj = daoObj.findByProductName(productName);
		if (searchedNameObj.isEmpty()) {
			throw new CatalogueServiceException("product not found with productname: " + productName);
		} else {
			return searchedNameObj;
		}
	}

	// method that delete product detail based on particular productname.

	@Override
	public String delByIndex1(int index1) {
		daoObj.deleteByIndex1(index1);
		return index1 + " is removed.";
	}

	/*
	 * method that retrive all the images from the specific
	 * folder(/home/manoranjan/Desktop/projectimage/) and send it to controller.
	 */
	@Override
	public ResponseEntity<Map<String, String>> getImages() {
		Map<String, String> mapImages = new HashMap<String, String>();
		File folder = new File("/home/manoranjan/Desktop/projectImage/");
		if (folder != null) {
			for (final File file : folder.listFiles()) {
				if (!file.isDirectory()) {
					String base64 = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						String iName = FilenameUtils.getName(file.getName());
						FileInputStream fileInputStreamReader = new FileInputStream(file);
						byte[] bytes = new byte[(int) file.length()];
						fileInputStreamReader.read(bytes);
						base64 = java.util.Base64.getEncoder().encodeToString(bytes);
						mapImages.put(iName, "data:image/" + extension + ";base64," + base64);
						fileInputStreamReader.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return new ResponseEntity<>(mapImages, HttpStatus.OK);
	}

}
