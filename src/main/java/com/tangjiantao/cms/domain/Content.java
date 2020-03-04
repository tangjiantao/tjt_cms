package com.tangjiantao.cms.domain;

import java.io.Serializable;

public class Content implements Serializable{
	
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 6535073257836849274L;

	public Content() {
		super();
	}

	public Content(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}
	
	private Integer id;
	private String name;
	private String content;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
