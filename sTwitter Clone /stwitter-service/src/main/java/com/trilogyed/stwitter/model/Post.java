package com.trilogyed.stwitter.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Post implements Serializable {


    private int postId;

    @NotNull(message = "Date of post required")
    private LocalDate postDate;

    @NotNull(message = "Name/Alias of poster required")
    @Size(max=50, message = "Cannot exceed 50 char")
    private String posterName;

    @NotNull(message = "Post required")
    @Size(max=255, message = "Max is 255 char")
    private String post;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postID) {
        this.postId = postID;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Post(){

    }

    public Post(int postID, LocalDate postDate, String posterName, String post) {
        this.postId = postID;
        this.postDate = postDate;
        this.posterName = posterName;
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post1 = (Post) o;
        return getPostId() == post1.getPostId() &&
                getPostDate().equals(post1.getPostDate()) &&
                getPosterName().equals(post1.getPosterName()) &&
                getPost().equals(post1.getPost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getPostDate(), getPosterName(), getPost());
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postDate=" + postDate +
                ", posterName='" + posterName + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}