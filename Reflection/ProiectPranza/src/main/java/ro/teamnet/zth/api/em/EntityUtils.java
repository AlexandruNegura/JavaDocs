package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandru.Negura on 7/12/2017.
 */
public class EntityUtils {
    private EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        if (entity.getAnnotation(Table.class) != null) {
            return ((Table) entity.getAnnotation(Table.class)).name();
        } else {
            return entity.getName();
        }
    }

    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> columnInfos = new ArrayList<>();

        Field[] fields = entity.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Column.class) != null || field.getAnnotation(Id.class) != null) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo.setColumnName(field.getName());
                columnInfo.setColumnType(field.getType());
                columnInfo.setDbColumnName(getTableName(entity));

                if (field.getAnnotation(Id.class) != null) {
                    columnInfo.setId(true);
                } else {
                    columnInfo.setId(false);
                }

                columnInfos.add(columnInfo);
            }
        }

        return columnInfos;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value instanceof BigDecimal && wantedType.getName().equals("java.lang.Integer")) {
            return ((BigDecimal) value).intValue();
        } else if (value instanceof BigDecimal && wantedType.getName().equals("java.lang.Long")) {
            return ((BigDecimal) value).longValue();
        } else if (value instanceof BigDecimal && wantedType.getName().equals("java.lang.Float")) {
            return ((BigDecimal) value).floatValue();
        } else if (value instanceof BigDecimal && wantedType.getName().equals("java.lang.Double")) {
            return ((BigDecimal) value).doubleValue();
        } else if (! (value instanceof BigDecimal)) {
            return value;
        } else {
            return value;
        }
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation){
        List<Field> returnFields = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(field.getAnnotation(annotation) != null){
                returnFields.add(field);
            }
        }

        return returnFields;
    }

    public static Object getSqlValue(Object object){
        if(object.getClass().getAnnotation(Table.class) != null){
            Field[] fields = object.getClass().getFields();

            for(Field field : fields){
                if(field.getAnnotation(Id.class) != null){
                    field.setAccessible(true);
                    return field;
                }
            }
        }

        return object;
    }
}
