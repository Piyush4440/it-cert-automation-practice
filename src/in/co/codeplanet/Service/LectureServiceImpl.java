package in.co.codeplanet.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.codeplanet.Dao.LectureDao;
import in.co.codeplanet.Model.LectureDetails;
import in.co.codeplanet.Output.Output;

@Service
public class LectureServiceImpl implements LectureService{
 
	@Autowired
	LectureDao lectureDao;

	@Override
	public Output addLecture(LectureDetails lectureDetails) {
		// TODO Auto-generated method stub
		return lectureDao.addLecture(lectureDetails);
	}

	@Override
	public Output editLecture(LectureDetails lectureDetails) {
		// TODO Auto-generated method stub
		return lectureDao.editLecture(lectureDetails);
	}

	@Override
	public Output deleteLecture(LectureDetails lectureDetails) {
		// TODO Auto-generated method stub
		return lectureDao.deleteLecture(lectureDetails);
	}

	@Override
	public Output getLectures(LectureDetails lectureDetails) {
		// TODO Auto-generated method stub
		return lectureDao.getLectures(lectureDetails);
	}

	
}
