package kitchenpos.product.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import kitchenpos.product.domain.Product;

public class ProductResponse {

	private Long id;
	private String name;
	private BigDecimal price;

	private ProductResponse(long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public static ProductResponse of(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getPrice());
	}

	public static List<ProductResponse> ofList(List<Product> products) {
		return products.stream()
			.map(ProductResponse::of)
			.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}
}
