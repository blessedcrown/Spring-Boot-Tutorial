package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리 하는 entity로 명시해준다
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //ORACLE에서의 시퀀스와 같다
    private Long id;
    //@Column(name = "username") 만약 DB의 컬럼이름이 다르면 이렇게 명시한다
    private String name;

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
}
