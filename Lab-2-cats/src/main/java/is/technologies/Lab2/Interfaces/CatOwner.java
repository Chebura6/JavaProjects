package is.technologies.Lab2.Interfaces;

import java.sql.Date;

public interface CatOwner {

    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public Date getDateOfBirth();

    public void setDateOfBirth(Date dateOfBirth);
}
