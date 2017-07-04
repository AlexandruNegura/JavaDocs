package Project;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {

    @Override
    public void mananca(Object param1) throws AnimalPeAnimalNuExceptie, AnimalManancaOmException {
        if (param1 instanceof AngajatZoo) {
            throw new AnimalManancaOmException();
        } else if (param1 instanceof Animal) {
            throw new AnimalPeAnimalNuExceptie();
        } else {
            System.out.println("AnimalZooFeroce mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooFeroce se joaca");
        super.doarme();
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooFeroce face baie");
    }

    @Override
    public void doarme() {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }
}
