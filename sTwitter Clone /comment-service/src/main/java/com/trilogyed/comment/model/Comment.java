package com.trilogyed.comment.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

//@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//@Table(name = "comment")
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @NotNull(message = "postId required")
    private int postId;

    @NotNull(message = "date of comment required")
    @Column(name="create_date")
    private LocalDate commentDate;

    @NotNull(message = "poster name required")
    @Size(max= 50, message = "size cannot exceed 50 characters")

    private String commenterName;
    @NotNull(message = "must include comment")
    @Size(max=255, message = "post size cannot exceed 255")

    private String comment;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDate getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(){

    }

    public Comment(int commentId, int postId, LocalDate commentDate, String commenterName, String comment) {
        this.commentId = commentId;
        this.postId = postId;
        this.commentDate = commentDate;
        this.commenterName = commenterName;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return getCommentId() == comment1.getCommentId() &&
                getPostId() == comment1.getPostId() &&
                getCommentDate().equals(comment1.getCommentDate()) &&
                getCommenterName().equals(comment1.getCommenterName()) &&
                getComment().equals(comment1.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommentId(), getPostId(), getCommentDate(), getCommenterName(), getComment());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", createDate=" + commentDate +
                ", commenterName='" + commenterName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}