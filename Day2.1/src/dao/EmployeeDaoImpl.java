package dao;
import static utils.DBUtils.getCn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {
	// state
	private Connection connection;
	private PreparedStatement pst1, pst2, pst3,pst4;

	public EmployeeDaoImpl() throws SQLException {
		connection = getCn();
		String sql = "select empid, name,salary,join_date from my_emp where  deptid=? and join_date>? order by salary";
		pst1 = connection.prepareStatement(sql);
		pst2 = connection.prepareStatement("insert  into my_emp values (default,?,?,?,?,?)");
		pst3 = connection.prepareStatement("update my_emp set salary = salary + ?, deptid=? where empid=?");
		pst4 = connection.prepareStatement("delete  from my_emp where empid = ?");
		System.out.println("emp dao created ....");
	}

	public List<Employee> getEmpDetailsByDeptAndDate(String dept, Date joinDate) throws SQLException {
		List<Employee> emps = new ArrayList<>();
		// set IN Params
		pst1.setString(1, dept);
		pst1.setDate(2, joinDate);
		try (ResultSet rst = pst1.executeQuery()) {
			// rst cursor --before 1st row
			while (rst.next())
				emps.add(new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getDate(4)));
			System.out.println();
		}
		return emps;
	}

	// add clean up method for closing DB related resources
	public void cleanUP() throws SQLException {
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (pst3 != null)
			pst3.close();
		if (pst4 != null)
			pst4.close();
	
		System.out.println("emp dao clean up...");
	}

	public String insertEmpDetails(Employee e) throws SQLException {
		// set IN params
		// name
		// API of PST : public void setType(int paramIndex. Type value) throws SQLExc.
		pst2.setString(1, e.getName());
		// adr
		pst2.setString(2, e.getAddress());
		// salary
		pst2.setDouble(3, e.getSalary());
		// detpt id
		pst2.setString(4, e.getDeptId());
		// join date
		pst2.setDate(5, e.getJoinDate());
		// exec DML
		int updateCount = pst2.executeUpdate();
		if (updateCount == 1)
			return "Emp details inserted.....";
		return "Emp details insertion failed!!!!!";
	}

	public String updateEmpDetails(double salIncr, String newDept, int empid) throws SQLException {
		// set IN params
		pst3.setDouble(1, salIncr);//salrIncr
		pst3.setString(2, newDept);
		pst3.setInt(3, empid);
		int updateCount = pst3.executeUpdate();
		if (updateCount == 1)
			return "Emp details updated.....";
		return "Emp details updation failed!!!!!!!!!";

		
	}

	
	public String deleteEmpDetails(int empid) throws SQLException {
		// set IN params : emp id
		pst4.setInt(1, empid);
		int UpdateCount = pst4.executeUpdate();
		if (UpdateCount == 1)
			return "Emp details deleted.....";
		return  "Emp details deletion failed!!!!!!!!!";
	}

}
