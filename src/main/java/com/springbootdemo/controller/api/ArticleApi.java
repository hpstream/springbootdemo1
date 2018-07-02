package com.springbootdemo.controller.api;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.springbootdemo.model.Article;
import com.springbootdemo.service.ArticleService;
import com.springbootdemo.util.FormUtil;
import com.springbootdemo.util.JsonUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api(description = "文章api")
@RestController
@RequestMapping("api/article")
class ArticleApi {
    private final ArticleService service;
    public ArticleApi(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public PageInfo<Article> getArticle(
            HttpServletRequest request,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        Map<String,Object> map = FormUtil.getParameterMap(request);

        Map<String,Object> condition = Maps.newHashMap();
        String conditionJson = (String)map.get("condition");
        if (null != conditionJson) {
            condition = JsonUtils.toObject(conditionJson, Map.class);
        }
        return new PageInfo<>(service.getArticle(offset, limit, condition));
    }

    @PostMapping
    public String addArticle(@RequestBody Article article){
        String id = "2";
        article.setId(id);
        service.addArticle(article);
        return "添加成功";
    }

    @PutMapping
    public  String editArticle(@RequestBody Article article){
        service.editArticle(article);
        return  "删除成功";
    }

    @DeleteMapping
    public  String deleteArticle(@RequestBody List<String> ids){
        service.deleteArticle(ids);
        return  "删除成功";
    }

}
