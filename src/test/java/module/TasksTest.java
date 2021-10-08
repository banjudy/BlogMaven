package module;

import controllers.RoleController;
import models.blogs.Blog;
import models.blogs.Comment;
import models.blogs.CommentType;
import models.blogs.Post;
import models.roles.Role;
import models.roles.RoleType;
import models.users.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class TasksTest {
    Tasks tasks = new Tasks();

    @Test
    public void taskConstructorTest() {
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

        Assert.assertEquals(expectedResult.size(), tasks.ac.uc.getUserList().size());
        Assert.assertNotNull(tasks.ac.bc.getBlogList());
        Assert.assertNotNull(tasks.ac.rc.getAllRoles());
    }

    @Test
    public void usersPerRoleTest() {

        List<User> expected = Arrays.asList(
                new User(1,
                        "banjudy007",
                        RoleType.ADMIN,
                        "hasselHoff01",
                        "banjudy@gmail.com",
                        Timestamp.valueOf("2021-03-03 09:46:41")));

       Assert.assertEquals(expected.get(0).getPassword(), tasks.usersPerRole(RoleType.ADMIN).get(0).getPassword());
        Assert.assertNotEquals(
                expected.get(0).getUserName(),
                tasks.usersPerRole(RoleType.MODERATOR).get(0).getUserName());
    }
    @Test
    public void findUserTest() {
        User expected = new User(
                2,
                "klarika",
                RoleType.MODERATOR,
                "tibcsi 012",
                "klarika@freemail.hu",
                Timestamp.valueOf("2021-02-03 10:46:41"));

        Assert.assertEquals(expected.getEmailAddress(), tasks.findUser("klarika").getEmailAddress());
        Assert.assertNotEquals(expected.getPassword(), tasks.findUser("klarika").getPassword());
    }

    @Test
    public void blogsOfUserTest() {
        List<Blog> expected = Arrays.asList(
                new Blog(
                        1,
                        "The Hungarian side of Medellín",
                        1,
                        "citylife",
                        Timestamp.valueOf("2021-03-05 09:46:41")),
                new Blog(
                        2,
                        "Wax The Hoff",
                        1,
                        "fitness",
                        Timestamp.valueOf("2021-05-03 09:46:41"))
        );
        Assert.assertEquals(expected.size(), tasks.blogsOfUser(1).size());
        Assert.assertEquals(expected.get(1).getTemplateName(), tasks.blogsOfUser(1).get(1).getTemplateName());
    }

    @Test
    public void postsOnBlogTest() {
        List<Post> expected = Arrays.asList(
                new Post(3,
                        "Trolling in nature as a real troll",
                        "published",
                        "blablablabla",
                        Timestamp.valueOf("2021-09-16 09:46:41")
                ));

        Assert.assertEquals(expected.get(0).getText(), tasks.postsOnBlog(4).get(0).getText());
        Assert.assertEquals(expected.get(0).getCreatedOn(), tasks.postsOnBlog(4).get(0).getCreatedOn());
    }
    @Test
    public void getAllCommentsOnPostTest() {
        List<Comment> expected = Arrays.asList(
                new Comment(
                        1,
                        "csigiri vagy migiri",
                        CommentType.NEW,
                        Timestamp.valueOf("2021-03-07 09:46:41")
                ),
                new Comment(
                        2,
                        "szerintem migiri",
                        CommentType.REPLY,
                        Timestamp.valueOf("2021-03-07 10:46:41")));

        Assert.assertEquals(expected.size(), tasks.getAllCommentsOnPost(2).size());
        Assert.assertEquals(
                expected.get(1).getCommentText(),
                tasks.getAllCommentsOnPost(2).get(1).getCommentText());
    }

    @Test
    public void newRoleAccessTest() {
        RoleController rc = new RoleController();
        Role additionalRole = new Role(RoleType.USER, "delete_comments");

        Assert.assertTrue(tasks.newRoleAccess(additionalRole));
    }
    @Test
    public void removeAccessTest() {
        RoleController rc = new RoleController();
        Role toBeCeased = rc.allRoles.get(15);

        Assert.assertTrue(tasks.removeAccess(toBeCeased));
    }
    @Test
    public void getAccessesPerRoleTypeTest() {
        List<String> expected = Arrays.asList(
                "read_all_userdata",
                "read_post",
                "modify_post",
                "delete_post",
                "modify_all_userdata",
                "write_comments"
        );
        Assert.assertEquals(expected.size(), tasks.getAccessesPerRoleType(RoleType.ADMIN).size());
        Assert.assertTrue(compareStringLists(expected, tasks.getAccessesPerRoleType(RoleType.ADMIN)));
    }

    private boolean compareStringLists(List<String> expected, List<String> actual) {
        if (!(actual != null && expected.size() == actual.size())){
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).equals(actual.get(i))) {
                return false;
            }
        }
        return true;
    }
}
