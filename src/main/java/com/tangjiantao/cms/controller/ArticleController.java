package com.tangjiantao.cms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.domain.Category;
import com.tangjiantao.cms.domain.Channel;
import com.tangjiantao.cms.domain.Content;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.domain.enums.ContentType;
import com.tangjiantao.cms.service.ArticleService;

//import com.tangjiantao.cms.utils.PageUtil;
/**
 * 
 * @ClassName: ArticleController
 * @Description: TODO(文章)
 * @author 唐建涛
 * @date 2020年1月10日
 *
 */
@RequestMapping("article")
@Controller
public class ArticleController {

	@Autowired
	private ArticleService service;

	// 个人查看文章
	@RequestMapping("selectArticle")
	public String selectArticle(Model m, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize, Article article, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (null != user) {
			article.setUserId(user.getId());
		}

		// 个人文章做完登录之后再处理
		PageInfo<Article> info = service.selectsByAdmin(article, pageNum, pageSize);
		m.addAttribute("list", info.getList());
		m.addAttribute("article", article);
		m.addAttribute("info", info);

		return "my/article";
	}

	// 管理员查询文章的方法
	@RequestMapping("selectsByAdmin")
	public String gerArticleList(Model m, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "3") Integer pageSize, Article article) {

		PageInfo<Article> info = service.selectsByAdmin(article, pageNum, pageSize);
		// String page = PageUtil.page(pageNum, info.getPages(),
		// "/article/selectsByAdmin", pageSize);

		m.addAttribute("list", info.getList());
		m.addAttribute("article", article);
		m.addAttribute("info", info);
		return "admin/article";
	}

	@ResponseBody
	@RequestMapping("updateArcitle")
	public boolean update(Article article) {
		return service.upate(article);

	}

	// 详情
	@ResponseBody
	@RequestMapping("select")
	public Object select(int id) {
		Article article = service.select(id);

		return article;
	}

	@RequestMapping("toAdd")
	public Object toAdd() {
		return "my/publish";
	}

	// 查询所有栏目
	@ResponseBody
	@RequestMapping("selectsChannel")
	public Object selectsChannel() {
		List<Channel> list = service.selectsChannel();

		return list;
	}

	// 根据栏目id查栏目下的分类
	@ResponseBody
	@RequestMapping("selectsCategory")
	public Object selectsCategory(int id) {
		List<Category> list = service.selectsCategory(id);
		return list;
	}

	// 上传
	@ResponseBody
	@RequestMapping("add")
	public boolean add(Article article, @RequestParam("file")MultipartFile file, HttpSession session) {
		// 从session中获得登录的用户信息
		User user = (User) session.getAttribute("user");
		if (null != user) {
			article.setUserId(user.getId());
		}
		try {
			if (file.getSize() > 0) {
				// 上传图片到e盘pic
				String path = "E:/pic/";
				// 获取上传文件名
				String realName = file.getOriginalFilename();
				// 获取后缀
				String endName = realName.substring(realName.lastIndexOf("."));
				// 获得新文件名称
				String newFileName = UUID.randomUUID().toString() + endName;
				// 创建上传文件
				File file1 = new File(path + newFileName);
				file.transferTo(file1);
				article.setPicture(newFileName);

			}

			// 添加
			service.add(article);
			
			System.out.println("++++++++++++++++++++"+article);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	
}
