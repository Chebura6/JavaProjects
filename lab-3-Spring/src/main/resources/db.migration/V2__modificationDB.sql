CREATE TABLE IF NOT EXISTS public.flea (
    Fleaid SERIAL PRIMARY KEY,
    Name varchar(35),
    CatId INT,
    FOREIGN KEY (CatId) REFERENCES Cat (CatID)
);