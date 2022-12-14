package Users;

public class User extends UserAbstractModel {
    User(int id, String name, String username, String password) {
        super(id, name, username, password);
    }
    public void makeInactive() {
        this.setActive(false);
    }
}