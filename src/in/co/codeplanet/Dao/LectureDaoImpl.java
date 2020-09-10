package in.co.codeplanet.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.co.codeplanet.Model.LectureDetails;
import in.co.codeplanet.Output.Output;

@Repository
public class LectureDaoImpl implements LectureDao{

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Output addLecture(LectureDetails lectureDetails) {
		Output addLectureData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Lecture(?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "add_lecture");
			callableSt.setString(2, lectureDetails.getLecture_Name());
			callableSt.setString(3, lectureDetails.getLecture_Description());
			callableSt.setInt(4, 0);
			callableSt.setInt(5, Integer.parseInt(lectureDetails.getUser_Id()));
			callableSt.setInt(6, Integer.parseInt(lectureDetails.getCourse_Id()));
			
			boolean b = callableSt.execute();
			if(b==true)
			{
				ResultSet rs=callableSt.getResultSet();
				if(rs.next())
				{
					int status =rs.getInt("status");
					if(status==-1)
					{
						errors.put("Message", "Entered UserId not exist");
						dataList.add(errors);
						addLectureData.setStatus(false);
						addLectureData.setError(dataList);
					}
					if(status==-2)
					{
						errors.put("Message", "Entered CourseId not exist");
						dataList.add(errors);
						addLectureData.setStatus(false);
						addLectureData.setError(dataList);
					}
				}
			}	
			else
			{	
				data.put("Message", "Lecture ADD Successfull...");
				dataList.add(data);
				addLectureData.setData(dataList);
				addLectureData.setStatus(true);
		    }	
		}	
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			addLectureData.setError(dataList);
			addLectureData.setStatus(false);
			
			e.printStackTrace();
		}
		return addLectureData;
  
	}

	@Override
	public Output editLecture(LectureDetails lectureDetails) {
		Output editLectureData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Lecture(?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "edit_lecture");
			callableSt.setString(2, lectureDetails.getLecture_Name());
			callableSt.setString(3, lectureDetails.getLecture_Description());
			callableSt.setInt(4, Integer.parseInt(lectureDetails.getLecture_Id()));
			callableSt.setInt(5, Integer.parseInt(lectureDetails.getUser_Id()));
			callableSt.setInt(6, 0);
			
			boolean b = callableSt.execute();
			if(b==true)
			{
				ResultSet rs=callableSt.getResultSet();
				if(rs.next())
				{
					int status =rs.getInt("status");
					if(status==-1)
					{
						errors.put("Message", "Entered UserId not exist");
						dataList.add(errors);
						editLectureData.setStatus(false);
						editLectureData.setError(dataList);
					}
					else if(status==-2)
					{
						errors.put("Message", "Entered LectueId not exist");
						dataList.add(errors);
						editLectureData.setStatus(false);
						editLectureData.setError(dataList);
					}
				}
			}
			else
			{
				data.put("Message", "Lecture Edit Successfull...");
				dataList.add(data);
				editLectureData.setData(dataList);
				editLectureData.setStatus(true);
			}
			
		}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			editLectureData.setError(dataList);
			editLectureData.setStatus(false);
			
			e.printStackTrace();
		}
		return editLectureData;


	}

	@Override
	public Output deleteLecture(LectureDetails lectureDetails) {
		Output deleteLectureData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Lecture(?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "delete_lecture");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, Integer.parseInt(lectureDetails.getLecture_Id()));
			callableSt.setInt(5, Integer.parseInt(lectureDetails.getUser_Id()));
			callableSt.setInt(6, Integer.parseInt(lectureDetails.getCourse_Id()));
			
			boolean b = callableSt.execute();
			
			if(b==true)
			{
				ResultSet rs=callableSt.getResultSet();
				if(rs.next())
				{
					int status =rs.getInt("status");
					if(status==-2)
					{
						errors.put("Message", "Entered CourseId not exist");
						dataList.add(errors);
						deleteLectureData.setStatus(false);
						deleteLectureData.setError(dataList);
					}
					else if(status==-3)
					{
						errors.put("Message", "Entered LectureId not exist");
						dataList.add(errors);
						deleteLectureData.setStatus(false);
						deleteLectureData.setError(dataList);
					}
					else if(status==-1)
					{
						errors.put("Message", "Entered UserId not exist");
						dataList.add(errors);
						deleteLectureData.setStatus(false);
						deleteLectureData.setError(dataList);
					}
				}
			}
			else
			{
				int count=callableSt.getUpdateCount();
				if(count==1)
				{
					data.put("Message", "Lecture DELETE Successfull...");
					dataList.add(data);
					deleteLectureData.setData(dataList);
					deleteLectureData.setStatus(true);
				}
				else {
					errors.put("Message", "Entered Lecture not exist in entered Course");
					dataList.add(data);
					deleteLectureData.setError(dataList);
					deleteLectureData.setStatus(false);
		    	
				}
			}
			}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			deleteLectureData.setError(dataList);
			deleteLectureData.setStatus(false);
			
			e.printStackTrace();
		}
		return deleteLectureData;


	}

	@Override
	public Output getLectures(LectureDetails lectureDetails) {
		Output getLectureData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		List<LectureDetails> l=new ArrayList<LectureDetails>();
		
		final String procedureCall = "{call User_Lecture(?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "get_lecture");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, Integer.parseInt(lectureDetails.getCourse_Id()));
			
		    ResultSet rs = callableSt.executeQuery();
		    int i=0;
			while(rs.next())
			{
				
				if(i==0 && rs.getInt("status")==-1)
				{
					i=-1;
					errors.put("Message", "Entered CourseId not exists");
					dataList.add(errors);
					getLectureData.setStatus(false);
					getLectureData.setError(dataList);
					break;
				}
				i++;
				LectureDetails ld= new LectureDetails();
				ld.setLecture_Id(String.valueOf(rs.getInt("Lecture_Id")));
				ld.setLecture_Name(rs.getString("Lecture_Name"));
				ld.setLecture_Description(rs.getString("Lecture_Description"));
				ld.setUser_Id(lectureDetails.getUser_Id());
				ld.setCourse_Id(lectureDetails.getCourse_Id());
				
				l.add(ld);
			
			}
			if(i==0)
			{
				data.put("Message", "EMPTY!! Their Is No Lecture Available");
				dataList.add(data);
				getLectureData.setData(dataList);
				getLectureData.setStatus(true);
			}
			
			else if(i>0)
			{
			    data.put("Message", "Lecture GET Successfully...");
			    dataList.add(data);
			    dataList.add(l);
			    getLectureData.setData(dataList);
			    getLectureData.setStatus(true);
			}
			
		}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			getLectureData.setError(dataList);
			getLectureData.setStatus(false);
			
			e.printStackTrace();
		}
		return getLectureData;

	}

}
