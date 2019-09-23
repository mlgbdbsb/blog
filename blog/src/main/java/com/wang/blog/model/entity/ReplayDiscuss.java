package com.wang.blog.model.entity;

/**
 * 回评实体类
 * @author wang
 *
 */
public class ReplayDiscuss extends BaseEntity {

	private static final long serialVersionUID = 3030500929043347425L;
	private Long id;
	private Long userId;
	private Long articleId;
	private Long replayUserId;
	private String discussContent;

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

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getReplayUserId() {
		return replayUserId;
	}

	public void setReplayUserId(Long replayUserId) {
		this.replayUserId = replayUserId;
	}

	public String getDiscussContent() {
		return discussContent;
	}

	public void setDiscussContent(String discussContent) {
		this.discussContent = discussContent;
	}

	@Override
	public String toString() {
		return "ReplayDiscuss [id=" + id + ", userId=" + userId + ", articleId=" + articleId + ", replayUserId="
				+ replayUserId + ", discussContent=" + discussContent + "]";
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
		ReplayDiscuss other = (ReplayDiscuss) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
