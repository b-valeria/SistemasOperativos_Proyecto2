/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemasoperativos_starwarstartrek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Queue;

/**
 *
 * @author ffust
 */
public class ArtificialIntelligence {
    private MyQueues myQueues; 
    private List<Winner> winners; // Lista para almacenar los IDs de los ganadores
    private Random random;

    // Constructor
    public ArtificialIntelligence(MyQueues myQueues) {
        this.myQueues = myQueues;
        this.winners = new ArrayList<>();
        this.random = new Random();
    }
    // Método para procesar el combate
    public void processBattle(Character swCharacter, Character stCharacter) {
        try {
            // Simular tiempo de procesamiento de 10 segundos
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int outcome = random.nextInt(100); // Resultado aleatorio entre 0 y 99

        if (outcome < 40) { // 40% de probabilidad de que haya un ganador
            determineWinner(swCharacter, stCharacter);
        } else if (outcome < 67) { // 27% de probabilidad de empate
            handleDraw(swCharacter, stCharacter);
        } else { // 33% de probabilidad de que no se lleve a cabo el combate
            NoBattle(swCharacter, stCharacter);
        }
    }

    // Método para determinar el ganador
    private void determineWinner(Character swCharacter, Character stCharacter) {
        int swScore = computeScore(swCharacter);
        int stScore = computeScore(stCharacter);

        if (swScore > stScore) {
            System.out.println("Winner: " + swCharacter.getname());
            winners.add(new Winner(swCharacter.getID(), swCharacter.getname())); // Almacena el ganador
            removeCharacter(stCharacter);
            removeCharacter(swCharacter);
        } else if (stScore > swScore) {
            System.out.println("Winner: " + stCharacter.getname());
            winners.add(new Winner(stCharacter.getID(), stCharacter.getname())); // Almacena el ganador
            removeCharacter(swCharacter);
            removeCharacter(stCharacter);
        } else {
            System.out.println("It's a tie in score!");
            handleDraw(swCharacter, stCharacter);
        }
    }
 
    // Método para imprimir los ganadores
    public void printWinners() {
        System.out.println("Winners:");
        for (Winner winner : winners) {
            System.out.println("Winner ID: " + winner.getId() + ", Name: " + winner.getName());
        }
    }

    // Método para calcular el puntaje
    private int computeScore(Character character) {
        return (character.getlifeScore() + character.getstrength() + character.getagility()) * character.getabilities();
    }

    // Método para manejar el empate
    private void handleDraw(Character swCharacter, Character stCharacter) {
        System.out.println("Draw between " + swCharacter.getname() + " and " + stCharacter.getname());

        // Encontrar y eliminar el personaje de la cola correspondiente
        Queue<Character> swQueue = findCharacterQueue(swCharacter);
        Queue<Character> stQueue = findCharacterQueue(stCharacter);

        // Eliminar de la cola actual
        if (swQueue != null) {
            removeFromQueue(swQueue, swCharacter); // Eliminar el personaje de su cola actual
        }
        if (stQueue != null) {
            removeFromQueue(stQueue, stCharacter); // Eliminar el personaje de su cola actual
        }

        // Reinsertar en cola de prioridad 1
        myQueues.queueSWPriority1.offer(swCharacter);
        myQueues.queueSTPriority1.offer(stCharacter);
}
    
    //Método para saber a qué cola pertenece un personaje en caso de que tengamos que eliminarlo
    public Queue <Character> findCharacterQueue (Character character) {
        //verificar cada cola para ver donde está el personaje
        if (myQueues.queueSWPriority1.contains(character)) {
            return myQueues.queueSWPriority1;
        } else if (myQueues.queueSWPriority2.contains(character)){
            return myQueues.queueSWPriority2;
        } else if (myQueues.queueSWPriority3.contains(character)) {
            return myQueues.queueSWPriority3;
        } else if (myQueues.queueSTPriority1.contains(character)) {
            return myQueues.queueSTPriority1;
        } else if (myQueues.queueSTPriority2.contains(character)){
            return myQueues.queueSTPriority2;
        } else if (myQueues.queueSTPriority3.contains(character)) {
            return myQueues.queueSTPriority3;
    }
        return null;
}

    //Método para cuando un combate no puede tomar lugar
    private void NoBattle(Character swCharacter, Character stCharacter) {
    System.out.println("Battle cannot take place. Moving characters to backup.");
    myQueues.queueSWBackUp.add(swCharacter); // Mover a la cola de refuerzo
    myQueues.queueSTBackUp.add(stCharacter); // Mover a la cola de refuerzo
    
    // Encontrar y eliminar el personaje de la cola correspondiente
    Queue<Character> swQueue = findCharacterQueue(swCharacter);
    Queue<Character> stQueue = findCharacterQueue(stCharacter);

        if (swQueue != null) {
            removeFromQueue(swQueue, swCharacter);
        }

        if (stQueue != null) {
            removeFromQueue(stQueue, stCharacter);
        }
    }
   
    // Método para eliminar un personaje de la simulación
    private void removeCharacter(Character character) {
    // Eliminar el personaje de las colas de Star Wars
    if (!removeFromQueue(myQueues.queueSWPriority1, character)) {
        if (!removeFromQueue(myQueues.queueSWPriority2, character)) {
            removeFromQueue(myQueues.queueSWPriority3, character);
        }
    }

    // Eliminar el personaje de las colas de Star Trek
    if (!removeFromQueue(myQueues.queueSTPriority1, character)) {
        if (!removeFromQueue(myQueues.queueSTPriority2, character)) {
            removeFromQueue(myQueues.queueSTPriority3, character);
        }
    }

    // Eliminar del backup
    removeFromQueue(myQueues.queueSWBackUp, character);
    removeFromQueue(myQueues.queueSTBackUp, character);
}

    // Método auxiliar para eliminar un personaje de una cola
    private boolean removeFromQueue(Queue<Character> queue, Character character) {
        // Crear una lista temporal para almacenar los elementos que no se eliminan
        List<Character> tempList = new ArrayList<>();
        boolean found = false;

        // Iterar sobre la cola y copiar elementos a la lista temporal
        while (!queue.isEmpty()) {
        Character current = queue.poll();
            if (current.getID() == character.getID()) {
                found = true; // Marcamos que encontramos el personaje
            } else {
                tempList.add(current); // Agregar a la lista temporal si no es el personaje a eliminar
            }
        }

        // Reinsertar los elementos de la lista temporal en la cola original
        for (Character c : tempList) {
            queue.add(c);
        }

        return found; // Retornar si se encontró y eliminó el personaje
    }
}
