/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achatrmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author PC
 */
public class Client extends JFrame{
    public static ServiceAV monService;
     
    public static JTextField nomP;
    public static JTextField quantite;
    public static JTextField nomc;
    public static JTextArea affichage;
    
    public static String resultat = "" ;

    
    public Client() throws NotBoundException, MalformedURLException, RemoteException {
      
      monService = (ServiceAV) Naming.lookup("//localhost/MonServeur");
      // JPanel for the text fields
      JPanel tfPanel = new JPanel(new GridLayout(3, 3, 10, 2));
      tfPanel.setBorder(BorderFactory.createTitledBorder("Bien venu: "));
 
      // chercher un produit
      tfPanel.add(new JLabel(" Nom du produit: "));
      nomP = new JTextField(10);
      tfPanel.add(nomP);
      
      
      JButton b1 = new JButton("chercher");
      b1.setMnemonic(KeyEvent.VK_C);
      tfPanel.add(b1);
      b1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

             // chercher un produit 
                ArrayList<Produit> Pliste = null ;
            try {
                 Pliste = monService.rechProduit(nomP.getText());
             } catch (RemoteException ex) {
                 Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
             }
                for(int i = 0 ; i < Pliste.size() ; i++)
                {
                    resultat = resultat + "Nom du produit : " + Pliste.get(i).getNom() + " -- " + 
                            " Prix : " + Pliste.get(i).getPrix() + " -- " + 
                            " Quantité : " + Pliste.get(i).getQuantite() + " \n" ; 
                }                          
                System.out.println(resultat);
                

            affichage.append("\n La liste des produit : \n" + resultat);
            
            resultat = "";
             
         }
      });
      
            // acheter un produit
      tfPanel.add(new JLabel(" Quantité: "));
      quantite = new JTextField(10);
      tfPanel.add(quantite);
      
      JButton b2 = new JButton("acheter");
      b2.setMnemonic(KeyEvent.VK_C);
      tfPanel.add(b2);
      b2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            
             try {
                 // acheter un produit
                 Produit p = null;
                 
                 p = monService.rechProduit(nomP.getText()).get(0);
  
                String s =  monService.achatProduit(p, Integer.parseInt(quantite.getText()), nomc.getText());
                 
                 affichage.append(s);
                 
             } catch (RemoteException ex) {
                 Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
      });
      
            // chercher un produit
      tfPanel.add(new JLabel("  Nom du client: "));
      nomc = new JTextField(10);
      tfPanel.add(nomc);
      
      JButton b3 = new JButton("afficher");
      b3.setMnemonic(KeyEvent.VK_C);
      tfPanel.add(b3);
      b3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             try {
                 // chercher un produit
                 ArrayList<Produit> aliste = null ;
                 
                 aliste = monService.produitAchetes(nomc.getText());
                 for(int i = 0 ; i < aliste.size() ; i++)
                 {
                     resultat = resultat + "Nom du produit : " + aliste.get(i).getNom() + " -- " +
                             " Prix : " + aliste.get(i).getPrix() + " --  \n" ;
                 }
                 System.out.println(resultat);
                 
                 affichage.append("\n La liste de votre achats : \n" + resultat);
                 
                 resultat = "" ;
             } catch (RemoteException ex) {
                 Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
             }
        
         }
      });
 
      // Create a JTextArea
      affichage = new JTextArea(" ");
      affichage.setFont(new Font("Serif", Font.ITALIC, 13));
      affichage.setLineWrap(true);       // wrap line
      affichage.setWrapStyleWord(true);  // wrap line at word boundary
      affichage.setBackground(new Color(204, 238, 241)); // light blue
      // Wrap the JTextArea inside a JScrollPane
      JScrollPane tAreaScrollPane = new JScrollPane(affichage);
      tAreaScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      tAreaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 
      // Setup the content-pane of JFrame in BorderLayout
      Container cp = this.getContentPane();
      cp.setLayout(new BorderLayout(5, 5));
      cp.add(tfPanel, BorderLayout.NORTH);
      cp.add(tAreaScrollPane, BorderLayout.CENTER);
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("JTextComponent Demo");
      setSize(500, 500);
      setVisible(true);
   }
         
	public static void main(String[] args) 
	 {
	     SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new Client();  // Let the constructor do the job
                        } catch (NotBoundException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RemoteException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                    }
                });
         }
}
