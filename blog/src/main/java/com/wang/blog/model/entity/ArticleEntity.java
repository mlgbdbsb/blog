package com.wang.blog.model.entity;

/**
 * 博客文章实体类
 * @author wang
 *
 */
public class ArticleEntity extends BaseEntity {

	private static final long serialVersionUID = -9092323451066740006L;
	private Long id;
	private Long userId;
	private String title;
	private String content;
	private String abstractTitle;
	private Integer likeCount;
	private Integer viewCount;
	private Integer replayCount;
	private Long articleAndCategoryId;
	private Integer publish;
	private Integer delete;
	private Integer allowDiscuss;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAbstractTitle() {
		return abstractTitle;
	}

	public void setAbstractTitle(String abstractTitle) {
		this.abstractTitle = abstractTitle;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getReplayCount() {
		return replayCount;
	}

	public void setReplayCount(Integer replayCount) {
		this.replayCount = replayCount;
	}

	public Long getArticleAndCategoryId() {
		return articleAndCategoryId;
	}

	public void setArticleAndCategoryId(Long articleAndCategoryId) {
		this.articleAndCategoryId = articleAndCategoryId;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}

	public Integer getAllowDiscuss() {
		return allowDiscuss;
	}

	public void setAllowDiscuss(Integer allowDiscuss) {
		this.allowDiscuss = allowDiscuss;
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
		ArticleEntity other = (ArticleEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleEntity [id=" + id + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", abstractTitle=" + abstractTitle + ", likeCount=" + likeCount + ", viewCount=" + viewCount
				+ ", replayCount=" + replayCount + ", articleAndCategoryId=" + articleAndCategoryId + ", publish="
				+ publish + ", delete=" + delete + ", allowDiscuss=" + allowDiscuss + "]";
	}

}
