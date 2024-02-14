package com.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.java.dto.CartMovie;
import com.java.dto.User;

public class CartDAO {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/moviehub", "root", "root");
    }

    public int saveCart(int uid, int movieid) throws SQLException, ClassNotFoundException {
        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO cartmovie VALUES (?, ?)")) {
            pst.setInt(1, uid);
            pst.setInt(2, movieid);
            return pst.executeUpdate();
        }
    }

    public List<Integer> getCart(User user) throws ClassNotFoundException, SQLException {
        if (user != null) {
            try (Connection con = getConnection();
                 PreparedStatement pst = con.prepareStatement("SELECT * FROM cartmovie WHERE user_id = ?")) {
                pst.setInt(1, user.getUid());

                ResultSet rt = pst.executeQuery();
                List<Integer> cart = new ArrayList<>();
                CartMovie cm = null;

                while (rt.next()) {
                    cm = new CartMovie(rt);
                    cart.add(cm.getMovieId());
                }

                Collections.sort(cart);

                if (cm != null) {
                    return cart;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
}
