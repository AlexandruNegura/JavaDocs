package Project;

/**
 * Created by Alexandru.Negura on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    public Integer bonus = 0;

    @Override
    public void calculeazaBonusSalarial() {
        bonus += 3 * valoareBonusPerAnimal;
    }

    @Override
    public void lucreaza(Animal animal) {
        if(animal instanceof AnimalZooFeroce){
            animal.faceBaie();
        }else {
            System.out.println("Veterinarul are grija de animal");
            calculeazaBonusSalarial();
        }
    }

    public Integer getBonus() {
        return bonus;
    }
}
