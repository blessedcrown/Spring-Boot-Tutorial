package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/*주의 할점!!
테스트는 서로 의존관계가 없어야한다.

현재 테스트동작을 시켰을때는 각각 잘 돌아간다.
하지만 테스트를 모두동시에 실행시켰을때는 findByName()에서 에러가 발생한다
(전체 테스트에서는 각 테스트실행 순서는 보장받지 못한다)
문제는 findByName()과 findAll()에서 같은 이름의 변수를 사용하고 있는것인데
findAll()이 먼저 실행되고나서 데이터가 Repository에 여전히 남아있어
findByName()테스트에서 저장되어 있는 데어터가 꺼내어져 테스트가 실패하는 것이다.
해서 각 테스트가 끝날때마다 repository를 비워주게 한다 @AfterEach 사용할것*/
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //org.junit.jupiter.api.assertions
        Assertions.assertEquals(member,result);
        //org.assertj.core.api.assertions
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //.get()을 사용하면 Optional을 한번 까서 꺼내올수있다
        Member result = repository.findByName("spring1").get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
