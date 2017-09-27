package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import entity.User;

public class UserDao extends BaseDao {

	public User searchByUserNameAndPassword(User user) {
		User u = null;
		try {
			getStatement();
			String sql = "select * from users where username='"
					+ user.getUsername() + "'andpassword='"
					+ user.getPassword() + "'";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				u = new User();
				u.setId(rs.getInt("id"));
				u.setPassword(rs.getString("username"));

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}

	public User add(User user) {// зЂВс
		User u = null;
		try {
			String sql = "insert into users(username,password)value(?,?)";
			Connection conn = getConnection();
			PreparedStatement pstat = conn.prepareStatement(sql);

			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			int result = pstat.executeUpdate();
			if (result > 0) {
				sql = "select last_insert_id()";
				ResultSet rs = pstat.executeQuery(sql);

				while (rs.next()) {
					u = new User();
					u.setId(rs.getInt(1));
					u.setPassword(user.getUsername());

				}

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u;
	}
}
