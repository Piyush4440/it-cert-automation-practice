package in.co.codeplanet.Service;

import in.co.codeplanet.Model.CourseDetails;
import in.co.codeplanet.Output.Output;

public interface CourseService {

	Output addCourse(CourseDetails courseDetails);

	Output deleteCourse(CourseDetails courseDetails);

	Output editCourse(CourseDetails courseDetails);

	Output getCourse(CourseDetails courseDetails);

}
