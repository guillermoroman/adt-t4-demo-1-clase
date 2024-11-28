package com.example;

import com.example.model.User;
import com.example.util.HibernateUtil;
import com.example.util.JdbcUserDAO;
import org.hibernate.Session;

public class AppComparison {
    public static void main(String[] args) {
        // Operaciones con JDBC
        //handleJDBCOperations();

        // Operaciones con Hibernate
        handleHibernateOperations();
    }

    private static void handleJDBCOperations(){
        System.out.println("\n=== Usando JDBC ===");

        JdbcUserDAO jdbcUserDAO = new JdbcUserDAO();

        // Crear un usuario con JDBC
        User user = new User("Juan JDBC", "juan.jdbc@example.com");
        jdbcUserDAO.insertUser(user);
        System.out.println("Usuario creado con JDBC: " + user.getName());

        // Leer usuario con JDBC
    }

    private static void handleHibernateOperations(){
        System.out.println("\n=== Usando Hibernate ===");

        User hibernateUser = new User ("Juan Hibernate", "juan.hibernate@example.com");

        try(Session session = HibernateUtil.getSessionFactory().openSession()){

            // empezar una transacción
            session.beginTransaction();

            // operar
            session.persist(hibernateUser);

            // ejecutar transacción
            session.getTransaction().commit();

            System.out.println("Usuario creado con Hibernate: " + hibernateUser.getName());
        }
    }
}
