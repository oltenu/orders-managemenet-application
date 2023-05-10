package data.access;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class AbstractDAO<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT " +
                        " * " +
                        " FROM " +
                        type.getSimpleName();

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
            ConnectionFactory.closeConnection(connection);
        }

        return null;
    }

    public T findById(long id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
            ConnectionFactory.closeConnection(connection);
        }

        return null;
    }

    public void insert(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = ConnectionFactory.getConnection();
            StringBuilder fieldsString = new StringBuilder("(");
            StringBuilder valuesString = new StringBuilder("('");
            Field[] fields = type.getDeclaredFields();

            for(int i = 0; i < fields.length - 1; i++){
                fields[i].setAccessible(true);
                fieldsString.append(fields[i].getName()).append(", ");
                valuesString.append(fields[i].get(type)).append("', '");
            }
            fieldsString.append(fields[fields.length - 1].getName()).append(")");
            valuesString.append(fields[fields.length - 1].get(type)).append("')");

            String query = "INSERT INTO " +
                        type.getSimpleName() +
                        fieldsString +
                        " VALUES " +
                        valuesString;
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        }catch (SQLException | IllegalAccessException e){
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    public void update(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        long id = -1;
        try{
            connection = ConnectionFactory.getConnection();
            Field[] fields = type.getDeclaredFields();
            StringBuilder updateString = new StringBuilder();
            for(Field field : fields){
                if(field.getName().equals("id"))
                    id = (long) field.get(type);
            }

            for(int i = 0; i < fields.length - 1; i++){
                fields[i].setAccessible(true);
                updateString.append(fields[i].getName()).append(" = '").append(fields[i].get(type)).append("', ");
            }
            updateString.append(fields[fields.length - 1].getName()).append(" = '").append(fields[fields.length - 1].get(type)).append("'");
            String query = "UPDATE " +
                        type.getSimpleName() +
                        " SET " +
                        updateString +
                        " WHERE id = ?";

            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException | IllegalAccessException e){
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    public void delete(long id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM " +
                type.getSimpleName() +
                "WHERE id = ?";

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    private List<T> createObjects(ResultSet resultSet){
        List<T> objects = new ArrayList<>();
        Constructor<T> constructor = null;

        try {
            constructor = type.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try{
            while(resultSet.next()){
                Objects.requireNonNull(constructor).setAccessible(true);
                T instance = constructor.newInstance();

                for(Field field : type.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    setMethod.invoke(instance, value);
                }

                objects.add(instance);
            }
        }catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                IntrospectionException e){
            e.printStackTrace();
        }

        return objects;
    }

    private String createSelectQuery(String field){

        return "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName() +
                " WHERE " +
                field +
                " =?";
    }


}
