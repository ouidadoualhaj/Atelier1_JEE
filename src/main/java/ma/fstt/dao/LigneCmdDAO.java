package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.connexion.ConnectionManager;
import ma.fstt.entities.LigneCmd;
import ma.fstt.service.LigneCmdRepository;

public class LigneCmdDAO implements LigneCmdRepository{

	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public LigneCmdDAO() throws SQLException, ClassNotFoundException {
		connection = ConnectionManager.getConnection();
	}

	@Override
	public void save(LigneCmd ligneCmd) throws SQLException {
		String qry = "insert into ligne_cmd (qte_cmd, code_prod, num_cmd) values (?, ?, ?)";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, ligneCmd.getQte_cmd());
		this.preparedStatement.setInt(2, ligneCmd.getCode_prod());
		this.preparedStatement.setInt(3, ligneCmd.getNum_cmd());
		this.preparedStatement.execute();		
	}

	@Override
	public void update(LigneCmd ligneCmd) throws SQLException {
		String qry = "update ligne_cmd set qte_cmd = ?, code_prod = ? where num_ligne = ?";
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, ligneCmd.getQte_cmd());
		this.preparedStatement.setInt(2, ligneCmd.getCode_prod());
		this.preparedStatement.setInt(3, ligneCmd.getNum_cmd());
		this.preparedStatement.execute();
		
	}

	@Override
	public void delete(int id) throws SQLException {
		String qry="delete from ligne_cmd where num_ligne=?";
		this.preparedStatement=this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.preparedStatement.execute();
		
	}

	@Override
	public java.util.List<LigneCmd> List() throws SQLException {
		String qry = "select * from ligne_cmd";
		List<LigneCmd> list = new ArrayList<LigneCmd>();
		this.statement = this.connection.createStatement();
		this.resultSet = this.statement.executeQuery(qry);
		while(this.resultSet.next()) {
			list.add(new LigneCmd(this.resultSet.getInt(1), this.resultSet.getInt(2), this.resultSet.getInt(3), this.resultSet.getInt(4)));
		}
		return list;
	}

	@Override
	public LigneCmd getById(int id) throws SQLException {
		String qry = "select * from ligne_cmd where num_ligne = ?";
		LigneCmd ligneDeCommande = null;
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.resultSet = this.preparedStatement.executeQuery();
		while(this.resultSet.next()) {
			ligneDeCommande = new LigneCmd(this.resultSet.getInt(1), this.resultSet.getInt(2), this.resultSet.getInt(3), this.resultSet.getInt(4));
			break;
		}
		return ligneDeCommande;
	}

	@Override
	public java.util.List<LigneCmd> getByIdCmd(int id) throws SQLException {
		String qry = "select * from ligne_cmd where num_cmd = ?";
		List<LigneCmd> list = new ArrayList<LigneCmd>();
		this.preparedStatement = this.connection.prepareStatement(qry);
		this.preparedStatement.setInt(1, id);
		this.resultSet = this.preparedStatement.executeQuery();
		while(this.resultSet.next()) {
			list.add(new LigneCmd(this.resultSet.getInt(1), this.resultSet.getInt(2), this.resultSet.getInt(3), this.resultSet.getInt(4)));
		}
		return list;
	}
}
