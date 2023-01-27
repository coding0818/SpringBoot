package kr.co.farmstory.service;

import kr.co.farmstory.dao.ArticleDAO;
import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;

    public int insertArticle(ArticleVO vo){
        int result = dao.insertArticle(vo);

        FileVO fvo = fileUpload(vo);

        if(fvo != null){
            dao.insertFile(fvo);
        }

        return result;
    }

    @Transactional
    public Map<String, Object> insertComment(ArticleVO vo){
        dao.insertComment(vo);
        dao.updateCommentPlus(vo.getNo());
        ArticleVO comment = dao.selectComment(vo.getNo());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", 1);
        resultMap.put("no", comment.getNo());
        resultMap.put("parent", comment.getParent());
        resultMap.put("nick", comment.getNick());
        resultMap.put("date", comment.getRdate().substring(2, 10));
        resultMap.put("content", comment.getContent());
        return resultMap;
    }
    public ArticleVO selectArticle(int no){
        return dao.selectArticle(no);
    }
    public List<ArticleVO> selectArticles(String cate, int start){
        return dao.selectArticles(cate, start);
    }
    public List<ArticleVO> selectComments(int no){
        return dao.selectComments(no);
    }
    public int updateArticle(ArticleVO vo){
        return dao.updateArticle(vo);
    }
    public int deleteArticle(int no){
        return dao.deleteArticle(no);
    }
    public Map<String, Integer> deleteComment(int no, int parent){
        int result = dao.deleteComment(no, parent);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    public FileVO fileUpload(ArticleVO vo){
        // 첨부 파일
        MultipartFile file = vo.getFname();
        FileVO fvo = null;

        if(!file.isEmpty()){
            // 시스템 경로
            String path = new File(uploadPath).getAbsolutePath();

            // 새 파일명 생성
            String oName = file.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String nName = UUID.randomUUID().toString()+ext;

            // 파일 저장
            try{
                file.transferTo(new File(path, nName));
            }catch (IllegalStateException e){
                log.error(e.getMessage());
            }catch (IOException e){
                log.error(e.getMessage());
            }

            fvo = FileVO.builder()
                    .parent(vo.getNo())
                    .oriName(oName)
                    .newName(nName)
                    .build();
        }
        return fvo;
    }

    // 로직

    // 페이지 시작값
    public int getLimitStart(int currentPage){
        return (currentPage - 1)*10;
    }

    // 현재 페이지
    public int getCurrentPage(String pg){
        int currentPage = 1;

        if(pg != null){
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    // 전체 게시물 개수
    public int getTotalCount(String cate){
        return dao.selectCountTotal(cate);
    }

    // 페이지 번호
    public int getLastPageNum(int total){
        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else {
            lastPageNum = (total / 10) + 1;
        }
        return lastPageNum;
    }

    // 페이지 시작번호
    public int getPageStartNum(int total, int start){
        return total - start;
    }

    // 페이지 그룹
    public int[] getPageGroup(int currentPage, int lastPageNum){

        log.info("ArticleService...getPageGroup...1");
        int groupCurrent = (int) Math.ceil(currentPage/10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        log.info("ArticleService...getPageGroup...2"+groupCurrent);
        log.info("ArticleService...getPageGroup...3"+groupStart);

        if(groupEnd > lastPageNum){
            groupEnd = lastPageNum;
        }
        log.info("ArticleService...getPageGroup...4"+groupEnd);

        int[] groups = {groupStart, groupEnd};

        return groups;
    }
}
