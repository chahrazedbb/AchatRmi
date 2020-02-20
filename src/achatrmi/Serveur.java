/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achatrmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author PC
 */
public class Serveur {
    
    public static void main(String[] args){

        try {
            
            LocateRegistry.createRegistry(1099);
            Registry registry =  LocateRegistry.getRegistry();
            registry.rebind("MonServeur", new ServiceAVImpl());            
            System.err.println("Serveur est pré");

        } catch (Exception e) {

            System.err.println("exception serveur: " + e.toString());
            e.printStackTrace();

        }

    }
    
}
