package is.programmingTechnologies.Models;
import is.programmingTechnologies.Interfaces.ClientBuilder.*;

/**
 * This class reflects the client from the subject area.
 *
 * @version 1.0.0 23.02.2023
 */

public class Client {

    public String Name;
    public String Surname;
    public String Passport;
    public String Address;
    private Client(String name, String surname, String passport, String address)
    {
        Name = name;
        Surname = surname;
        Passport = passport;
        Address = address;
    }

    public static INameBuilder builder() {
        return new ClientBuilder();
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getPassport() {
        return Passport;
    }

    public String getAddress() {
        return Address;
    }

    /**
     * toString override
     * @return String string interpretation of client
     */

    public String toString()
    {
        return "\tName: " + Name + "\n\tSurname: " + Surname + "\n\tPassport: " + Passport + "\n\tAddress: " + Address;
    }
    private static class ClientBuilder implements INameBuilder, ISurnameBuilder, IOptionalBuilder
    {
        String Name;
        String Surname;
        String Passport;
        String Address;
        public ISurnameBuilder withName(String name)
        {
            Name = name;
            return this;
        }

        public IOptionalBuilder withSurname(String surname)
        {
            Surname = surname;
            return this;
        }

        public IOptionalBuilder withAddress(String address)
        {
            Address = address;
            return this;
        }

        public IOptionalBuilder withPassport(String passport)
        {
            Passport = passport;
            return this;
        }

        public Client build()
        {
            return new Client(Name, Surname, Passport, Address);
        }
    }

}
