package com.xworkz.finalProject.model.repository.implementations;

import com.xworkz.finalProject.dto.*;
import com.xworkz.finalProject.model.repository.interfaces.AdminRepository;
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
public class AdminRepoImplementation implements AdminRepository {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<AdminDTO> findByEmailAndPassword(String email, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByAdminEmailAndPassword");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Object object = query.getSingleResult();
            AdminDTO adminDTO = (AdminDTO) object;
            return Optional.ofNullable(adminDTO);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<SignupDTO> fetchAllClientRecords() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("select signup from SignupDTO signup");
            List<SignupDTO> list = query.getResultList();
            return list;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDTO> fetchAllCompliant() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getAllComplaintDetails");
            List<ComplaintDTO> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDTO> fetchByComplaintTypeOrCity(String complaintType, String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getAllComplaintDetailsByTypeOrCity");
            query.setParameter("complaintType", complaintType);
            query.setParameter("city", city);
            List<ComplaintDTO> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDTO> getAllComplaintDetailsByTypeAndCity(String complaintType, String city) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("getAllComplaintDetailsByTypeAndCity");
            query.setParameter("complaintType", complaintType);
            query.setParameter("city", city);
            List<ComplaintDTO> list = query.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<DepartmentDTO> findByDepartmentType(String departmentType) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByDepartmentType");
            query.setParameter("departmentType", departmentType);
            Object object = query.getSingleResult();
            DepartmentDTO departmentDTO = (DepartmentDTO) object;
            return Optional.ofNullable(departmentDTO);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public boolean updateStatus(long id, String status) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createNamedQuery("statusUpdate");
            query.setParameter("status", status);
            query.setParameter("id", id);
            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public Optional<DepartmentAdminDTO> fetchByDepAdminPhone(long phoneNumber) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByDepartmentAdminPhoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
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
    public Optional<DepartmentAdminDTO> fetchByDepAdminEmail(String email) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("findByDepartmentAdminEmail");
            query.setParameter("email", email);
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
    public boolean addDepartmentAdminDTO(DepartmentAdminDTO departmentAdminDTO) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.merge(departmentAdminDTO);
            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        return false;
    }

    @Override
    public List<ComplaintDTO> findUnread() {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createNamedQuery("AdminRead");
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
    @Override
    public void markNotificationAsRead(long id) {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction= entityManager.getTransaction();
        try {
            entityTransaction.begin();
            entityManager.createNamedQuery("MarkAsRead")
                    .setParameter("complaintId", id)
                    .executeUpdate();
            entityTransaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            entityTransaction.rollback();
        }finally {
            entityManager.close();
        }
    }
}
