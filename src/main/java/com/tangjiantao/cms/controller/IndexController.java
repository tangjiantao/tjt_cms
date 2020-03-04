package com.tangjiantao.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.service.ArticleService;
import com.tangjiantao.cms.service.CommentService;

@RequestMapping("indexs")
@Controller
public class IndexController {
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("select")
	public String select(Model m,Article article,@RequestParam(defaultValue = "1")int pageNum) {
		//通过文章id查询文章详情
		Article article1 = articleService.select(article.getId());
		m.addAttribute("article", article1);
		
		
		//通过文章的id  查询所有的评论
		PageInfo info=commentService.selects(article.getId(),pageNum,10);
		m.addAttribute("info", info);
				
		return "index/article";
		
	}
	
}
