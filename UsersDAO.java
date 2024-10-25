//package DAO;
//
//import Beans.Users;
//import Util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//public class UsersDAO {
//
//    // Yeh method user ko username aur password ke aadhar par database se nikalta hai
//    public Users getUserByUsernameAndPassword(String username, String password) {
//        Users user = null;  // Result ko store karne ke liye variable
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            // Hibernate query banayi jo username aur password ke base par data nikalti hai
//            Query<Users> query = session.createQuery(
//                    "FROM Users WHERE username = :username AND password = :password", Users.class);
//            query.setParameter("saurav", username);
//            query.setParameter("8873", password);
//
//            user = query.uniqueResult(); // Agar unique result milega to usse return karega
//        } catch (Exception e) {
//            e.printStackTrace(); // Agar koi error aayi to usse print karega
//        }
//        return user;
//    }
//}



package DAO;

import Beans.Users;
import Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {

    public Users getUserByUsernameAndPassword(String username, String password) {
        Users user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new Users();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
