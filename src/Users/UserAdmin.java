package Users;

public class UserAdmin extends UserAbstractModel {
    UserAdmin(int id, String name, String username, String password) {
        super(id, name, username, password);
        this.setAdmin(true);
    }
    
    @Override
    public void makeInactive() {
        this.setActive(false);
    }
}