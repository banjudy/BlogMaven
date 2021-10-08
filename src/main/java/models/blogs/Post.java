package models.blogs;

import java.sql.Timestamp;
import java.util.List;;

public class Post extends Blog {
    private int postID;
    private String title;
    private String status;
    private String text;
    private Timestamp createdOn;

    private List<Comment> comments;

    public Post() {
    }

    public Post(int postID, String title, String status, String text, Timestamp createdOn) {
        this.postID = postID;
        this.title = title;
        this.status = status;
        this.text = text;
        this.createdOn = createdOn;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

