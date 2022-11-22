package ma.fstt.controller;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
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



@WebServlet("/ShowListServlet")
public class ShowListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject private CommandeDAO commandeDao;
	@Inject private ProduitDAO produitDao;
	@Inject private ClientDAO clientDAO;
	@Inject private LigneCmdDAO ligneCommandeDao;
       

    public ShowListServlet() throws ClassNotFoundException, SQLException {
        super();
        commandeDao = new CommandeDAO();
        produitDao = new ProduitDAO();
        clientDAO = new ClientDAO();
        ligneCommandeDao = new LigneCmdDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int action  = Integer.parseInt(request.getParameter("act"));
			Client client = (Client) session.getAttribute("client");
			
			List<Commande> listCmd = new ArrayList<Commande>();
			List<Produit> listProd = new ArrayList<Produit>();
			List<Client> listUtil = new ArrayList<Client>();
			List<LigneCmd> listLigCmd = new ArrayList<LigneCmd>();
			
			
			// --- 1 : Commandes ---
			if(action == 1)
			{	
				if(client.getType() == 1)
				{
					listCmd = commandeDao.List();
					request.setAttribute("listCmd", listCmd);
					this.getServletContext().getRequestDispatcher("/showCommandes.jsp").forward(request, response);
				}
				else
				{
					listCmd = commandeDao.getByIdUser(client.getId());
					request.setAttribute("listCmd", listCmd);
					this.getServletContext().getRequestDispatcher("/showCommandes.jsp").forward(request, response);
				}
			}
			
			
			// --- 11 : Commande à modifier ---
			else if(action == 11)
			{
				int id  = Integer.parseInt(request.getParameter("num_cmd"));
				Commande cmdToUpdate = commandeDao.getById(id);
				session.setAttribute("cmdToUpdate", cmdToUpdate);
				
				this.getServletContext().getRequestDispatcher("/updateCommande.jsp").forward(request, response);
			}
			
			// --- 2 : Produits ---
			else if(action == 2)
			{
				listProd = produitDao.List();
				request.setAttribute("listProd", listProd);
				this.getServletContext().getRequestDispatcher("/showProduits.jsp").forward(request, response);
			}
					
			// --- 22 : Produit à modifier ---
			else if(action == 22)
			{
				int id  = Integer.parseInt(request.getParameter("code_prod"));
				Produit prodToUpdate = produitDao.getById(id);
				session.setAttribute("prodToUpdate", prodToUpdate);
				
				this.getServletContext().getRequestDispatcher("/updateProduit.jsp").forward(request, response);
			}
			
			// --- 3 : Utilisateurs ---
			else if(action == 3)
			{
				listUtil = clientDAO.List();
				request.setAttribute("listUtil", listUtil);
				this.getServletContext().getRequestDispatcher("/showClients.jsp").forward(request, response);
			}
			
			// --- 4 : Lignes de commande ---
			else if(action == 4)
			{
			int id  = Integer.parseInt(request.getParameter("num_ligne"));
			session.setAttribute("num_cmd", id);
							
			listLigCmd = ligneCommandeDao.getByIdCmd(id);
			request.setAttribute("listLigCmd", listLigCmd);
							
     	    this.getServletContext().getRequestDispatcher("/showLignesCommande.jsp").forward(request, response);
		   }
			
			
		  // --- 44 : Ligne de commande à modifier ---
		   else if(action == 44)
		   {
			int id  = Integer.parseInt(request.getParameter("num_ligne"));
			LigneCmd lcmdToUpdate = ligneCommandeDao.getById(id);
			session.setAttribute("lcmdToUpdate", lcmdToUpdate);
			listProd = produitDao.List();
			request.setAttribute("listProd", listProd);
							
			this.getServletContext().getRequestDispatcher("/updateLigneCommande.jsp").forward(request, response);
		   }
			
		   // --- 444 : Pour afficher les produits dans la Drop Down List (Create/Update ligne de commande) ---
		   else if(action == 444)
		   {
		    listProd = produitDao.List();
			request.setAttribute("listProd", listProd);
			this.getServletContext().getRequestDispatcher("/createLigneCommande.jsp").forward(request, response);
		   }			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
