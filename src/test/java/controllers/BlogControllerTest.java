package controllers;

import models.blogs.Blog;
import models.blogs.Comment;
import models.blogs.CommentType;
import models.blogs.Post;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class BlogControllerTest {

    BlogController bc = new BlogController();

    @Test
    public void listAllBlogsTest() {
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
                        Timestamp.valueOf("2021-05-03 09:46:41")),
                new Blog(
                        3,
                        "Spinach in all my food",
                        2,
                        "food",
                        Timestamp.valueOf("2021-07-07 09:46:41")),
                new Blog(
                        4,
                        "Naturally Troll",
                        3,
                        "nature",
                        Timestamp.valueOf("2021-09-13 09:46:41")));

        Assert.assertEquals(expected.size(), bc.listAllBlogs().size());
    }
    @Test
    public void loadAllCommentsTest() {
        List<Comment> expected = Arrays.asList(
                new Comment(
                        1,
                            "csigiri vagy migiri",
                        CommentType.NEW,
                        Timestamp.valueOf("2021-03-07 09:46:41")),
                new Comment(
                        2,
                        "szerintem migiri",
                        CommentType.REPLY,
                        Timestamp.valueOf("2021-03-07 10:46:41")),
                new Comment(
                        3,
                        "hova tunik a feher ha elolvad a ho?",
                        CommentType.NEW,
                        Timestamp.valueOf("2021-09-17 09:46:41")));

        Assert.assertEquals(expected.size(), bc.loadAllComments().size());
        Assert.assertEquals(expected.get(2).getCommentText(), bc.loadAllComments().get(2).getCommentText());

    }
    @Test
    public void listAllPostsTest() {
        List<Post> expected = Arrays.asList(
                new Post(1,
                        "First day in Laureles",
                        "published",
                        "once upon a time i will start to...",
                        Timestamp.valueOf("2021-03-06 09:46:41")),
                new Post(2,
                        "Weekly reminder for Wax the Hoff",
                        "archived",
                        "it is very important to...",
                        Timestamp.valueOf("2021-05-06 09:46:41")),
                new Post(3,
                        "Trolling in nature as a real troll",
                        "published",
                        "blablablabla",
                        Timestamp.valueOf("2021-09-16 09:46:41")),
                new Post(4,
                        "Smooth method for heating the wax",
                        "draft",
                        "it is very important to...",
                        Timestamp.valueOf("2021-06-06 09:46:41")));

        Assert.assertEquals(expected.size(), bc.listAllPosts().size());
        Assert.assertTrue(compareLists(expected, bc.listAllPosts()));
    }

    @Test
    public void getCommentIDsForPostTest() {
        List<Integer> expected = Arrays.asList(1, 2);

        Assert.assertEquals(expected.size(), bc.getCommentIDsForPost(2).size());
        Assert.assertEquals(expected.get(0), bc.getCommentIDsForPost(2).get(0));
    }

    @Test
    public void findCommentPerIDTest() {
        Comment expected = new Comment(
                3,
                "hova tunik a feher ha elolvad a ho?",
                CommentType.NEW,
                Timestamp.valueOf("2021-09-17 09:46:41"));

        Assert.assertEquals(expected.getCommentText(), bc.findCommentPerID(3).getCommentText());
    }
    @Test
    public void findPostIDPerBlogIDTest(){
        List<Integer> expected = Arrays.asList(2, 4);

        Assert.assertEquals(expected.size(), bc.findPostIDPerBlogID(2).size());
        Assert.assertEquals(expected.get(0), bc.findPostIDPerBlogID(2).get(0));

    }
    @Test
    public void findPostPerIDTest(){
        Post expected = new Post(
                2,
                "Weekly reminder for Wax the Hoff",
                "archived",
                "it is very important to...",
                Timestamp.valueOf("2021-05-06 09:46:41"));
        Assert.assertEquals(expected.getTitle(), bc.findPostPerID(2).getTitle());
    }

    private boolean compareLists(List<Post> expected, List<Post> actual) {
        if (!(actual != null && expected.size() == actual.size())){
            return false;
        }
        for (int i = 0; i < expected.size(); i++) {
            if (!expected.get(i).getText().equals(actual.get(i).getText())) {
                return false;
            }
        }
        return true;
    }


}
