package com.team04.technikon.repository.impl;

import com.team04.technikon.model.Property;
import com.team04.technikon.model.PropertyOwner;
import com.team04.technikon.repository.PropertyOwnerRepository;
import com.team04.technikon.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class PropertyOwnerRepositoryImpl extends RepositoryImpl<PropertyOwner> implements PropertyOwnerRepository {

    private static final Logger logger = LogManager.getLogger(PropertyOwnerRepositoryImpl.class);
    private final Properties sqlCommands = new Properties();

    {
        final ClassLoader loader = getClass().getClassLoader();
        try ( InputStream config = loader.getResourceAsStream("sql.properties")) {
            sqlCommands.load(config);
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getClassName() {
        return PropertyOwner.class.getName();
    }

    @Override
    public PropertyOwner search(int id) {
        return JpaUtil.getEntityManager().find(PropertyOwner.class, id);
    }

    @Override
    public PropertyOwner search(String email) {
        return JpaUtil.getEntityManager().find(PropertyOwner.class, email);
    }

    @Override
    public void updateAddress(int id, String address) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        try {
            propertyOwner.setAddress(address);
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(propertyOwner);
            transaction.commit();
            logger.info("The owner's address has been updated. Owne's VAT : {}.", propertyOwner.getVat());
        } catch (Exception e) {
            logger.warn("Can't be upadated", e);
        }
    }

    @Override
    public void updateEmail(int id, String email) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        propertyOwner.setEmail(email);
        try {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(propertyOwner);
            transaction.commit();
            logger.info("The owner's email has been updated. Owne's VAT : {}.", propertyOwner.getVat());
        } catch (Exception e) {
            logger.warn("Can't be upadated", e);
        }
    }

    @Override
    public void updatePassword(int id, String password) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        try {
            propertyOwner.setPassword(password);
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(propertyOwner);
            transaction.commit();
            logger.info("The owner's password has been updated. Owne's VAT : {}.", propertyOwner.getVat());
        } catch (Exception e) {
            logger.warn("Can't be upadated", e);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        List<Property> propertyList = entityManager.createQuery("select p from property p where p.owner.id=:ownerId", Property.class)
                .setParameter("ownerId", id).getResultList();
        try {
            for (Property property : propertyList) {
                entityManager.getTransaction().begin();
                entityManager.find(Property.class, property.getId());
                entityManager.remove(property);
                entityManager.getTransaction().commit();
            }
            PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
            entityManager.getTransaction().begin();
            entityManager.remove(propertyOwner);
            entityManager.getTransaction().commit();
            logger.info("Deletion completed successfully");
        } catch (Exception e) {
            logger.warn("Can't be deleted", e);
        }
        return true;
    }

    @Override
    public List<PropertyOwner> read(String ownerName) {
        return entityManager.createQuery(sqlCommands.getProperty("select.owner.byName"), PropertyOwner.class).setParameter("name", ownerName).getResultList();
    }

    @Override
    public List<PropertyOwner> readAll() {
        List<PropertyOwner> results = JpaUtil.getEntityManager().createQuery(sqlCommands.getProperty("select.owners"))
                .getResultList();
        return results;
    }

    @Override
    @Transactional
    public void createPropertyOwner(PropertyOwner propertyOwner) {
        entityManager.persist(propertyOwner);
    }

    @Override
    @Transactional
    public PropertyOwner findById(int id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);

        return propertyOwner;
    }

    @Override
    @Transactional
    public PropertyOwner findByVat(int vat) {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("SELECT p from propertyowner p where p.vat =:vat", PropertyOwner.class);
        query.setParameter("vat", vat);
        return query.getSingleResult();
    }

    @Override
    public PropertyOwner findByEmail(String email) {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("SELECT p FROM propertyowner p WHERE p.email = :email", PropertyOwner.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public boolean deleteOwner(int id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner == null) {
            return false;
        }
        entityManager.remove(propertyOwner);
        return true;
    }

    @Override
    public Class<PropertyOwner> findClass() {
        return PropertyOwner.class;
    }

    @Override
    public void updateFields(PropertyOwner initialValue, PropertyOwner finalValue) {
        finalValue.setName(initialValue.getName());
        finalValue.setAddress(initialValue.getAddress());
        finalValue.setEmail(initialValue.getEmail());
        finalValue.setId(initialValue.getId());
        finalValue.setPassword(initialValue.getPassword());
        finalValue.setPhoneNumber(initialValue.getPhoneNumber());
        finalValue.setProperties(initialValue.getProperties());
        finalValue.setSurname(initialValue.getSurname());
        finalValue.setUsername(initialValue.getUsername());
        finalValue.setVat(initialValue.getVat());
    }
    
    @Override
    public List<PropertyOwner> findAll() {
        return entityManager.createQuery("select p from propertyowner p").getResultList();
    }

    @Override
    public List<PropertyOwner> findUsernames(String username) {
        return entityManager.createQuery("SELECT p FROM propertyowner p WHERE p.username = :username", PropertyOwner.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public String checkRole(String username, String password) {
        try {
            return entityManager.createQuery("SELECT p.role from propertyowner p where username=:u1 and password=:u2")
                    .setParameter("u1", username)
                    .setParameter("u2", password)
                    .getSingleResult()
                    .toString();
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public PropertyOwner findByUNameAndPassword(String username, String password) {
        return entityManager.createQuery("select u from propertyowner u where username=:u1 and password=:u2", PropertyOwner.class)
                .setParameter("u1", username)
                .setParameter("u2", password)
                .getSingleResult();
    }

}
