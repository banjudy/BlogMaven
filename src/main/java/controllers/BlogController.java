package controllers;

import database.DBEngine;
import models.blogs.Blog;
import models.blogs.Comment;
import models.blogs.CommentType;
import models.blogs.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BlogController {

    public List<Blog> blogList;
    public List<Post> postList;
    public List<Comment> commentList;

    public BlogController() {
        this.blogList = listAllBlogs();
        this.postList = listAllPosts();
        this.commentList = loadAllComments();
    }

    public Post findPostPerID(int wantedPostID) {
        for (Post currentPost : getPostList()) {
            if (currentPost.getPostID() == wantedPostID) {
                return currentPost;
            }
        }
        return null;
    }

    public List<Integer> findPostIDPerBlogID(int wantedBlogID) {
        String query = "SELECT * FROM blog_post WHERE blog_id = ?;";

        List<Integer> wantedPostIDs = new ArrayList<>();

        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ps.setInt(1, wantedBlogID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("post_id");
                wantedPostIDs.add(postID);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        return wantedPostIDs;
    }

    public Comment findCommentPerID(int commentID) {
        for (Comment currentComment : getCommentList()) {
            if (currentComment.getCommentID() == commentID) {
                return currentComment;
            }
        }
        return null;
    }

    public List<Integer> getCommentIDsForPost(int wantedPostID) {
        String query = "SELECT * FROM post_receivedcomment WHERE post_id = ?;";

        List<Integer> wantedCommentIDs = new ArrayList<>();

        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ps.setInt(1, wantedPostID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int commentID = rs.getInt("receivedcomment_id");
                wantedCommentIDs.add(commentID);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        return wantedCommentIDs;
    }

    public List<Post> listAllPosts() {
        String query = "SELECT * FROM post;";
        postList = new ArrayList<>();

        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int postID = rs.getInt("post_id");
                String title = rs.getString("post_title");
                String status = rs.getString("post_status");
                String text = rs.getString("post_text");
                Timestamp createdOn = rs.getTimestamp("created_on");

                Post post = new Post(postID, title, status, text, createdOn);
                postList.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Blog> listAllBlogs() {
        String query = "SELECT * FROM blog;";

        blogList = new ArrayList<>();

        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int blogID = rs.getInt("blog_id");
                String blogName = rs.getString("blog_name");
                int createdBy = rs.getInt("created_by");
                String templateName = rs.getString("chosen_template_name");
                Timestamp createdOn = rs.getTimestamp("created_on");

                Blog blog = new Blog(blogID, blogName, createdBy, templateName, createdOn);
                blogList.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogList;
    }

    public List<Comment> loadAllComments() {
        String query = "SELECT * FROM receivedcomment;";

        commentList = new ArrayList<>();
        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int commentID = rs.getInt("comment_id");
                String text = rs.getString("comment_text");
                CommentType commentType = CommentType.valueOf(rs.getString("comment_type").toUpperCase());
                Timestamp dateSent = rs.getTimestamp("date_sent");

                Comment comment = new Comment(commentID, text, commentType, dateSent);
                commentList.add(comment);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        return commentList;
    }

    public List<Blog> getBlogList() {
        return blogList;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

}


