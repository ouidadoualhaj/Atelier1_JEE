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
import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LigneCmdDAO;
import ma.fstt.dao.ProduitDAO;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject private CommandeDAO commandeDao;
	@Inject private ProduitDAO produitDao;
	@Inject private ClientDAO clientDAO;
	@Inject private LigneCmdDAO ligneCommandeDao;
	
    public DeleteServlet() throws ClassNotFoundException, SQLException {
        super();
        commandeDao = new CommandeDAO();
        produitDao = new ProduitDAO();
        clientDAO = new ClientDAO();
        ligneCommandeDao = new LigneCmdDAO();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			String action = (String) session.getAttribute("page");
			
			
			// --- Suppression d'une commande ---
			if(action == "cmd")
			{
				int id  = Integer.parseInt(request.getParameter("num_cmd"));
				commandeDao.delete(id);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=1").forward(request, response);
			}
			
			// --- Suppression d'un produit ---
			else if(action == "prod")
			{
				int id  = Integer.parseInt(request.getParameter("code_prod"));
				produitDao.delete(id);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=2").forward(request, response);
			}
			
			// --- Suppression d'un utilisateur ---
			else if(action == "util")
			{
				int id  = Integer.parseInt(request.getParameter("id_client"));
				clientDAO.delete(id);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=3").forward(request, response);
			}
			
			// --- Suppression d'une ligne de commande ---
			else if(action == "lcmd")
			{
				int id  = Integer.parseInt(request.getParameter("num_ligne"));
				int idCmd = (Integer) session.getAttribute("num_cmd");
				ligneCommandeDao.delete(id);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=4&id="+idCmd+"").forward(request, response);
			}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
