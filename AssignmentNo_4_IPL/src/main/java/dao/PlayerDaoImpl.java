package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pojos.Player;

import static utils.DBUtils.*;

public class PlayerDaoImpl implements PlayerDao {
	private Connection connection;
	private PreparedStatement pst1;
	
	public PlayerDaoImpl() throws SQLException {
		connection = getCn();
		pst1 = connection.prepareStatement("insert into players values(default,?,?,?,?,?,?)");
		//(String firstName, String lastName, Date dob, double battingAvg, int wicketsTaken, int teamId)
	}
	
	@Override
	public String addPlayerToTeam(Player newPlayer, int teamId) throws SQLException {
		pst1.setString(1, newPlayer.getFirstName());
		pst1.setString(2, newPlayer.getLastName());
		pst1.setDate(3, newPlayer.getDob());
		pst1.setDouble(4, newPlayer.getBattingAvg());
		pst1.setInt(5, newPlayer.getWicketsTaken());
		pst1.setInt(6, teamId);
		
		int rowCnt = pst1.executeUpdate();
		if(rowCnt==1)
			return "Player is added to the team";
		
		return "Player is not added t0 the team";
	}
	
	public void cleanUp() throws SQLException {
		if (pst1 != null)
			pst1.close();
		
		System.out.println("player dao cleaned up....");
	}
}
