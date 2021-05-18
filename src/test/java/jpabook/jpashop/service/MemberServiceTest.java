package jpabook.jpashop.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberReposiroy;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	@Autowired
	MemberReposiroy memberReposiroy;
	@Autowired
	EntityManager em;

	@Test
	public void 회원가입() throws Exception {
		// given
		Member member = new Member();
		member.setName("kim");

		// when
		Long saveId = memberService.join(member);

		// then
		em.flush();
		assertEquals(member, memberReposiroy.findOne(saveId));
	}

	@Test(expected = IllegalStateException.class)
	public void 중복_회원_예외() throws Exception {
		// given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");
		// when
		memberService.join(member1);
		memberService.join(member2);

		// then
		fail();
	}
}