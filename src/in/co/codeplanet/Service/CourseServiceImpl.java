package in.co.codeplanet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.codeplanet.Dao.CourseDao;
import in.co.codeplanet.Model.CourseDetails;
import in.co.codeplanet.Output.Output;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDao courseDao;

	@Override
	public Output addCourse(CourseDetails courseDetails) {
		// TODO Auto-generated method stub
		return courseDao.addCourse(courseDetails);
	}

	@Override
	public Output deleteCourse(CourseDetails courseDetails) {
		// TODO Auto-generated method stub
		return courseDao.deleteCourse(courseDetails);
	}

	@Override
	public Output editCourse(CourseDetails courseDetails) {
		// TODO Auto-generated method stub
		return courseDao.editCourse(courseDetails);
	}

	@Override
	public Output getCourse(CourseDetails courseDetails) {
		// TODO Auto-generated method stub
		return courseDao.getCourse(courseDetails);
	}

}
