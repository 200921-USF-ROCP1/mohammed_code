package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utilities.DAOUtilities;

public class UserDAOImpl implements UserDAO{

	private Connection connection;
	
	GenericDAO<Role> roleDAO = new RoleGenericDAOImpl();
	
	public User create(User user) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password, first_name, last_name, email, role_id) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRole().getId());
			ps.executeUpdate();
			
			// Get new account key
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int userId = rs.getInt(1);
			
			return get(userId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}

		return null;
	}

	public User get(int id) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = roleDAO.get(rs.getInt("role_id"));
				user.setRole(role);
				
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		// There were 0 records returned
		return null;
	}

	public User update(User user) {
		// TODO Auto-generated method stub
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE users "
					+ "SET username = ?, password = ?, first_name = ?, last_name = ?, email = ?, role_id = ? "
					+ "WHERE id= ?");
			
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getRole().getId());
			ps.setInt(7, user.getId());
			
			ps.executeUpdate();
			
			return get(user.getId());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		return null;
	}

	public void delete(User user) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id = ?;");
			ps.setInt(1, user.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
	}

	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		
		try {
			connection = DAOUtilities.getConnection();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users "
					+ "LEFT JOIN role on users.role_id = role.id;");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = new Role();
				
				role.setId(rs.getInt("role_id"));
				role.setRole(rs.getString("role"));
				
				user.setRole(role);
				
				users.add(user);
			}
			
			return users;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		//No results
		return null;
	}

	public User getUserByUsernameAndPassword(String userName, String password) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE userName = ? AND password = ?");
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = roleDAO.get(rs.getInt("role_id"));
				user.setRole(role);
				
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		// There were 0 records returned
		return null;
	}

}
