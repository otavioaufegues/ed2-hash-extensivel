/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashextensivel;

/**
 *
 * @author Augusto
 */
public class Balde {

    private int ID;

    private Integer profundidade;
    private Integer numChavesInseridas;
    private String[] chaves;

    public Balde(Integer tamBalde, Integer profundidade) {
        this.profundidade = profundidade;
        this.numChavesInseridas = 0;
        this.chaves = new String[tamBalde];
    }

    public Balde(Integer tamBalde) {

        this.profundidade = 0;
        this.numChavesInseridas = 0;
        this.chaves = new String[tamBalde];
    }

    public boolean isFull() {
        return this.chaves.length <= this.numChavesInseridas;
    }

    public void insere(String chave) {
        setChave(this.numChavesInseridas, chave);
        this.numChavesInseridas++;
    }

    public String busca(String k) {
        for (String chave : this.chaves) {
            if (chave.equals(k)) {
                return chave;
            }
        }
        return null;
    }

    public void showBalde() {
        System.out.println("Balde "+ ID+"\nProfundidade: " + this.profundidade + "\n");
        int i = 0;
        for (String chave : this.chaves) {
            System.out.println("Posição: " + i + " => " + chave + "\n");
            i++;
        }
    }

    public void limpaBalde() {
        setNumChavesInseridas(0);
        for (int i = 0; i < this.chaves.length; i++) {
            this.setChave(i, null);
        }
    }

    public void addProfundidade() {
        this.profundidade++;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Integer profundidade) {
        this.profundidade = profundidade;
    }

    public Integer getNumChavesInseridas() {
        return numChavesInseridas;
    }

    public void setNumChavesInseridas(Integer numChavesInseridas) {
        this.numChavesInseridas = numChavesInseridas;
    }

    public String[] getChaves() {
        return this.chaves;
    }

    public void setChaves(String[] chaves) {
        this.chaves = chaves;
    }

    public String getChave(Integer i) {
        return chaves[i];
    }

    public void setChave(Integer i, String value) {
        this.chaves[i] = value;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
