package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Employe;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;

public class EmployeService implements IDao<Employe> {

	@Override
	public boolean create(Employe o) {
		String req = "insert into employe values (null,?,?,? )";
				
		try {
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setString(1, o.getNom());
			pr.setString(2, o.getPrenom());
			pr.setDouble(3, o.getSalaire());
                        if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Employe o) {
		String req = "delete from employe where id  = ? ";
		try {
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, o.getId());
                        if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
        public boolean update(Employe o, Employe s) {
		String req = "UPDATE Employe SET nom =?,prenom =?,salaire =? where id =?";
		try {
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setString(1, s.getNom());
			pr.setString(2, s.getPrenom());
			pr.setDouble(3, s.getSalaire());
                        pr.setInt(4, o.getId());
                        if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
        public boolean update(Employe o) {
			String req = "UPDATE Employe SET nom =?,prenom =?,salaire =? where id =?";
		try {
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setString(1, o.getNom());
			pr.setString(2, o.getPrenom());
			pr.setDouble(3, o.getSalaire());
                        pr.setInt(3, o.getId());
                        if (pr.executeUpdate() == 1)
				return true;
                        
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}


	@Override
	public List<Employe> findAll() {
		List<Employe> employes = new ArrayList<Employe>();
		String sql = "select * from employe";
		try {
			Statement statement = Connexion.getInstance().getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				employes.add(new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getDouble("salaire")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employes;
	}

	@Override
	public Employe findById(int id) {
		Employe employe = null;
		String sql = "select * from employe where id = " + id;
		try {
			Statement statement = Connexion.getInstance().getConnection().createStatement();
			ResultSet rs = statement.executeQuery(sql);
			if(rs.next()) {
				employe = new Employe(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getDouble("salaire"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return employe;
	}
        


}
