package com.etc.myblog.dao;
import java.util.List;

import com.etc.myblog.entity.Diary;
public interface DiaryDao {
	
	
	// 根据用户id查询日记表的方法
	List<Diary> queryAllDiary(Integer userid);
	
	//插入一条日记
	boolean addDiary(Diary diary);
	
	//删除一条日记
	boolean removeDiary(Integer diary_id);
	
	//修改一个日记的内容
	boolean updateDiaty(Integer diary_id,String diary_content);
}
