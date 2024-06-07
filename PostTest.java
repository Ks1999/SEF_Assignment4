import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PostTest {

    @Test
    public void testAddPost() {
        Post post1 = new Post(1, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertTrue(post1.addPost());

        Post post2 = new Post(2, "Valid Title", "A".repeat(200), new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post2.addPost());

        Post post3 = new Post(3, "12345Valid", "A".repeat(300), new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post3.addPost());

        Post post4 = new Post(4, "Valid Title", "A".repeat(300), new String[]{"TAG1", "tag2"}, "Difficult", "Highly Needed");
        assertFalse(post4.addPost());

        Post post5 = new Post(5, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2", "tag3", "tag4"}, "Easy", "Highly Needed");
        assertFalse(post5.addPost());

        Post post6 = new Post(6, "Valid Title", "A".repeat(300), new String[]{"tag1"}, "Easy", "Ordinary");
        assertFalse(post6.addPost());
    }

    @Test
    public void testAddComment() {
        Post post1 = new Post(1, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        assertTrue(post1.addComment("This is a comment here"));

        Post post2 = new Post(2, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        post2.addComment("Valid comment one here");
        post2.addComment("Valid comment two here");
        post2.addComment("Valid comment three here");
        assertFalse(post2.addComment("This comment should fail"));

        Post post3 = new Post(3, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Difficult", "Highly Needed");
        post3.addComment("Valid comment one here");
        post3.addComment("Valid comment two here");
        post3.addComment("Valid comment three here");
        post3.addComment("Valid comment four here");
        post3.addComment("Valid comment five here");
        assertFalse(post3.addComment("This comment should fail"));

        Post post4 = new Post(4, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Very Difficult", "Highly Needed");
        assertFalse(post4.addComment("Invalid comment"));

        Post post5 = new Post(5, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Very Difficult", "Highly Needed");
        assertTrue(post5.addComment("This is a comment here"));

        Post post6 = new Post(6, "Valid Title", "A".repeat(300), new String[]{"tag1", "tag2"}, "Easy", "Ordinary");
        assertFalse(post6.addComment("Invalid comment"));
    }
}
