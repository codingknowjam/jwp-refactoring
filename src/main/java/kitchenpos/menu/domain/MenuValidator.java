package kitchenpos.menu.domain;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import kitchenpos.common.domain.Price;
import kitchenpos.menugroup.domain.MenuGroupRepository;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;

@Component
public class MenuValidator {
	private final ProductRepository productRepository;
	private final MenuGroupRepository menuGroupRepository;

	public MenuValidator(ProductRepository productRepository, MenuGroupRepository menuGroupRepository) {
		this.productRepository = productRepository;
		this.menuGroupRepository = menuGroupRepository;
	}

	public void validateCreate(Menu menu) {
		validateIsExistedMenuGroup(menu.getMenuGroupId());
		validateIsExistedProduct(menu.getMenuProductsValue());
		validateMenuPriceSumGreaterThanProductsSum(menu.getMenuProductsValue(), menu.getPrice());



	}

	private void validateIsExistedProduct(List<MenuProduct> menuProducts) {
		menuProducts.forEach(mp -> findProductById(mp.getProductId()));
	}

	private Product findProductById(Long productId) {
		return productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다"));
	}

	private void validateIsExistedMenuGroup(Long menuGroupId) {
		menuGroupRepository.findById(menuGroupId)
			.orElseThrow(() -> new IllegalArgumentException("메뉴그룹이 존재하지 않습니다."));
	}

	private void validateMenuPriceSumGreaterThanProductsSum(List<MenuProduct> menuProducts, Price price) {
		if (price.greaterThan(sumProductPrice(menuProducts))) {
			throw new IllegalArgumentException("메뉴의 가격은 상품들의 가격합보다 작거나 같아야 합니다");
		}
	}

	private Price sumProductPrice(List<MenuProduct> menuProducts) {
		return Price.valueOf(menuProducts.stream()
			.map(mp -> findProductById(mp.getProductId()).getPrice()
				.multiply(BigDecimal.valueOf(mp.getQuantityValue())))
			.reduce(BigDecimal::add)
			.orElseThrow(IllegalArgumentException::new));
	}
}