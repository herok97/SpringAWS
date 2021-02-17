package springAWS.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // 머스태치 플러그인을 사용하기 때문에 앞의 경로인 src/main/resource와 뒤의 확장자명인 .mustache는 자동으로 붙는다
    @GetMapping
    public String index() {
        return "index";
    }
    // 따라서 src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.
}
