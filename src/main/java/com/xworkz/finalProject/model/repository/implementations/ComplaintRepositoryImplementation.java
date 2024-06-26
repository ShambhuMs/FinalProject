package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.ComplaintDTO;
import com.xworkz.finalProject.dto.ProfileDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ComplaintRepositoryImplementation implements ComplaintRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public boolean saveComplaintDetails(ComplaintDTO complaintDTO) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(complaintDTO);
            entityTransaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public List<ComplaintDTO> findByUserId(int userId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findComplaintDTOByUserId");
            query.setParameter("userId", userId);
            List<ComplaintDTO> list=   query.getResultList();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<ComplaintDTO> findById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findComplaintDTOById");
            query.setParameter("id", id);
            Object object=query.getSingleResult();
            ComplaintDTO complaintDTO=(ComplaintDTO) object;
            return Optional.ofNullable(complaintDTO);
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public boolean updateComplaint(ComplaintDTO complaintDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(complaintDTO);
            entityTransaction.commit();
            return true;
        }catch (Exception e){
            System.out.println(e);
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
        return false;
    }
}

