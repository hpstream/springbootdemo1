package com.springbootdemo.mapper;

import com.springbootdemo.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {

    List<Article> getArticle();

    void addArticle(Article article);

    void editArticle(Article article);

    void deleteArticle(List<String> ids);
}
