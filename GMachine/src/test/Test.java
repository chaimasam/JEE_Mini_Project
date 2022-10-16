package test;

import beans.Employe;
import service.EmployeService;

public class Test {
	
	public static void main(String[] args) {
		EmployeService es = new EmployeService();
//		es.create(new Employe("Safi3", "Ali3", 10000));
//		es.create(new Employe("Safi4", "Ali4", 10000));
//		es.create(new Employe("Safi5", "Ali5", 10000));
//		es.create(new Employe("Safi6", "Ali6", 10000));
//		es.create(new Employe("Safi7", "Ali7", 10000));
		
		//es.delete(es.findById(3));
		
		
		for(Employe e : es.findAll()) {
			System.out.println(e);
		}
	}

}
