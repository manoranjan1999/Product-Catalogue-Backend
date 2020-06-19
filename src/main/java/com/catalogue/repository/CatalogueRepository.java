package com.catalogue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogue.model.MyCatalogue;

//'CatalogueRepository' Interface is act's as a repository
public interface CatalogueRepository extends JpaRepository<MyCatalogue, Integer>
{
	List<MyCatalogue> findByProductName(String productName);
	String deleteByIndex1(int index1);
}
