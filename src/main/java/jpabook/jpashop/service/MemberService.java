package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberReposiroy;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberReposiroy memberReposiroy;

	/**
	 * 회원 가입
	 * @param member
	 * @return 멤버 id
	 */
	@Transactional
	public Long join(Member member) {

		validateDuplicateMember(member); // 중복 회원 검증
		memberReposiroy.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> findMembers = memberReposiroy.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	// 회원 전체 조회
	public List<Member> findMembers() {
		return memberReposiroy.findAll();
	}

	public Member findOne(Long memberId) {
		return memberReposiroy.findOne(memberId);
	}

}
