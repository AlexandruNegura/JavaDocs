package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexandru.Negura on 7/14/2017.
 */
public class DepartmentDao {
    private EntityManager entityManager = new EntityManagerImpl();

    public Department findById(Long id){
        try {
            return entityManager.findById(Department.class, id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Long getNextIdVal(String columnIdName){
        try {
            return entityManager.getNextIdVal(EntityUtils.getTableName(Department.class), columnIdName);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Department insert(Department entity){
        try {
            return ((Department) entityManager.insert(entity));
        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return  null;
    }

    public  List<Department> findAll(Class<Department> entityClass){
        try {
            return entityManager.findAll(Department.class);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Department update(Department entity){
        try {
            return entityManager.update(entity);
        } catch (SQLException | ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void delete(Department entity){
        try {
            entityManager.delete(entity);
        } catch (SQLException | ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
