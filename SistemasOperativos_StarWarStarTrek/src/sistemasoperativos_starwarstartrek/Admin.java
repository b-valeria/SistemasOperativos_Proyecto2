/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemasoperativos_starwarstartrek;
import java.util.Random;
import java.util.Queue;

import java.util.Queue;
import java.util.Random;

/**
 *
 * @author ffust
 */
public class Admin {
    private MyQueues myQueues; //instancia de MyQueues para acceder a las colas
    private Random random;
    private int reviewCycleCount;
    
    //Constructor
    public Admin(MyQueues myQueues){
        this.myQueues = myQueues;
        this.random = new Random();
        this.reviewCycleCount = 0;
    }
    
    //Método para gestionar las colas
    public void manageQueues(){
        //incrementar el contador de ciclos de revisión
        reviewCycleCount++;
        //cada 4 personajes
        if (reviewCycleCount % 2 == 0){
            if (random.nextInt(100) < 80){ //agregar un nuevo personaje con 80% de probabilidad
                addNewSWCharacter("Star Wars");
                addNewSTCharacter("Star Trek");
            }
        }
        
        //seleccionar personajes para el combate
        chooseCharactersForBattle();          
    }
    
    // Método para agregar un nuevo personaje de Star Wars a la cola correspondiente
    private void addNewSWCharacter(String franchise) {
        Random rand = new Random();
    
        // Generar un nombre aleatorio (puedes personalizar esto con una lista de nombres)
        String[] names = {"Count Dooku", "Qui-Gon Jinn", "Poe Dameron", "Ahsoka Tano", "Admiral Ackbar", "Rey"};
        String name = names[rand.nextInt(names.length)];

        // Generar habilidades, vida, fuerza y agilidad aleatoriamente
        int abilities = rand.nextInt(10) + 1; // Habilidades entre 1 y 10
        int lifeScore = rand.nextInt(10) + 1; // Vida entre 1 y 100
        int strength = rand.nextInt(10) + 1; // Fuerza entre 1 y 100
        int agility = rand.nextInt(10) + 1; // Agilidad entre 1 y 100

        // Crear el nuevo personaje
        Character newCharacter = new Character(name, franchise, abilities, lifeScore, strength, agility);
        myQueues.addCharacter(newCharacter);
        System.out.println("Added new character: " + newCharacter.getname() + " (ID: " + newCharacter.getID() + ") to " + franchise);
    }
    
    // Método para agregar un nuevo personaje de Star Trek a la cola correspondiente
    private void addNewSTCharacter(String franchise) {
        Random rand = new Random();
    
        // Generar un nombre aleatorio (puedes personalizar esto con una lista de nombres)
        String[] names = {"Kathryn Janeway", "Nyota Uhura", "Christine Chapel", "J.M. Colt", "Odo", "T'Pol"};
        String name = names[rand.nextInt(names.length)];

        // Generar habilidades, vida, fuerza y agilidad aleatoriamente
        int abilities = rand.nextInt(10) + 1; // Habilidades entre 1 y 10
        int lifeScore = rand.nextInt(10) + 1; // Vida entre 1 y 100
        int strength = rand.nextInt(10) + 1; // Fuerza entre 1 y 100
        int agility = rand.nextInt(10) + 1; // Agilidad entre 1 y 100

        // Crear el nuevo personaje
        Character newCharacter = new Character(name, franchise, abilities, lifeScore, strength, agility);
        myQueues.addCharacter(newCharacter);
        System.out.println("Added new character: " + newCharacter.getname() + " (ID: " + newCharacter.getID() + ") to " + franchise);
    }
    
    // Método para seleccionar un personaje de una cola
    private Character chooseCharacterFromQueue(Queue<Character> queue) {
        // Devuelve el primer personaje de la cola
        Character character = queue.peek();
        if (character != null) {
            System.out.println("Selected character: " + character.getname() + character.getID());
        } else {
            System.out.println("No characters available in the queue.");
        }
        return character;
    }

    // Método para seleccionar personajes para el combate
    private void chooseCharactersForBattle() {
        Character swCharacter = chooseCharacterFromQueue(myQueues.queueSWPriority1);
        if (swCharacter == null) {
        swCharacter = chooseCharacterFromQueue(myQueues.queueSWPriority2);
        }
        if (swCharacter == null) {
        swCharacter = chooseCharacterFromQueue(myQueues.queueSWPriority3);
        }

        Character stCharacter = chooseCharacterFromQueue(myQueues.queueSTPriority1);
        if (stCharacter == null) {
        stCharacter = chooseCharacterFromQueue(myQueues.queueSTPriority2);
        }
        if (stCharacter == null) {
        stCharacter = chooseCharacterFromQueue(myQueues.queueSTPriority3);
        }

        if (swCharacter != null && stCharacter != null) {
        System.out.println("Selected for battle: " + swCharacter.getname() + " (Star Wars) vs " + stCharacter.getname() + " (Star Trek)");
        } else {
        System.out.println("Not enough characters in the queues for battle.");
        //Añadir código que tome la cola de donde se escogió
    }
}

    // Método para indicar que la inteligencia artificial puede comenzar a trabajar nuevamente
    public void notifyAI() {
        System.out.println("AI can start working with the updated characters.");
    }
}