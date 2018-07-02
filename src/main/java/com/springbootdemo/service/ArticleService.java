package com.springbootdemo.service;



import com.springbootdemo.model.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    /**
     *
     * @param offset
     * @param limit
     * @param condition
     * @return
     */
    List<Article> getArticle(int offset, int limit, Map<String, Object> condition);

}
