package pages;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDaoImpl;
import dao.TeamDaoImpl;

/**
 * Servlet implementation class AddPlayerForm
 */
@WebServlet("/add_player_form")
public class AddPlayerForm extends HttpServlet {
	private PlayerDaoImpl playerDao;
	private TeamDaoImpl teamDao;
       
    
	@Override
	public void init() throws ServletException {
		System.out.println("in init of "+ getClass());
		try {
			openConnection();
			//create player n team dao instance
			playerDao = new PlayerDaoImpl();
			teamDao = new TeamDaoImpl();
			
		} catch (SQLException e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	
	}
	
	public void destroy() {
		System.out.println("in destroy of " + getClass());
		try {
			playerDao.cleanUp();
			teamDao.cleanUp();
			closeConnection();
		} catch (Exception e) {
			// inform WC about the err : OPTIONAL !
			throw new RuntimeException("err in destroy of " + getClass(), e);
		}
	}
	
	
	//String firstName, String lastName, Date dob, double battingAvg, int wicketsTaken, int teamId)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// 1. set resp cont type
			response.setContentType("text/html");
			// 2. get PW to send buffered text resp to the clnt
			try (PrintWriter pw = response.getWriter()) {
				
				
				
				HttpSession hs = request.getSession();
				hs.setAttribute("player_dao", playerDao);
				hs.setAttribute("team_dao", teamDao);
				//invoke dao's method 
				List<String> teamsabbreviations = teamDao.getTeamsAbbreviations();
				pw.print("<form method='post' action='add_player'>");
				pw.print("<h5>Choose a Team : <select name='abbreaviation'>");
				for(String s:teamsabbreviations)
					pw.print("<h5><option value='"+ s +"'</option>" + s + "</h5>");
				pw.print("</h5> </select>");
				pw.print("<h5>Player Name <input type = 'text' name='nm'/></h5>");
				pw.print("<h5>Last Name <input type = 'text' name='lnm'/></h5>");
				pw.print("<h5>DoB<input type = 'date' name='dob'/></h5>");
				pw.print("<h5>Batting Avg <input type = 'number' name='avg'/></h5>");
				pw.print("<h5>Wickets Taken <input type = 'text' name='wickets'/></h5>");
				pw.print("<h5><input type='submit' value='Add New Player'/></h5>");
				pw.print("</from>");
				
				
			}catch (Exception e) {
				throw new ServletException("error in do-get"+getClass(), e);
			}
			
		}

}
