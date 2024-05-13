
package contactmanagementsys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class OpenContact {
    
    

    public OpenContact (Contact c, String oper, Database database ,ContactsList contacts ) throws SQLException{
        JFrame frame = new JFrame("Contacts Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);
        
        JPanel table = new JPanel(new GridLayout(6,2,15,15));
        table.setBackground(Color.WHITE);
        table.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        table.add(GUI.label("ID:"));
        JLabel id = GUI.label(String.valueOf(c.getID()));
        table.add(id);
        table.add(GUI.label("First Name: "));
        JTextField firstName = GUI.textField(c.getFirstName());
        table.add(firstName);
        table.add(GUI.label("Last Name: "));
        JTextField lastName = GUI.textField(c.getLastName());
        table.add(lastName);
        table.add(GUI.label("Phone Name: "));
        JTextField phoneNumber = GUI.textField(c.getPhoneNumber());
        table.add(phoneNumber);
        table.add(GUI.label("Email: "));
        JTextField email = GUI.textField(c.getEmail());
        table.add(email);
        
        
        JButton cancel = GUI.button("Cancel", Color.darkGray);
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            
            }
        
        });
        table.add(cancel);
        
        JButton save = GUI.button("Save", Color.darkGray);
        table.add(save);
        
        frame.add(table, BorderLayout.CENTER);
        
        if (oper.equals("new")){
            id.setText(String.valueOf(database.getNextID()));
            
            save.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent e){
                    c.setID(Integer.parseInt(id.getText().toString()));
                    c.setFirstName(firstName.getText().toString());
                    c.setLastName(lastName.getText().toString());
                    c.setPhoneNumber(phoneNumber.getText().toString());
                    c.setEmail(email.getText().toString());
                    try {
                        database.insertContact(c);
                        frame.dispose();
                        contacts.refresh(database.getContacts());
                    } catch (SQLException e1){
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
        
        } else if (oper.equals("edit")){
        
                save.addActionListener(new ActionListener(){
            
                public void actionPerformed(ActionEvent e){
                    c.setID(Integer.parseInt(id.getText().toString()));
                    c.setFirstName(firstName.getText().toString());
                    c.setLastName(lastName.getText().toString());
                    c.setPhoneNumber(phoneNumber.getText().toString());
                    c.setEmail(email.getText().toString());
                    try {
                        database.insertContact(c);
                        frame.dispose();
                        contacts.refresh(database.getContacts());
                    } catch (SQLException e1){
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
        
        } else if (oper.equals("view")){
            save.setVisible(false);
            cancel.setVisible(false);
        
        }
        frame.setVisible(true);
        
    
    }
    
}
