package exercise3;

/**
 * Created by Alexandru.Negura on 7/7/2017.
 */
public class EvenDumberStudent extends Student {
    public EvenDumberStudent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode() {
        return getFirstName().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }

        if(o == null || this.getClass() != o.getClass()){
            return false;
        }

        Student student = ((Student) o);

        return student.getFirstName().equals(this.getFirstName())
                && student.getLastName().equals(this.lastName);
    }
}
