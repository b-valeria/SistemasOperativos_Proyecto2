/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemasoperativos_starwarstartrek;

/**
 *
 * @author valeriabadell
 */
public class IDGenerator {
    private static int counter = 0;

    public static int getNextID() {
        return ++counter;
    }
}