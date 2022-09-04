package com.etc.myblog.dao.impl;

import java.util.List;

import com.etc.myblog.dao.DiaryDao;
import com.etc.myblog.entity.Diary;
import com.etc.myblog.util.DBUtil;

public class DiaryDaoImpl implements DiaryDao {

	@Override
	public List<Diary> queryAllDiary(Integer userid) {
		
		String sql = "SELECT t_diary.diary_id,t_diary.diary_content,t_diary.diary_time,t_diary.diary_author FROM t_diary where t_diary.diary_author = ? ORDER BY t_diary.diary_time DESC";
		return DBUtil.exQuery(sql, Diary.class, userid);
	}

	@Override
	public boolean addDiary(Diary diary) {
		
		String sql = "INSERT INTO `myblog`.`t_diary` (`diary_content`, `diary_time`, `diary_author`) VALUES (?,?,?)";
		int flag = DBUtil.exUpdate(sql, diary.getDiary_content(),diary.getDiary_time(),diary.getDiary_author());
		return flag > 0;
	}
	
	@Override
	public boolean removeDiary(Integer diary_id) {
		
		String sql = "DELETE FROM t_diary WHERE t_diary.diary_id = ?";
		int flag = DBUtil.exUpdate(sql, diary_id);
		return flag > 0;
	}
	@Override
	public boolean updateDiaty(Integer diary_id, String diary_content) {
		String sql = "UPDATE `myblog`.`t_diary` SET  `diary_content`=? WHERE (`diary_id`=?)";
		int flag = DBUtil.exUpdate(sql, diary_content,diary_id);
		return flag>0;
	}
}
