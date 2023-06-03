package com.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCrud {
	public static void main(String[] args) {

	//conncetions
	String url = "jdbc:mysql://localhost:3306/employeepayroll";
	String username = "root";
	String password = "Deepak@123";

	try(Connection connection = DriverManager.getConnection(url,username,password)){
	// create a table
		try(Statement statement = connection.createStatement()){
			String createTableQuery = "create table employee_payroll (id int primary key, name varchar(20),salary int)";
			statement.executeUpdate(createTableQuery);
			System.out.println("Table is created successfully");
			}
		//insert
		try(Statement statement = connection.createStatement()){
			String insertQuery = "insert into employee_payroll (id,name,salary) values (1,'Deepak',4000),(2,'Ravi',6000),(3,'Rohit',9000)";
			statement.executeUpdate(insertQuery);
			System.out.println("values inserted successfully");
			}
		//select
		try(Statement statement = connection.createStatement()){
			String selectQuery = "select * from employee_payroll";
			ResultSet resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()){
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int salary = resultSet.getInt("salary");

			System.out.println("ID :"+ id +",Name : " + name + ", salary: "+ salary);
			}
			resultSet.close();
			System.out.println("Successful");
			}
		//select with name
		try(Statement statement = connection.createStatement()){
			String selectQuery = "select salary from employee_payroll WHERE name='Deepak'";
			ResultSet resultSet = statement.executeQuery(selectQuery);

			while (resultSet.next()){
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			int salary = resultSet.getInt("salary");

			System.out.println("ID :"+ id +",Name : " + name + ", salary: "+ salary);
			}
			resultSet.close();
			System.out.println("Successful");
			}
		//Alter
		try(Statement statement1 = connection.createStatement()){
			String updateQuery1 = "alter table employee_payroll ADD COLUMN gender varchar(10) ";
			statement1.executeUpdate(updateQuery1);
			System.out.println("values updated successfully");
	
		//Sum
		try(Statement statement = connection.createStatement()){
			String sumQuery = "select SUM(salary) from employee_payroll WHERE gender='M' GROUP BY GENDER";
			statement.executeUpdate(sumQuery);
			ResultSet resultSet = statement.executeQuery(sumQuery);
			while (resultSet.next()){
				int salary = resultSet.getInt("salary");
				System.out.println("salary: "+ salary);
				resultSet.close();
			}
			
	}catch (SQLException e){
		e.printStackTrace();
		}
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
}
	
