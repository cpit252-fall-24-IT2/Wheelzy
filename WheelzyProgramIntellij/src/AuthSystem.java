
import java.io.IOException;
import java.util.Scanner;

public class AuthSystem {
    private final String FILE_PATH = "login.txt";
    Scanner reader = new Scanner(FILE_PATH);

    // after register , store to login file
    public void register(User user) throws IOException {

    }

    // login will check the login file if exist or not or it valid or not
    public User login(String username, String password) throws IOException {

        return null;
    }

    // check while register if the username exists or not
    public boolean userExists(String username) throws IOException {
        return true;
    }
}
