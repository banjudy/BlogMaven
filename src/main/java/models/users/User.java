package models.users;

import models.blogs.Blog;
import models.roles.RoleType;

import java.sql.Timestamp;
import java.util.List;

public class User {

    private int userID;
    private String userName;
    private RoleType roleType;
    private String password;
    private String emailAddress;
    private Timestamp registrationDate;

    private List<String> accesses;
    private List<Blog> blogs;



    public User(
                int userID,
                String userName,
                RoleType roleType,
                String password,
                String emailAddress,
                Timestamp registrationDate) {
        this.userID = userID;
        this.userName = userName;
        this.roleType = roleType;
        this.password = password;
        this.emailAddress = emailAddress;
        this.registrationDate = registrationDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
    public List<String> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<String> accesses) {
        this.accesses = accesses;
    }
}
