package com.tangjiantao.cms.service;

import com.github.pagehelper.PageInfo;
import com.tangjiantao.cms.domain.Comment;

public interface CommentService {

	/**
	 * 
	 * @Title: addComment 
	 * @Description: 添加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	public int addComment(Comment comment);
	
	/**
	 * 
	 * @Title: selects 
	 * @Description: 根据文章id查询文章的评论
	 * @param id  文章的编号
	 * @return
	 * @return: List<Comment>
	 */
	public PageInfo selects(int id,int pageNum,int pageSize);
}
