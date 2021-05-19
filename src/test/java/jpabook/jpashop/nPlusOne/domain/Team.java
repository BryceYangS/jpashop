package jpabook.jpashop.nPlusOne.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id
	@GeneratedValue
	@Column(name = "team_id")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "team")
	private List<TestMember> testMembers = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestMember> getMembers() {
		return testMembers;
	}

	public void setMembers(List<TestMember> testMembers) {
		this.testMembers = testMembers;
	}
}
