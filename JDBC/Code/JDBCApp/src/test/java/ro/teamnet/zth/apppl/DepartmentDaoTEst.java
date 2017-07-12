package ro.teamnet.zth.apppl;

import org.junit.Test;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static ro.teamnet.zth.api.em.EntityUtils.getTableName;

/**
 * Created by Alexandru.Negura on 7/14/2017.
 */
public class DepartmentDaoTEst {

    @Test
    public void testfindById() throws SQLException, ClassNotFoundException {
        DepartmentDao departmentDao = new DepartmentDao();
        Department dep = departmentDao.findById(20L);

        assertEquals("Should print Rome", dep.getDepartmentName(), "Marketing");
    }

    @Test
    public void testgetNextIdVal() throws SQLException, ClassNotFoundException {
        DepartmentDao departmentDao = new DepartmentDao();
        Long id = departmentDao.getNextIdVal("location_id");

        assertEquals("Should be 9091", new Long(9091), id);
    }

    @Test
    public void testInsert() throws SQLException, ClassNotFoundException {
        DepartmentDao departmentDao = new DepartmentDao();

        Department department = new Department();
        department.setDepartmentName("asdaasdaaa");
        department.setId(1231L);
        department.setLocation(9090L);

        Department returnedDep = departmentDao.insert(department);

        assertEquals("Should be equal", returnedDep.getDepartmentName(), department.getDepartmentName());
    }

    @Test
    public void testUpdate() throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException,
            InstantiationException {
        DepartmentDao departmentDao = new DepartmentDao();

        String modifyDepartment = "MUSCEL";

        Department department = new Department();
        department.setDepartmentName(modifyDepartment);
        department.setId(10L);

        departmentDao.update(department);


        Department returnedLocation = departmentDao.findById(10L);

        assertEquals("Should be the same", modifyDepartment, returnedLocation.getDepartmentName());
    }
}
