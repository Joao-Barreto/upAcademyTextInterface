package io.altar.jseproject.model;

public class Shelf extends Entity{
	

	private int capacity;
	private Product product;
	private int dailyRentalPrice;

	
	public Shelf(int capacity, int dailyRentalPrice) {
		
		super();
		this.capacity = capacity;
		this.dailyRentalPrice = dailyRentalPrice;
		
	}
	

	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getDailyRentalPrice() {
		return dailyRentalPrice;
	}

	public void setDailyRentalPrice(int dailyRentalPrice) {
		this.dailyRentalPrice = dailyRentalPrice;
	}


	@Override
	public String toString() {
		return "Shelf [capacity=" + capacity + ", product=" + product + ", dailyRentalPrice=" + dailyRentalPrice + "]";
	}


	

}
