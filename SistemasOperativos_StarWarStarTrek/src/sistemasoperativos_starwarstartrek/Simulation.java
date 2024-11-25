/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemasoperativos_starwarstartrek;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author valeriabadell
 */
public class Simulation {
    public static void main(String[] args) {
        List <Character> characters = new ArrayList<>();
        //Creando Personajes de Star Wars
        characters.add(new Character ("Anakin Skywalker","Star Wars", 7, 8, 8, 7));
        characters.add(new Character ("Luke Skywalker","Star Wars", 9, 8, 7, 7));
        characters.add(new Character ("Han Solo","Star Wars", 7, 7, 6, 5));
        characters.add(new Character ("Yoda","Star Wars", 10, 9, 8, 9));
        characters.add(new Character ("Princess Leia","Star Wars", 8, 8, 7, 7));
        characters.add(new Character ("Mace Windu","Star Wars", 10, 4, 4, 7));
        characters.add(new Character ("C-3PO","Star Wars", 0, 1, 2, 3));
        characters.add(new Character ("R2-D2","Star Wars", 1, 2, 3, 3));
        characters.add(new Character ("Lando Calrissian","Star Wars", 4, 5, 4, 4));
        characters.add(new Character ("Chewbacca","Star Wars", 5, 3, 4, 4));
        
        //Creando Personajes de Star Trek
        characters.add(new Character ("Admiral James Kirk","Star Trek", 9, 7, 8, 9));
        characters.add(new Character ("Spock","Star Trek", 9, 8, 9, 9));
        characters.add(new Character ("Jean-Luc Picard","Star Trek", 4, 5, 5, 4));
        characters.add(new Character ("Sisco","Star Trek", 6, 6, 7, 7));
        characters.add(new Character ("Data","Star Trek", 4, 4, 6, 4));
        characters.add(new Character ("Chakotay","Star Trek", 4, 6, 3, 2));
        characters.add(new Character ("Dukat","Star Trek", 5, 6, 3, 7));
        characters.add(new Character ("Worf","Star Trek", 4, 4, 3, 1));
        characters.add(new Character ("Christopher Pike","Star Trek", 8, 9, 6, 6));
        characters.add(new Character ("Scotty","Star Trek", 7, 8, 8, 7));
        
        Interfaz inter = new Interfaz();
        inter.setVisible(true);
        
         // Crear una instancia de MyQueues
        MyQueues myQueues = new MyQueues();
        
        // Mostrar resultados de cada personaje en consola
        for (Character c : characters) {
            // Imprimimos el ID, nombre, cantidad de habilidades de calidad y clasificación del personaje
            System.out.println("ID: " + c.getID() + ", Name: " + c.getname() +
                    ", Quality: " + c.getqualityCount() +
                    ", Class: " + c.classify());
        
        //Agregar personajes a las colas
        myQueues.addCharacter(c);
        }
        //imprimir las colas
        myQueues.printQueues();
        
        // Crear una instancia de Admin
        Admin admin = new Admin(myQueues);
        
        // Crear una instancia de ArtificialIntelligence
        ArtificialIntelligence ai = new ArtificialIntelligence(myQueues);

       // Simular la gestión de las colas
        while (!myQueues.queueSWPriority1.isEmpty() || 
                !myQueues.queueSWPriority2.isEmpty() || 
                !myQueues.queueSWPriority3.isEmpty() || 
                !myQueues.queueSWBackUp.isEmpty() || 
                !myQueues.queueSTPriority1.isEmpty() || 
                !myQueues.queueSTPriority2.isEmpty() || 
                !myQueues.queueSTPriority3.isEmpty() || 
                !myQueues.queueSTBackUp.isEmpty()) {
            System.out.println("Cycle:");
            admin.manageQueues();

            // Simular un combate entre personajes seleccionados
            Character swCharacter = myQueues.queueSWPriority1.peek();
            Character stCharacter = myQueues.queueSTPriority1.peek();

            if (swCharacter != null && stCharacter != null) {
                // Procesar el combate y obtener el resultado
                ai.processBattle(swCharacter, stCharacter); // devuelve el resultado
            } else {
                System.out.println("Not enough characters available for battle.");
            }

            // Imprimir el estado de las colas después del combate
            myQueues.printQueues();
            System.out.println();
        }
        ai.printWinners();
    }
}
   


