package exercise3;

/**
 * Created by Alexandru.Negura on 7/7/2017.
 */
public class DumbStudent extends  Student{
    public DumbStudent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode() {
        return getFirstName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getFirstName().equals(((Student) obj).getFirstName())){
            return true;
        }
        return false;
    }
}
