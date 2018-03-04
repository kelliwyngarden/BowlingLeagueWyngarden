package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

/**
 * Servlet implementation class editPlayersServlet
 */
@WebServlet("/editPlayersServlet")
public class editPlayersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editPlayersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlayerHelper ph = new PlayerHelper();
		String act = request.getParameter("doThisToPlayer");
		
		if(act == null) {
			getServletContext().getRequestDispatcher("/viewAllPlayers.jsp").forward(request, response);
		} else if (act.equals("Delete Selected Player")) {
			Integer id = Integer.parseInt(request.getParameter("id"));
			Player playerToDelete = ph.searchForPlayerById(id);
			ph.deletePlayer(playerToDelete);
			
			getServletContext().getRequestDispatcher("/viewAllPlayersServlet").forward(request, response);
		} else if (act.equals("Add New Player")) {
			getServletContext().getRequestDispatcher("/addPlayer.html").forward(request, response);
		}
	}

}
