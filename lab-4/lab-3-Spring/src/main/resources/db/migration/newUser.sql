INSERT INTO public.role (rolename) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO public.user (username, pwd, roleid) VALUES ('artem', '100', 2);
INSERT INTO public.user (username, pwd, ownerid, roleid) VALUES ('god', '100', 2, 1);
INSERT INTO public.user (username, pwd, ownerid, roleid) VALUES ('nastya', '100', 1, 1);

DELETE FROM public.user WHERE ownerid = 2;