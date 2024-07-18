package com.xworkz.finalProject.model.repository.implementations;


import com.xworkz.finalProject.dto.ComplaintDTO;
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
import java.util.Collections;
import java.util.List;
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

    @Override
    public List<DepartmentDTO> fetchAllDepartments() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
          Query query=  entityManager.createNamedQuery("fetchAllDepartments");
          List<DepartmentDTO> list= query.getResultList();
          return list;
        }catch (Exception e){
            System.out.println(e);
        }finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<EmployeeDTO> findByEmployeeEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByEmployeeEmail");
            query.setParameter("email", email);
            Object object = query.getSingleResult();
            EmployeeDTO employeeDTO = (EmployeeDTO) object;
            return Optional.ofNullable(employeeDTO);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<EmployeeDTO> findByEmployeePhoneNumber(long phoneNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByEmployeePhoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            Object object = query.getSingleResult();
            EmployeeDTO employeeDTO = (EmployeeDTO) object;
            return Optional.ofNullable(employeeDTO);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartmentId(int departmentId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findEmployeeByDepartmentId");
            query.setParameter("departmentId", departmentId);
            List<EmployeeDTO> employeeDTOS=query.getResultList();
            return employeeDTOS;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDTO> fetchByComplaintType(String complaintType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByComplaintType");
            query.setParameter("complaintType", complaintType);
            List<ComplaintDTO> complaintDTOList=query.getResultList();
            return complaintDTOList;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDTO> getComplaintsByDepartmentId(int departmentId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findComplaintsByDepartmentId");
            query.setParameter("departmentId", departmentId);
            List<ComplaintDTO> complaintDTOList=query.getResultList();
            return complaintDTOList;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }
}
