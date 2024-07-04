package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.LikePointDao;
import com.example.demo.vo.LikePoint;

@Service
public class LikePointService {

	private LikePointDao LikePointDao;

	public LikePointService(LikePointDao LikePointDao) {
		this.LikePointDao = LikePointDao;
	}

	public int getLikePoint(String relTypeCode, int relId) {
		return LikePointDao.getTotalCnt(relTypeCode, relId);
	}

	public void insertOrUpdateLikePoint(int userId, String relTypeCode, int relId) {
		boolean have = haveLike(userId, relTypeCode, relId);
        if (!have) {
            LikePointDao.insertLikePoint(userId, relTypeCode, relId);
        } else {
            LikePointDao.updateLikePoint(userId, relTypeCode, relId);
        }
    }
	
	public void deleteLikePoint(int loginedMemberId, String relTypeCode, int relId) {
		this.LikePointDao.deleteLikePoint(loginedMemberId, relTypeCode, relId);
	}

	public LikePoint haveLikePoint(String relTypeCode, int relId) {
		return LikePointDao.getLikePoint(relTypeCode, relId);
	}

	public boolean haveLike(int loginMemberId, String relTypeCode, int relId) {
		  int count = LikePointDao.haveLike(loginMemberId, relTypeCode, relId);
		return count > 0;
	}
}