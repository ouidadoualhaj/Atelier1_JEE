package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;
import ma.fstt.entities.Client;

public interface ClientRepository {

	public Client getById(int id) throws SQLException;
	public void save(Client client) throws SQLException;
	public void update(Client client)throws SQLException;
	public void delete(int id) throws SQLException;
	public List<Client> List() throws SQLException;
	public Client getByEmail(String email) throws SQLException;

}
