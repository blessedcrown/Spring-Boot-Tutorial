package hello.hellospring.controller;

public class MemberForm {
    private String name; //Form 에서 넘어오는값이랑 자동 매칭

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
