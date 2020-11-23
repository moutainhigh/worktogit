package com.lwxf.industry4.webapp.common.utils;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * JDBC操作数据库
 * 作者: 强乐
 * 时间：2017年5月4日 上午8:14:40
 */
public class DbUtil {
	
	private boolean isShowSql=false;//是否显示执行的sql
	private boolean isShowException=false;//是否显示执行的异常
	private Connection connection=null;//数据库连接
	private boolean isDefaultConfig=true;//是否外部获取连接
	private String driverClassName="";
	private String url="";
	private String userName="";
	private String password="";
	
	
	public DbUtil(){}
	public DbUtil(String driverClassName,String url,String userName,String password){
		isDefaultConfig=false;
		this.driverClassName=driverClassName;
		this.url=url;
		this.userName=userName;
		this.password=password;
	}
	
	/**
	 * 打开连接
	 */
	public boolean openConnection(){
		boolean isSuccess=false;
		try{
			if(connection==null){
				if(isDefaultConfig){
					//connection=DataAccess.getConnection();
				}else{
					Class.forName(driverClassName);
					connection=DriverManager.getConnection(url,userName,password);
				}
			}
			isSuccess=true;
		}catch(Exception e){
			printException(e);
		}
		return isSuccess;
	}
	
	/**
	 * 关闭连接
	 */
	public boolean closeConnection(){
		boolean isSuccess=false;
		try{
			if(connection!=null){
				connection.close();
			}
			connection=null;
			isSuccess=true;
		}catch(Exception e){
			printException(e);
		}
		return isSuccess;
	}
	
