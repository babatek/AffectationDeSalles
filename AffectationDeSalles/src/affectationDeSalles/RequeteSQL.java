package affectationDeSalles;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RequeteSQL
{
	
	private String result = "error";
	
	public String toString(int id, String table, String colonne)
	{
		Connection c = null;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/projet_java",
        		   user = "root",
        		   password = "";
        	c = DriverManager.getConnection(url, user, password);
                
        	String req = "select " + colonne + " from " + table + " where id_" + table + " = " + id + ";";
        	Statement s = c.createStatement();
        	ResultSet rs = s.executeQuery(req);
                
        	while (rs.next())
        	{
        		return rs.getString(1);
        	}
        }
        
        catch (ClassNotFoundException e)
        {
        	return "Pilote JDBC non installé.";
        }
        
        catch (SQLException e)
        {
        	return result;
        }
        
        finally
        {
        	try
            {
        		if (c != null)
                   	c.close();
            }
                
            catch(SQLException e)
            {
            	return "Impossible de fermer la connection.";
            }
        }
        
        return result;
	}
	
	public String salle_affectee (int id)
	{
		RequeteSQL salle = new RequeteSQL();
		return salle.toString(id, "ligue", "salle_affectee");
	}
	
	public void update_table(int id_ligue, int id_salle)
	{
		Connection c = null;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/projet_java",
        		   user = "root",
        		   password = "";
        	c = DriverManager.getConnection(url, user, password);
                
        	String req1 = "update ligue set salle_affectee = 'non affectée' where salle_affectee = (select nom_salle from salle where id_salle = " + id_salle + ");";
        	String req = "update ligue set salle_affectee = (select nom_salle from salle where id_salle = " + id_salle + ") where id_ligue = " + id_ligue + ";";
                
        	Statement s = c.createStatement();
        	s.executeUpdate(req1);
        	s.executeUpdate(req);
        }
        
        catch (ClassNotFoundException e)
        {
        	System.out.println("Pilote JDBC non installé.");
        }
        
        catch (SQLException e)
        {
        	System.out.println(e);
        }
        
        finally
        {
        	try
            {
        		if (c != null)
                  	c.close();
            }
                
            catch(SQLException e)
            {
            	System.out.println("Impossible de fermer la connection.");
            }
        }
	}
	
	public void desaffecter(int id_ligue)
	{
		Connection c = null;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/projet_java",
        		   user = "root",
        		   password = "";
        	c = DriverManager.getConnection(url, user, password);
                
        	String req = "update ligue set salle_affectee = 'non affectée' where id_ligue = " + id_ligue + ";";
                
        	Statement s = c.createStatement();
        	s.executeUpdate(req);
        }
        
        catch (ClassNotFoundException e)
        {
        	System.out.println("Pilote JDBC non installé.");
        }
        
        catch (SQLException e)
        {
        	System.out.println(e);
        }
        
        finally
        {
        	try
            {
        		if (c != null)
        			c.close();
            }
        	
            catch(SQLException e)
            {
            	System.out.println("Impossible de fermer la connection.");
            }
        }
	}
	
	public int idSalle(String salle_affectee)
	{
		Connection c = null;
        try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url = "jdbc:mysql://localhost/projet_java",
        		   user = "root",
        		   password = "";
        	c = DriverManager.getConnection(url, user, password);
                
        	String req = "select id_salle from salle where nom_salle = '" + salle_affectee + "';";
                
        	Statement s = c.createStatement();
        	ResultSet rs = s.executeQuery(req);
				
        	while (rs.next())
        		return Integer.parseInt(rs.getString(1));
        }
        
        catch (ClassNotFoundException e)
        {
        	System.out.println("Pilote JDBC non installé.");
        }
        
        catch (SQLException e)
        {
        	System.out.println(e);
        }
        
        finally
        {
        	try
            {
        		if (c != null)
        			c.close();
            }
        	
            catch(SQLException e)
            {
            	System.out.println("Impossible de fermer la connection.");
            }
        }
        
        return 0;
	}
	
    public static void main(String[] args)
    {
    	
    }
}
