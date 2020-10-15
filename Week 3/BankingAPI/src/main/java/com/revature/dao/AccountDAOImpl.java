package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.utilities.DAOUtilities;

public class AccountDAOImpl implements AccountDAO {

	private Connection connection;

	GenericDAO<Role> roleDAO = new RoleGenericDAOImpl();

	GenericDAO<AccountType> typeDAO = new AccountTypeGenericDAOImpl();

	GenericDAO<AccountStatus> statusDAO = new AccountStatusGenericDAOImpl();

	public Account create(Account account) {
		
		Account newAccount;
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO account (balance, account_status_id, account_type_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getStatus().getId());
			ps.setInt(3, account.getType().getId());
			ps.executeUpdate();
			
			// Get new account key
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int accountId = rs.getInt(1);
			
						
			PreparedStatement accountUserPs = connection.prepareStatement("INSERT INTO user_account (user_id, account_id) VALUES (?, ?)");
						
			accountUserPs.setInt(1, account.getOwner().getId());
			accountUserPs.setInt(2, accountId);
			accountUserPs.executeUpdate();
			
			return get(accountId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		return null;
	}

	public Account get(int id) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_account "
					+ "LEFT JOIN account on user_account.account_id = account.id "
					+ "LEFT JOIN users on user_account.user_id = users.id "
					+ "WHERE account_id = ?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getDouble("balance"));
				
				
				AccountType type = typeDAO.get(rs.getInt("account_type_id"));
				AccountStatus status = statusDAO.get(rs.getInt("account_status_id"));
				
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = roleDAO.get(rs.getInt("role_id"));
				
				user.setRole(role);
				account.setType(type);
				account.setStatus(status);
				account.setOwner(user);
				
				return account;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		// There were 0 records returned
		return null;	}

	public Account update(Account account) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("UPDATE account "
					+ "SET balance = ?, account_status_id = ?, account_type_id = ? "
					+ " WHERE id = ?");
			
			
			ps.setDouble(1, account.getBalance());
			ps.setInt(2, account.getStatus().getId());
			ps.setInt(3, account.getType().getId());
			ps.setInt(4, account.getId());

			ps.executeUpdate();
									
//			PreparedStatement accountUserPs = connection.prepareStatement("INSERT INTO user_account (user_id, account_id) VALUES (?, ?)");
//			
//			accountUserPs.setInt(1, account.getOwner().getId());
//			accountUserPs.setInt(2, account.getId());
//			accountUserPs.executeUpdate();
			return get(account.getId());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		return null;
	}

	public void delete(Account account) {
		try {
			connection = DAOUtilities.getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE FROM account WHERE id = ?;");
			ps.setInt(1, account.getId());
			
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
	}

	public List<Account> getAll() {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			connection = DAOUtilities.getConnection();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_account "
					+ "LEFT JOIN account on user_account.account_id = account.id "
					+ "LEFT JOIN account_type on account.account_type_id = account_type.id "
					+ "LEFT JOIN account_status on account.account_status_id = account_status.id "
					+ "LEFT JOIN users on user_account.user_id = users.id "
					+ "LEFT JOIN role on users.role_id = role.id;");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getDouble("balance"));
				
				AccountType accountType = new AccountType();
				accountType.setId(rs.getInt("account_type_id"));
				accountType.setType(rs.getString("type"));
				
				AccountStatus accountStatus = new AccountStatus();
				accountStatus.setId(rs.getInt("account_status_id"));
				accountStatus.setStatus(rs.getString("status"));
				
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRole(rs.getString("role"));
				
				user.setRole(role);
				
				account.setType(accountType);
				account.setStatus(accountStatus);
				account.setOwner(user);
				
				accounts.add(account);
			}
			
			return accounts;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		//No results
		return null;	
	}

	@Override
	public List<Account> getAccountsByStatus(int statusId) {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			connection = DAOUtilities.getConnection();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_account "
					+ "LEFT JOIN account on user_account.account_id = account.id "
					+ "LEFT JOIN account_type on account.account_type_id = account_type.id "
					+ "LEFT JOIN account_status on account.account_status_id = account_status.id "
					+ "LEFT JOIN users on user_account.user_id = users.id "
					+ "LEFT JOIN role on users.role_id = role.id "
					+ "WHERE account.account_status_id = ?;");
			
			ps.setInt(1, statusId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getDouble("balance"));
				
				AccountType accountType = new AccountType();
				accountType.setId(rs.getInt("account_type_id"));
				accountType.setType(rs.getString("type"));
				
				AccountStatus accountStatus = new AccountStatus();
				accountStatus.setId(rs.getInt("account_status_id"));
				accountStatus.setStatus(rs.getString("status"));
				
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRole(rs.getString("role"));
				
				user.setRole(role);
				
				account.setType(accountType);
				account.setStatus(accountStatus);
				account.setOwner(user);
				
				accounts.add(account);
			}
			
			return accounts;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		//No results
		return null;
	}

	@Override
	public List<Account> getAccountsByUser(int userId) {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			connection = DAOUtilities.getConnection();

			PreparedStatement ps = connection.prepareStatement("SELECT * FROM user_account "
					+ "LEFT JOIN account on user_account.account_id = account.id "
					+ "LEFT JOIN account_type on account.account_type_id = account_type.id "
					+ "LEFT JOIN account_status on account.account_status_id = account_status.id "
					+ "LEFT JOIN users on user_account.user_id = users.id "
					+ "LEFT JOIN role on users.role_id = role.id "
					+ "WHERE user_account.user_id = ?;");
			
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("account_id"));
				account.setBalance(rs.getDouble("balance"));
				
				AccountType accountType = new AccountType();
				accountType.setId(rs.getInt("account_type_id"));
				accountType.setType(rs.getString("type"));
				
				AccountStatus accountStatus = new AccountStatus();
				accountStatus.setId(rs.getInt("account_status_id"));
				accountStatus.setStatus(rs.getString("status"));
				
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setRole(rs.getString("role"));
				
				user.setRole(role);
				
				account.setType(accountType);
				account.setStatus(accountStatus);
				account.setOwner(user);
				
				accounts.add(account);
			}
			
			return accounts;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DAOUtilities.closeConnection();
		}
		
		// No Results
		return null;
	}

}
