import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String postType;
    private String postEmergency;
    private ArrayList<String> postComments = new ArrayList<>();

    private static final String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private static final String[] postEmergencyLevels = {"Immediately Needed", "Highly Needed", "Ordinary"};

    public Post(int postID, String postTitle, String postBody, String[] postTags, String postType, String postEmergency) {
        this.postID = postID;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.postTags = postTags;
        this.postType = postType;
        this.postEmergency = postEmergency;
    }

    public boolean addPost() {

        if (!isValidPostTitle(postTitle)) return false;
        //makes sure body of post is more than 250 characters
        if (postBody.length() < 250) return false;
        if (postTags.length < 2 || postTags.length > 5) return false;
        for (String tag : postTags) {
            if (!isValidTag(tag)) return false;
        }
        if (postType.equals("Easy") && postTags.length > 3) return false;
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postBody.length() < 300) return false;
        if (postType.equals("Easy") && !postEmergency.equals("Ordinary")) return false;
        if ((postType.equals("Very Difficult") || postType.equals("Difficult")) && postEmergency.equals("Ordinary")) return false;

        
        //Writes the post onto "post.txt" if validation passes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("post.txt", true))) {
            writer.write(this.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
	//checks title validation with length requirement and no numbers or special characters for the first five characters
    private boolean isValidPostTitle(String title) {
        if (title.length() < 10 || title.length() > 250) return false;
        return !Pattern.compile("^[0-9!@#\\$%\\^&\\*]").matcher(title.substring(0, 5)).find();
    }
    
    //validates that tags are lowercase and within specified length range
    private boolean isValidTag(String tag) {
        if (tag.length() < 2 || tag.length() > 10) return false;
        return tag.equals(tag.toLowerCase());
    }

    public boolean addComment(String comment) {
    	//checks comment length
        if (comment.split("\\s+").length < 4 || comment.split("\\s+").length > 10) return false;
        //checks if first character is uppercase
        if (!Character.isUpperCase(comment.charAt(0))) return false;
        //limits number of comments based on post type
        if (postType.equals("Easy") || postEmergency.equals("Ordinary")) {
            if (postComments.size() >= 3) return false;
        } else {
            if (postComments.size() >= 5) return false;
        }
        postComments.add(comment);

        //Writes the comments onto "comment.txt" if validation passes
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("comment.txt", true))) {
            writer.write("PostID: " + postID + " - " + comment);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //provides a string representation of the post for writing to a file
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PostID: ").append(postID).append("\n");
        sb.append("Title: ").append(postTitle).append("\n");
        sb.append("Body: ").append(postBody).append("\n");
        sb.append("Tags: ");
        for (String tag : postTags) {
            sb.append(tag).append(" ");
        }
        sb.append("\n");
        sb.append("Type: ").append(postType).append("\n");
        sb.append("Emergency: ").append(postEmergency).append("\n");
        return sb.toString();
    }
}
