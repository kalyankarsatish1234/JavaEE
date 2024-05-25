package tester;

import java.sql.Date;
import static utils.DBUtils.*;
import java.sql.SQLException;
import java.util.Scanner;

import dao.EmployeeDaoImpl;
import pojos.Employee;

public class TestEmpCRUD {

	public static void main(String[] args) throws SQLException {
		try (Scanner sc = new Scanner(System.in)) {
			//open db connection
			openConnection();
			
			// init phase: create DAO instance
			EmployeeDaoImpl empDao = new EmployeeDaoImpl();
			boolean exit = false;
			while (!exit) {
				System.out.println("Options 1. Insert Emp Details 2. Get Emp Details 3. Update Emp details 4. delete Emp Details10.Exit");
				System.out.println("choose");
				try {
				
					switch (sc.nextInt()) {
					case 1:
						System.out.println("Enter emp details: name, address, salary, deptId, joinDate(yr-mon-dd)");
						System.out.println(empDao.insertEmpDetails(new Employee(sc.next(),sc.next(),sc.nextDouble(),
								sc.next(),Date.valueOf(sc.next()))));
						break;

					case 2:
						System.out.println("Enter dept id n join date (yyyy-mm-dd");
						empDao.getEmpDetailsByDeptAndDate
						(sc.next(), Date.valueOf(sc.next())).forEach(System.out::println);
						break;

					case 3:
						System.out.println("Enter sal incr , new dept n emp id, to update emp details");
						System.out.println(empDao.updateEmpDetails(sc.nextDouble(), sc.next(), sc.nextInt()));
						break;
						
					case 4:
						System.out.println("Enter emp id, to delete emp details");
						System.out.println(empDao.deleteEmpDetails(sc.nextInt()));
						break;
					
					case 10:
						exit = true;
						//clean up dao
						empDao.cleanUP();
						closeConnection();
						break;
						
						
					default:
                        System.out.println("Invalid option. Please try again.");
                        break;

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
