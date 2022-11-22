package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.connexion.ConnectionManager;
import ma.fstt.entities.Client;
import ma.fstt.service.ClientRepository;

public class ClientDAO implements ClientRepository{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public ClientDAO() throws SQLException, ClassNotFoundException {
		connection = ConnectionManager.getConnection();
	}
	
	
	@Override
	public Client getById(int id) throws SQLException {
		String qry = "select * from client where id_client = ?";
		Client client = null;
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.resultSet = this.preparedStatement.executeQuery();
		while (this.resultSet.next()) {
			client = new Client(this.resultSet.getInt(1), this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4), 
					this.resultSet.getString(5), this.resultSet.getString(6), this.resultSet.getString(7), this.resultSet.getInt(8));
			break;
		}
		return client;
	}

	@Override
	public void save(Client client) throws SQLException {
		String qry = "insert into client(nom, prenom, tele, email, adresse, motDePasse) values (?, ? , ?, ?, ?, ?)";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setString(1, client.getNom());
		this.preparedStatement.setString(2, client.getPrenom());
		this.preparedStatement.setString(3, client.getTele());
		this.preparedStatement.setString(4, client.getEmail());
		this.preparedStatement.setString(5, client.getAdresse());
		this.preparedStatement.setString(6, client.getMotDePasse());
		this.preparedStatement.setInt   (7,client.getType());
		this.preparedStatement.execute();
	}

	
	@Override
	public void update(Client client) throws SQLException {
		String qry = "update client set nom = ?, prenom = ?, tele = ? , email = ?, adresse = ?, motDePasse = ?, type = ?  where id_client=?";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setString(1, client.getNom());
		this.preparedStatement.setString(2, client.getPrenom());
		this.preparedStatement.setString(3, client.getTele());
		this.preparedStatement.setString(4, client.getEmail());
		this.preparedStatement.setString(5, client.getAdresse());
		this.preparedStatement.setString(6, client.getMotDePasse());
		this.preparedStatement.setInt(7, client.getType());
		this.preparedStatement.setInt(8, client.getId());
		this.preparedStatement.execute();
		
	}

	@Override
	public void delete(int id) throws SQLException {
		String qry = "delete from client where id_client = ?";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.preparedStatement.execute();
		
	}

	@Override
	public java.util.List<Client> List() throws SQLException {
		String qry = "select * from client";
		List<Client> list = new ArrayList<Client>();
		this.statement = this.connection.createStatement();
		this.resultSet = this.statement.executeQuery(qry);
		while (this.resultSet.next()) {
			list.add(new Client(this.resultSet.getInt(1), this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4), 
					this.resultSet.getString(5), this.resultSet.getString(6), this.resultSet.getString(7), this.resultSet.getInt(8)));
		}
		return list;
	}


	@Override
	public Client getByEmail(String email) throws SQLException {
		String qry = "select * from client where email = ?";
		Client client = null;
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setString(1, email);
		this.resultSet = this.preparedStatement.executeQuery();
		while(this.resultSet.next()) {
			client = new Client(this.resultSet.getInt(1), this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4), 
					this.resultSet.getString(5), this.resultSet.getString(6), this.resultSet.getString(7), this.resultSet.getInt(8));
			break;
		}
		return client;
	}

	

}
