package com.catalogue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.catalogue.service.CatalogueService;
import com.catalogue.exceptionhandling.CatalogueServiceException;
import com.catalogue.model.MyCatalogue;
import com.catalogue.repository.CatalogueRepository;

//@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class ProductCatalogueApplicationTests {

	@Autowired
	private CatalogueService service;

	@MockBean
	private CatalogueRepository repo;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddData() {
		MyCatalogue catalogue = new MyCatalogue();
		catalogue.setProductName("nescafe");
		catalogue.setProductPrice(120);
		catalogue.setModelNo("sm-123");
		catalogue.setGenericName("jjjdh");
		catalogue.setCountryOfOrigin("India");
		catalogue.setImageName("coffee.jpg");// "nescafe coffee", 120, "sm-123", "coffee", "India", "coffee.png"

		when(repo.save(catalogue)).thenReturn(catalogue);
		assertEquals(catalogue, service.addData(catalogue));
	}

	@Test
	public void testGetAllCatalogueDetails() {
		when(repo.findAll()).thenReturn(Stream
				.of(new MyCatalogue("nescafe coffee", 120, "sm-123", "coffee", "India", "coffee.png"),
						new MyCatalogue("casio watch", 3200, "ca-1223", "watch", "Russia", "casio.jpg"))
				.collect(Collectors.toList()));
		assertEquals(2, service.getAllCatalogueDetails().size());
		// verify(repo).findAll();
	}

	@Test
	public void testGetByName() throws CatalogueServiceException {
		String productName = "casio";
		when(repo.findByProductName(productName)).thenReturn(
				Stream.of(new MyCatalogue("casio wrist watch", 3400, "CA-123", "watch", "India", "casio.png"))
						.collect(Collectors.toList()));
		assertEquals(1, service.getByName(productName).size());
	}

	@Test
	public void testDelByName() {
		int index1 = 11;
		when(repo.deleteByIndex1(index1)).thenReturn(index1 + " is removed.");
		assertEquals(index1 + " is removed.", service.delByIndex1(index1));
	}

}
