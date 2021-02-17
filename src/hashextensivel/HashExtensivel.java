/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashextensivel;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Augusto
 */
public class HashExtensivel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int tamBalde;
        int tamChave;
        int numChaves;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o tamanho dos Baldes: \n");
        tamBalde = scanner.nextInt();

        System.out.println("Digite o número de bits das chaves: \n");
        tamChave = scanner.nextInt();

        System.out.println("Digite o número de pseudo-Chaves: \n");
        numChaves = scanner.nextInt();
        
        Diretorio diretorio = new Diretorio(tamBalde);
        Diretorio diretorio2 = new Diretorio(tamBalde);

        Random r = new Random();
        for (int i = 0; i < numChaves; i++) {
            diretorio.insereChave(String.format("%" + tamChave + "s", Integer.toBinaryString(r.nextInt((int) Math.pow((double) 2, tamChave)))).replace(' ', '0'));
        }
        
        for (int i = 0; i < numChaves; i++) {
            diretorio2.insereChave("11111"+String.format("%" + tamChave + "s", Integer.toBinaryString(r.nextInt((int) Math.pow((double) 2, tamChave)))).replace(' ', '0'));
        }

        
        
        diretorio.showResultado();
        diretorio2.showResultado();
        
//        para ver a estrutura do hash descomentar essas chamadas de metedos abaixo
//        diretorio.showBaldes();
//        diretorio2.showBaldes();
    }

}
