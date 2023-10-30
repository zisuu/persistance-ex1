//package ch.finecloud.persistenceex1.services;
//
//import ch.finecloud.persistenceex1.entities.Employee;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class EmployeeServiceJpaTest {
//
//    @Autowired
//    private EmployeeServiceJpa employeeServiceJpa;
//
//    @Test
//    void testCreateEmplyee() {
//        Employee employee = employeeServiceJpa.createEmployee(1, "John Doe", 1000);
//        Employee foundEmployee = employeeServiceJpa.getEmployee(employee.getId()).get();
//        assertEquals("John Doe", foundEmployee.getName());
//        assertEquals(1000, foundEmployee.getSalary());
//    }
//}