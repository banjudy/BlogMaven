package controllers;

import models.blogs.Blog;
import models.blogs.Comment;
import models.blogs.CommentType;
import models.blogs.Post;
import models.roles.RoleType;
import models.users.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class AllControllerTest {

    AllController ac = new AllController();

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
        Assert.assertEquals(expected.size(), ac.getAccessesPerRoleType(RoleType.ADMIN).size());
        Assert.assertTrue(compareLists(expected, ac.getAccessesPerRoleType(RoleType.ADMIN)));
    }

    @Test
    public void loadAccessesForUsersTest(){
        User expectedAdmin = new User(
                1,
                        "banjudy007",
                        RoleType.ADMIN,
                        "hasselHoff01",
                        "banjudy@gmail.com",
                        Timestamp.valueOf("2021-03-03 09:46:41"));

        List<String> expectedAccessOfAdmin = Arrays.asList(
                "read_all_userdata",
                "read_post",
                "modify_post",
                "delete_post",
                "modify_all_userdata",
                "write_comments"
        );

        Assert.assertTrue(ac.loadAccessesForUsers());
        Assert.assertEquals(expectedAccessOfAdmin, ac.uc.getUserList().get(0).getAccesses());
    }

    @Test
    public void loadCommentListForPostTest() {
        Post post = new Post(
                2,
                "Weekly reminder for Wax the Hoff",
                "archived",
                "it is very important to...",
                Timestamp.valueOf("2021-05-06 09:46:41"));

        Comment expectedCommentOnPost = new Comment(
                        1,
                        "csigiri vagy migiri",
                        CommentType.NEW,
                        Timestamp.valueOf("2021-03-07 09:46:41"));
        Assert.assertTrue(ac.loadCommentListForPost());
        Assert.assertEquals(
                expectedCommentOnPost.getCommentText(),
                ac.bc.getPostList().get(1).getComments().get(0).getCommentText());
    }
    @Test
    public void loadPostListForBlogTest() {
        Blog expected = new Blog(
                4,
                "Naturally Troll",
                3,
                "nature",
                Timestamp.valueOf("2021-09-13 09:46:41"));

        Post expectedPostOnBlog = new Post(3,
                        "Trolling in nature as a real troll",
                        "published",
                        "blablablabla",
                        Timestamp.valueOf("2021-09-16 09:46:41"));

        Assert.assertTrue(ac.loadPostListForBlog());
        Assert.assertEquals(
                expectedPostOnBlog.getTitle(),
                ac.bc.getBlogList().get(3).getPosts().get(0).getTitle());
    }
    @Test
    public void loadBlogsForUsersTest() {
        User expected = new User(
                1,
                "banjudy007",
                RoleType.ADMIN,
                "hasselHoff01",
                "banjudy@gmail.com",
                Timestamp.valueOf("2021-03-03 09:46:41"));
        Blog expectedBlog = new Blog(
                1,
                "The Hungarian side of Medellín",
                1,
                "citylife",
                Timestamp.valueOf("2021-03-05 09:46:41"));

        Assert.assertTrue(ac.loadBlogsForUsers());
        Assert.assertEquals(
                expectedBlog.getBlogName(),
                ac.uc.getUserList().get(0).getBlogs().get(0).getBlogName());
    }

    @Test
    public void blogsPerUserIDTest() {
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
        Assert.assertEquals(expected.size(), ac.blogsPerUserID(1).size());
        Assert.assertEquals(expected.get(1).getTemplateName(), ac.blogsPerUserID(1).get(1).getTemplateName());
    }

    @Test
    public void findPostsOnBlogTest() {
        List<Post> expected = Arrays.asList(
                new Post(3,
                        "Trolling in nature as a real troll",
                        "published",
                        "blablablabla",
                        Timestamp.valueOf("2021-09-16 09:46:41")
                ));

        Assert.assertEquals(expected.get(0).getText(), ac.findPostsOnBlog(4).get(0).getText());
        Assert.assertEquals(expected.get(0).getCreatedOn(), ac.findPostsOnBlog(4).get(0).getCreatedOn());
    }

    @Test
    public void findAllCommentsPerPostTest(){
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

        Assert.assertEquals(expected.size(), ac.findAllCommentsPerPost(2).size());
        Assert.assertEquals(
                expected.get(1).getCommentText(),
                ac.findAllCommentsPerPost(2).get(1).getCommentText());
    }

    private boolean compareLists(List<String> expected, List<String> actual) {
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
