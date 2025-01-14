package kitchenpos.menugroup.ui;

import kitchenpos.menugroup.application.MenuGroupService;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.menugroup.dto.MenuGroupRequest;
import kitchenpos.menugroup.dto.MenuGroupResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MenuGroupRestController {
	private final MenuGroupService menuGroupService;

	public MenuGroupRestController(final MenuGroupService menuGroupService) {
		this.menuGroupService = menuGroupService;
	}

	@PostMapping("/api/menu-groups")
	public ResponseEntity<MenuGroupResponse> create(@RequestBody final MenuGroupRequest menuGroupRequest) {
		final MenuGroup created = menuGroupService.create(menuGroupRequest);
		final URI uri = URI.create("/api/menu-groups/" + created.getId());
		return ResponseEntity.created(uri).body(MenuGroupResponse.of(created));
	}

	@GetMapping("/api/menu-groups")
	public ResponseEntity<List<MenuGroupResponse>> list() {
		return ResponseEntity.ok().body(MenuGroupResponse.ofList(menuGroupService.list()));
	}
}
