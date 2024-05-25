package tester;
import static utils.DBUtils.openConnection;

import java.sql.Connection;

public class TestConnection {
	public static void main (String args[]) {
		try(//get fixed db connection from DB Utils
				Connection cn = openConnection();
				
				)
		{
			System.out.println("Connected to DB"+cn);//imple class instance : MYSQL specific cn
			
		}//rst.close, st.close,cn.close...
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
