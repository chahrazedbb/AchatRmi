/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achatrmi;
import java.rmi.*;
import java.util.*;
/**
 *
 * @author PC
 */
public interface ServiceAV extends Remote{
    
     
    
    public ArrayList<Produit> rechProduit(String nomProduit)
            throws RemoteException ;
    
    public String achatProduit(Produit Produit,int quantite, String nomClient)
            throws RemoteException ;
    
    public ArrayList<Produit> produitAchetes(String nomClient)
            throws RemoteException ;
    

}
