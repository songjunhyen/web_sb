package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {

    @Select("SELECT * FROM reply WHERE userId = #{loginedMemberId} AND relTypeCode = #{relTypeCode} AND relId = #{relId}")
	public
    List<Reply> getReply(int loginedMemberId, String relTypeCode, int relId);

    @Insert("INSERT INTO reply (regDate, updateDate, userId, relTypeCode, relId, body) VALUES (NOW(), NOW(), #{loginedMemberId}, #{relTypeCode}, #{relId}, #{body})")
	public void writeReply(int loginedMemberId, String relTypeCode, int relId, String body);

    @Delete("DELETE FROM reply WHERE userId = #{loginedMemberId} AND relTypeCode = #{relTypeCode} AND relId = #{relId}")
	public void deleteReply(int loginedMemberId, String relTypeCode, int relId);

    @Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();
}