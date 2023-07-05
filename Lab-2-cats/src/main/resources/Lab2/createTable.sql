CREATE TABLE CatOwner (
    OwnerID SERIAL PRIMARY KEY,
    Name varchar(35),
    DateOfBirth DATE
);
CREATE TABLE Cat (
    CatID SERIAL PRIMARY KEY,
    CatName varchar(35),
    DateOfBirth date,
    Breed varchar(35),
    Color varchar(35),
    OwnerID INT,
    FOREIGN KEY (OwnerID) REFERENCES CatOwner (OwnerID)
);
INSERT INTO CatOwner (Name, Dateofbirth) VALUES ('JOJOoрр', NULL);
DELETE FROM Cat;
DELETE FROM CatOwner