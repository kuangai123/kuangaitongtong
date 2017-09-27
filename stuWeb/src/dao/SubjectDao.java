package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;

public class SubjectDao extends BaseDao {

	public int searchCount() {
		int count = 0;

		// 添加gdbc 七个步骤
		try {
			getStatement();
			String sql = "select count(id)  from subject ";

			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);

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

		return count;

	}

	public int searchCount(Subject condition) {
		int count = 0;

		// 添加gdbc 七个步骤
		try {
			getStatement();
			Statement stat = conn.createStatement();
			String where = "where 1=1";
			if (condition.getName() != null) {
				where += " and name like'%" + condition.getName() + "%'";

			}

			String sql = "select count(id)  from subject " + where;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);

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

		return count;

	}

	public boolean add(Subject bj) {
		boolean flag = false;
		try {

			String sql = "insert into subject (name) " + " value(?)";
			getPreparedStatement(sql);
			pstat.setString(1, bj.getName());

			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
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
		return flag;
	}

	public Subject searchById(int id) {
		Subject bj = null;
		try {
			getStatement();

			String sql = "select * from subject where id=" + id;

			rs = stat.executeQuery(sql);
			if (rs.next()) {
				bj = new Subject();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));

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

		return bj;
	}

	public boolean modify(Subject bj) {

		boolean flag = false;
		try {

			String sql = "update subject set name=? where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, bj.getName());

			pstat.setInt(2, bj.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
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
		return flag;

	}

	public boolean delete(int id) {

		boolean flag = false;
		try {

			String sql = "delete from subject where id=?";
			getConnection();
			conn.setAutoCommit(false);
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();
			// if (rs > 0) {
			// flag = true;
			// } else {
			// return false;
			// }
			// sql = "update student set bj_id = null where bj_id=?";
			// pstat = conn.prepareStatement(sql);
			// pstat.setInt(1, id);
			// rs = pstat.executeUpdate();
			conn.commit();
			if (rs > 0) {
				flag = true;
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
		return flag;
	}

	public List<Subject> searchByCondition(Subject condition, int begin,
			int yeNum) {
		List<Subject> list = new ArrayList<Subject>();

		// 添加gdbc 七个步骤
		try {
			getStatement();
			String where = "where 1=1";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}

			String sql = "select * from subject " + where + " limit " + begin
					+ "," + yeNum;

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject bj = new Subject();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));

				list.add(bj);

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

		return list;
	}

	public List<Subject> searchByBegin(int begin, int num) {
		List<Subject> list = new ArrayList<Subject>();

		// 添加gdbc 七个步骤
		try {
			getStatement();
			String sql = "select * from subject limit " + begin + "," + num;

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject bj = new Subject();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));

				list.add(bj);

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

		return list;

	}

	public List<Subject> searchAll() {
		List<Subject> list = new ArrayList<Subject>();
		Connection conn = null;
		// 添加gdbc 七个步骤
		try {
			getStatement();

			String sql = "select * from subject";

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject bj = new Subject();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));

				list.add(bj);

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

		return list;

	}

	public List<Subject> searchNoSubByBjId(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		Connection conn = null;
		// 添加gdbc 七个步骤
		try {
			getStatement();

			String sql = "select * from subject where id not in(select subId from v_bj_sub where bjId="
					+ bjId + " and subId is not null)";

			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject bj = new Subject();
				bj.setId(rs.getInt("id"));
				bj.setName(rs.getString("name"));

				list.add(bj);

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

		return list;

	}

}
