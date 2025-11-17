package pojos;

public class ProductPojo {
	private int categoryId;
	private int price;
	private String imageUrl;
	private String name;
	private Object discount;
	private String description;
	private int stock;
	private String sku;
	private String manufacturer;

	public int getCategoryId(){
		return categoryId;
	}

	public int getPrice(){
		return price;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public String getName(){
		return name;
	}

	public Object getDiscount(){
		return discount;
	}

	public String getDescription(){
		return description;
	}

	public int getStock(){
		return stock;
	}

	public String getSku(){
		return sku;
	}

	public String getManufacturer(){
		return manufacturer;
	}
}
