package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.SignupDTO;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepoImplementation implements AdminRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public Optional<AdminDTO> findByEmailAndPassword(String email, String password) {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query= entityManager.createNamedQuery("findByAdminEmailAndPassword");
            query.setParameter("email",email);
            query.setParameter("password",password);
            Object object=  query.getSingleResult();
            AdminDTO adminDTO=(AdminDTO) object;
             return Optional.ofNullable(adminDTO);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<SignupDTO> fetchAllClientRecords() {
        EntityManager entityManager= entityManagerFactory.createEntityManager();
        try {
            Query query=entityManager.createQuery("select signup from SignupDTO signup");
            List<SignupDTO> list=query.getResultList();
            return list;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }
}
