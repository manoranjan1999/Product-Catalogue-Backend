package com.catalogue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Model class 'MyCatalogue' having certain field's   

@Entity
@Table(name = "catalogue")
public class MyCatalogue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int index1;

	@Column(name = "productname")
	private String productName;

	@Column(name = "productprice")
	private int productPrice;

	@Column(name = "modelno")
	private String modelNo;

	@Column(name = "genericname")
	private String genericName;

	@Column(name = "countryoforigin")
	private String countryOfOrigin;

	@Column(name = "imagename")
	private String imageName;

	public int getIndex() {
		return index1;
	}

	public void setIndex(int index) {
		this.index1 = index;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getModelNo() {
		return modelNo;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public MyCatalogue(int index1, String productName, int productPrice, String modelNo, String genericName,
			String countryOfOrigin, String imageName) {
		super();
		this.index1 = index1;
		this.productName = productName;
		this.productPrice = productPrice;
		this.modelNo = modelNo;
		this.genericName = genericName;
		this.countryOfOrigin = countryOfOrigin;
		this.imageName = imageName;
	}

	public MyCatalogue(String productName, int productPrice, String modelNo, String genericName, String countryOfOrigin,
			String imageName) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.modelNo = modelNo;
		this.genericName = genericName;
		this.countryOfOrigin = countryOfOrigin;
		this.imageName = imageName;
	}

	public MyCatalogue() {
		super();
		// TODO Auto-generated constructor stub
	}

}
