package kr.co.farmstory.service;

import kr.co.farmstory.dao.UserDAO;
import kr.co.farmstory.repository.UserRepo;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO dao;

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    public TermsVO selectTerms(){
        return dao.selectTerms();
    }

    public int insertUser(UserVO vo){
        vo.setPass(encoder.encode(vo.getPass1()));

        log.info("UserService...1");

        return dao.insertUser(vo);
    }

    public int countUid(String uid){
        return dao.countUid(uid);
    }

    public int countNick(String nick){
        return dao.countNick(nick);
    }

    // 이메일 인증
    public int[] sendEmailCode(String receivedEmail){
        // 인증코드 생성
        int code = ThreadLocalRandom.current().nextInt(100000,1000000);



        String title = "Farmstory 인증코드입니다.";
        String content = "<h1>인증코드는 "+code+" 입니다.</h1>";


        return null;

    }
}
