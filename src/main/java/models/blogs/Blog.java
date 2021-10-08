package models.blogs;

import java.sql.Timestamp;
import java.util.List;

public class Blog {

    private int blogID;
    private String blogName;
    private int createdBy;
    private String templateName;
    private Timestamp createdOn;

    public List<Post> posts;

    public Blog() {
    }

    public Blog(
                int blogID,
                String blogName,
                int createdBy,
                String templateName,
                Timestamp createdOn) {
        this.blogID = blogID;
        this.blogName = blogName;
        this.createdBy = createdBy;
        this.templateName = templateName;
        this.createdOn = createdOn;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
