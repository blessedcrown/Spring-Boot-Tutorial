package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String Hello0(Model model){
        model.addAttribute("data", "hello");
        return "hello";
        //spring-boot-devtools 라이브러리를 사용하면 html 컴파일만 하고 서버 재기동은 하지 않아도된다
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body section에 return값을 직접 넣어주겠다
    public String helloString(@RequestParam("name") String name) {
        return "hello" +name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello { //class 안에서 static class 사용가능
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
