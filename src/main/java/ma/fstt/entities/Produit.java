package ma.fstt.entities;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Produit {

	private int code_prod;
	private String nom;
	private double pu;
	private int qte_stock;
	
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Produit(int code_prod, String nom, double pu, int qte_stock) {
		super();
		this.code_prod = code_prod;
		this.nom = nom;
		this.pu = pu;
		this.qte_stock = qte_stock;
	}



	public int getCode_prod() {
		return code_prod;
	}

	public void setCode_prod(int code_prod) {
		this.code_prod = code_prod;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPu() {
		return pu;
	}

	public void setPu(double pu) {
		this.pu = pu;
	}

	public int getQte_stock() {
		return qte_stock;
	}

	public void setQte_stock(int qte_stock) {
		this.qte_stock = qte_stock;
	}

	@Override
	public String toString() {
		return "Produit [code_prod=" + code_prod + ", nom=" + nom + ", pu=" + pu + ", qte_stock=" + qte_stock + "]";
	}
	
	
	
}
