package is.programmingTechnologies.Servlet.DTO;

import is.programmingTechnologies.Interfaces.IAccount;

/**
 * This class is only for storing data for creating a real object in system.
 * It stores parameters for creating a real client object in central bank.
 *
 * @version 1.0.0 23.02.2023
 */

public class ClientDTO {
    private String Name;
    private String Surname;
    private String Passport;
    private String Address;
    private IAccount _accountType;

    public IAccount getAccountType() {
        return _accountType;
    }

    public void setAccountType(IAccount _accountType) {
        this._accountType = _accountType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPassport() {
        return Passport;
    }

    public void setPassport(String passport) {
        Passport = passport;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
