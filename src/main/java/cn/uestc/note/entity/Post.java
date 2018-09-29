package cn.uestc.note.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Post，Commons，Person三个类以及相关dao和service是用来进行演示
 * mybatis关联查询的。
 */
public class Post implements Serializable{
	private static final long serialVersionUID = -4722438109030592372L;
	
	private Integer id;
	private String title;
	
	/** 发帖人 */
	private Person person;
	
	/** 当前帖子收到的回复
	 * 	凡是实体类中的集合，建议初始化出来，防止各种空指针异常
	 * */
	private List<Comment> comments = 
			new ArrayList<Comment>();
	
	public Post() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", person=" + person + ", comments=" + comments + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}




