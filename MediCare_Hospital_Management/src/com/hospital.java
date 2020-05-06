package com;

import java.sql.*;

public class hospital {
	
	//A common method to connect to the DB
			private Connection connect()
			{
				Connection con = null;
				
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					
					//Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospitalmanagement", "root", "");
				}
				catch (Exception e)
				{e.printStackTrace();}
				
				return con;
			}
			
			//Insert _________________________________________________________________________________________________________
			
			public String insertHospital(String name, String address)
			{
				String output = "";
				
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
			
					// create a prepared statement
					String query = " insert into hospital(`hospitalID`,`hospitalName`,`hospitalAddress`)" + " values (?, ?, ?)";
			
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 1);
					preparedStmt.setString(2, name);
					preparedStmt.setString(3, address);
					
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newHos = viewHospital();
					output = "{\"status\":\"success\", \"data\": \"" + newHos + "\"}";
				}
					catch (Exception e)
				{
						output = "{\"status\":\"error\", \"data\": \"Error while inserting the hospital.\"}";
						System.err.println(e.getMessage());
				}
				return output;
				
				}
			
			//View ______________________________________________________________________________________________________
			
			public String viewHospital()
				{
					String output = "";
					
					try
					{
					Connection con = connect();
					
					if (con == null)
					{
					return "Error while connecting to the database for reading."; }
					
					// Prepare the html table to be displayed
					output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Hospital Address</th><th>Update</th><th>Remove</th></tr>";
					
							String query = "select * from hospital";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);
							
					// iterate through the rows in the result set
					while (rs.next())
					{
						String hospitalID = Integer.toString(rs.getInt("hospitalID"));
						String hospitalName = rs.getString("hospitalName");
						String hospitalAddress = rs.getString("hospitalAddress");
					
						// Add into the html table
						output += "<td>" + hospitalName + "</td>";
						output += "<td>" + hospitalAddress + "</td>";
						
						// buttons
						
						output += "<td><input name='btnUpdate'type='button' "
								+ "value='Update'class='btnUpdate btn btn-secondary'></td>"
								+ "<td><input name='btnRemove'type='button' "
								+ "value='Remove'class='btnRemove btn btn-danger'data-itemid='"+ hospitalID + "'>" + "</td></tr>";
					}
					
					con.close();
					// Complete the html table
					output += "</table>";
					}
					catch (Exception e){
						output = "Error while reading the items.";
						System.err.println(e.getMessage());
					}
					
			return output;
			}	
			
			//Update_________________________________________________________________________________________________________________________
				
			public String updateHospitals(String ID, String name, String address)
				{
				String output = "";
				
				try
				{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
				
					// create a prepared statement
					String query = "UPDATE hospital SET hospitalName=?,hospitalAddress=? WHERE hospitalID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
				
					// binding values
					preparedStmt.setString(1, name);
					preparedStmt.setString(2, address);
					preparedStmt.setInt(3, Integer.parseInt(ID));
				
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newHos = viewHospital();
					output = "{\"status\":\"success\", \"data\": \"" + newHos + "\"}";;
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while updating the hospital.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}
			
			//Delete ___________________________________________________________________________________________
			
			public String deleteHospitals(String hospitalID)
			{
				String output = "";
				
				try
				{
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for deleting."; }
				
					// create a prepared statement
					String query = "delete from hospital where hospitalID=?";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
				
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(hospitalID));
				
					// execute the statement
					preparedStmt.execute();
					con.close();
					
					String newHos = viewHospital();
					output = "{\"status\":\"success\", \"data\": \"" + newHos + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}

}
