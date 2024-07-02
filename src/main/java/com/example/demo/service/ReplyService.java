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

    public List<Reply> getReply(int loginedMemberId, String relTypeCode, int relId) {
        return replyDao.getReply(loginedMemberId, relTypeCode, relId);
    }

    public void deleteReply(int loginedMemberId, String relTypeCode, int relId) {
        replyDao.deleteReply(loginedMemberId, relTypeCode, relId);
    }

    public int getLastInsertId() {
        return replyDao.getLastInsertId();
    }

    public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body) {
        replyDao.writeReply(loginedMemberId, relTypeCode, relId, body);
    }
}