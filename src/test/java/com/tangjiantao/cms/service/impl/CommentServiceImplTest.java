package com.tangjiantao.cms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bw.utils.RandomUtil;
import com.tangjiantao.cms.domain.Article;
import com.tangjiantao.cms.domain.Comment;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.service.CommentService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class CommentServiceImplTest {

	@Autowired
	private CommentService service;
	/**
	 * 
	 * @Title: testAddComment 
	 * @Description: 测试评论添加
	 * @return: void
	 */
	@Test
	public void testAddComment() {
		/*
		 * jUnit测试完保存评论方法后，还需要模拟1000条评论数据到表中，
		 * 并且每条评论数据的用户ID可以为1个，但文章ID需要在10个以上随机分配，
		 * 
		 */
		User user=new User();
		user.setId(127);
		for(int i=0;i<1000;i++) {
			Comment comment=new Comment();
			//每条评论数据的用户ID可以为1个
			//装入模拟用户
			comment.setUser(user);
			Article article=new Article();
			//文章ID需要在10个以上随机分配，
			//如何随机获得文章的id
			int id = RandomUtil.getRandomNum(8, 18);
			article.setId(id);
			comment.setArticle(article);
			//评论内容需要用随机字符串生成，最少100字以上，
			String content = RandomUtil.getRandomChineseString(RandomUtil.getRandomNum(100, 200));
			comment.setContent(content);
			
			//发布时间从2019-1-1 00:00:00至今随机。
			//获得2019-1-1 long类型的ms值
			Calendar calender=Calendar.getInstance();
			calender.set(2019, 0, 1, 0, 0, 0);
			//2019-1-1 的ms值
			long time1 = calender.getTime().getTime();
			//获得当前时间的毫秒值
			long time2 = new Date().getTime(); 
			//从time1  和  time2之前获得一个随机数
			long time=(long)(Math.random()*(time2-time1))+time1;
			Date date=new Date();
			date.setTime(time);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//把随机数给comment
			comment.setCreated(sdf.format(date));
			//执行添加
			System.out.println(comment);
			service.addComment(comment);
			
		}
		
		
	}
	
	
	
	
	
	
	
	@Test
	public void test() {
		
	}

}
