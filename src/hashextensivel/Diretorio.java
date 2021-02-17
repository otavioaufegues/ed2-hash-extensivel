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
    private int tamChave;
    private double profundidadeGlobal;
    private Balde[] diretorio;

    public Diretorio(int tb, int tc) {
        profundidadeGlobal = 0;
        this.diretorio = new Balde[(int) Math.pow((double) 2, profundidadeGlobal)];
        tamBalde = tb;
        tamChave = tc;
        Balde balde = new Balde(tamBalde);
        balde.setID(IDBalde);
        this.diretorio[0] = balde;
    }

    public void insereChave(String chave) {
        Balde b = this.buscaBalde(chave);
//        System.out.println(b.getID() + " " + chave);
        if (!b.isFull()) {
            b.insere(chave);
        } else if (this.profundidadeGlobal == b.getProfundidade()) {
            duplicarDiretorio();
            b.addProfundidade();
            Balde novoBalde = new Balde(tamBalde, b.getProfundidade());
            IDBalde++;
            novoBalde.setID(IDBalde);
            atualizaDiretorio(novoBalde, chave);
            redistribuirChaves(b, chave);
        } else if (this.profundidadeGlobal > b.getProfundidade()) {
            b.addProfundidade();
            Balde novoBalde = new Balde(tamBalde, b.getProfundidade());
            IDBalde++;
            novoBalde.setID(IDBalde);
            atualizaDiretorio(novoBalde, chave);
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

    public void atualizaDiretorio(Balde baldeNovo, String chave) {
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
//        System.out.println("\n");
        Balde aux = new Balde(tamBalde);
        for (int i = 0; i < tamBalde; i++) {
            aux.setChave(i, balde.getChave(i));
        }
        balde.limpaBalde();
        for (String c : aux.getChaves()) {
            insereChave(c);
        }
        insereChave(chave);
    }

    public double getProfundidadeGlobal() {
        return profundidadeGlobal;
    }

    public void setProfundidadeGlobal(Integer profundidadeGlobal) {
        this.profundidadeGlobal = profundidadeGlobal;
    }

    public void showDir() {
        for (Balde chave : this.diretorio) {
            System.out.println(chave + "\n");
        }
    }

    public void showBaldes() {
        System.out.println("Diretorio, profundidade " + (int) this.profundidadeGlobal);
        for (Integer i = 0; i < this.diretorio.length; i++) {
            System.out.println("\nEndereço diretório: " + Integer.toBinaryString(i));
            this.diretorio[i].showBalde();
        }

    }

}
