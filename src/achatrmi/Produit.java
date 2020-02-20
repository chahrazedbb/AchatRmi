/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achatrmi;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Produit implements Serializable{
    private long id = 0;
    private String nom ;
    private int quantite ;
    private float prix ;

    public Produit(String nom, int quantite, float prix) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        id ++ ;
    }
    
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

}
