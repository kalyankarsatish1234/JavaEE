package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PlayerDaoImpl;
import dao.TeamDaoImpl;
import pojos.Player;
import pojos.Team;

/**
 * Servlet implementation class AddPlayer
 */
@WebServlet("/add_player")
public class AddPlayer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// 2. get PW to send buffered text resp to the clnt
		try (PrintWriter pw = response.getWriter()) {
			//pw.print("<h5> New Page</h5>");
			
			HttpSession hs = request.getSession();
			PlayerDaoImpl pDao = (PlayerDaoImpl) hs.getAttribute("player_dao");
			TeamDaoImpl tDao = (TeamDaoImpl)hs.getAttribute("team_dao");
			
			if(tDao!=null) {
				String abbr = request.getParameter("abbreaviation");
				//get selected team details
				Team team = tDao.getTeamDetails(abbr);
				String name = request.getParameter("nm");
				String lastName = request.getParameter("lnm");
				String date = request.getParameter("dob");
				LocalDate dob = LocalDate.parse(date);
				String avg = request.getParameter("avg");
				Double battAvg = Double.parseDouble(avg);
				int wktsTaken = Integer.parseInt(request.getParameter("wickets"));
				
				//validations
				int age = Period.between(dob, LocalDate.now()).getYears();
				if(age < team.getMaxAge() && battAvg > team.getMinBattingAvg() && wktsTaken > team.getMinWicketsTaken()) 
				{
					Player player = new Player(name, lastName, Date.valueOf(dob), battAvg, wktsTaken, age);
					pw.print("<h3> Status "+ pDao.addPlayerToTeam(player, team.getTeamId()) + "</h3>");
				}else
					pw.print("<h3 style color='red'> Can't add player details , Invalid I/ps</h3>");
					
					
			}else
				pw.print("<h4> Session Tracking failed !!!!!!! No cookies.....</h4>");
			
			
		}catch (Exception e) {
			throw new ServletException("error in do-post"+getClass(), e);
		}
	}
		
	

}
