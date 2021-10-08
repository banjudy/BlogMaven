package module;

import controllers.AllController;
import models.blogs.Blog;
import models.blogs.Comment;
import models.blogs.Post;
import models.roles.Role;
import models.roles.RoleType;
import models.users.User;

import java.util.ArrayList;
import java.util.List;

public class Tasks {

    public AllController ac;

    public Tasks() {
        this.ac = new AllController();
    }

    /**
     * 1. Returns the list of Users of a specific role type!
     */

    public List<User> usersPerRole(RoleType roleType) {
        List<User> filteredUserList = new ArrayList<>();
        for (User currentUser : ac.uc.getUserList()) {
            if (currentUser.getRoleType().equals(roleType)) {
                filteredUserList.add(currentUser);
            }
        }
        return filteredUserList;
    }

    /**
     * 2. Returns all data of a chosen user!
     */

    public User findUser(String userName) {
        for (User currentUser : ac.uc.getUserList()) {
            if (currentUser.getUserName().equals(userName)) {
                return currentUser;
            }
        }
        return null;
    }

    /**
     * 3. Returns all blogs of one user!
     */

    public List<Blog> blogsOfUser(int userID) {
        for (User currentUser : ac.uc.getUserList()) {
            if (currentUser.getUserID() == userID) {
                return currentUser.getBlogs();
            }
        }
        return null;
    }

    /**
     * 4. Returns all posts of a specific blog!
     */
    public List<Post> postsOnBlog(int blogID) {
        for (Blog currentBlog : ac.bc.getBlogList()) {
            if (currentBlog.getBlogID() == blogID) {
                return currentBlog.getPosts();
            }
        }
        return null;
    }

    /**
     * 5. Returns all comments on a Post!
     */
    public List<Comment> getAllCommentsOnPost(int postID) {
        for (Post currentPost : ac.bc.getPostList()) {
            if (currentPost.getPostID() == postID) {
                return currentPost.getComments();
            }
        }
        return null;
    }

    /**
     * 6.a
     * Add new Role-Access types to the current list!
     */
    public boolean newRoleAccess(Role newRole) {
        boolean success = ac.rc.addNewAccess(newRole);
        return success;
    }

    /**
     * 6.b
     * Remove ceased role from the roleList.
     */
    public boolean removeAccess(Role ceasedRole) {
        boolean removed = ac.rc.removeCeasedAccess(ceasedRole);
        return removed;
    }

    /**
     * 7. List of all access types based on RoleType!
     */

    public List<String> getAccessesPerRoleType(RoleType roleType) {
        List<String> accesses = ac.getAccessesPerRoleType(roleType);
        return accesses;
    }

}