/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemasoperativos_starwarstartrek;
import java.util.LinkedList;
import java.util.Queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author valeriabadell
 */
public class MyQueues {
    //Colas correspondientes a "Star Wars"
    public Queue<Character> queueSWPriority1; //Cola de prioridad 1
    public Queue<Character> queueSWPriority2; //Cola de prioridad 2
    public Queue<Character> queueSWPriority3; //Cola de prioridad 3
    public Queue<Character> queueSWBackUp; //Cola de refuerzo
    
    //Colas correspondientes a "Star Trek"
    public Queue<Character> queueSTPriority1; //Cola de prioridad 1
    public Queue<Character> queueSTPriority2; //Cola de prioridad 2
    public Queue<Character> queueSTPriority3; //Cola de prioridad 3
    public Queue<Character> queueSTBackUp; //Cola de refuerzo
    
    //Inicialización de las colas
    public MyQueues(){
        queueSWPriority1 = new LinkedList<>();
        queueSWPriority2 = new LinkedList<>();
        queueSWPriority3 = new LinkedList<>();
        queueSWBackUp = new LinkedList<>();
        queueSTPriority1 = new LinkedList<>();
        queueSTPriority2 = new LinkedList<>();
        queueSTPriority3 = new LinkedList<>();
        queueSTBackUp = new LinkedList<>();
    }
    //Método para agregar los personajes a las colas
    public void addCharacter(Character character){
        switch (character.getFranchise()){
            case "Star Wars":
                switch (character.classify()){
                    case "Exceptional (Level 1)":
                        queueSWPriority1.add(character);
                        break;
                    case "Average (Level 2)":
                        queueSWPriority2.add(character);
                        break;
                    case "Deficient (Level 3)":
                        queueSWPriority3.add(character);
                        break;
                }
                break; //salir del switch principal cuando termina con Star Wars
            case "Star Trek":
                switch (character.classify()){
                    case "Exceptional (Level 1)":
                        queueSTPriority1.add(character);
                        break;
                   case "Average (Level 2)":
                        queueSTPriority2.add(character);
                        break;
                    case "Deficient (Level 3)":
                        queueSTPriority3.add(character);
                        break;
                }
                break;
        }
        // Incrementar el contador del personaje
        incrementCounter(character);
    }
    // Método para incrementar el contador y manejar la promoción
    private void incrementCounter(Character character) {
        character.counter++;
        // Verificar si el contador alcanza 8 para promover al personaje
        if (character.counter >= 8) {
            character.counter = 0;
            promoteCharacter(character);
        }
    }
    
    // Método para promover al personaje a la cola de mayor prioridad
    private void promoteCharacter(Character character) {
        // Verificar si el personaje ya está en la cola de mayor prioridad
        if (character.getFranchise().equals("Star Wars")) {
            if (queueSWPriority1.contains(character)) {
                // Ya está en la cola de mayor prioridad, no hacer nada
                return;
            } else if (queueSWPriority2.remove(character)) {
                queueSWPriority1.add(character);
            } else if (queueSWPriority3.remove(character)) {
                queueSWPriority2.add(character);
            }
        } else if (character.getFranchise().equals("Star Trek")) {
            if (queueSTPriority1.contains(character)) {
                return;
            } else if (queueSTPriority2.remove(character)) {
                queueSTPriority1.add(character);
            } else if (queueSTPriority3.remove(character)) {
                queueSTPriority2.add(character);
            }
        }
    }
    
    // Método para asegurar que cada cola tenga al menos un personaje
public void ensureMinimumCharacters() {
    // Asegurarse que cada cola de Star Wars tenga al menos un personaje
    if (queueSWPriority1.isEmpty() && !queueSWPriority2.isEmpty()) {
        queueSWPriority1.add(queueSWPriority2.poll());
    }
    if (queueSWPriority1.isEmpty() && !queueSWPriority3.isEmpty()) {
        queueSWPriority1.add(queueSWPriority3.poll());
    }
    if (queueSWPriority2.isEmpty() && !queueSWPriority1.isEmpty()) {
        queueSWPriority2.add(queueSWPriority1.poll());
    }
    if (queueSWPriority2.isEmpty() && !queueSWPriority3.isEmpty()) {
        queueSWPriority2.add(queueSWPriority3.poll());
    }
    if (queueSWPriority3.isEmpty() && !queueSWPriority1.isEmpty()) {
        queueSWPriority3.add(queueSWPriority1.poll());
    }
    if (queueSWPriority3.isEmpty() && !queueSWPriority2.isEmpty()) {
        queueSWPriority3.add(queueSWPriority2.poll());
    }

    // Asegurarse que cada cola de Star Trek tenga al menos un personaje
    if (queueSTPriority1.isEmpty() && !queueSTPriority2.isEmpty()) {
        queueSTPriority1.add(queueSTPriority2.poll());
    }
    if (queueSTPriority1.isEmpty() && !queueSTPriority3.isEmpty()) {
        queueSTPriority1.add(queueSTPriority3.poll());
    }
    if (queueSTPriority2.isEmpty() && !queueSTPriority1.isEmpty()) {
        queueSTPriority2.add(queueSTPriority1.poll());
    }
    if (queueSTPriority2.isEmpty() && !queueSTPriority3.isEmpty()) {
        queueSTPriority2.add(queueSTPriority3.poll());
    }
    if (queueSTPriority3.isEmpty() && !queueSTPriority1.isEmpty()) {
        queueSTPriority3.add(queueSTPriority1.poll());
    }
    if (queueSTPriority3.isEmpty() && !queueSTPriority2.isEmpty()) {
        queueSTPriority3.add(queueSTPriority2.poll());
    }
}

// Método que se puede llamar después de agregar todos los personajes
public void initializeQueues(Character[] characters) {
    for (Character character : characters) {
        addCharacter(character);
    }
    ensureMinimumCharacters();
}
    //Método para recuperar los IDs de los personajes
    public String getIDstring(Queue<Character> queue){
        String completeID = "";
        for (Character character : queue) {
        completeID += character.getID() + " ";
    }
        return completeID;
    }
    
    // Método para imprimir las colas
        public void printQueues() {
        System.out.println("Colas de Star Wars:");
        System.out.println("Prioridad 1: " + getIDstring(queueSWPriority1));
        System.out.println("Prioridad 2: " + getIDstring(queueSWPriority2));
        System.out.println("Prioridad 3: " + getIDstring(queueSWPriority3));
        System.out.println("Backup: " + getIDstring(queueSWBackUp));
        
        System.out.println("Colas de Star Trek:");
        System.out.println("Prioridad 1: " + getIDstring(queueSTPriority1));
        System.out.println("Prioridad 2: " + getIDstring(queueSTPriority2));
        System.out.println("Prioridad 3: " + getIDstring(queueSTPriority3));
        System.out.println("Backup: " + getIDstring(queueSTBackUp));
        }
}

