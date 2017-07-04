package Project;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class AnimalZooRar extends Animal {
    String name;
    String numeleTariiDeOrigine;

    public AnimalZooRar(String name, String numeleTariiDeOrigine) {
        this.name = name;
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }

    public AnimalZooRar(String name) {
        this.name = name;
    }

    public AnimalZooRar() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumeleTariiDeOrigine() {
        return numeleTariiDeOrigine;
    }

    public void setNumeleTariiDeOrigine(String numeleTariiDeOrigine) {
        this.numeleTariiDeOrigine = numeleTariiDeOrigine;
    }

    @Override
    public void mananca(Object param1) throws AnimalManancaOmException, AnimalPeAnimalNuExceptie {
        if(param1 instanceof AngajatZoo){
            throw new AnimalManancaOmException();
        } else if(param1 instanceof Animal){
            throw new AnimalPeAnimalNuExceptie();
        }else{
            System.out.println("AnimalZooRar mananca");
        }
    }

    @Override
    public void seJoaca() {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

    @Override
    public void faceBaie() {
        System.out.println("AnimalulZooRar face baie");
    }
}
