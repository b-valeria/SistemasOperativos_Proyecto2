/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemasoperativos_starwarstartrek;
import java.util.Random;
import java.util.Random;
/**
 *
 * @author valeriabadell
 */
public class Character {
     
    public static int counter = 0;
    private int ID;
    private String name;
    private String franchise;
    private int abilities;
    private int lifeScore;
    private int strength;
    private int agility;
    private int qualityCount;
    
    //Constructor
    public Character(String name, String franchise, int abilities, int lifeScore, int strength, int agility){
        this.ID = ++counter;
        this.name = name;
        this.franchise = franchise;
        this.abilities = abilities;
        this.lifeScore = lifeScore;
        this.strength = strength;
        this.agility = agility;
        this.qualityCount = establishQuality();
    }
    
     // Método para determinar la calidad de las habilidades
    private int establishQuality() {
        Random rand = new Random();
        int quality = 0;

        // Habilidades
        if (rand.nextInt(100) < 60) quality++; // 60% probabilidad
        // Puntos de vida
        if (rand.nextInt(100) < 70) quality++; // 70% probabilidad
        // Fuerza
        if (rand.nextInt(100) < 50) quality++; // 50% probabilidad
        // Agilidad
        if (rand.nextInt(100) < 40) quality++; // 40% probabilidad

        return quality;
    }

    // Método para clasificar al personaje
    public String classify() {
        if (qualityCount >= 3) {
            return "Exceptional (Level 1)";
        } else if (qualityCount == 2) {
            return "Average (Level 2)";
        } else {
            return "Deficient (Level 3)";
        }
    }
      
   // Getters
    public int getID(){
        return ID;
    }
    public String getname(){
        return name;
    }
    public String getFranchise() {
        return franchise;
    }
    public int getabilities(){
        return abilities;
    }
    public int getlifeScore(){
        return lifeScore;
    }
    public int getstrength(){
        return strength;
    }
    public int getagility(){
        return agility;
    }
    public int getqualityCount(){
        return qualityCount;
    }
    
    @Override
    public String toString() {
        return name;  // Solo devuelve el nombre
    }
    
}
