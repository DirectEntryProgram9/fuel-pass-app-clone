package db;

import java.util.ArrayList;

public class InMemoryDB {
    private static ArrayList<User> userDatabase = new ArrayList<>();

    public static boolean registerUser(User user) {
        if (findUser(user.getNic()) != null) return false;
        userDatabase.add(user);
        return true;
    }

    public static User findUser(String nic) {
        for (User user : userDatabase) {
            if (user.getNic().equalsIgnoreCase(nic)) {
                return user;
            }
        }
        return null;
    }

    public static void removeUser(String nic) {
        User user = findUser(nic);
        if (user != null) userDatabase.remove(user);
    }

    public static ArrayList<User> getUserDatabase() {
        return userDatabase;
    }
}
