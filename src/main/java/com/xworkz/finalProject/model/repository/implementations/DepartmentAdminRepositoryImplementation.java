package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.AdminDTO;
import com.xworkz.finalProject.dto.DepartmentAdminDTO;
import com.xworkz.finalProject.dto.DepartmentDTO;
import com.xworkz.finalProject.dto.EmployeeDTO;
import com.xworkz.finalProject.model.repository.interfaces.DepartmentAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class DepartmentAdminRepositoryImplementation implements DepartmentAdminRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<DepartmentAdminDTO> findByAdminEmailAndPassword(String email, String password) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                Query query = entityManager.createNamedQuery("findByDepartmentAdminEmailAndPassword");
                query.setParameter("email", email);
                query.setParameter("password", password);
                Object object = query.getSingleResult();
                DepartmentAdminDTO departmentAdminDTO = (DepartmentAdminDTO) object;
                return Optional.ofNullable(departmentAdminDTO);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                entityManager.close();
            }
            return Optional.empty();
        }

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.persist(employeeDTO);
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
