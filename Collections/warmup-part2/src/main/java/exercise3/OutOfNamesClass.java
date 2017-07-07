package exercise3;

/**
 * Created by Alexandru.Negura on 7/7/2017.
 */
public class OutOfNamesClass extends Student {
    public OutOfNamesClass(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int hashCode() {
        int result = this.getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();

        return result;
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
                && student.getLastName().equals(this.getLastName());
    }
}
