package springAWS.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springAWS.config.auth.LoginUser;
import springAWS.config.auth.dto.SessionUser;
import springAWS.domain.posts.PostsRepository;
import springAWS.service.posts.PostsService;
import springAWS.web.dto.PostsResponseDto;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    // 머스태치 플러그인을 사용하기 때문에 앞의 경로인 src/main/resource와 뒤의 확장자명인 .mustache는 자동으로 붙는다
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user)
    {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
    // 따라서 src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
