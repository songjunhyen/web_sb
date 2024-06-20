package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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

	@Insert("INSERT INTO article (regDate, updateDate, title, `body`, writer, viewcount) "
			+ "VALUES (#{regDate}, #{updateDate}, #{title}, #{body}, #{writer}, #{viewcount})")
	void writeArticle(Article article);

	@Update("""
			UPDATE article
				SET title = #{title}, `body` = #{body}, updateDate = #{updateDate}
				WHERE id = #{id}
			""")
	void updateArticle(Article article, String title, String body);

	@Delete("""
			DELETE FROM article
				WHERE id = #{id}
			""")
	void deleteArticle(Article article);
}
