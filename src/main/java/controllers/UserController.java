package controllers;

import database.DBEngine;
import models.roles.RoleType;
import models.users.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    public List<User> userList;

    public UserController() {
        this.userList = listAllUsers();
    }

    public List<User> listAllUsers(){
        String query = "SELECT * FROM userdata;";
        userList = new ArrayList<>();

        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userID = rs.getInt("user_id");
                String userName = rs.getString("user_name");
                RoleType roleType = RoleType.valueOf(rs.getString("role_name").toUpperCase());
                String password = rs.getString("password");
                String emailAddress = rs.getString("email_address");
                Timestamp registrationDate = rs.getTimestamp("registration_date");

                User user = new User(userID, userName, roleType, password, emailAddress, registrationDate);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
