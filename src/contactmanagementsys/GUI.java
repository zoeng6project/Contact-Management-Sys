package contactmanagementsys;

import java.awt.*;
import javax.swing.*;


public class GUI {
    
    public static JLabel label (String text) {
        JLabel label = new JLabel (text);
        label.setFont(new Font ("Tahoma", Font.PLAIN, 17));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(200,30));
        return label; 
        
        
    }
    
    public static JButton button (String text, Color background) {
        JButton btn = new JButton(text);
        //btn.setSize(50,50);
        //btn.setBackground(background);
        //btn.setForeground(Color.);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btn.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        btn.setPreferredSize(new Dimension(70, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
        return btn;
    }
    
    public static JTextField textField(String text){
        JTextField tf = new JTextField();
        tf.setText(text);
        tf.setHorizontalAlignment(SwingConstants.CENTER);
        tf.setFont(new Font("Tahoma", Font.PLAIN, 17));
        
        return tf;
        
    }
}
