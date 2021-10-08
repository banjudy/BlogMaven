package models.blogs;

import java.sql.Timestamp;

public class Comment extends Post {

    private int commentID;
    private String commentText;
    private CommentType commentType;
    private Timestamp dateSent;

    public Comment(int commentID, String commentText, CommentType commentType, Timestamp dateSent) {
        this.commentID = commentID;
        this.commentText = commentText;
        this.commentType = commentType;
        this.dateSent = dateSent;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public Timestamp getDateSent() {
        return dateSent;
    }

    public void setDateSent(Timestamp dateSent) {
        this.dateSent = dateSent;
    }
}