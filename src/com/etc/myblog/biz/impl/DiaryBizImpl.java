package com.etc.myblog.biz.impl;

import java.util.List;

import com.etc.myblog.biz.DiaryBiz;
import com.etc.myblog.entity.Diary;
import com.etc.myblog.dao.impl.DiaryDaoImpl;

public class DiaryBizImpl implements DiaryBiz {
	DiaryDaoImpl dao = new DiaryDaoImpl();
	
	@Override
	public List<Diary> getDiaryByUserId(Integer userid) {
		if(userid == null) {
			return null;
		}
		return dao.queryAllDiary(userid);
	}

	@Override
	public boolean addDiaryByLoginUser(Diary diary) {
		// TODO Auto-generated method stub
		if(diary == null||diary.getDiary_author() == null || diary.getDiary_content() == null|| diary.getDiary_time() == null) {
			return false;
		}
		return dao.addDiary(diary);
	}
	
	@Override
	public boolean removeDiaryByLoginUser(Integer diaryid) {
		// TODO Auto-generated method stub
		if(diaryid == null) {
			return false;
		}
		return dao.removeDiary(diaryid);
	}

	@Override
	public boolean updateDiaryByLoginUser(Integer diary_id, String diary_context) {
		if(diary_id == null | diary_context == null ) {
			return false;
		}
		return dao.updateDiaty(diary_id, diary_context);
	}
}
