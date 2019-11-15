package io.altar.jseproject.model;

import java.util.List;


public class Product extends Entity {
	
	private List<Long> inShelfList;
	private int basePrice;
	private int iva;
	private int pvp;
	
	public Product(int basePrice, int iva) {
		super();
		this.basePrice = basePrice;
		this.iva = iva;
		this.pvp = basePrice + ((basePrice * this.iva)/100);
	}

	public List<Long> getInShelfList() {
		return inShelfList;
	}

	public void setInShelfList(List<Long> inShelfList) {
		this.inShelfList = inShelfList;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
		this.pvp = basePrice + ((basePrice * this.iva)/100);
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
		this.pvp = basePrice + ((basePrice * this.iva)/100);
	}

	public int getPvp() {
		return pvp;
	}


	@Override
	public String toString() {
		return "Product [inShelfList=" + inShelfList + ", basePrice=" + basePrice + ", iva=" + iva + ", pvp=" + pvp
				+ "]";
	}

	
}
