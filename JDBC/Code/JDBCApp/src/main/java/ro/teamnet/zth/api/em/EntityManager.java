package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Alexandru.Negura on 7/13/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id) throws SQLException, ClassNotFoundException;

    Long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException;

    <T> Object insert(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException;

    <T> List<T> findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchFieldException;

    <T> T update(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;

    void delete(Object entity) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;

    <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException,
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException;


}
