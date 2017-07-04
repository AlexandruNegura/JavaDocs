package Project;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public abstract class Animal {
    public abstract void mananca(Object param1) throws AnimalManancaOmException, AnimalPeAnimalNuExceptie;
    public abstract void seJoaca();
    public abstract void faceBaie();

    public void doarme(){
        System.out.println("Animalul doarme");
    }

    public Animal() {
        System.out.println("Animal nou");
    }
}
