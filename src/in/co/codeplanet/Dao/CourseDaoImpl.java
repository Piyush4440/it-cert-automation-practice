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

import in.co.codeplanet.Model.CourseDetails;
import in.co.codeplanet.Output.Output;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Output addCourse(CourseDetails courseDetails) {
		// TODO Auto-generated method stub
		Output addCourseData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Courses(?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "add_course");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, Integer.parseInt(courseDetails.getUser_Id()));
			callableSt.setString(4, courseDetails.getCourse_Name());
			callableSt.setString(5, courseDetails.getCourse_Description());
			callableSt.setString(6, courseDetails.getCourse_Fee());
			callableSt.setString(7, courseDetails.getCourse_Duration());
			callableSt.setInt(8, Integer.parseInt(courseDetails.getIs_Active()));
			callableSt.setInt(9, Integer.parseInt(courseDetails.getIs_Certification()));
			
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
						addCourseData.setStatus(false);
						addCourseData.setError(dataList);
					}
				}
			}	
			else
			{	
				data.put("Message", "Course ADD Successfull...");
				dataList.add(data);
				addCourseData.setData(dataList);
				addCourseData.setStatus(true);
		    }	
				
			}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			addCourseData.setError(dataList);
			addCourseData.setStatus(false);
			
			e.printStackTrace();
		}
		return addCourseData;
	}

	@Override
	public Output deleteCourse(CourseDetails courseDetails) {
		Output deleteCourseData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Courses(?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "delete_course");
			callableSt.setInt(2, Integer.parseInt(courseDetails.getCourse_Id()));
			callableSt.setInt(3, Integer.parseInt(courseDetails.getUser_Id()));
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setString(7, null);
			callableSt.setInt(8, 0);
			callableSt.setInt(9, 0);
			
			boolean b = callableSt.execute();
			if(b==true)
			{
				ResultSet rs=callableSt.getResultSet();
				if(rs.next())
				{
					int status =rs.getInt("status");
					if(status==-1)
					{
						errors.put("Message", "Entered CourseId not exist");
						dataList.add(errors);
						deleteCourseData.setStatus(false);
						deleteCourseData.setError(dataList);
					}
					else if(status==-3)
					{
						errors.put("Message", "Entered Course is already deleted");
						dataList.add(errors);
						deleteCourseData.setStatus(false);
						deleteCourseData.setError(dataList);
					}
					else if(status==-2)
					{
						errors.put("Message", "Entered UserId not exist");
						dataList.add(errors);
						deleteCourseData.setStatus(false);
						deleteCourseData.setError(dataList);
					}
				}
			}
			else
			{
					data.put("Message", "Course Deleted Successfully.....");
					dataList.add(data);
					deleteCourseData.setData(dataList);
					deleteCourseData.setStatus(true);
			}	
			
		}	
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			deleteCourseData.setError(dataList);
			deleteCourseData.setStatus(false);
			
			e.printStackTrace();
		}
		return deleteCourseData;

	}

	@Override
	public Output editCourse(CourseDetails courseDetails) {
		Output editCourseData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		final String procedureCall = "{call User_Courses(?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "edit_course");
			callableSt.setInt(2, Integer.parseInt(courseDetails.getCourse_Id()));
			callableSt.setInt(3, Integer.parseInt(courseDetails.getUser_Id()));
			callableSt.setString(4, courseDetails.getCourse_Name());
			callableSt.setString(5, courseDetails.getCourse_Description());
			callableSt.setString(6, courseDetails.getCourse_Fee());
			callableSt.setString(7, courseDetails.getCourse_Duration());
			callableSt.setInt(8, Integer.parseInt(courseDetails.getIs_Active()));
			callableSt.setInt(9, Integer.parseInt(courseDetails.getIs_Certification()));
			
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
						editCourseData.setStatus(false);
						editCourseData.setError(dataList);
					}
					else if(status==-2)
					{
						errors.put("Message", "Entered CourseId not exist");
						dataList.add(errors);
						editCourseData.setStatus(false);
						editCourseData.setError(dataList);
					}
				}
			}
			else
			{
				data.put("Message", "Course Edit Successfull...");
				dataList.add(data);
				editCourseData.setData(dataList);
				editCourseData.setStatus(true);
			}
		}	
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			editCourseData.setError(dataList);
			editCourseData.setStatus(false);
			
			e.printStackTrace();
		}
		return editCourseData;

	}

	@Override
	public Output getCourse(CourseDetails courseDetails) {
		Output getCourseData = new Output();
		Dictionary<Object, Object> errors = new Hashtable<Object, Object>();
		Dictionary<Object, Object> data = new Hashtable<Object, Object>();
		List<Object> dataList = new ArrayList<Object>();
		List<CourseDetails> l=new ArrayList<CourseDetails>();
		
		final String procedureCall = "{call User_Courses(?,?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		try
		{
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "get_course");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, Integer.parseInt(courseDetails.getUser_Id()));
			callableSt.setString(4, null);
			callableSt.setString(5, null);
			callableSt.setString(6, null);
			callableSt.setString(7, null);
			callableSt.setInt(8, 0);
			callableSt.setInt(9, 0);
			
			ResultSet rs = callableSt.executeQuery();
			int i=0;
			while(rs.next())
			{
				if(i==0 && rs.getInt("status")==-1)
				{
					i=-1;
					errors.put("Message", "Entered UserId not exists");
					dataList.add(errors);
					getCourseData.setStatus(false);
					getCourseData.setError(dataList);
					break;
				}
				i++;
				CourseDetails c= new CourseDetails();
				c.setCourse_Id(String.valueOf(rs.getInt("Course_Id")));
				c.setCourse_Name(rs.getString("Course_Name"));
				c.setCourse_Description(rs.getString("Course_Description"));
				c.setCourse_Duration(rs.getString("Course_Duration"));
				c.setCourse_Fee(rs.getString("Course_Fee"));
				c.setIs_Certification(String.valueOf(rs.getInt("Is_Certification_Course")));
				c.setIs_Active(String.valueOf(rs.getInt("Is_Active")));
				l.add(c);
			}
			if(i==0)
			{	
				data.put("Message", "EMPTY!! Their Is No Course Available");
				dataList.add(data);
				getCourseData.setData(dataList);
				getCourseData.setStatus(true);
			}			
			else if(i>0)
			{	    
				data.put("Message", "Course GET Successfully...");
				dataList.add(data);
				dataList.add(l);
				getCourseData.setData(dataList);
				getCourseData.setStatus(true);
			
    		}
		}
		catch(Exception e)
		{
			errors.put("Message", "Error in Connecting to Database");
			dataList.add(errors);
			getCourseData.setError(dataList);
			getCourseData.setStatus(false);
			
			e.printStackTrace();
		}
		return getCourseData;

	}
}


