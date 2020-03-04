package com.tangjiantao.cms.dao;

import java.util.List;

import com.tangjiantao.cms.domain.Comment;

public interface CommentDao {

	/**
	 * 
	    * @Title: addComment
	    * @Description:  添加评论
	    * @param @param comment
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public int addComment(Comment comment);
	
	/**
	 * 
	    * @Title: selects
	    * @Description:根据文章id查询文章的评论
	    * @param @param id 文章的编号
	    * @param @return    参数
	    * @return List<Comment>    返回类型
	    * @throws
	 */
	public List<Comment> selects(int id);
}
