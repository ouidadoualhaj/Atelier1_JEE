package ma.fstt.controller;

import java.io.IOException;
import java.sql.Date;
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
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneCmd;
import ma.fstt.entities.Produit;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject private CommandeDAO commandeDao;
	@Inject private ProduitDAO produitDao;
	@Inject private ClientDAO clientDAO;
	@Inject private LigneCmdDAO ligneCommandeDao;
	
    public UpdateServlet() throws ClassNotFoundException, SQLException {
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
			Client client = (Client) session.getAttribute("client");
			
			// --- Modification d'une commande ---
			if(action == "cmd")
			{
				int id  = Integer.parseInt(request.getParameter("num_cmd"));
				String dateCmd = request.getParameter("date_cmd");
				
				int idUser = commandeDao.getById(id).getId_client();
				Commande commande = new Commande(id, Date.valueOf(dateCmd), idUser);
				
				commandeDao.update(commande);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=1").forward(request, response);
			}
			
			// --- Modification d'un produit ---
			else if(action == "prod")
			{
				int id  = Integer.parseInt(request.getParameter("code_prod"));
				String nomProd = request.getParameter("nom");
				double prixProd = Double.parseDouble(request.getParameter("pu"));
				int QteStock  = Integer.parseInt(request.getParameter("qte_stock"));

				
				Produit produit = new Produit(id, nomProd, prixProd ,QteStock);
				
				produitDao.update(produit);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=2").forward(request, response);
			}
			
			// --- Modification d'une ligne de commande ---
			else if(action == "lcmd")
			{
				int id  = Integer.parseInt(request.getParameter("num_ligne"));
				LigneCmd lcmd = ligneCommandeDao.getById(id);
				int idCmd = lcmd.getNum_cmd();
				int idProd = Integer.parseInt(request.getParameter("code_prod"));
				int qte = Integer.parseInt(request.getParameter("qte_cmd"));
				LigneCmd ligneDeCommande = new LigneCmd(id, qte, idCmd, idProd);
				
				ligneCommandeDao.update(ligneDeCommande);
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
