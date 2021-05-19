package jpabook.jpashop.nPlusOne;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import jpabook.jpashop.nPlusOne.domain.Team;
import jpabook.jpashop.nPlusOne.domain.TestMember;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Jpatest {

	@Autowired
	EntityManager em;

	@Test
	public void N플러스1_테스트() throws Exception {
		// given
		Team teamA = new Team();
		teamA.setName("팀A");
		em.persist(teamA);

		Team teamB = new Team();
		teamB.setName("팀B");
		em.persist(teamB);

		TestMember testMember1 = new TestMember();
		testMember1.setName("회원1");
		testMember1.setTeam(teamA);
		em.persist(testMember1);

		TestMember testMember2 = new TestMember();
		testMember2.setName("회원2");
		testMember2.setTeam(teamA);
		em.persist(testMember2);

		TestMember testMember3 = new TestMember();
		testMember3.setName("회원3");
		testMember3.setTeam(teamB);
		em.persist(testMember3);

		em.flush();
		em.clear();

		// when
		String query = "select m from TestMember m join fetch m.team";
		List<TestMember> resultList = em.createQuery(query, TestMember.class).getResultList();

		for (TestMember member : resultList) {
			System.out.println("member = " + member.getName() + ", " + member.getTeam().getName());
		}

		// then
		assertThat(resultList.size()).isEqualTo(3);
	}

}
