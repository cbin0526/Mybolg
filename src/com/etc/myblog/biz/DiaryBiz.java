package com.etc.myblog.biz;

import java.util.List;

import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.Diary;

public interface DiaryBiz {

	//获取当前用户的日记
	List<Diary> getDiaryByUserId(Integer userid);
	
	//给当前用户添加日记
	boolean addDiaryByLoginUser(Diary diary);
	
	//删除当前用户日记
	boolean removeDiaryByLoginUser(Integer diaryid);
	
	//修改当前用户的一篇日记
	boolean updateDiaryByLoginUser(Integer diary_id,String diary_context);

}
