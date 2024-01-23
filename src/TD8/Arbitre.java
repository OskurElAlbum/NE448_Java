package TD8;

public class Arbitre extends Thread {
    
    private boolean[] baguettes;
    int NBPhilosophes;

    public Arbitre(int nbPhilosophes) {
        this.NBPhilosophes=nbPhilosophes;
        this.baguettes = new boolean[nbPhilosophes];
        for (int i = 0; i < nbPhilosophes; i++) {
            this.baguettes[i] = true; // Toutes les baguettes sont initialement libres
        }
    }

    public synchronized boolean autorisation(int numPhilo) {
        int gauche = numPhilo-1;
        int droite = (numPhilo);
        if (numPhilo==0){
            gauche = NBPhilosophes-1;
        }
        else{
            gauche = numPhilo-1;
        }
        if (baguettes[gauche] && baguettes[droite]) { // Si les deux baguettes sont libres
            baguettes[gauche] = false;
            baguettes[droite] = false;
            return true;
        }
        return false;
    }

    public synchronized void liberation(int numPhilo) {
        int gauche = numPhilo-1;
        int droite = (numPhilo);
        if (numPhilo==0){
            gauche = NBPhilosophes-1;
        }
        else{
            gauche = numPhilo-1;
        }
        baguettes[gauche] = true;
        baguettes[droite] = true;
        //notifyAll(); // Réveiller les threads en attente d'une baguette
    }
}
