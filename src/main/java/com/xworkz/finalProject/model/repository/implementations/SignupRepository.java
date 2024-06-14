package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.SignUpRepo;
import org.hibernate.query.internal.QueryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class SignupRepository implements SignUpRepo {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(SignupDTO signupDTO) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(signupDTO);
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
    public Optional<SignupDTO> findByEmail(String email) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
         Query query= entityManager.createNamedQuery("findByEmail");
         query.setParameter("email",email);
        Object object=  query.getSingleResult();
         SignupDTO signupDTO=(SignupDTO) object;
         return Optional.ofNullable(signupDTO);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SignupDTO> findByPhoneNumber(long phoneNumber) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createNamedQuery("findByPhoneNumber");
            query.setParameter("phoneNumber",phoneNumber);
            Object object=  query.getSingleResult();
            SignupDTO signupDTO=(SignupDTO) object;
            return Optional.ofNullable(signupDTO);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SignupDTO> findByEmailAndPassword(String email, String password) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createNamedQuery("findByEmailAndPassword");
            query.setParameter("email",email);
            query.setParameter("password",password);
            Object object=  query.getSingleResult();
            SignupDTO signupDTO=(SignupDTO) object;
            return Optional.ofNullable(signupDTO);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(SignupDTO signupDTO) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(signupDTO);
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
