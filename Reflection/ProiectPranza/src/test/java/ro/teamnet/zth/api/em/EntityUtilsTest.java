package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Job;
import ro.teamnet.zth.appl.domain.Location;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexandru.Negura on 7/12/2017.
 */
public class EntityUtilsTest {
    @Test
    public void testGetTableNameMethod() {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);

        String columnName = EntityUtils.getTableName(Location.class);
        assertEquals("Table name should be locations!", "locations", columnName);
    }

    @Test
    public void testGetColumnsMethod() {
        int nrOfColumnsAndIds = EntityUtils.getColumns(Department.class).size();
        int nrOfColumnsAndIdsJob = EntityUtils.getColumns(Job.class).size();

        assertEquals("Result should be 2", 3, nrOfColumnsAndIds);
        assertEquals("Result should be 2", 4, nrOfColumnsAndIdsJob);
    }

    @Test
    public void testCastMethod() {
        Object x = EntityUtils.castFromSqlType(new BigDecimal(20), Integer.class);

        boolean test = false;

        if (x instanceof Integer) {
            test = true;
        }

        assertTrue("Should be true", test);

        Integer value = ((Integer) x);
        assertEquals("Should be 20", new Integer(20), value);
    }
}

// TODO: TO BE CONTINUED