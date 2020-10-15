package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.AccountStatus;
import com.revature.utilities.DAOUtilities;

public class AccountStatusGenericDAOImpl implements GenericDAO<AccountStatus>{

	private Connection connection;

	public AccountStatus create(AccountStatus accountStatus) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO account_status (status) VALUES (?)");
			
			ps.setString(1, accountStatus.getStatus());
		
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		return null;
	}

	public AccountStatus get(int id) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM account_status WHERE id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				AccountStatus accountStatus = new AccountStatus();
				accountStatus.setId(rs.getInt("id"));
				accountStatus.setStatus(rs.getString("status"));
				
				return accountStatus;
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

	public AccountStatus update(AccountStatus accountStatus) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE account_status "
					+ "SET status = ? WHERE id = ?");
			
			ps.setString(1, accountStatus.getStatus());
			ps.setInt(2, accountStatus.getId());
		
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		return accountStatus;
	}

	public void delete(AccountStatus accountStatus) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM account_status WHERE id = ?;");
			ps.setInt(1, accountStatus.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
	}

	public List<AccountStatus> getAll() {
		List<AccountStatus> accountStatuses = new ArrayList<AccountStatus>();
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM account_status");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				AccountStatus accountStatus = new AccountStatus();
				accountStatus.setId(rs.getInt("id"));
				accountStatus.setStatus(rs.getString("status"));

				accountStatuses.add(accountStatus);
			}
			return accountStatuses;
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
