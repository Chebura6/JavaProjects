package is.technologies.Lab2.Interfaces;

import java.sql.Date;

public interface Cat {
    public int getId();

    public void setId(int id);

    public String getName();

    public void setName(String name);

    public Date getDateOfBirth();

    public void setDateOfBirth(Date dateOfBirth);

    public String getBreed();

    public void setBreed(String breed);

    public String getColor();

    public void setColor(String color);

    public CatOwner getOwner();

    public void setOwner(CatOwner owner);
}
