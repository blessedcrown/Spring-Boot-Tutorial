package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository를 상속받고 있으면 Spring이 자동으로
//인터페이스의 구현체를 만들어준다

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JpaRepository는 공통 인터페이스로서 기본 CRUD를 포함한 페이징관련
    //메소드까지 제공해준다. 다만 특정 메소드를 사용하고 싶다면 직접 구현해야한다.
    @Override
    Optional<Member> findByName(String name);
}
