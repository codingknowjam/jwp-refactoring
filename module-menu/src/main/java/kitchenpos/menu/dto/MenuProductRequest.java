package kitchenpos.menu.dto;

public class MenuProductRequest {

	private Long productId;
	private int quantity;

	public MenuProductRequest() {
	}

	public MenuProductRequest(long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public long getProductId() {
		return productId;
	}

	public Long getQuantity() {
		return (long)quantity;
	}
}
