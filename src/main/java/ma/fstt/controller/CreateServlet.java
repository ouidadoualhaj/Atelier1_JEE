package ma.fstt.controller;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LigneCmdDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Client;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneCmd;
import ma.fstt.entities.Produit;


@WebServlet("/CreateServlet")
@MultipartConfig
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject private CommandeDAO commandeDao;
	@Inject private ProduitDAO produitDao;
	@Inject private LigneCmdDAO ligneCommandeDao;
       

    public CreateServlet() throws ClassNotFoundException, SQLException {
        super();
        commandeDao = new CommandeDAO();
        produitDao = new ProduitDAO();
        ligneCommandeDao = new LigneCmdDAO();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			String action = (String) session.getAttribute("page");
			Client client = (Client) session.getAttribute("client");
			
			
			// --- Création d'une commande ---
			if(request.getParameter("act") != null && Integer.parseInt(request.getParameter("act")) == 1)
			{
				java.util.Date date = new java.util.Date();
				Commande commande = new Commande(0, new Date(date.getTime()), client.getId());
				
				commandeDao.save(commande);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=1").forward(request, response);
			}
			
			
			
			// --- Création d'un produit ---
			else if(action == "prod")
			{

				String nom = request.getParameter("nom");
				double pu = Double.parseDouble(request.getParameter("pu"));
				int qte_stock = Integer.parseInt(request.getParameter("qte_stock"));
				
				Produit produit = new Produit(0,nom, pu,qte_stock);
				
				produitDao.save(produit);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=2").forward(request, response);
			}
			
			
			// --- Création d'une ligne de commande ---
			else if(action == "lcmd")
			{
				int qte_cmd = Integer.parseInt(request.getParameter("qte_cmd"));
				int num_cmd = (Integer) session.getAttribute("num_cmd");
				int code_prod = Integer.parseInt(request.getParameter("code_prod"));
				
				LigneCmd ligneCommande = new LigneCmd(0, qte_cmd,code_prod, num_cmd);
				
				ligneCommandeDao.save(ligneCommande);
				this.getServletContext().getRequestDispatcher("/ShowListServlet?act=4&id="+num_cmd+"").forward(request, response);
			}
			
		}  catch(SQLException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	// --- Méthode servant de découpage de l'en-tête Http et extraction du nom du fichier ---
	private static String getNameFile(Part part) 
	{
        for (String contentDisposition : part.getHeader( "content-disposition" ).split( ";" )) 
        {
            if (contentDisposition.trim().startsWith( "filename" )) 
            {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }

}
