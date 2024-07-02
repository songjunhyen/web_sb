package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.LikePoint;

@Mapper
public interface LikePointDao {

    @Select("""
            SELECT *
                FROM likePoint
                WHERE userId = #{userId}
                AND relTypeCode = #{relTypeCode}
                AND relId = #{relId}
            """)
    public LikePoint getLikePoint(int userId, String relTypeCode, int relId);

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

    @Delete("""
            DELETE FROM likePoint
                WHERE userId = #{userId}
                AND relTypeCode = #{relTypeCode}
                AND relId = #{relId}
            """)
    public void deleteLikePoint(int userId, String relTypeCode, int relId);
}