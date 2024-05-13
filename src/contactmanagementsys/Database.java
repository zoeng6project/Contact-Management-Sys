
package contactmanagementsys;

import java.sql.*;
import java.util.*;


public class Database {
    
    private String url = "jdbc:mysql://localhost/Contacts Management System";
    private String user = "root";
    private String pass = "";
    private Statement statement;
    
    public Database() throws SQLException{
        Connection connection = DriverManager.getConnection(url,user,pass);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        
    }
    
    public ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        String select = "SELECT * FROM `contacts`;";
        ResultSet rs = statement.executeQuery(select);
        while (rs.next()){
            Contact c = new Contact();
            c.setID(rs.getInt("ID"));
            c.setFirstName(rs.getString("First Name"));
            c.setLastName(rs.getString("Last Name"));
            c.setPhoneNumber(rs.getString("Phone Number"));
            c.setEmail(rs.getString("Email"));
            contacts.add(c);
            
            
        }
        return contacts;
    }
    
    public void insertContact(Contact c) throws SQLException{
        String insert = "INSERT INTO `Contacts`(`ID`, `First Name`, `Last Name`, `Phone Number`, `Email`) VALUES ('"+c.getID()+"','"+c.getFirstName()+"','"+c.getLastName()+"','"+c.getPhoneNumber()+"','"+c.getEmail()+"')";
        statement.execute(insert);
    
    }
    
    public void updateContact(Contact c) throws SQLException{
        String update = "UPDATE `Contacts` SET `First Name`='"+c.getFirstName()+"',`Last Name`='"+c.getLastName()+"',`Phone Number`='"+c.getPhoneNumber()+"',`Email`='"+c.getEmail()+"' WHERE `ID` = "+c.getID()+";";
        statement.execute(update);
    }
    
    public void deleteContact(Contact c)throws SQLException{
        String delete = "DELETE FROM `Contacts` WHERE `ID` = "+c.getID()+";";
        statement.execute(delete);
    }
    
    public int getNextID() throws SQLException {
        
        int id = 0;
        int maxID = 0;
        ArrayList<Contact> contacts = getContacts();

        for (Contact contact : contacts) {
            int contactID = contact.getID();
            if (contactID > maxID) {
                maxID = contactID;
            }
        }
        
        if (maxID!=0){
            
            id = maxID+1;
        }
        
        return id;
    
    }
}
