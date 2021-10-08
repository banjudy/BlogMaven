package controllers;

import models.blogs.Blog;
import models.blogs.Comment;
import models.blogs.Post;
import models.roles.Role;
import models.roles.RoleType;
import models.users.User;

import java.util.ArrayList;
import java.util.List;

public class AllController {
    public UserController uc;
    public BlogController bc;
    public RoleController rc;

    public AllController() {
        this.uc = new UserController();
        this.bc = new BlogController();
        this.rc = new RoleController();
        loadCommentListForPost();
        loadPostListForBlog();
        loadBlogsForUsers();
        loadAccessesForUsers();
    }

    public List<String> getAccessesPerRoleType(RoleType roleType) {
        List<String> accesses = new ArrayList<>();
        for (Role currentRole : rc.getAllRoles()) {
            if (currentRole.getRoleType().equals(roleType)) {
                accesses.add(currentRole.getAccessType());
            }
        }
        return accesses;
    }

    public boolean loadAccessesForUsers() {
        if (!uc.getUserList().isEmpty()) {
            for (User currentUser : uc.getUserList()) {
                currentUser.setAccesses(getAccessesPerRoleType(currentUser.getRoleType()));
            }
            return true;
        }
        return false;
    }

    public boolean loadCommentListForPost() {
        List<Post> postList = bc.getPostList();
        if (!postList.isEmpty()) {
            for (Post currentPost : postList) {
                currentPost.setComments(findAllCommentsPerPost(currentPost.getPostID()));
            }
            return true;
        }
        return false;
    }

    public boolean loadPostListForBlog() {
        List<Blog> blogList = bc.getBlogList();
        if (!blogList.isEmpty()) {
            for (Blog currentBlog : blogList) {
                currentBlog.setPosts(findPostsOnBlog(currentBlog.getBlogID()));
            }
            return true;
        }
        return false;
    }

    public boolean loadBlogsForUsers() {
        List<User> userList = uc.getUserList();
        if (!userList.isEmpty()) {
            for (User currentUser : userList) {
                currentUser.setBlogs(blogsPerUserID(currentUser.getUserID()));
            }
            return true;
        }
        return false;
    }

    public List<Blog> blogsPerUserID(int userID) {
        List<Blog> blogs = new ArrayList<>();
        for (Blog currentBlog : bc.getBlogList()) {
            if (currentBlog.getCreatedBy() == userID) {
                blogs.add(currentBlog);
            }
        }
        return blogs;
    }

    public List<Post> findPostsOnBlog(int blogID) {
        List<Post> listOfWantedPosts = new ArrayList<>();
        for (Integer currentPostID : bc.findPostIDPerBlogID(blogID)) {
            listOfWantedPosts.add(bc.findPostPerID(currentPostID));
        }
        return listOfWantedPosts;
    }

    public List<Comment> findAllCommentsPerPost(int wantedPostID) {
        List<Comment> allWantedComments = new ArrayList<>();
        for (Integer currentCommentID : bc.getCommentIDsForPost(wantedPostID)) {
            allWantedComments.add(bc.findCommentPerID(currentCommentID));
        }
        return allWantedComments;
    }
}
