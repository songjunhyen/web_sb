package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.LikePoint;

@Mapper
public interface LikePointDao {

    @Select("""
            SELECT *
                FROM likePoint
                WHERE relTypeCode = #{relTypeCode}
                AND relId = #{relId}
            """)
    public LikePoint getLikePoint(String relTypeCode, int relId);

    @Select("""
            SELECT IFNULL(SUM(`point`), 0) `likePoint`
                FROM likePoint
                WHERE relTypeCode = #{relTypeCode}
                AND relId = #{relId}
            """)
    public int getTotalCnt(String relTypeCode, int relId);

    @Insert("""
            INSERT INTO likePoint (userId, relTypeCode, relId, `point`)
                VALUES (#{userId}, #{relTypeCode}, #{relId}, 1)
            """)
    public void insertLikePoint(int userId, String relTypeCode, int relId);
    
    @Update("""
            UPDATE likePoint
            SET `point` = 1
            WHERE userId = #{userId}
              AND relTypeCode = #{relTypeCode}
              AND relId = #{relId}
            """)
	public void updateLikePoint(int userId, String relTypeCode, int relId);

    @Delete("""
            DELETE FROM likePoint
                WHERE userId = #{userId}
                AND relTypeCode = #{relTypeCode}
                AND relId = #{relId}
            """)
    public void deleteLikePoint(int userId, String relTypeCode, int relId);

    @Select("""    		
    		SELECT COUNT(*) 
    			FROM likePoint 
    			WHERE userId = #{loginMemberId} 
    				AND relTypeCode = #{relTypeCode} 
    				AND relId = #{relId}    		
    		""")
	public int haveLike(int loginMemberId, String relTypeCode, int relId);



}