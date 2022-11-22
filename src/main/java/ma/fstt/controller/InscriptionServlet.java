package ma.fstt.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;



@WebServlet("/InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject private ClientDAO clientDAO;
	
    public InscriptionServlet() throws ClassNotFoundException, SQLException {
        super();
        clientDAO= new ClientDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String error = null;
		
		try {
			String nom = (String) request.getParameter("nom");
			String prenom = (String) request.getParameter("prenom");
			String tele = (String) request.getParameter("tele");
			String email = (String) request.getParameter("email");
			String adresse = (String) request.getParameter("adresse");
			String motDePasse = (String) request.getParameter("motDePasse");
			
			Client client = new Client(0, nom, prenom, tele, email, adresse,motDePasse,2);
			request.setAttribute("client", client);
			clientDAO.save(client);
		
		} catch (SQLException e) {
			e.printStackTrace();
			//error = "e-mail déjà utilisé !";
			request.setAttribute("error", error);
		}
		
		// --- Si tout est correct ---
		if(error == null)
			this.getServletContext().getRequestDispatcher("/authentification.jsp").forward(request, response);

        // --- Si l'email est déjà utilisé---
		else
			this.getServletContext().getRequestDispatcher("/inscrire.jsp").forward(request, response);
			
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
