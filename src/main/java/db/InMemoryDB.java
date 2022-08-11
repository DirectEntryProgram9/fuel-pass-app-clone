package db;

import java.util.ArrayList;

public class InMemoryDB {
    private static ArrayList<User> userDatabase = new ArrayList<>();

    static {
        userDatabase.add(new User("123456789V","Kasun","Nuwan","Galle Road, Panadura",16));
        userDatabase.add(new User("456987123V","Eranga","Bandara","Galle Road, Kaluthara",20));
        userDatabase.add(new User("789654123V","Naveen","Ranasinghe","Galle Road, Rathmalana",16));
        userDatabase.add(new User("189456723V","Nipuna","Dinendra","Galle Road, Moratuwa",20));
    }

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
    public static ArrayList<User> findUsers(String query) {
        ArrayList<User> searchResult = new ArrayList<>();
        for (User user : userDatabase) {
            if (user.getNic().contains(query) ||
                    user.getFirstName().contains(query) ||
                    user.getLastName().contains(query) ||
                    user.getAddress().contains(query)) {
                searchResult.add(user);
            }
        }
        return searchResult;
    }

    public static void removeUser(String nic) {
        User user = findUser(nic);
        if (user != null) userDatabase.remove(user);
    }

    public static ArrayList<User> getUserDatabase() {
        return userDatabase;
    }
}
