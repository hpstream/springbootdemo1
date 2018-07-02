package com.springbootdemo.mapper;

import com.springbootdemo.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleMapper {

    List<Article> getArticle();

    void addArticle(Article article);

    void editArticleById(Article article);

    void deleteArticle(Map map);
}
