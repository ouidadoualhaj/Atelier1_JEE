package ma.fstt.entities;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Client {

	private int id_client;
	private String nom;
	private String prenom;
	private String tele;
	private String email;
	private String adresse;
	private String motDePasse;
	private int type;
	
	public Client() {
		super();
	}



	public Client(int id_client, String nom, String prenom, String tele, String email, String adresse,
			String motDePasse, int type) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.prenom = prenom;
		this.tele = tele;
		this.email = email;
		this.adresse = adresse;
		this.motDePasse = motDePasse;
		this.type = type;
	}

  
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	public int getId() {
		return id_client;
	}

	public void setId(int id) {
		this.id_client = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", prenom=" + prenom + ", tele=" + tele + ", email="
				+ email + ", adresse=" + adresse + ", motDePasse=" + motDePasse + ", type=" + type + "]";
	}
	
	
	
}
