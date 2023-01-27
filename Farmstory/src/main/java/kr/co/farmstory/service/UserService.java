package kr.co.farmstory.service;

import kr.co.farmstory.dao.UserDAO;
import kr.co.farmstory.repository.UserRepo;
import kr.co.farmstory.vo.TermsVO;
import kr.co.farmstory.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;
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

    @Autowired
    public JavaMailSender emailsender;

    // 이메일 인증
    public int[] sendEmailCode(String receivedEmail){
        // 인증코드 생성
        int code = ThreadLocalRandom.current().nextInt(100000,1000000);
        String title = "Farmstory 인증코드입니다.";
        String content = "<h1>인증코드는 "+code+" 입니다.</h1>";

        SimpleMailMessage smm = new SimpleMailMessage();

        int status = 0;

        try{
            smm.setTo(receivedEmail);
            smm.setSubject(title);
            smm.setText(content);

            emailsender.send(smm);
            log.info("here1");

            status = 1;
        }catch (Exception e){
            e.printStackTrace();
            status = 0;
            log.info("here2 : "+ e.getMessage());
        }
        int result[] = {code, status};

        return result;

    }
}
