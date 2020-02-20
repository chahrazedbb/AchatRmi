/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achatrmi;

import static achatrmi.ServiceAVImpl.donnee;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author PC
 */
public class Donnee implements Serializable{
    
    // les attributs
    private ArrayList<Produit> listeProduit ;

    private HashMap<String,ArrayList<Produit>> listeDonnees = new HashMap<String,ArrayList<Produit>>() ;
    

    public Donnee(){
        listeProduit = new ArrayList<Produit>() ;
        listeProduit.add(new Produit("sac",20, (float) 10.5));
        listeProduit.add(new Produit("veste",80, (float) 100.75));
        listeProduit.add(new Produit("livre",200, (float) 4));
        listeProduit.add(new Produit("collier",40, (float) 15.25));
        listeProduit.add(new Produit("jupe",70, (float) 3.99));
        
        
        ajouterDonnee("chahrazed",new Produit("collier",2, (float) 15.25) );
        ajouterDonnee("chahrazed",new Produit("livre",5, (float) 4));
        ajouterDonnee("maroua",new Produit("veste",1, (float) 100.75));
        ajouterDonnee("imen",new Produit("jupe",2, (float) 3.99));


    }
    
    //pour avoir la liste de tout les produit qui existentent
     public ArrayList<Produit> getListeProduit() {
        return listeProduit;
    }
     
    //pour ajouter un nouveau produit
    public void ajouterProduit(Produit p) {
        listeProduit.add(p);
    }
    
    //pour ajouter les nouveau donnee c'eat a dire lie clients avec leurs achats 
    public void ajouterDonnee(String nomClient , Produit produit)
    {
        if(listeDonnees.containsKey(nomClient))
            {
                listeDonnees.get(nomClient).add(produit);
            }
            else{
              ArrayList<Produit> l = new ArrayList<Produit>();
              l.add(produit);
              listeDonnees.put(nomClient,l);
            }

    }
   
    //pour avoir la liste des client avec leurs achats
       public HashMap<String, ArrayList<Produit>> getListeDonnees() {
          return listeDonnees;
    }

}
