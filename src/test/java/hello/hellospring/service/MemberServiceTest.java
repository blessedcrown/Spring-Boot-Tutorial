package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/*
@SpringBootTest -> 스프링 컨테이너와 테스트를 함께 실행한다.

@Transactional -> @AfterEach가 필요가 없다?

스프링에서 제공하는 기능중 하나.

db는 기본적으로 transaction이라는 개념이 있다

@Transactional 애노테이션을 사용하면 스프링이 db에 쿼리를 날리되 commit은 하지 않는다(롤백).

해서 db는 계속해서 데이터가 남지 않는 상태가 되는것 (테스트 케이스에 한정된다)

단위 테스트 vs 스프링과 함께 하는 통합 테스트
단위테스트를 적극 활용하는 습관을 기르자.
통합테스트는 단위 테스트에 비해 걸리는 시간이 굉장히 길다.
* */
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //assertThrows(발생해야하는 예외, 어떤 메서드를 불렀을때)
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}