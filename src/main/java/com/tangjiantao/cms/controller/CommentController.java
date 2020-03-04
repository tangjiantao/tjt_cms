package com.tangjiantao.cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tangjiantao.cms.domain.Comment;
import com.tangjiantao.cms.domain.User;
import com.tangjiantao.cms.service.CommentService;

@RequestMapping("comment")
@Controller
public class CommentController {

	@Autowired 
	private CommentService service;
	
	@ResponseBody
	@RequestMapping("addComment")
	public int addComment(Comment comment,HttpSession session) {
		int i=0;
		//user_id
		User user = (User) session.getAttribute("user");
		if(user==null) {
			return -1;
		}
		comment.setUser(user);
		////created
		//获得系统当前时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		comment.setCreated(sdf.format(date));
		 i = service.addComment(comment);
		return i;
	}
	
}
