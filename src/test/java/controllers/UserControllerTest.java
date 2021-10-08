package controllers;

import models.roles.RoleType;
import models.users.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class UserControllerTest {

    @Test
    public void listAllUsersTest() {
        UserController uc = new UserController();
        List<User> expectedResult = Arrays.asList(
                new User(1,
                        "banjudy007",
                        RoleType.ADMIN,
                        "hasselHoff01",
                        "banjudy@gmail.com",
                        Timestamp.valueOf("2021-03-03 09:46:41")),

                new User(2,
                        "klarika",
                        RoleType.MODERATOR,
                        "tibcsi012",
                        "klarika@freemail.hu",
                        Timestamp.valueOf("2021-02-03 10:46:41")),

                new User(3,
                        "trollka",
                        RoleType.USER,
                        "mekkmester9",
                        "ero@gmail.esz",
                        Timestamp.valueOf("2021-06-03 09:42:41")));

    Assert.assertTrue(compareLists(expectedResult, uc.listAllUsers()));
    }

    private boolean compareLists(List<User> expected, List<User> actual) {
        if (!(actual != null && expected.size() == actual.size())){
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).getUserName().equals(actual.get(i).getUserName())) {
                return false;
            }
        }
        return true;
    }

}
