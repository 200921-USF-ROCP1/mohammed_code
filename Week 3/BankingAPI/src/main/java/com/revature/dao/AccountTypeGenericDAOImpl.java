package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountType;
import com.revature.utilities.DAOUtilities;

public class AccountTypeGenericDAOImpl implements GenericDAO<AccountType> {

	private Connection connection;

	public AccountType create(AccountType accountType) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO account_type (type) VALUES (?)");
			
			ps.setString(1, accountType.getType());
		
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		return accountType;
	}

	public AccountType get(int id) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM account_type WHERE id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AccountType accountType = new AccountType();
				accountType.setId(rs.getInt("id"));
				accountType.setType(rs.getString("type"));
				
				return accountType;
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

	public AccountType update(AccountType accountType) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE account_type "
					+ "SET type = ? WHERE id = ?");
			
			ps.setString(1, accountType.getType());
			ps.setInt(2, accountType.getId());
		
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		return accountType;
	}

	public void delete(AccountType accountType) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM account_type WHERE id = ?;");
			ps.setInt(1, accountType.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
	}

	public List<AccountType> getAll() {
		List<AccountType> accountTypes = new ArrayList<AccountType>();
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM account_type");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AccountType accountType = new AccountType();
				accountType.setId(rs.getInt("id"));
				accountType.setType(rs.getString("type"));

				accountTypes.add(accountType);
			}
			return accountTypes;
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
