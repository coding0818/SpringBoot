package kr.co.farmstory.controller;

import kr.co.farmstory.entity.UserEntity;
import kr.co.farmstory.security.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class MainController {

    @GetMapping(value = {"/", "index"})
    public String index(@AuthenticationPrincipal MyUserDetails myUser, Model model){

        UserEntity user = null;

        if(myUser != null){
            user = myUser.getUser();
        }

        log.info("user : " + user);

        if(user != null){
            model.addAttribute("user", user);
        }

        return "index";
    }
}
