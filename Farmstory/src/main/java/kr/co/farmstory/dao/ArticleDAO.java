package kr.co.farmstory.dao;

import kr.co.farmstory.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDAO {

    public int insertArticle(ArticleVO vo);
    public ArticleVO selectArticle(int no);
    public List<ArticleVO> selectArticles(int start);
    public int updateArticle(ArticleVO vo);
    public int deleteArticle(int no);

    public int selectCountTotal();
}
