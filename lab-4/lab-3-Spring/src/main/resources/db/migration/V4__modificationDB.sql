CREATE TABLE IF NOT EXISTS public.Role (
    roleid BIGSERIAL PRIMARY KEY,
    roleName varchar(30)
);

CREATE TABLE IF NOT EXISTS public.User (
    userId BIGSERIAL PRIMARY KEY,
    username varchar(30),
    pwd varchar(100),
    ownerId BIGINT,
    roleid BIGINT,
    FOREIGN KEY (roleid) REFERENCES public.Role (roleid)
);
