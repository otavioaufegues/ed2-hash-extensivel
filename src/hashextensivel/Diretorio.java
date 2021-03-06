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
public class Diretorio {

    int IDBalde = 1;
    private int tamBalde;
    private int numRegistros;
    private double profundidadeGlobal;
    private Balde[] diretorio;

    public Diretorio(int tb) {
        profundidadeGlobal = 0;
        numRegistros = 0;
        this.diretorio = new Balde[(int) Math.pow((double) 2, profundidadeGlobal)];
        tamBalde = tb;
        Balde balde = new Balde(tamBalde);
        balde.setID(IDBalde);
        this.diretorio[0] = balde;
    }

    public void insereChave(String chave) {
        Balde b = this.buscaBalde(chave);
        if (!b.hasChave(chave)) {
            insere(chave);
            this.numRegistros++;
        }
    }

    public void insere(String chave) {
        Balde b = this.buscaBalde(chave);
//        System.out.println(b.getID() + " " + chave);
        if (!b.isFull()) {
            b.insere(chave);
        } else if (this.profundidadeGlobal == b.getProfundidade()) {
            duplicarDiretorio();
            b.addProfundidade();
            atualizaDiretorio(b.getProfundidade(), chave);
            redistribuirChaves(b, chave);
        } else if (this.profundidadeGlobal > b.getProfundidade()) {
            b.addProfundidade();
            atualizaDiretorio(b.getProfundidade(), chave);
            redistribuirChaves(b, chave);
        }
    }

    public Balde buscaBalde(String chave) {
        for (int i = 0; i < this.diretorio.length; i++) {
            if (this.profundidadeGlobal == 0) {
                return this.diretorio[0];
            } else if (i == Integer.parseInt(chave.substring(0, (int) this.profundidadeGlobal), 2)) {
                return this.diretorio[i];
            }
        }
        return null;
    }

    public void atualizaDiretorio(int profundidade, String chave) {
        Balde baldeNovo = new Balde(tamBalde, profundidade);
        IDBalde++;
        baldeNovo.setID(IDBalde);

        for (int i = 0; i < this.diretorio.length; i++) {
            String indiceBin = String.format("%" + (int) this.profundidadeGlobal + "s", Integer.toBinaryString(i)).replace(' ', '0');
            String leftBits;

            if ((int) this.profundidadeGlobal < 1) {
                leftBits = chave.substring(0, 1);
            } else {
                leftBits = indiceBin.substring(0, (int) baldeNovo.getProfundidade());
            }
            if (leftBits.compareTo(chave.substring(0, (int) baldeNovo.getProfundidade())) == 0) {
                this.diretorio[i] = baldeNovo;
            }
        }

    }

    public void duplicarDiretorio() {
        Balde[] arrBaldeAux = new Balde[(int) Math.pow((double) 2, profundidadeGlobal)];
        this.profundidadeGlobal++;

        arrBaldeAux = this.diretorio.clone();

        this.diretorio = new Balde[(int) Math.pow((double) 2, profundidadeGlobal)];

        for (int i = 0; i < this.diretorio.length; i++) {
            String indiceBin = String.format("%" + (int) this.profundidadeGlobal + "s", Integer.toBinaryString(i)).replace(' ', '0');
            String leftBits;
            if ((int) this.profundidadeGlobal == 1) {
                leftBits = "0";
            } else {
                leftBits = indiceBin.substring(0, (int) this.profundidadeGlobal - 1);
            }
            this.diretorio[i] = arrBaldeAux[(int) Integer.parseInt(leftBits, 2)];
        }
    }

    public void redistribuirChaves(Balde balde, String chave) {
        Balde aux = new Balde(tamBalde);
        for (int i = 0; i < tamBalde; i++) {
            aux.setChave(i, balde.getChave(i));
        }
        balde.limpaBalde();
        for (String c : aux.getChaves()) {
            insere(c);
        }
        insere(chave);
    }

    public double getProfundidadeGlobal() {
        return profundidadeGlobal;
    }

    public void setProfundidadeGlobal(Integer profundidadeGlobal) {
        this.profundidadeGlobal = profundidadeGlobal;
    }

    public void showResultado() {
        System.out.println("\n\nNúmero de Baldes: " + IDBalde);
        System.out.println("Fator de Carga: " + this.numRegistros + " / " + this.IDBalde * this.tamBalde + " = " + (double) this.numRegistros / (double) (this.IDBalde * this.tamBalde));
        System.out.println("Tamanho do Diretório: "+ this.diretorio.length);
    }

    public void showBaldes() {
        System.out.println("Diretorio, profundidade " + (int) this.profundidadeGlobal);

        for (Integer i = 0; i < this.diretorio.length; i++) {
            System.out.println("\nEndereço diretório: " + Integer.toBinaryString(i) + " - " + i);
            this.diretorio[i].showBalde();
        }
    }

}
