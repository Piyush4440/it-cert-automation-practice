package in.co.codeplanet.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.co.codeplanet.Model.LectureDetails;
import in.co.codeplanet.Output.Output;
import in.co.codeplanet.Service.LectureService;

@RestController
public class LectureController {
	@Autowired
	LectureService lectureService;
	
	@PostMapping(value="add-lecture")
	public Output addLecture(@RequestBody LectureDetails lectureDetails)
	{	
		Output addLectureData=lectureService.addLecture(lectureDetails);
		return addLectureData;
	}

	@PutMapping(value="edit-lecture")
	public Output editLecture(@RequestBody LectureDetails lectureDetails)
	{	
		Output editLectureData=lectureService.editLecture(lectureDetails);
		return editLectureData;
	}
	@DeleteMapping(value="delete-lecture")
	public Output deleteLecture(@RequestBody LectureDetails lectureDetails)
	{	
		Output deleteLectureData=lectureService.deleteLecture(lectureDetails);
		return deleteLectureData;
	}
	@GetMapping(value="get-lecture")
	public Output getLectures(@RequestBody LectureDetails lectureDetails)
	{	
		Output getLectureData=lectureService.getLectures(lectureDetails);
		return getLectureData;
	}
	
}
