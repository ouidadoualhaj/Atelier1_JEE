package ma.fstt.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;



@WebServlet("/AuthentificationServlet")
public class AuthentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject private ClientDAO clientDAO;
	
    public AuthentificationServlet() throws ClassNotFoundException, SQLException {
        super();
        clientDAO= new ClientDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = null;
		String errorVide = null;
		HttpSession session = request.getSession();
		
		
		        // --- LogOut ---
				if(request.getParameter("act") != null)
				{
					int action  = Integer.parseInt(request.getParameter("act"));
					if(action == 0)
					{
						session.removeAttribute("client");
						this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);
						return;
					}
				}
				
				
				// --- LogIn ---
				try {
					Client client = new Client();
					String email = request.getParameter("email");
					String password = request.getParameter("motDePasse");
			
					client = clientDAO.getByEmail(email);
					request.setAttribute("client", client);
					
					session.setAttribute("client", client);
					
					if(client != null)
					{
						// --- Si tout est correct ---
						if(password.equals(client.getMotDePasse()))
							this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
						// --- Si le mot de passe est incorrect ---
						else
						{
							error = "Le mot de passe est incorrect !";
							request.setAttribute("error", error);
							this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);
						}
					}
					// --- Si l'email est incorrect (n'existe pas dans la BDD) ---
					else
					{
						errorVide = "E-mail incorrect";
						request.setAttribute("errorVide", errorVide);
						this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
