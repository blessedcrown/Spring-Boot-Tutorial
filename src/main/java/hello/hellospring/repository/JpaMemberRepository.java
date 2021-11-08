package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    /*jpa에서의 핵심이라고 볼수 있다. starter-data-jpa 라이브러리를 사용하게 되면
    spring boot가 EntityManager객채를 생성하고 DB와의 연결까지 해준후 사용자에게 준다.
    개발자는 그저 인젝션만 잘해주면 된다*/
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //JPQL
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /**Member 객체(Entity)를 대상으로 쿼리를 날린다
         * sql에서 처럼 *를 사용하기보다 객체 자체를 탐색한다*/
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
