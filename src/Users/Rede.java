package Users;

public class Rede {
    public static void main(String[] args) {
        User user = new User(0, "user0", "username0", "123");
        System.out.println("Nome: " + user.getName());
        user.setName("João");
        System.out.println("Nome: " + user.getName());
    
    }
    
}
