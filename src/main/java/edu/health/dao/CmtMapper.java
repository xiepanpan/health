package edu.health.dao;

import edu.health.model.Cmt;
import edu.health.model.CmtJoinUser;
import edu.health.model.CmtWithBLOBs;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CmtMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@Delete({ "delete from cmt", "where cmt_id = #{cmtId,jdbcType=INTEGER}" })
	int deleteByPrimaryKey(Integer cmtId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@Insert({ "insert into cmt (cmt_id, user_id, ", "topic_id, content, ", "repeat)",
			"values (#{cmtId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
			"#{topicId,jdbcType=INTEGER}, #{content,jdbcType=LONGVARCHAR}, ", "#{repeat,jdbcType=LONGVARCHAR})" })
	int insert(CmtWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@InsertProvider(type = CmtSqlProvider.class, method = "insertSelective")
	int insertSelective(CmtWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@Select({ "select", "cmt_id, user_id, topic_id, content, repeat", "from cmt",
			"where cmt_id = #{cmtId,jdbcType=INTEGER}" })
	@Results({ @Result(column = "cmt_id", property = "cmtId", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
			@Result(column = "topic_id", property = "topicId", jdbcType = JdbcType.INTEGER),
			@Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR),
			@Result(column = "repeat", property = "repeat", jdbcType = JdbcType.LONGVARCHAR) })
	CmtWithBLOBs selectByPrimaryKey(Integer cmtId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@UpdateProvider(type = CmtSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(CmtWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@Update({ "update cmt", "set user_id = #{userId,jdbcType=INTEGER},", "topic_id = #{topicId,jdbcType=INTEGER},",
			"content = #{content,jdbcType=LONGVARCHAR},", "repeat = #{repeat,jdbcType=LONGVARCHAR}",
			"where cmt_id = #{cmtId,jdbcType=INTEGER}" })
	int updateByPrimaryKeyWithBLOBs(CmtWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table cmt
	 * @mbg.generated  Tue Jan 14 13:01:07 CST 2020
	 */
	@Update({ "update cmt", "set user_id = #{userId,jdbcType=INTEGER},", "topic_id = #{topicId,jdbcType=INTEGER}",
			"where cmt_id = #{cmtId,jdbcType=INTEGER}" })
	int updateByPrimaryKey(Cmt record);
	
	
	@Select({
		"select cmt.*,users.login_name as stu_name from cmt",
		"left join users on users.user_id=cmt.user_id",
		"where topic_id=#{id,jdbcType=INTEGER}"
	})
	@Results({ 
		@Result(column = "cmt_id", property = "cmtId", jdbcType = JdbcType.INTEGER, id = true),
		@Result(column = "user_id", property = "userId", jdbcType = JdbcType.INTEGER),
		@Result(column = "topic_id", property = "topicId", jdbcType = JdbcType.INTEGER),
		@Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR),
		@Result(column = "repeat", property = "repeat", jdbcType = JdbcType.LONGVARCHAR),
		@Result(column ="stu_name", property ="stuName", jdbcType=JdbcType.VARCHAR)
	})
	List<CmtJoinUser> finByTopic(@Param("id")Integer id);
}