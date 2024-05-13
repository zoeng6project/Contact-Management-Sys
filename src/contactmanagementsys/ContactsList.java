
package contactmanagementsys;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactsList {
    
    private  GridLayout gridLayout;
    private  JPanel table;
    Database database ;

    
    
    public ContactsList(Database database){
        this.database = database;
        JFrame frame = new JFrame("Contacts Management System");
        frame.setLayout(new BorderLayout());
        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);
        //frame.getContentPane().setBackground(Color.white);
        
        JPanel top = new JPanel(new BorderLayout());
        top.setBorder(BorderFactory.createEmptyBorder(77,50,50,50));
        //top.setBackground(null);
        
        JLabel title = new JLabel("Welcome to Contacts Management System");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        top.add(title,BorderLayout.CENTER);
        
        JButton newContact = new JButton("New Contact");
        newContact.setFont(new Font("Tahoma", Font.BOLD, 18));
        newContact.setForeground(Color.red);
        newContact.setBounds(100,100,95,30);
        newContact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newContact.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                try {
                    new OpenContact(new Contact(), "new", database, ContactsList.this);
                } catch (SQLException e1){
                    JOptionPane.showMessageDialog(null, e1.toString());
                }
            }
        });
        
        top.add(newContact, BorderLayout.EAST); 
        
        frame.add(top, BorderLayout.NORTH);
        
        gridLayout = new GridLayout(8,1,0,0);
       
        table = new JPanel(gridLayout);
        table.setBackground(Color.white);
        try{
            refresh(database.getContacts());
        } catch (SQLException e1){
            JOptionPane.showMessageDialog(null, e1.toString());
        }
        
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(0,50,50,50));
        sp.setBackground(null);
        frame.add(sp, BorderLayout.CENTER);
        
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
        public  void refresh(ArrayList<Contact> contacts) {
        table.removeAll();
        table.repaint();
        table.revalidate();
        
        int rows = contacts.size();
        if (rows<8) rows=8;
        gridLayout.setRows(rows);
        
       
        for (int i=0; i<contacts.size(); i++) {
            Contact c = contacts.get(i);
            JPanel panel = new JPanel (new FlowLayout (FlowLayout.CENTER, 20,0) );
            //panel.setBackground(null);
            panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
            if (i%2 == 0) panel.setBackground (Color.decode("#ADDFFF"));
            panel.setPreferredSize(new Dimension(100, 55));
            panel.add(GUI.label(c.getFirstName()+" "+c.getLastName()));
            panel.add(GUI.label(c.getPhoneNumber()));
            panel.add(GUI.label(c.getEmail()));
            
            JButton view = GUI.button("View", Color.GREEN);
            view.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        new OpenContact(c, "view",database, ContactsList.this );
                    } catch (SQLException e1){
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
            panel.add(view);
            
            JButton edit = GUI.button("Edit", Color.BLUE);
            edit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        new OpenContact(c, "edit",database, ContactsList.this );
                    } catch (SQLException e1){
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
            panel.add(edit);
            
            JButton delete = GUI.button("Delete", Color.ORANGE);
            delete.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  try {
                    database.deleteContact(c);
                    refresh(database.getContacts());
                    } catch (SQLException e1){
                        JOptionPane.showMessageDialog(null, e1.toString());
                    }
                }
            });
            panel.add(delete);
            
            table.add(panel);
        }
    }
}
