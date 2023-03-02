package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        User user1 = new User("Andre", "Smith", (byte) 10);
        User user2 = new User("Andy", "Smith", (byte) 7);
        User user3 = new User("Edie", "Smith", (byte) 14);
        User user4 = new User("Walter", "Smith", (byte) 18);
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        us.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        us.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        us.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        us.getAllUsers();
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
