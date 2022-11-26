import java.util.Arrays;
public class Post {
    int id;
    String date;
    String time;
    String timestamp;
    String content;
    public Post(String content) {
        this.date = TimeStamp.getDate();
        this.time = TimeStamp.getTime();
        this.timestamp = TimeStamp.getDateTime();
        this.content = content;
    }
    void printPost () {
        for (String s : Arrays.asList(" Post: " + id, "Em " + timestamp, "\n Conteúdo: " + content)) {
            System.out.println(s);
        }
    }
}