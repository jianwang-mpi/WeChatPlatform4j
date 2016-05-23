package Utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangjian on 16-5-20.
 */
public class DataBaseOperation {
    final static String driverName = "com.mysql.jdbc.Driver"; //驱动名称
    static final String DBUser = "7b3c4fc0ad1e432c83befab9ba1a0ee0"; //mysql用户名
    static final String DBPasswd = "3b05da2171d54f108cc97fa35618a21e"; //mysql密码
    static final String DBName = "gLqNBKGAHFWicVlogCWf"; //数据库名
    static final String MySQLServer = "sqld.duapp.com"; //数据库名
    static final String MySQLServerPort = "4050"; //数据库名
    public static List<Map<String,Object>> queryDB(String sql){
        Connection conn=null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Map<String,Object>> resultList = new ArrayList<Map<String, Object>>();
        String url = "jdbc:mysql://sqld.duapp.com:4050/gLqNBKGAHFWicVlogCWf";
        try{
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,DBUser,DBPasswd);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while(resultSet.next()){
                Map<String,Object> column = new HashMap<String, Object>();
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    column.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
                }
                if(column.size()!=0)
                    resultList.add(column);
            }
            preparedStatement.close();
            conn.close();
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
    }
    public static void updateDB(String sql){
        Connection conn=null;
        PreparedStatement preparedStatement = null;
        String url = "jdbc:mysql://"+MySQLServer+":"+MySQLServerPort+"/" + DBName;
        try{
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,DBUser,DBPasswd);
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                preparedStatement.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void insertDB(String sql){
        updateDB(sql);
    }
    public static void deleteDB(String sql){
        updateDB(sql);
    }
}
