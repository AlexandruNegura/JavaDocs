package Project;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo{
    public Integer bonus = 0;

    @Override
    public void calculeazaBonusSalarial() {
        bonus += valoareBonusPerAnimal * 2;
    }

    @Override
    public void lucreaza(Animal animal) {
        System.out.println("Ingrijitorul intrain cusca animalului");
        calculeazaBonusSalarial();
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException, AnimalManancaOmException, AnimalPeAnimalNuExceptie {
        if(animal instanceof AnimalZooRar && mancare == null){
            throw new AnimalPeCaleDeDisparitieException();
        }else {
            lucreaza(animal);
            animal.mananca(mancare);
        }
    }

    public Integer getBonus() {
        return bonus;
    }
}
