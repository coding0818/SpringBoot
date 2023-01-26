package kr.co.farmstory.controller;

import kr.co.farmstory.entity.UserEntity;
import kr.co.farmstory.security.MyUserDetails;
import kr.co.farmstory.service.ArticleService;
import kr.co.farmstory.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class BoardController {


    @Autowired
    private ArticleService service;

    @GetMapping("board/list")
    public String list(Model model, String group, String cate, @AuthenticationPrincipal MyUserDetails myUser, String pg){

        UserEntity user = null;

        if(myUser != null){
            user = myUser.getUser();
            model.addAttribute("user", user);
        }

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        int total = service.getTotalCount();
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int[] groups = service.getPageGroup(currentPage, lastPageNum);

        List<ArticleVO> articles = service.selectArticles(start);

        model.addAttribute("lastPage", lastPageNum);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("articles", articles);

        return "board/list";
    }

    @GetMapping("board/modify")
    public String modify(){
        return "board/modify";
    }

    @GetMapping("board/view")
    public String view(){
        return "board/view";
    }

    @GetMapping("board/write")
    public String write(){
        return "board/write";
    }


}
