package ma.fstt.entities;


import java.sql.Date;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Commande {

	private int num_cmd;
	private Date date_cmd;
	private int id_client;
	
	public Commande() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
	public Commande(int num_cmd, Date date_cmd, int id_client) {
		super();
		this.num_cmd = num_cmd;
		this.date_cmd = date_cmd;
		this.id_client = id_client;
	}


	public int getNum_cmd() {
		return num_cmd;
	}

	public void setNum_cmd(int num_cmd) {
		this.num_cmd = num_cmd;
	}

	public Date getDate_cmd() {
		return date_cmd;
	}

	public void setDate_cmd(Date date_cmd) {
		this.date_cmd = date_cmd;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	@Override
	public String toString() {
		return "Commande [num_cmd=" + num_cmd + ", date_cmd=" + date_cmd + ", id_client=" + id_client + "]";
	}
	
	
}
