package com.springbootdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.springbootdemo.mapper.ArticleMapper;
import com.springbootdemo.model.Article;
import com.springbootdemo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper mapper;
    public ArticleServiceImpl(ArticleMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Article> getArticle(int offset, int limit, Map<String, Object> condition) {
        PageHelper.offsetPage(offset, limit);
        return mapper.getArticle();

    }

    @Override
    public void addArticle(Article article) {
        mapper.addArticle(article);
    }

    @Override
    public void editArticle(Article article) {
        mapper.editArticle(article);
    }

    @Override
    public void deleteArticle(List<String> ids) {
       mapper.deleteArticle(ids);
    }
}
