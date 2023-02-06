package kr.co.farmstory.controller;

import kr.co.farmstory.entity.UserEntity;
import kr.co.farmstory.security.MyUserDetails;
import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private ArticleService service;

    @GetMapping(value = {"/", "index"})
    public String index(Model model, Principal principal){
        List<ArticleVO> grow = service.selectLatest("grow");
        List<ArticleVO> school = service.selectLatest("school");
        List<ArticleVO> story = service.selectLatest("story");

        model.addAttribute("grow", grow);
        model.addAttribute("school", school);
        model.addAttribute("story", story);

        return "index";
    }
}
