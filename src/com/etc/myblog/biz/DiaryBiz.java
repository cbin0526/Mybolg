package com.etc.myblog.biz;

import java.util.List;

import com.etc.myblog.entity.Blog;
import com.etc.myblog.entity.Diary;

public interface DiaryBiz {

	//��ȡ��ǰ�û����ռ�
	List<Diary> getDiaryByUserId(Integer userid);
	
	//����ǰ�û�����ռ�
	boolean addDiaryByLoginUser(Diary diary);
	
	//ɾ����ǰ�û��ռ�
	boolean removeDiaryByLoginUser(Integer diaryid);
	
	//�޸ĵ�ǰ�û���һƪ�ռ�
	boolean updateDiaryByLoginUser(Integer diary_id,String diary_context);

}
