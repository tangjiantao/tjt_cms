package com.tangjiantao.cms.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.dao.ArticleDao;
import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.domain.Category;
import com.tangjiantao.cms.domain.Channel;
import com.tangjiantao.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Override
	public PageInfo<Article> selectsByAdmin(Article article, Integer pageNum, Integer pageSize) {
		
		PageHelper.startPage(pageNum,pageSize);
		List<Article> list=articleDao.selectsByAdmin(article);
		
		return new PageInfo<Article>(list);
	}

	@Override
	public boolean upate(Article article) {
		
		try {
			articleDao.update(article);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Article select(int id) {
		
		return articleDao.select(id);
	}

	//所以栏目
	@Override
	public List<Channel> selectsChannel() {
		
		return articleDao.selectsCannel();
	}
	
	//根据id差所有栏目下的分类
	@Override
	public List<Category> selectsCategory(int id) {
		
		return articleDao.selectsCategory(id);
	}

	//添加+上传
	@Override
	public void add(Article article) {
		articleDao.add(article);
	}

	//24小时内文章
	@Override
	public List<Article> select24Article(String createTime) {
		// TODO Auto-generated method stub
		return articleDao.select24Article(createTime);
	}

	//查询热门文章
	@Override
	public List getHotList() {
		// TODO Auto-generated method stub
		return articleDao.getHotList();
	}

	//发布图片
	@Override
	public void addContent(Article article) {
		articleDao.addContent(article);
	}

	
	
}
