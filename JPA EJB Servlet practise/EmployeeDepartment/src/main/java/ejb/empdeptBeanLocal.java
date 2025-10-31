/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import entity.Dept;
import entity.Emp;
import jakarta.ejb.Local;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
@Local
public interface empdeptBeanLocal {

    //Department Operations
    void addDepartment(String name, String location);

    List<Dept> getAllDepartment();

    void updateDepartment(String name, String location, int id);

    void deleteDepartment(int id, String name, String location);

    //Employee Operations
    void addEmployee(String ename, double salary, Dept deptid, Date dateofjoining);

    List<Emp> getAllEmployees();

    void updateEmployee(int empno, String ename, double salary, Dept deptid, Date dateofjoining);

    void deleteEmployee(int empno, String ename, double salary, Dept deptid, Date dateofjoining);

}
