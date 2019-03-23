package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class Principal extends JFrame {


 JComboBox combo;
 JTextArea texto;
 JButton btncambiar;
 String fuentes[];
 
 public Principal(){
  
  colocarSkin();
  
  GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
  fuentes= environment.getAvailableFontFamilyNames();
    
  combo=new JComboBox(fuentes);
  btncambiar=new JButton("Cambiar");
  btncambiar.addActionListener(new ActionListener(){


  
   public void actionPerformed(ActionEvent e) {
    texto.setFont(new Font(""+fuentes[combo.getSelectedIndex()],Font.PLAIN,16));
    texto.updateUI();
   }
   
  });
  JPanel pf=new JPanel();
  pf.add(new JLabel("Fuentes: "));
  pf.add(combo);
  pf.add(btncambiar);
  add(pf,BorderLayout.NORTH);
  
  texto=new JTextArea();
  add(new JScrollPane(texto),BorderLayout.CENTER);
  
 }
 
 public void colocarSkin(){
  try {
   UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
   } catch (ClassNotFoundException e) {
    e.printStackTrace();
   } catch (InstantiationException e) {
    e.printStackTrace();
   } catch (IllegalAccessException e) {
    e.printStackTrace();
   } catch (UnsupportedLookAndFeelException e) {
    e.printStackTrace();
   }
 }
 
 public static void main(String[] args) {
  Principal p=new Principal();
  p.setVisible(true);
  p.setBounds(0, 0, 400, 400);
  p.setLocationRelativeTo(null);
  p.setDefaultCloseOperation(EXIT_ON_CLOSE);
 }
}