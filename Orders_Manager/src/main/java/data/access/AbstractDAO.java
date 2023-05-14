package data.access;

import connection.ConnectionFactory;
import model.*;

import java.beans.*;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

/**
 * This class implements methods for queries on database using reflection techniques.
 *
 * @param <T> it is an object which defines the class type which is used for querying database.
 */
public class AbstractDAO<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Retrieves all the objects of a SQL table.
     *
     * @return A list of objects.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
            ConnectionFactory.closeConnection(connection);
        }

        return null;
    }

    /**
     * Retrieves an object from database.
     *
     * @param id ID of the object to find.
     * @return The instance of the object.
     */
    public T findById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            List<T> list = createObjects(resultSet);
            if (list.isEmpty()) {
                return null;
            }

            return list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeResultSet(resultSet);
            ConnectionFactory.closeConnection(connection);
        }

        return null;
    }

    /**
     * Inserts a new object in database.
     *
     * @param t The object to be inserted.
     */
    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            StringBuilder fieldsString = new StringBuilder("(");
            StringBuilder valuesString = new StringBuilder("('");
            Field[] fields = type.getDeclaredFields();

            for (int i = 0; i < fields.length - 1; i++) {
                fields[i].setAccessible(true);
                fieldsString.append(fields[i].getName()).append(", ");
                valuesString.append(fields[i].get(t)).append("', '");
            }
            fields[fields.length - 1].setAccessible(true);
            fieldsString.append(fields[fields.length - 1].getName()).append(")");
            valuesString.append(fields[fields.length - 1].get(t)).append("')");

            String query = "INSERT INTO " +
                    type.getSimpleName() +
                    fieldsString +
                    " VALUES " +
                    valuesString;
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Updates an object in database.
     *
     * @param t The object to be updated.
     */
    public void update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        long id = -1;
        try {
            connection = ConnectionFactory.getConnection();
            Field[] fields = type.getDeclaredFields();
            StringBuilder updateString = new StringBuilder();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("id"))
                    id = (long) field.get(t);
            }

            for (int i = 0; i < fields.length - 1; i++) {
                fields[i].setAccessible(true);
                updateString.append(fields[i].getName()).append(" = '").append(fields[i].get(t)).append("', ");
            }
            updateString.append(fields[fields.length - 1].getName()).append(" = '").append(fields[fields.length - 1].get(t)).append("'");
            String query = "UPDATE " +
                    type.getSimpleName() +
                    " SET " +
                    updateString +
                    " WHERE id = ?";

            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Deletes an object from database.
     *
     * @param id The ID of the object to be deleted.
     */
    public void delete(long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = "DELETE FROM " +
                type.getSimpleName() +
                " WHERE id = ?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
        }
    }

    /**
     * Selects the max ID from a SQL table. It is used for model validators.
     *
     * @param flag It is used for selecting the desired class.
     * @return The max ID from the selected SQL table.
     */
    public static long selectMaxId(int flag) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Class<?> cls = null;
        switch (flag) {
            case 0 -> cls = Bill.class;
            case 1 -> cls = ClientM.class;
            case 2 -> cls = OrderM.class;
            case 3 -> cls = Product.class;
        }

        String query = "SELECT MAX(id) " +
                " FROM " +
                Objects.requireNonNull(cls).getSimpleName();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeStatement(statement);
            ConnectionFactory.closeConnection(connection);
            ConnectionFactory.closeResultSet(resultSet);
        }

        return -1;
    }

    /**
     * Creates a list of objects received from database.
     *
     * @param resultSet Contains the objects gathered from database.
     * @return A list of objects.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> objects = new ArrayList<>();
        Constructor<T> constructor = null;

        try {
            constructor = type.getConstructor();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()) {
                Objects.requireNonNull(constructor).setAccessible(true);
                T instance = constructor.newInstance();

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method setMethod = propertyDescriptor.getWriteMethod();
                    setMethod.invoke(instance, value);
                }

                objects.add(instance);
            }
        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 IntrospectionException e) {
            e.printStackTrace();
        }

        return objects;
    }

    /**
     * Creates a SQL select query.
     *
     * @return A string containing the SQL query.
     */
    private String createSelectQuery() {

        return "SELECT " +
                " * " +
                " FROM " +
                type.getSimpleName() +
                " WHERE id = ?";
    }
}
