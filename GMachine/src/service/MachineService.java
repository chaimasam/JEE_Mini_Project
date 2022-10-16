package service;

import beans.Employe;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Machine;
import connexion.Connexion;
import dao.IDao;
import java.sql.Statement;
import java.util.ArrayList;

public class MachineService implements IDao<Machine> {

	EmployeService es = new EmployeService();

	@Override
        public boolean create(Machine o) {
		try {
			String req = "insert into machine values(null, ?, ?, ?, ?, ?)";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setString(1, o.getReference());
			pr.setString(2, o.getMarque());
			pr.setDate(3, new Date(o.getDateAchat().getTime()));
			pr.setDouble(4, o.getPrix());
			pr.setInt(5, o.getEmploye().getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
        
        

	@Override
	public boolean delete(Machine o) {
		try {
			String req = "delete from machine where id  = ?";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, o.getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Machine o) {
		// TODO Auto-generated method stub
		return false;
	}
        public boolean update(Machine o, Machine s) {
		String req = "UPDATE Machine SET reference =?,marque=?,dateAchat =?,prix= ?,employe= ? where id =?";
		try {
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setString(1, s.getReference());
			pr.setString(2, s.getMarque());
			pr.setDate(3, new Date(s.getDateAchat().getTime()));
			pr.setDouble(4, s.getPrix());
			pr.setInt(5, s.getEmploye().getId());
                        pr.setInt(6, o.getId());
                        if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Machine> findAll() {
            List<Machine> m = new ArrayList<Machine>();
		try {
			String req = "select * from machine";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next())
				 m.add(new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"), rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe"))));

		} catch (SQLException e) {
                    e.printStackTrace();
                    return null;
		}
		return m;
	}

	@Override
	public Machine findById(int id) {
		try {
			String req = "select * from machine where id = ?";
			PreparedStatement pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next())
				return new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"),
						rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe")));

		} catch (SQLException e) {
                    e.printStackTrace();
		}

		return null;
	}
        
        public List<Machine> machinesEmp(Employe o) {
		List<Machine> machines = new ArrayList<Machine>();
		String req = "Select * from Machine where employe = ?";
		PreparedStatement pr;
		try {
			pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setInt(1, o.getId());
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				machines.add( new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"), rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe")))) ;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return machines;
	}
	public List<Machine> machinesDate(java.util.Date d1,java.util.Date d2) {
		List<Machine> machines = new ArrayList<Machine>();
		String req = "Select * from Machine where dateAchat between ? and ? ";
		try {
			PreparedStatement  pr = Connexion.getInstance().getConnection().prepareStatement(req);
			pr.setDate(1,  new Date(d1.getTime()));
			pr.setDate(2,  new Date(d2.getTime()));
			ResultSet rs = pr.executeQuery();
			while(rs.next()) {
				machines.add( new Machine(rs.getInt("id"), rs.getString("reference"), rs.getString("marque"), rs.getDate("dateAchat"), rs.getDouble("prix"), es.findById(rs.getInt("employe")))) ;
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		}
		return machines;
        }
}
