package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Imya", "Familevich", (byte) 31);
        userService.saveUser("Ivan", "Ivanov", (byte) 32);
        userService.saveUser("Petya", "Petrov", (byte) 33);
        userService.saveUser("Vova", "Valey", (byte) 34);
        userService.removeUserById(2);
        userService.cleanUsersTable();
        System.out.println(userService.getAllUsers().toString());
        userService.dropUsersTable();


    }
}
