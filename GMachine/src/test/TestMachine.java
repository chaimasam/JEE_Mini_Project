package test;

import java.util.Date;

import beans.Machine;
import service.EmployeService;
import service.MachineService;

public class TestMachine {

	public static void main(String[] args) {
		MachineService ms = new MachineService();
		EmployeService es = new EmployeService();

		ms.create(new Machine("DR43", "HP", new Date(), 5000, es.findById(2)));

		//ms.delete(ms.findById(2));
	}

}
