package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

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
}
