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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
        int total = service.getTotalCount(cate);
        int lastPageNum = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int[] groups = service.getPageGroup(currentPage, lastPageNum);

        List<ArticleVO> articles = service.selectArticles(cate, start);

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
    public String modify(Model model,String group, String cate, int no){
        ArticleVO article = service.selectArticle(no);

        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("article", article);

        return "board/modify";
    }

    @GetMapping("board/view")
    public String view(Model model,String group, String cate, int no){
        ArticleVO article = service.selectArticle(no);
        List<ArticleVO> comments = service.selectComments(no);
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);
        model.addAttribute("article", article);
        model.addAttribute("comments", comments);

        return "board/view";
    }

    @GetMapping("board/write")
    public String write(Model model, String group, String cate){
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "board/write";
    }

    @PostMapping("board/write")
    public String write(ArticleVO vo, String group){
        log.info("BoardController...write...Post");
        int result = service.insertArticle(vo);
        log.info("BoardController...write...Post...1");
        return "redirect:/board/list?group="+group+"&cate="+vo.getCate();
    }

    @GetMapping("board/delete")
    public String delete(String group, String cate, int no){
        int result = service.deleteArticle(no);

        return "redirect:/board/list?group="+group+"&cate="+cate;
    }

    @ResponseBody
    @PostMapping("board/commentWrite")
    public Map<String, Object> commentWrite(ArticleVO vo){

        Map<String, Object> resultMap = service.insertComment(vo);

        return resultMap;
    }

    @ResponseBody
    @GetMapping("board/commentDelete")
    public Map<String, Integer> commentDelete(int no, int parent){

        Map<String, Integer> resultMap = service.deleteComment(no, parent);
        return resultMap;
    }

}
