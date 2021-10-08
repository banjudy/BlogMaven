package controllers;

import models.roles.Role;
import models.roles.RoleType;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleControllerTest {

    @Test
    public void listOfAllRolesTest() {
        RoleController rc = new RoleController();
        List<Role> expectedRoleList = Arrays.asList(
                new Role(RoleType.MODERATOR, "read_post"),
                new Role(RoleType.MODERATOR, "modify_post"),
                new Role(RoleType.MODERATOR, "delete_post"),
                new Role(RoleType.MODERATOR, "write_comments"));

        Assert.assertTrue(compareLists(expectedRoleList, filterForRole(rc.listOfAllRoles(), RoleType.MODERATOR)));
    }

    @Test
    public void addNewAccessTest() {
        RoleController rc = new RoleController();
        Role additionalRole = new Role(RoleType.USER, "delete_comments");

        Assert.assertTrue(rc.addNewAccess(additionalRole));
    }
    @Test
    public void removeCeasedAccessTest() {
        RoleController rc = new RoleController();
        Role toBeCeased = rc.allRoles.get(15);

        Assert.assertTrue(rc.removeCeasedAccess(toBeCeased));
    }

    private boolean compareLists(List<Role> expected, List<Role> actual) {
        if (!(actual != null && expected.size() == actual.size())){
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).getAccessType().equals(actual.get(i).getAccessType())) {
                return false;
            }
        }
        return true;
    }
    private List<Role> filterForRole(List<Role> actual, RoleType roleType) {
        List<Role> filtered = new ArrayList<>();
        for (Role currentRole : actual) {
            if (currentRole.getRoleType().equals(roleType)) {
                filtered.add(currentRole);
            }
        }
        return filtered;
    }
}
