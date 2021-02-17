/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashextensivel;

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
        int tamBalde = 2;
        int tamChave = 5;
        String[] chave;
        Scanner scanner = new Scanner(System.in);

//        System.out.println("Digite o tamanho dos Baldes: \n");
//        tamBalde = scanner.nextInt();
//
//        System.out.println("Digite o número de bits das chaves: \n");
//        tamChave = scanner.nextInt();
//
        Diretorio diretorio = new Diretorio(tamBalde, tamChave);

        diretorio.insereChave("00001");
        diretorio.insereChave("01100");
        diretorio.insereChave("10000");
        diretorio.insereChave("10001");
        diretorio.insereChave("11001");
        diretorio.insereChave("10101");
        diretorio.insereChave("01111");
//        diretorio.insereChave("11111");
//        diretorio.insereChave("00110");
//        diretorio.insereChave("11100");
//        diretorio.insereChave("11101");
        diretorio.showBaldes();

    }

}
