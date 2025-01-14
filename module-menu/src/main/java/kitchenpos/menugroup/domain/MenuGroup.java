package kitchenpos.menugroup.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MenuGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "bigint(20)")
	private Long id;

	@Column(nullable = false)
	private String name;

	public MenuGroup() {
	}

	public MenuGroup(String name) {
		this(null, name);
	}

	public MenuGroup(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MenuGroup menuGroup = (MenuGroup)o;
		return Objects.equals(id, menuGroup.id) && Objects.equals(name, menuGroup.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
