package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utilities.DAOUtilities;

public class RoleGenericDAOImpl implements GenericDAO<Role> {
	private Connection connection;

	public Role create(Role role) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO role (role) VALUES (?)");
			
			ps.setString(1, role.getRole());
		
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		return role;
		
	}

	public Role get(int id) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM role WHERE id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setRole(rs.getString("role"));
				
				return role;
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

	public Role update(Role role) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE role "
					+ "SET role = ? WHERE id = ?");
			
			ps.setString(1, role.getRole());
			ps.setInt(2, role.getId());
		
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		return role;
	}

	public void delete(Role role) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM role WHERE id = ?;");
			ps.setInt(1, role.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
	}

	public List<Role> getAll() {
		List<Role> roles = new ArrayList<Role>();
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM role");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setRole(rs.getString("role"));

				roles.add(role);
			}
			return roles;
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
