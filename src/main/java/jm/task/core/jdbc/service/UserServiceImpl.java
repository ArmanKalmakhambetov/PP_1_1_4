package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;


import java.util.List;

public class UserServiceImpl implements UserService {


    private final UserDao udji = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        udji.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        udji.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        udji.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        udji.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return udji.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        udji.cleanUsersTable();
    }
}