	/**
	 * 执行SQL语句
	 */
	public boolean runSql(String sql){
		boolean isSuccess=false;
		Statement statement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			statement=connection.createStatement();
			isSuccess=statement.execute(sql);
		}catch(Exception e){
			printException(e);
		}finally{
        	if(statement!=null){
    			try {
    				statement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return isSuccess;
	}
	
	/**
	 * 执行SQL语句(防止SQL注入)
	 */
	public boolean runSql(String sql,Object... objects){
		boolean isSuccess=false;
		PreparedStatement preparedStatement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			preparedStatement=connection.prepareStatement(sql);
			setPreparedStatement(preparedStatement, objects);
			int result=preparedStatement.executeUpdate();
			if(result!=0){
				isSuccess=true;
			}
		}catch(Exception e){
			printException(e);
		}finally{
        	if(preparedStatement!=null){
    			try {
    				preparedStatement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return isSuccess;
	}
	
	/**
	 * 执行批量SQL语句
	 */
	public boolean runSql(List<String> sqls){
		boolean isSuccess=false;
		Statement statement=null;
		try{
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			connection.setAutoCommit(false);
			statement=connection.createStatement();
			if(sqls!=null && sqls.size()>0){
				for(String sql : sqls){
					printSql(sql);
					statement.addBatch(sql);
				}
				statement.executeBatch();
				connection.commit();
			}
			connection.setAutoCommit(true);
			isSuccess=true;
		}catch(Exception e){
			printException(e);
			if(connection!=null){
				try {
					connection.rollback();
					connection.setAutoCommit(true);
				} catch (Exception e1) {
					printException(e1);
				}
			}
		}finally{
        	if(statement!=null){
    			try {
    				statement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return isSuccess;
	}
	
	/**
	 * 执行批量SQL语句(防止SQL注入)
	 */
	public boolean runSql(List<String> sqls,List<Object[]> objects){
		boolean isSuccess=false;
		PreparedStatement preparedStatement=null;
		try{
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			connection.setAutoCommit(false);
			if(sqls!=null && sqls.size()>0){
				for(int i=0;i<sqls.size();i++){
					String sql=sqls.get(i);
					printSql(sql);
					preparedStatement=connection.prepareStatement(sql);
					setPreparedStatement(preparedStatement, objects.get(i));
					int result=preparedStatement.executeUpdate();
					if(result==0){
						throw new Exception("执行SQL语句出错");
					}
				}
				connection.commit();
			}
			connection.setAutoCommit(true);
			isSuccess=true;
		}catch(Exception e){
			printException(e);
			if(connection!=null){
				try {
					connection.rollback();
					connection.setAutoCommit(true);
				} catch (Exception e1) {
					printException(e1);
				}
			}
		}finally{
        	if(preparedStatement!=null){
    			try {
    				preparedStatement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return isSuccess;
	}
	
	/**
	 * 执行更新SQL语句(防止SQL注入)
	 */
	public int runUpdateSql(String sql,Object... objects){
		int successInt=0;
		PreparedStatement preparedStatement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			preparedStatement=connection.prepareStatement(sql);
			setPreparedStatement(preparedStatement, objects);
			successInt=preparedStatement.executeUpdate();
		}catch(Exception e){
			printException(e);
		}finally{
        	if(preparedStatement!=null){
    			try {
    				preparedStatement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return successInt;
	}
	
	/**
	 * 查询SQL语句
	 * @return 返回执行是否成功
	 */
	public List<RowInfo> querySql(String sql){
		List<RowInfo> returnList=null;
		ResultSet resultSet=null;
		Statement statement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            if(resultSet!=null){
				returnList=new ArrayList<RowInfo>();
				List<String> columnNames=new ArrayList<String>();
            	//获取列表类型
            	ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
				List<String> columnTypeList=new ArrayList<String>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
					columnNames.add(resultSetMetaData.getColumnLabel(i));
					columnTypeList.add(resultSetMetaData.getColumnTypeName(i));
				}
				while(resultSet.next()){
					RowInfo rowInfo=new RowInfo();
					rowInfo.addColumInfo(columnNames);
					for(int i=0;i<columnTypeList.size();i++){
						String columName=columnNames.get(i);
						String columnType=columnTypeList.get(i);
						Object columnValue=null;
						if( columnType.equals("VARCHAR") || columnType.equals("NVARCHAR") || columnType.equals("CHAR")){
							columnValue=resultSet.getString(i+1);
						}else if(columnType.equals("INT")){
							columnValue=resultSet.getInt(i+1);							
						}else if(columnType.equals("BLOB")){
							columnValue=blobToBytes(resultSet.getBlob(i+1));
						}else{
							columnValue=resultSet.getObject(i+1);
						}
						rowInfo.addColumValue(columName, columnValue);
					}
					returnList.add(rowInfo);
				}
            }
		}catch(Exception e){
			printException(e);
		}finally{
        	if(statement!=null){
    			try {
    				statement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return returnList;
	}
	
	/**
	 * 查询SQL语句(防止SQL注入)
	 * @return 返回执行是否成功
	 */
	public List<RowInfo> querySql(String sql,Object... objects){
		List<RowInfo> returnList=null;
		ResultSet resultSet=null;
		PreparedStatement preparedStatement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			preparedStatement=connection.prepareStatement(sql);
			setPreparedStatement(preparedStatement, objects);
            resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
				returnList=new ArrayList<RowInfo>();
				List<String> columnNames=new ArrayList<String>();
            	//获取列表类型
            	ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
				List<String> columnTypeList=new ArrayList<String>();
				for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
					columnNames.add(resultSetMetaData.getColumnLabel(i));
					columnTypeList.add(resultSetMetaData.getColumnTypeName(i));
				}
				while(resultSet.next()){
					RowInfo rowInfo=new RowInfo();
					rowInfo.addColumInfo(columnNames);
					for(int i=0;i<columnTypeList.size();i++){
						String columName=columnNames.get(i);
						String columnType=columnTypeList.get(i);
						Object columnValue=null;
						if( columnType.equals("VARCHAR") || columnType.equals("NVARCHAR") || columnType.equals("CHAR")){
							columnValue=resultSet.getString(i+1);
						}else if(columnType.equals("INT")){
							columnValue=resultSet.getInt(i+1);							
						}else if(columnType.equals("BLOB")){
							columnValue=blobToBytes(resultSet.getBlob(i+1));
						}else{
							columnValue=resultSet.getObject(i+1);
						}
						rowInfo.addColumValue(columName, columnValue);
					}
					returnList.add(rowInfo);
				}
            }
		}catch(Exception e){
			printException(e);
		}finally{
        	if(preparedStatement!=null){
    			try {
    				preparedStatement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return returnList;
	}
	
	/**
	 * 查询SQL语句
	 * @return 返回执行是否成功
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryTForSql(String sql){
		T t=null;
		ResultSet resultSet=null;
		Statement statement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            if(resultSet!=null){
				if(resultSet.next()){
					t=(T) resultSet.getObject(1);
				}
            }
		}catch(Exception e){
			printException(e);
		}finally{
        	if(statement!=null){
    			try {
    				statement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return t;
	}
	
	/**
	 * 查询SQL语句(防止SQL注入)
	 * @return 返回执行是否成功
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryTForSql(String sql,Object... objects){
		T t=null;
		ResultSet resultSet=null;
		PreparedStatement preparedStatement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			preparedStatement=connection.prepareStatement(sql);
			setPreparedStatement(preparedStatement, objects);
            resultSet=preparedStatement.executeQuery();
            if(resultSet!=null){
				if(resultSet.next()){
					t=(T) resultSet.getObject(1);
				}
            }
		}catch(Exception e){
			printException(e);
		}finally{
        	if(preparedStatement!=null){
    			try {
    				preparedStatement.close();
    			} catch (Exception e) {
    				printException(e);
    			}
    		}
			closeConnection();
		}
		return t;
	}
	
	/**
	 * 通过blob大字段生成byte数组信息
	 */
	private byte[] blobToBytes(Blob blob){
		BufferedInputStream bufferedInputStream = null;
		byte[] bytes = null;
		try {
			bufferedInputStream = new BufferedInputStream(blob.getBinaryStream());
			bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;

			while (offset < len && (read = bufferedInputStream.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
		} catch (Exception e){
			printException(e);
		} finally {
			try {
				bufferedInputStream.close();
			} catch (Exception e) {
				printException(e);
			}
		}
		return bytes;
	}
	
	public boolean isShowSql() {
		return isShowSql;
	}

	public void setShowSql(boolean isShowSql) {
		this.isShowSql = isShowSql;
	}

	public boolean isShowException() {
		return isShowException;
	}

	public void setShowException(boolean isShowException) {
		this.isShowException = isShowException;
	}

	private void printSql(String sql){
		if(isShowSql){
			System.out.println("SQL:"+sql!=null?sql:"null");
		}
	}
	
	private void printException(Exception e){
		if(isShowException){
			System.out.println("messger:"+e.getMessage()!=null?e.getMessage():"");
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置参数 
	 */
	private void setPreparedStatement(PreparedStatement preparedStatement,Object... objects) throws Exception{
		if(preparedStatement!=null && objects!=null && objects.length>0){
			for(int i=1;i<=objects.length;i++){
				Object obj=objects[i-1];
				if(obj instanceof Array){
					preparedStatement.setArray(i, (Array)obj);
				}if(obj instanceof BigDecimal){
					preparedStatement.setBigDecimal(i, (BigDecimal)obj);
				}else if(obj instanceof Blob){
					preparedStatement.setBlob(i, (Blob)obj);
				}else if(obj instanceof Boolean){
					preparedStatement.setBoolean(i, (Boolean) obj);
				}else if(obj instanceof Byte){
					preparedStatement.setByte(i, (Byte)obj);
				}else if(obj instanceof Byte[]){
					preparedStatement.setBytes(i, (byte[])obj);
				}else if(obj instanceof Clob){
					preparedStatement.setClob(i, (Clob)obj);
				}else if(obj instanceof Date){
					preparedStatement.setDate(i, new java.sql.Date(((Date)obj).getTime()));
				}else if(obj instanceof Double){
					preparedStatement.setDouble(i, (Double)obj);
				}else if(obj instanceof Float){
					preparedStatement.setFloat(i, (Float)obj);
				}else if(obj instanceof Integer){
					preparedStatement.setInt(i, (Integer)obj);
				}else if(obj instanceof Long){
					preparedStatement.setLong(i, (Long)obj);
				}else if(obj instanceof Short){
					preparedStatement.setShort(i, (Short)obj);
				}else if(obj instanceof String){
					preparedStatement.setString(i, (String)obj);
				}else if(obj instanceof Time){
					preparedStatement.setTime(i, (Time)obj);
				}else if(obj instanceof Timestamp){
					preparedStatement.setTimestamp(i, (Timestamp)obj);
				}else if(obj instanceof URL){
					preparedStatement.setURL(i, (URL)obj);
				}else if(obj instanceof Object){
					preparedStatement.setObject(i, obj);
				}
			}
		}
	}
	
	/**
	 * 查询SQL语句（需要手动关闭连接）
	 * @return 返回执行是否成功
	 */
	public ResultSet queryResultSetBySql(String sql){
		ResultSet resultSet=null;
		Statement statement=null;
		try{
			printSql(sql);
			if(!openConnection()){
				throw new Exception("数据库连接获取失败");
			}
			statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
		}catch(Exception e){
			printException(e);
		}finally{
        	if(statement!=null){
    			try {
    				statement.close();
    			} catch (Exception e){
    				printException(e);
    			}
    		}
		}
		return resultSet;
	}
	
	/**
	 * 取查询结果第一条数据
	 * @param sql
	 * @return
	 */
	public RowInfo queryFirstBySql(String sql){
		RowInfo rowInfo=null;
		if(sql!=null && sql.toLowerCase().indexOf("limit")<0){
			sql+="  limit 0,1 ";
		}
		List<RowInfo> list=this.querySql(sql);
		if(list!=null && list.size()>0){
			rowInfo=list.get(0);
		}
		return rowInfo;
	}
	
	/**
	 * 取查询结果第一条数据(防止SQL注入)
	 * @param sql
	 * @return
	 */
	public RowInfo queryFirstBySql(String sql,Object... objects){
		RowInfo rowInfo=null;
		if(sql!=null && sql.toLowerCase().indexOf("limit")<0){
			sql+="  limit 0,1 ";
		}
		List<RowInfo> list=this.querySql(sql,objects);
		if(list!=null && list.size()>0){
			rowInfo=list.get(0);
		}
		return rowInfo;
	}
}
