package com.etc.myblog.dao;
import java.util.List;

import com.etc.myblog.entity.Diary;
public interface DiaryDao {
	
	
	// �����û�id��ѯ�ռǱ�ķ���
	List<Diary> queryAllDiary(Integer userid);
	
	//����һ���ռ�
	boolean addDiary(Diary diary);
	
	//ɾ��һ���ռ�
	boolean removeDiary(Integer diary_id);
	
	//�޸�һ���ռǵ�����
	boolean updateDiaty(Integer diary_id,String diary_content);
}
