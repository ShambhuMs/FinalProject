package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.model.repository.interfaces.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepoImplementation implements ProfileRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public boolean saveProfileDetails(ProfileDTO profileDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(profileDTO);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
           e.printStackTrace();
           entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public Optional<ProfileDTO> findByUserId(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByUserId");
            query.setParameter("userId", userId);
            return query.getResultList().stream().findFirst();
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public boolean updateProfileDetails(ProfileDTO profileDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(profileDTO);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }

        return false;
    }

    @Override
    public boolean updateStatus(  ProfileDTO profileDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
           /*Query query=  entityManager.createNamedQuery("updateStatus");
            query.setParameter("id",userId);*/
            entityManager.merge(profileDTO);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public List<ProfileDTO> findDatasById(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findDatasByUserId");
            query.setParameter("userId", userId);
            List<ProfileDTO> list=   query.getResultList();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            entityManager.close();
        }
        return Collections.emptyList();
    }


}
