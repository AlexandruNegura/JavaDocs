package exercise3;

/**
 * Created by Alexandru.Negura on 7/7/2017.
 */
public class AutismIsHighWIthThisOne extends Student {
    public AutismIsHighWIthThisOne(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public int hashCode() {
        int result = this.getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getFirstName().equals(((Student) obj).getFirstName());
    }
}
