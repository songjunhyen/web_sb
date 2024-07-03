package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {

    @Select("""
    		SELECT * FROM reply 
			WHERE relTypeCode = #{relTypeCode}
			  AND relId = #{relId}
    		""")
	public
    List<Reply> getReply(String relTypeCode, int relId);

    @Insert("""
    		INSERT INTO reply (regDate, updateDate, userId, relTypeCode, relId, body, writer) 
				VALUES (NOW(), NOW(), #{userId}, #{relTypeCode}, #{relId}, #{body}, #{writer})
    		""")
	public void writeReply(Reply reply);

    @Delete("""
    		DELETE FROM reply 
			WHERE userId = #{loginedMemberId} 
			  AND relTypeCode = #{relTypeCode} 
			  AND relId = #{relId}
    		""")
	public void deleteReply(int loginedMemberId, String relTypeCode, int relId);

    @Select("""
    		SELECT userId 
				FROM reply 
				WHERE id = #{replyId}    		
    		""")
	public int getReplyWriterid(int replyId);

}