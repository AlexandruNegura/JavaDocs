package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.em.*;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static ro.teamnet.zth.api.em.EntityUtils.getColumns;
import static ro.teamnet.zth.api.em.EntityUtils.getTableName;

/**
 * Created by Alexandru.Negura on 7/14/2017.
 */
public class EmployeeDao {

    public List<Employee> getEmployeesFromDep(String str) throws IllegalAccessException {
        try {
            Connection connection = DBManager.getConnection();

            String querry = "select employee_id, first_name, last_name, departments.department_name from employees \n" +
                    "inner join departments on employees.department_id = departments.department_id\n" +
                    "where UPPER(department_name) LIKE '%' || UPPER('" + str + "') || '%'";

            ResultSet resultSet = connection.prepareStatement(querry).executeQuery();
            List<ColumnInfo> columns = getColumns(Employee.class);


            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                System.out.println(resultSet.getString(2));
                for (ColumnInfo columnInfo : columns) {
                    Field field = employee.getClass().getDeclaredField(columnInfo.getColumnName());
                    field.set(employee, resultSet.getObject(columnInfo.getDbColumnName()));
                }

                employees.add(employee);
            }

            return employees;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

}
