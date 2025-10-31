/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import entity.Dept;
import entity.Emp;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
@Stateless
public class empdeptBean implements empdeptBeanLocal {

    @PersistenceContext(unitName = "my_empdept_unit")
    EntityManager em;

    @Override
    public void addDepartment(String name, String location) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Dept d = new Dept();
        d.setDeptName(name);
        d.setLocation(location);
        em.persist(d);
    }

    @Override
    public List<Dept> getAllDepartment() {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createQuery("SELECT d FROM Dept d", Dept.class).getResultList();
    }

    @Override
    public void updateDepartment(String name, String location, int id) {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Dept d = em.find(Dept.class, id);
        if (d != null) {
            d.setDeptName(name);
            d.setLocation(location);
            em.merge(d);
        }

    }

    @Override
    public void deleteDepartment(int id, String name, String location) {
        //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Dept d = em.find(Dept.class, id);
        if (d != null) {
            em.remove(d);
        }
    }

    @Override
    public void addEmployee(String ename, double salary, Dept deptid, Date dateofjoining) {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Emp e = new Emp();
        e.setEname(ename);
        e.setSalary(salary);
        e.setDeptid(deptid);
        e.setDateofjoining(dateofjoining);
    }

    @Override
    public List<Emp> getAllEmployees() {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return em.createQuery("SELECT e FROM Emp e", Emp.class).getResultList();
    }

    @Override
    public void updateEmployee(int empno, String ename, double salary, Dept deptid, Date dateofjoining) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Emp e = em.find(Emp.class, empno);
        if (e != null) {
            e.setDateofjoining(dateofjoining);
            e.setEname(ename);
            e.setSalary(salary);
            e.setDeptid(deptid);
            em.merge(e);
        }
    }

    @Override
    public void deleteEmployee(int empno, String ename, double salary, Dept deptid, Date dateofjoining) {
        //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Emp e = em.find(Emp.class, empno);
        if (e != null) {
            em.remove(e);
        }
    }

}
