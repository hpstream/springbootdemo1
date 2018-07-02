package com.springbootdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.springbootdemo.mapper.ArticleMapper;
import com.springbootdemo.model.Article;
import com.springbootdemo.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public void editArticleById(Article article) {
        mapper.editArticleById(article);
    }

    @Override
    public void deleteArticle(List<String> ids) {
        Map<String,Object> map= new HashMap<>();
        map.put("ids",ids);
        mapper.deleteArticle(map);
    }
}