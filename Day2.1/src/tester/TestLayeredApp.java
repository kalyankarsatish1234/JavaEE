package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.EmployeeDaoImpl;

public class TestLayeredApp {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in))
		{
			//create DAO instance (ctor) --cn, pst
			EmployeeDaoImpl dao = new EmployeeDaoImpl();
			System.out.println("Enter dept id n join date (yyyy-mm-dd");
			dao.getEmpDetailsByDeptAndDate
			(sc.next(), Date.valueOf(sc.next())).forEach(System.out::println);
			//clean up dao..
			dao.cleanUP();  
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
