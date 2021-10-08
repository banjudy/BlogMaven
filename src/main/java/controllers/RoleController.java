package controllers;

import database.DBEngine;
import models.roles.Role;
import models.roles.RoleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleController {

    public List<Role> allRoles;

    public RoleController() {
        this.allRoles = listOfAllRoles();
    }

    /**
     * Helps to maintain users' roles and accesses.
     */

    public boolean addNewAccess(Role newRole){
        String query = "INSERT INTO roleinformation (role_name, access_type) VALUES (?, ?);";
        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ps.setString(1, newRole.getRoleType().toString());
            ps.setString(2, newRole.getAccessType());

            ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean removeCeasedAccess(Role ceasedRole){
        String query = "DELETE FROM roleinformation WHERE role_name = ? and access_type = ?;";
        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ps.setString(1, ceasedRole.getRoleType().toString());
            ps.setString(2, ceasedRole.getAccessType());

            ps.executeUpdate();
            ps.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Role> listOfAllRoles() {
        String query = "SELECT * FROM roleinformation;";

        allRoles = new ArrayList<>();
        try {
            PreparedStatement ps = DBEngine.getInstance().connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RoleType roleType = RoleType.valueOf(rs.getString("role_name").toUpperCase());
                String accessType = rs.getString("access_type");

                allRoles.add(new Role(roleType, accessType));
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
            e.printStackTrace();
        }
        return allRoles;
    }

    public List<Role> getAllRoles() {
        return listOfAllRoles();
    }
}
