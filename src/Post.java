import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
public class Post {
    String date;
    String time;
    String content;
    public Post(String content) {
        this.date = getDate();
        this.time = getTime();
        this.content = content;
    }
    void printPosts () {
        for (String s : Arrays.asList("Data: " + date, "Hora: " + time, "Content: " + content)) {
            System.out.println(s);
        }
    }
    public String getDate() {
        LocalDateTime now = LocalDateTime.now();                                       // guarda DATA e HORA da execução, via java.time.LocalDateTime
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/uuuu");   // formata a DATA, via java.time.format.DateTimeFormatter
        String dateToday = formatterData.format(now);                                  // salva DATA como String no formato 'DD/MM/AAAA'
        System.out.println(dateToday);
        return dateToday;
    }
    public String getTime() {
        LocalDateTime now = LocalDateTime.now();                                       // guarda DATA e HORA da execução, via java.time.LocalDateTime
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");     // formata a HORA, via java.time.format.DateTimeFormatter
        String timeNow = formatterHora.format(now);                                    // salva a HORA no formato 'HH:mm:ss.'
        System.out.println(timeNow);
        return timeNow;
    }
}