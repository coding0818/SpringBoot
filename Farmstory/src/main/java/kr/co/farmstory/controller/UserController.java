package kr.co.farmstory.controller;

import kr.co.farmstory.service.UserService;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("user/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("user/terms")
    public String terms(Model model){

        TermsVO terms = service.selectTerms();
        model.addAttribute("terms", terms);

        return "user/terms";
    }

    @GetMapping("user/register")
    public String register(){
        return "user/register";
    }

    @PostMapping("user/register")
    public String register(UserVO vo, HttpServletRequest req){
        log.info("UserController...1");

        vo.setRegip(req.getRemoteAddr());

        log.info("UserController...2");
        int result = service.insertUser(vo);

        log.info("UserController...3");
        return "redirect:/user/login?success="+result;
    }

    @ResponseBody
    @GetMapping("user/checkUid")
    public Map<String, Integer> checkUid(String uid){
        int result = service.countUid(uid);
        log.info("result : "+result);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }

    @ResponseBody
    @GetMapping("user/checkNick")
    public Map<String, Integer> checkNick(String nick){
        int result = service.countNick(nick);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }

    @ResponseBody
    @GetMapping("user/emailAuth")
    public Map<String, Integer> emailAuth(String email){
        log.info("here1 : " + email);
        int[] result = service.sendEmailCode(email);

        log.info("here2 : " + result[1]);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("code", result[0]);
        resultMap.put("status", result[1]);
        return resultMap;
    }
}
