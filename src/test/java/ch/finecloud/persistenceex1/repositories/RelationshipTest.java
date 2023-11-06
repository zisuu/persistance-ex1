package ch.finecloud.persistenceex1.repositories;

import ch.finecloud.persistenceex1.TestPersistenceEx1Application;
import ch.finecloud.persistenceex1.entities.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestPersistenceEx1Application.class)
@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RelationshipTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void createEmployeeWithRelationships() {
        Employee bigBoss = new Employee();
        bigBoss.setName("Big Boss");
        bigBoss.setSalary(100000L);

        // Immer den Rückgabewert von save() weiterverwenden!
        bigBoss = employeeRepository.saveAndFlush(bigBoss);

        Project project = new Project();
        project.setName("Bookstore");
        project = projectRepository.saveAndFlush(project);

        Department department = new Department();
        department.setName("IT");
        department = departmentRepository.saveAndFlush(department);

        Address address = new Address();
        address.setStreet("Bahnhofstrasse 42");
        address.setZip("8000");
        address.setCity("Zürich");
        address.setState("ZH");

        Employee peterMuster = new Employee();
        peterMuster.setName("Peter Muster");
        peterMuster.setSalary(80000L);

        peterMuster.setDepartment(department);
        peterMuster.setAddress(address);

        peterMuster.setBoss(bigBoss);
        // Rückbeziehung auch setzen beim Erzeugen
        bigBoss.addDirect(peterMuster);

        // Beide Seiten der Beziehung abfüllen
        peterMuster.getProjects().add(project);
        project.getEmployees().add(peterMuster);

        // Immer den Rückgabewert von save() weiterverwenden!
        peterMuster = employeeRepository.saveAndFlush(peterMuster);

        Phone phone = new Phone();
        phone.setType(PhoneType.MOBILE);
        phone.setNumber("079 123 45 67");

        // Convenience Methode verwenden die auch die Rückbeziehung setzt
        peterMuster.addPhone(phone);

        // Immer den Rückgabewert von save() weiterverwenden!
        peterMuster = employeeRepository.saveAndFlush(peterMuster);

        em.clear();

        Employee peterMusterFromDb = em.find(Employee.class, peterMuster.getId());

        assertThat(peterMusterFromDb.getId()).isNotNull();
        assertThat(peterMusterFromDb.getBoss()).isNotNull();
        assertThat(peterMusterFromDb.getBoss().getId()).isEqualTo(bigBoss.getId());
        assertThat(peterMusterFromDb.getAddress()).isNotNull();
        assertThat(peterMusterFromDb.getAddress().getId()).isNotNull();
    }

    // test if address is deleted if employee is deleted
    @Test
    public void deleteEmployeeWithRelationships() {
        Employee bigBoss = new Employee();
        bigBoss.setName("Big Boss");
        bigBoss.setSalary(100000L);

        // Immer den Rückgabewert von save() weiterverwenden!
        bigBoss = employeeRepository.saveAndFlush(bigBoss);

        Project project = new Project();
        project.setName("Bookstore");
        project = projectRepository.saveAndFlush(project);

        Department department = new Department();
        department.setName("IT");
        department = departmentRepository.saveAndFlush(department);

        Address address = new Address();
        address.setStreet("Bahnhofstrasse 42");
        address.setZip("8000");
        address.setCity("Zürich");
        address.setState("ZH");

        Employee peterMuster = new Employee();
        peterMuster.setName("Peter Muster");
        peterMuster.setSalary(80000L);

        peterMuster.setDepartment(department);
        peterMuster.setAddress(address);

        peterMuster.setBoss(bigBoss);
        // Rückbeziehung auch setzen beim Erzeugen
        bigBoss.addDirect(peterMuster);

        // Beide Seiten der Beziehung abfüllen
        peterMuster.getProjects().add(project);
        project.getEmployees().add(peterMuster);

        // Immer den Rückgabewert von save() weiterverwenden!
        peterMuster = employeeRepository.saveAndFlush(peterMuster);

        Phone phone = new Phone();
        phone.setType(PhoneType.MOBILE);
        phone.setNumber("079 123 45 67");

        // Convenience Methode verwenden die auch die Rückbeziehung setzt
        peterMuster.addPhone(phone);

        // Immer den Rückgabewert von save() weiterverwenden!
        peterMuster = employeeRepository.saveAndFlush(peterMuster);

        em.clear();

        Employee peterMusterFromDb = em.find(Employee.class, peterMuster.getId());

        assertThat(peterMusterFromDb.getId()).isNotNull();
        assertThat(peterMusterFromDb.getBoss()).isNotNull();
        assertThat(peterMusterFromDb.getBoss().getId()).isEqualTo(bigBoss.getId());
        assertThat(peterMusterFromDb.getAddress()).isNotNull();
        assertThat(peterMusterFromDb.getAddress().getId()).isNotNull();


        project.setEmployees(null);
        projectRepository.saveAndFlush(project);
        employeeRepository.delete(peterMusterFromDb);
        em.flush();
        em.clear();

        Employee peterMusterFromDbAfterDelete = em.find(Employee.class, peterMuster.getId());
        assertThat(peterMusterFromDbAfterDelete).isNull();
        Address addressFromDbAfterDelete = em.find(Address.class, address.getId());
        assertThat(addressFromDbAfterDelete).isNull();

    }

}
