package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

//ArticleDao 클래스: 
// 데이터베이스 접근을 담당하는 클래스로, 게시글에 대한 CRUD(Create, Read, Update, Delete) 기능을 구현합니다. 
// List를 사용하여 메모리 상에서 데이터를 관리하고 있습니다

@Mapper
public interface ArticleDao {
	@Select("""
			SELECT *
				FROM article
				WHERE id = #{id}
			""")
	Article getArticleById(int id);

	@Select("""
			SELECT *
				FROM article
				ORDER BY id DESC
			""")
	List<Article> getAllArticles();
	
	@Update("""
			UPDATE article
		        SET viewcount = viewcount + 1
		        WHERE id = #{id}
			""")
	void updateView(int id);
	
	@Insert("INSERT INTO article (regDate, updateDate, title, `body`, writer, boardid, viewcount) "
	        + "VALUES (#{article.regDate}, #{article.updateDate}, #{article.title}, #{article.body}, #{article.writer}, #{boardid}, #{article.viewcount})")
	void writeArticle(@Param("article") Article article, @Param("boardid") int boardid);

	@Update("""
			UPDATE article
				SET title = #{title}, `body` = #{body}, updateDate = now()
				WHERE writer = #{writer}
			""")
	void updateArticle(String writer, String title, String body);

	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	void deleteArticle(Article article);

	@Select("""
			SELECT writer
				FROM article
				WHERE id = #{id}
			""")
	String getWriterById(int id);

	@Select("""
			   SELECT login_code
			    FROM login_user
			    WHERE userid = #{userid}
			""")
	String getLogin_codeByUserid(String userid);

	@Select("""
	        SELECT id, regdate, title, writer, viewcount
			    FROM article
				ORDER BY id DESC
	        """)
	List<Article> getArticleslist();

	@Select("""
	        SELECT id, regdate, title, writer, viewcount
			    FROM article
			    WHERE boardid =  #{boardid}
				ORDER BY id DESC
	        """)
	List<Article> getArticleslist2(String boardid);
	
	@Select("""
		    SELECT id, regdate, title, writer, viewcount
		    FROM article
		    WHERE boardid = #{boardid}
		    AND (title LIKE CONCAT('%', #{keyword}, '%')) OR (id IN (SELECT id FROM article WHERE body LIKE CONCAT('%', #{keyword}, '%')))
		    ORDER BY id DESC
			""")
	List<Article> getArticleslistByKeyword(String keyword, int boardid);
	@Select("""
		    SELECT id, regdate, title, writer, viewcount
		    FROM article
		    WHERE (title LIKE CONCAT('%', #{keyword}, '%')) OR (id IN (SELECT id FROM article WHERE body LIKE CONCAT('%', #{keyword}, '%')))
		    ORDER BY id DESC
			""")
	List<Article> getArticleslistByKeyword2(String keyword);
}