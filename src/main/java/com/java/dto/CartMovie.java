package com.java.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartMovie {
private int userId;
private int movieId;
public CartMovie() {
	
}
public CartMovie(ResultSet rt) throws SQLException {
	super();
	this.userId = rt.getInt(1);
	this.movieId = rt.getInt(2);
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getMovieId() {
	return movieId;
}
public void setMovieId(int movieId) {
	this.movieId = movieId;
}
@Override
public String toString() {
	return "CartMovie [userId=" + userId + ", movieId=" + movieId + "]";
}

}
