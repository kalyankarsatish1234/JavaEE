package tester;
import static utils.DBUtils.openConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class TestPreparedStatement {

	public static void main(String[] args) {
		String sql ="select empid, name,salary,join_date from my_emp where  deptid=? and join_date>? order by salary";
		try(Scanner sc = new Scanner(System.in);
				//get cn from DB utils
				Connection cn = openConnection();
				//create PST to hold parameterized query, which represents pre-parsed n pre-compiled sql in the object form 
				PreparedStatement pst = cn.prepareStatement(sql);
				
				)
		{
			System.out.println("Enter dept Id n join date(yyyy-mm-dd)");
			//set IN params: dept id
			pst.setString(1, sc.next());
			//set JOIN Date
			pst.setDate(2,Date.valueOf(sc.next()));
			//exec method: public Resultset executeQuery() throws SQLExc
			try(ResultSet rst = pst.executeQuery())
			{
					while(rst.next())
						System.out.printf("Emp Id %d Name %s  Salary %.1f Joined on %s %n",rst.getInt(1),rst.getString(2),
								rst.getDouble(3),rst.getDate(4));
						
					}//rst.close.
		}//pst.close, cn.close, sc.close.
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
