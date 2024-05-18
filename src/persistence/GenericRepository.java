package persistence;

import service.Audit;
import service.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GenericRepository<T> {
    DBConnection dbConnection = DBConnection.getInstance();

    public void add(T entity);
    public T get(int id) throws SQLException;
    public ArrayList<T> getAll();
    public void update(T entity);
    public void delete(T entity);

    default int retrievLastId(String index){
        String selectQuery = "SELECT last_number FROM user_sequences WHERE SEQUENCE_NAME = ?";
        String result = null;
        try{
            PreparedStatement preparedStatement = dbConnection.getContext().prepareStatement(selectQuery);
            preparedStatement.setString(1,index);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                result = resultSet.getString("last_number");
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return Integer.parseInt(result) -1;
    }
}
