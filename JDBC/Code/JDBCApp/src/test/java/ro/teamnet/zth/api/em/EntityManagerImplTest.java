package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.*;
import static ro.teamnet.zth.api.em.EntityUtils.getTableName;

/**
 * Created by Alexandru.Negura on 7/13/2017.
 */
public class EntityManagerImplTest {
    @Test
    public void testfindById() throws SQLException, ClassNotFoundException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Location location = entityManager.findById(Location.class, 3202L);

        assertEquals("Should print asda", location.getCity(), "asda");
    }

    @Test
    public void testgetNextIdVal() throws SQLException, ClassNotFoundException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Long id = entityManager.getNextIdVal(getTableName(Department.class), "location_id");

        assertEquals("Should be 9091", new Long(9091), id);
    }

    @Test
    public void testInsert() throws SQLException, ClassNotFoundException {
        EntityManagerImpl entityManager = new EntityManagerImpl();

        try {
            Location location = new Location();
            location.setCity("asda");
            location.setId(10L);
            location.setPostalCode("asda");
            location.setStateProvince("asda");
            location.setStreetAddress("asdad");


            Location object = ((Location) entityManager.insert(location));

            assertEquals("Should be equal", location.getCity(), object.getCity());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() throws SQLException, ClassNotFoundException, IllegalAccessException,
            NoSuchFieldException, InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();

        List<Location> locations = entityManager.findAll(Location.class);

        assertNotEquals("Should not be 0", 0, locations.size());
    }

    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException,
            InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();

        String modifyCity = "MUSCEL";

        Location location = new Location();
        location.setId(1800L);
        location.setCity(modifyCity);

        entityManager.update(location);

        Location returnedLocation = entityManager.findById(Location.class, 1800L);

        assertEquals("Should be the same", modifyCity, returnedLocation.getCity());
    }

    @Test
    public void testDelete() throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException,
            InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Long idToBeRemoved = entityManager.getNextIdVal(getTableName(Location.class),
				"location_id") - 1;

        Location location = new Location();
        location.setId(idToBeRemoved);

        entityManager.delete(location);

        assertNull("Should be null ", entityManager.findById(Location.class, idToBeRemoved));
    }

    @Test
    public void testParams() throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException,
            InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();

        Map<String, Object> map = new HashMap<>();
        map.put("location_id",1100);

        List<Location> locations = entityManager.findByParams(Location.class, map);

        System.out.println(locations.size());
        assertEquals("Should be only one", 1, locations.size());
    }

    @Test
    public void testInsertAll() throws SQLException, ClassNotFoundException, IllegalAccessException,
            NoSuchFieldException,
            InstantiationException {
        EntityManagerImpl entityManager = new EntityManagerImpl();

        Employee employee1 = new Employee();
        employee1.setDepartmentId(100L);
        employee1.setCommissionPct(100F);
		employee1.setEmail("asdasd");
		employee1.setEmployeeID(10000L);
		employee1.setFirstName("asdad");
		employee1.setHireDate(new Date(100));
        employee1.setJobId(1000L);
        employee1.setLastName("asda");
        employee1.setSalary(189346F);
        employee1.setPhoneNumber("2131");
        employee1.setManagerId(200L);

        Employee employee2 = new Employee();
		employee2.setDepartmentId(500L);
		employee2.setCommissionPct(100F);
		employee2.setEmail("asdasd");
		employee2.setEmployeeID(9999L);
		employee2.setFirstName("asdad");
		employee2.setHireDate(new Date(100));
		employee2.setJobId(600L);
		employee2.setLastName("asda");
		employee2.setSalary(189346F);
		employee2.setPhoneNumber("2131");
		employee2.setManagerId(1000L);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        entityManager.insertAll(employees);
    }
}
