package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;

import ma.fstt.entities.LigneCmd;


public interface LigneCmdRepository {

	public LigneCmd getById(int id) throws SQLException;
	public void save(LigneCmd ligneCmd) throws SQLException;
	public void update(LigneCmd ligneCmd) throws SQLException;
	public void delete(int id) throws SQLException;
	public List<LigneCmd> List() throws SQLException;
	public List<LigneCmd> getByIdCmd(int id) throws SQLException;
}
