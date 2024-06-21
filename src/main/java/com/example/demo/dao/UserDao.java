package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.User;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM user WHERE userid = #{userid}")
    User getUserById(String userid);

    @Insert("INSERT INTO user (userid, userpw, nickname, regDate, updateDate) VALUES (#{userid}, #{userpw}, #{nickname}, #{regDate}, #{updateDate})")
    void Signup(User user);

    @Select("SELECT * FROM user WHERE userid = #{userid} AND userpw = #{userpw}")
    User Login(String userid, String userpw);
    
    @Insert({
        "INSERT INTO `login_user` (userid, nickname, login_code)",
        "VALUES (#{userid}, #{nickname}, #{loginCode})",
        "ON DUPLICATE KEY UPDATE",
        "    nickname = VALUES(nickname),",
        "    login_code = VALUES(login_code)"
    })
    void updateUserLoginSession(String userid, String nickname, String loginCode);

    @Update("UPDATE `login_user` SET login_code = #{loginCode} WHERE userid = #{userid}")
    void updateLoginCode(String userid, String loginCode);

    @Update("UPDATE user SET login_code = null WHERE userid = #{userid}")
    String Logout(User foundUser);

    @Update("UPDATE user SET userpw = #{userpw}, nickname = #{nickname}, updateDate = #{updateDate} WHERE userid = #{userid}")
    void Update(User foundUser);

    @Delete("DELETE FROM user WHERE userid = #{userid}")
    void Delete(User foundUser);
    

}