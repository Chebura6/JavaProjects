CREATE TABLE IF NOT EXISTS public.CatOwner (
 OwnerID BIGSERIAL PRIMARY KEY,
 Name varchar(35),
 DateOfBirth DATE
);
CREATE TABLE IF NOT EXISTS public.Cat (
  CatID BIGSERIAL PRIMARY KEY,
  Name varchar(35),
  DateOfBirth date,
  Breed varchar(35),
  Color varchar(35),
  OwnerID BIGSERIAL,
  FOREIGN KEY (OwnerID) REFERENCES public.CatOwner (OwnerID)
);