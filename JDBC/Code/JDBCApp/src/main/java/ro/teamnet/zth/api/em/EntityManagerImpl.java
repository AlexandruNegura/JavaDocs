package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ro.teamnet.zth.api.em.EntityUtils.*;

/**
 * Created by Alexandru.Negura on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {

    @Override
    public <T> T findById(Class<T> entityClass, Long id) throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();

        String tableName = getTableName(entityClass);
        List<ColumnInfo> columns = getColumns(entityClass);

        ColumnInfo columnInfo = getColumnUtil(columns);

        Condition condition = new Condition();
        condition.setColumnName(columnInfo.getDbColumnName());
        condition.setValue(id);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder
                .setQueryType(QueryType.SELECT)
                .setTableName(tableName)
                .addCondition(condition)
                .addQueryColumns(columns);

        String querry = queryBuilder.createQuery();

        ResultSet resultSet = connection.prepareStatement(querry).executeQuery();

        try {
            T t = entityClass.newInstance();
            if (resultSet.next()) {
                for (ColumnInfo columnInfo1 : columns) {
                    try {
                        Field f = t.getClass().getDeclaredField(columnInfo1.getColumnName());
                        f.setAccessible(true);
                        f.set(t, castFromSqlType(resultSet.getObject(columnInfo1.getDbColumnName()), columnInfo.getColumnType()));
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
                return t;
            } else {
                return null;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) throws SQLException, ClassNotFoundException {
        Connection connection = DBManager.getConnection();
        String querry = "SELECT max(" + columnIdName + ") + 1 FROM " + tableName;

        ResultSet resultSet = connection.prepareStatement(querry).executeQuery();

        if(resultSet.next()){
            return resultSet.getLong(1);
        } else {
            return Long.MIN_VALUE;
        }
    }

    @Override
    public <T> Object insert(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Connection connection = DBManager.getConnection();

        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());

        Long idToBeInserted = null;

        for (ColumnInfo columnInfo : columns) {
            if (columnInfo.isId()) {
                idToBeInserted = getNextIdVal(tableName, columnInfo.getDbColumnName());
                columnInfo.setValue(idToBeInserted);
            } else {
                Field f = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                f.setAccessible(true);
                columnInfo.setValue(f.get(entity));
            }
        }

        QueryBuilder queryBuilder = new QueryBuilder();
        String querry = queryBuilder
                .setQueryType(QueryType.INSERT)
                .setTableName(tableName)
                .addQueryColumns(columns)
                .createQuery();

        connection.createStatement().executeUpdate(querry);

        T returnedEntity = null;
        if (idToBeInserted != null) {
            return findById(entity.getClass(), idToBeInserted);
        }
        return returnedEntity;
    }

    // TODO : Nu merge sa bagi date in SQL, nu stiu cum e formatul. CSF N-AI CSF
    public <T> void insertAll(List<T> entities) throws SQLException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Connection connection = DBManager.getConnection();
//        connection.setAutoCommit(false);

        for(T entity : entities) {
            String tableName = getTableName(entity.getClass());
            List<ColumnInfo> columns = getColumns(entity.getClass());

            Long idToBeInserted = null;

            for (ColumnInfo columnInfo : columns) {
                if (columnInfo.isId()) {
                    String querry = "SELECT max(" + columnInfo.getDbColumnName() + ") + 1 FROM " + tableName;

                    ResultSet resultSet = connection.prepareStatement(querry).executeQuery();

                    if(resultSet.next()){
                        idToBeInserted =  resultSet.getLong(1);
                    } else {
                        throw new IndexOutOfBoundsException();
                    }

                    columnInfo.setValue(idToBeInserted);
                } else {
                    Field f = entity.getClass().getDeclaredField(columnInfo.getColumnName());
                    f.setAccessible(true);
                    columnInfo.setValue(f.get(entity));
                }
            }

            QueryBuilder queryBuilder = new QueryBuilder();
            String querry = queryBuilder
                    .setQueryType(QueryType.INSERT)
                    .setTableName(tableName)
                    .addQueryColumns(columns)
                    .createQuery();

            connection.createStatement().executeUpdate(querry);
        }

        connection.close();
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection connection = DBManager.getConnection();

        String tableName = getTableName(entityClass);
        List<ColumnInfo> columns = getColumns(entityClass);

        QueryBuilder queryBuilder = new QueryBuilder();
        String queryy = queryBuilder.setTableName(tableName)
                                    .setQueryType(QueryType.SELECT)
                                    .addQueryColumns(columns)
                                    .createQuery();

        ResultSet resultSet = connection.prepareStatement(queryy).executeQuery();

        List<T> returnList = new ArrayList<>();

        while(resultSet.next()){
            T t = entityClass.newInstance();

            for(ColumnInfo columnInfo : columns){
                Field f = entityClass.getDeclaredField(columnInfo.getColumnName());
                f.setAccessible(true);
                f.set(t, castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()), columnInfo.getColumnType()));
            }

            returnList.add(t);
        }

        return returnList;
    }

    @Override
    public <T> T update(T entity) throws SQLException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Connection connection = DBManager.getConnection();

        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());
        Condition condition = new Condition();

        fillColumns(columns, condition, entity);

        QueryBuilder queryBuilder = new QueryBuilder();
        String querry = queryBuilder.setQueryType(QueryType.UPDATE)
                                    .setTableName(tableName)
                                    .addQueryColumns(columns)
                                    .addCondition(condition)
                                    .createQuery();

        connection.prepareStatement(querry).executeUpdate();

        return entity;
    }

    @Override
    public void delete(Object entity) throws SQLException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException {
        Connection connection = DBManager.getConnection();

        String tableName = getTableName(entity.getClass());
        List<ColumnInfo> columns = getColumns(entity.getClass());
        Condition condition = new Condition();

        fillColumns(columns, condition, entity);

        QueryBuilder queryBuilder = new QueryBuilder();
        String querry = queryBuilder.setQueryType(QueryType.DELETE)
                .setTableName(tableName)
                .addQueryColumns(columns)
                .addCondition(condition)
                .createQuery();

        connection.prepareStatement(querry).executeUpdate();
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws SQLException,
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Connection connection = DBManager.getConnection();

        String tableName = getTableName(entityClass);
        List<ColumnInfo> columns = getColumns(entityClass);

        List<T> returnList = new ArrayList<>();

        QueryBuilder queryBuilder = new QueryBuilder();
        for(Map.Entry<String, Object> e : params.entrySet()){
            Condition condition1 = new Condition();
            condition1.setColumnName(e.getKey());
            condition1.setValue(e.getValue());

            queryBuilder.addCondition(condition1);
        }
        String querry = queryBuilder.setQueryType(QueryType.SELECT)
                .setTableName(tableName)
                .addQueryColumns(columns)
                .createQuery();

        ResultSet resultSet = connection.prepareStatement(querry).executeQuery();

        while(resultSet.next()){
            T t = entityClass.newInstance();
            for(ColumnInfo columnInfo : columns){
                Field field = t.getClass().getDeclaredField(columnInfo.getColumnName());
                field.setAccessible(true);
                field.set(t, castFromSqlType(resultSet.getObject(columnInfo.getDbColumnName()), columnInfo.getColumnType()));
            }

            returnList.add(t);
        }

        return returnList;
    }

    private void fillColumns(List<ColumnInfo> columns, Condition condition, Object entity) throws NoSuchFieldException, IllegalAccessException, SQLException, ClassNotFoundException {
        ColumnInfo idColumn = getColumnUtil(columns);
        Field idField = entity.getClass().getDeclaredField(idColumn.getColumnName());
        idField.setAccessible(true);

        Object dbEntity = findById(entity.getClass(), (Long) idField.get(entity));

        condition.setValue(idField.get(entity));
        condition.setColumnName(idColumn.getDbColumnName());

        for(ColumnInfo columnInfo : columns){
            Field field = entity.getClass().getDeclaredField(columnInfo.getColumnName());
            field.setAccessible(true);

            if(field.get(entity) != null){
                columnInfo.setValue(field.get(entity));
            } else if(field.get(dbEntity) != null) {
                columnInfo.setValue(field.get(dbEntity));
            } else{
                columnInfo.setValue("");
            }
        }
    }


    private ColumnInfo getColumnUtil(List<ColumnInfo> columns) {
        for (ColumnInfo columnInfo : columns) {
            if (columnInfo.isId()) {
                return columnInfo;
            }
        }

        return null;
    }
}
