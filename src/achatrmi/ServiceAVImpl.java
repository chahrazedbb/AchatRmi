/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achatrmi;

import java.rmi.RemoteException;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author PC
 */
public class ServiceAVImpl extends UnicastRemoteObject implements ServiceAV{
    
      private static final long serialVersionUID = 1L;
      
      public static Donnee donnee = new Donnee();

    public ServiceAVImpl() throws RemoteException 
    {
        super();
    }
    
    
   
    @Override
    public ArrayList<Produit> rechProduit(String nomProduit) 
            throws RemoteException 
    {
   
        
        ArrayList<Produit> lp = donnee.getListeProduit();
        ArrayList<Produit> lp2 = new ArrayList<Produit>();
        
        for(int i = 0 ; i < lp.size() ; i++)
        {
            if(lp.get(i).getNom().equals(nomProduit))
            {
                lp2.add(lp.get(i));
            }
        }
        
        if(lp2 != null)
        {
            return lp2 ;
        }else{
            System.out.println("produit introuvable");
            return null;
        }
    }

    @Override
    public String achatProduit(Produit produit, int quantite, String nomClient) 
            throws RemoteException
    {
               
        int q = produit.getQuantite() ;
        if(q == 0){
            return " le stock est vide " ;
        }
        else if (q < quantite )
        {
            return " la quantité de ce produit est limité ";
        }
        else{
        produit.setQuantite(q - quantite);
        
        for(int i = 0 ; i < donnee.getListeProduit().size() ; i++){
          
            if(donnee.getListeProduit().get(i).getNom().equals(produit.getNom()))

            {
                donnee.getListeProduit().set(i, produit);
            }
        }
        donnee.ajouterDonnee(nomClient,produit);
        
        return "\n vous avez acheter  " + quantite + " de " +  produit.getNom() + "  avec succé ";
        }
    }

    @Override
    public ArrayList<Produit> produitAchetes(String nomClient)
            throws RemoteException 
    {
        
        return donnee.getListeDonnees().get(nomClient);
    }

    
}
