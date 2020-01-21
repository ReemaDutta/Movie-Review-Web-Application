package com.my.moviereview1.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;


import com.my.moviereview1.exception.UserException;
import com.my.moviereview1.pojo.User;
import com.my.moviereview1.pojo.UserMovies;



public class UserDAO extends DAO{
	
	public UserDAO() {
	}

	//Register User
	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");
			
			User user = new User();
			user.setUsername(u.getUsername());
			user.setPassword(u.getPassword());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(u.getEmail());			
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}

	//get username, password
	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	//save user
	public UserMovies saveUser(String username, String movieid) throws Exception {
		try {
			begin();
			UserMovies um = new UserMovies();
			um.setUsername(username);
			um.setMovieid(movieid);
			getSession().save(um);
			commit();
			return um;
		}catch(HibernateException e) {
			rollback();
			throw new Exception("Could not get user" + username, e);
		}
		}
	
	//delete user
	public void deleteUser(String username, String movie) throws Exception {
		try {
			Query query = getSession().createQuery("Delete from UserMovies where username = :username and movieid = :movie");
			query.setParameter("username", username);
			query.setParameter("movie", movie);
			
			query.executeUpdate();
				
		}catch(HibernateException e) {
			rollback();
			throw new Exception("Could not get user" + username, e);
		}
		}
	
	//Retrive MovieID
	public boolean retrieveMovieId(String username, String movie) {
		
		Query query = getSession().createQuery("from UserMovies where username = :username and movieid = :movie");
		query.setParameter("username", username);
		query.setParameter("movie", movie);
		
		List list = query.list();
		
		if(list.size() > 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public List retrieveMovieUser(String username) {		
		Query query = getSession().createQuery("from UserMovies where username = :username");
		query.setParameter("username", username);		
		List list = query.list();
		close();
		return list;	
	}
	
	
	//List of Users
	public List retrieveUser() {		
		Query query = getSession().createQuery("from User");			
		List list = query.list();
		close();
		return list;	
	}
	
	//Admin
	public ArrayList<User> displayUser(String username) throws UserException {		
		
		String name = "admin";
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			begin();
			Query q = null;
			if(username.equals("admin")) {
			 q = getSession().createQuery("from User where username != :name");
			 q.setParameter("name", name);
			}
			userlist =  (ArrayList<User>) q.list();
			commit();
			return userlist;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}	
	}
	
	//delete user
	public void deleteUserByAdmin(String username) throws Exception {
		try {
			Query query = getSession().createQuery("Delete from User where username = :username");
			query.setParameter("username", username);			
			query.executeUpdate();
				
		}catch(HibernateException e) {
			rollback();
			throw new Exception("Could not get user" + username, e);
		}
		}
	
	//get user based on name
	public User getUser(String username) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username");
			q.setString("username", username);					
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	
	
	public void EditUserDetails(String username) throws Exception {
		
		try {
			Query query = getSession().createQuery("Update User where username = :username");
			query.setParameter("username", username);			
			query.executeUpdate();
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new Exception("Could not get user" + username, e);
		}
	

	}
	
	public User getId(String username) throws Exception {
		try {
			begin();
			Query query = getSession().createQuery("from User where username = :username");
			query.setString("username", username);			
			User u = (User) query.uniqueResult();			
			commit();
			return u;
		}catch(HibernateException e) {
			rollback();
			throw new Exception("Could not get user" + username, e);
		}
		
	}
	
	public void updateUser(User user) throws Exception {
		String username = user.getUsername();
		String firstname = user.getFirstName();
		String lastname = user.getLastName();
		String email = user.getEmail();
		String password = user.getPassword();
	
		try {
			begin();
			Query query = getSession().createQuery("update User SET firstname = :firstname," +
							" lastname = :lastname," +
							" email = :email," +
							" password = :password" +
							" where username = :username");
			query.setParameter("firstname", firstname);
			query.setParameter("lastname", lastname);
			query.setParameter("email", email);
			query.setParameter("password", password);
			query.setParameter("username", username);
			
			int x = query.executeUpdate();
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new Exception("Could not get user" + username, e);
		}
	}	
}
	
	
	

