package in.co.codeplanet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.co.codeplanet.Model.CourseDetails;
import in.co.codeplanet.Output.Output;
import in.co.codeplanet.Service.CourseService;

@RestController
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@PostMapping(value = "add-course")
	public Output addCourse(@RequestBody CourseDetails courseDetails) {
		
		Output addCourseData = courseService.addCourse(courseDetails);
		return addCourseData;
	}
	
	@DeleteMapping(value = "delete-course")
	public Output deleteCourse(@RequestBody CourseDetails courseDetails) {
		
		Output deleteCourseData = courseService.deleteCourse(courseDetails);
		
		return deleteCourseData;
	}
	@PutMapping(value = "edit-course")
	public Output editCourse(@RequestBody CourseDetails courseDetails) {
		
		Output editCourseData = courseService.editCourse(courseDetails);
		
		return editCourseData;
	}
	@GetMapping(value = "get-course")
	public Output getCourse(@RequestBody CourseDetails courseDetails) {
		
		Output getCourseData = courseService.getCourse(courseDetails);
		
		return getCourseData;
	}
}
