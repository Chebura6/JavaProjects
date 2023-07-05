package is.technologies.Lab2.Hibernate;

import is.technologies.Lab2.Hibernate.Models.HibernateCat;
import is.technologies.Lab2.Hibernate.Models.HibernateCatOwner;
import is.technologies.Lab2.Hibernate.Models.SessionFactoryManager;
import is.technologies.Lab2.Interfaces.CatOwner;
import is.technologies.Lab2.Interfaces.DataBaseOperations;
import is.technologies.Lab2.Interfaces.Cat;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateService implements DataBaseOperations {

    public HibernateService() {
    }
    @Override
    public Cat saveCat(Cat cat) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(cat);
        session.getTransaction().commit();
        return cat;
    }

    @Override
    public CatOwner saveCatOwner(CatOwner catOwner) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(catOwner);
        session.getTransaction().commit();
        return catOwner;
    }

    @Override
    public void deleteCatById(int id) throws SQLException {
        try (Session session = SessionFactoryManager.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            HibernateCat catToDelete = session.get(HibernateCat.class, id);
            session.remove(catToDelete);
            session.getTransaction().commit();
        }
        catch (Exception e) {

        }

    }

    @Override
    public void deleteCatOwnerById(int id) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        HibernateCatOwner catOwnerToDelete = session.get(HibernateCatOwner.class, id);
        session.remove(catOwnerToDelete);
        session.getTransaction().commit();
    }

    @Override
    public void deleteCat(Cat entity) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteCatOwner(CatOwner entity) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    @Override
    public void deleteAllCats() throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<HibernateCat> criteriaDelete = criteriaBuilder.createCriteriaDelete(HibernateCat.class);
        Root<HibernateCat> root = criteriaDelete.from(HibernateCat.class);
        session.createQuery(criteriaDelete).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void deleteAllCatOwners() throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<HibernateCatOwner> criteriaDelete = criteriaBuilder.createCriteriaDelete(HibernateCatOwner.class);
        Root<HibernateCatOwner> root = criteriaDelete.from(HibernateCatOwner.class);
        session.createQuery(criteriaDelete).executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public Cat updateCat(Cat cat, int id) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        HibernateCat catToUpdate = session.get(HibernateCat.class, id);
        catToUpdate.setName(cat.getName());
        catToUpdate.setBreed(cat.getBreed());
        catToUpdate.setColor(cat.getColor());
        catToUpdate.setOwner(cat.getOwner());
        catToUpdate.setDateOfBirth(cat.getDateOfBirth());
        session.getTransaction().commit();
        return catToUpdate;
    }

    @Override
    public CatOwner updateCatOwner(CatOwner catOwner, int id) throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        HibernateCatOwner catOwnerToUpdate = session.get(HibernateCatOwner.class, id);
        catOwnerToUpdate.setName(catOwner.getName());
        catOwnerToUpdate.setDateOfBirth(catOwner.getDateOfBirth());
        session.getTransaction().commit();
        return catOwnerToUpdate;
    }

    @Override
    public Cat getCatById(int id) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        HibernateCat cat = session.get(HibernateCat.class, id);
        session.getTransaction().commit();
        return cat;
    }

    @Override
    public CatOwner getCatOwnerById(int id) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        HibernateCatOwner owner = session.get(HibernateCatOwner.class, id);
        session.getTransaction().commit();
        return owner;
    }

    @Override
    public List<Cat> getAllCats() throws SQLException {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Cat> cq = cb.createQuery(Cat.class);
        Root<HibernateCat> rootEntry = cq.from(HibernateCat.class);
        CriteriaQuery<Cat> all = cq.select(rootEntry);
        TypedQuery<Cat> allQuery = session.createQuery(all);

        List<Cat> owners = allQuery.getResultList();
        session.getTransaction().commit();

        return owners;
    }

    @Override
    public List<CatOwner> getAllCatOwners() {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CatOwner> cq = cb.createQuery(CatOwner.class);
        Root<HibernateCatOwner> rootEntry = cq.from(HibernateCatOwner.class);
        CriteriaQuery<CatOwner> all = cq.select(rootEntry);
        TypedQuery<CatOwner> allQuery = session.createQuery(all);

        List<CatOwner> owners = allQuery.getResultList();
        session.getTransaction().commit();

        return owners;
    }

    @Override
    public List<Cat> getAllByCatOwnerId(int id) {
        Session session = SessionFactoryManager.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        Query query = session.createQuery("from cat where catid = :id");
//
//        List<Cat> result = query.list();
//        return result;

        session.beginTransaction();
        List<Cat> arrayList = new ArrayList<Cat>(session.createQuery("FROM HibernateCat WHERE Owner.Id" +
                " = " + id).getResultList());
        session.getTransaction().commit();
        return  arrayList;


//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Cat> cq = cb.createQuery(Cat.class);
//        Root<HibernateCat> rootEntr   y = cq.from(HibernateCat.class);
//
//        CriteriaQuery<Cat> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("ownerid"), id));
//        TypedQuery<Cat> allQuery = session.createQuery(all);
//
//        List<Cat> cats = allQuery.getResultList();
//        session.getTransaction().commit();
//        return cats;
    }
}
