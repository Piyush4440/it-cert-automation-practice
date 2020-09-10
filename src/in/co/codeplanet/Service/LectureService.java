package in.co.codeplanet.Service;

import in.co.codeplanet.Model.LectureDetails;
import in.co.codeplanet.Output.Output;

public interface LectureService {

	Output addLecture(LectureDetails lectureDetails);

	Output editLecture(LectureDetails lectureDetails);

	Output deleteLecture(LectureDetails lectureDetails);

	Output getLectures(LectureDetails lectureDetails);

}
