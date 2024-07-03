package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.vo.Reply;
import com.example.demo.dao.ReplyDao;

@Service
public class ReplyService {
    private final ReplyDao replyDao;

    public ReplyService(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public List<Reply> getReply(String relTypeCode, int relId) {
        return replyDao.getReply(relTypeCode, relId);
    }

    public void deleteReply(int loginedMemberId, String relTypeCode, int relId) {
        replyDao.deleteReply(loginedMemberId, relTypeCode, relId);
    }

    public void writeReply(Reply reply) {
        replyDao.writeReply(reply);
    }

	public int getReplyWriterid(int replyId) {
		return replyDao.getReplyWriterid(replyId);
	}
}