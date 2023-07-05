CREATE TABLE IF NOT EXISTS public.flea (
    Fleaid BIGSERIAL PRIMARY KEY,
    Name varchar(35),
    CatId INT,
    FOREIGN KEY (CatId) REFERENCES public.Cat (CatID)
);