package dao;

import static utils.DBUtils.getCn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class BankAccountDaoImpl implements IBankAccountDao {
	// state
	private Connection cn;
	private CallableStatement cst1;

	// def const
	public BankAccountDaoImpl() throws SQLException {
		// get Fixed connection from DB utils
		cn = getCn();
		cst1 = cn.prepareCall("{call transfer_funds(?,?,?,?,?)}");
		// register OUPT param
		// API of CST: public void registerOutpParameter(int paramIndex,int sqlType)
		cst1.registerOutParameter(4, Types.DOUBLE);// Inform jvm about JDBC data type of the OUT param
		cst1.registerOutParameter(5, Types.DOUBLE);// Inform jvm about JDBC data type of the OUT param

		System.out.println("acct dao created.....");
	}

	

	public String transferFunds(int srcId, int destId, double amount) throws SQLException {
		// set IN params : using inherited AP;I form pst
		cst1.setInt(1, srcId);
		cst1.setInt(2, destId);
		cst1.setDouble(3, amount);
		//exec the proc
		
		cst1.execute();//ret type is unused here
		
		
		return " Update src Balance " + cst1.getDouble(4) + "Dest balance" + cst1.getDouble(5);
	}

	// Clean up
	public void cleanUp() throws SQLException {
		if (cst1 != null)
			cst1.close();
		System.out.println("bank account dao created......");

	}

}
