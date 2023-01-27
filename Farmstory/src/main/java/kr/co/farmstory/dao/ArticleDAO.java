package kr.co.farmstory.dao;

import kr.co.farmstory.vo.ArticleVO;
import kr.co.farmstory.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArticleDAO {

    public int insertArticle(ArticleVO vo);
    public int insertFile(FileVO vo);
    public int insertComment(ArticleVO vo);
    public ArticleVO selectArticle(int no);
    public ArticleVO selectComments(int no);
    public List<ArticleVO> selectArticles(@Param("cate") String cate, @Param("start") int start);
    public int updateArticle(ArticleVO vo);
    public int deleteArticle(int no);

    public int selectCountTotal(String cate);
}
