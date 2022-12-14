package Posts;

public class Post {
    int seqID;
    
    String username;
    
    String timestamp;
    String content;
    
    public Post(int id, String username, String timestamp, String content) {
        this.seqID = id;
        this.username = username;
        this.timestamp = timestamp;
        this.content = content;
    }
}