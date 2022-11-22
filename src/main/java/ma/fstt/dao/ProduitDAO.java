package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.connexion.ConnectionManager;
import ma.fstt.entities.Produit;
import ma.fstt.service.ProduitRepository;

public class ProduitDAO implements ProduitRepository{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public ProduitDAO() throws SQLException, ClassNotFoundException {
		connection = ConnectionManager.getConnection();
	}
	
	
	@Override
	public void save(Produit produit) throws SQLException {
		String qry = "insert into produit (nom, pu, qte_stock) values (?, ?, ?)";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setString(1, produit.getNom());
		this.preparedStatement.setDouble(2, produit.getPu());
		this.preparedStatement.setInt(3,produit.getQte_stock());
		this.preparedStatement.execute();
	}

	@Override
	public void update(Produit produit) throws SQLException {
		String qry = "update produit set nom = ?, pu = ?, qte_stock = ? where code_prod = ?";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setString(1, produit.getNom());
		this.preparedStatement.setDouble(2, produit.getPu());
		this.preparedStatement.setInt(3, produit.getQte_stock());
		this.preparedStatement.setInt(4, produit.getCode_prod());
		this.preparedStatement.execute();
	}

	@Override
	public void delete(int id) throws SQLException {
		String qry = "delete from produit where code_prod = ?";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.preparedStatement.execute();	
	}

	
	@Override
	public java.util.List<Produit> List() throws SQLException {
		String qry = "select * from produit";
		List<Produit> list = new ArrayList<Produit>();
		this.statement = this.connection.createStatement();
		this.resultSet = this.statement.executeQuery(qry);
		while(this.resultSet.next()) {
			list.add(new Produit(this.resultSet.getInt(1), this.resultSet.getString(2), this.resultSet.getDouble(3), this.resultSet.getInt(4)));
		}
		return list;
	}

	@Override
	public Produit getById(int id) throws SQLException {
		String qry = "select * from produit where code_prod = ?";
		Produit produit = null;
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.resultSet = this.preparedStatement.executeQuery();
		while(this.resultSet.next()) {
			produit = new Produit(this.resultSet.getInt(1), this.resultSet.getString(2), this.resultSet.getDouble(3), this.resultSet.getInt(4));
			break;
		}
		return produit;
	}
}
