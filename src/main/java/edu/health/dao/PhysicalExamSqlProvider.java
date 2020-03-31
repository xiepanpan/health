package edu.health.dao;

import edu.health.model.PhysicalExam;
import org.apache.ibatis.jdbc.SQL;

public class PhysicalExamSqlProvider {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table physical_exam
	 * @mbg.generated  Thu Feb 27 11:23:34 CST 2020
	 */
	public String insertSelective(PhysicalExam record) {
		SQL sql = new SQL();
		sql.INSERT_INTO("physical_exam");
		if (record.getTestId() != null) {
			sql.VALUES("test_id", "#{testId,jdbcType=INTEGER}");
		}
		if (record.getHeight() != null) {
			sql.VALUES("height", "#{height,jdbcType=DECIMAL}");
		}
		if (record.getWeight() != null) {
			sql.VALUES("weight", "#{weight,jdbcType=DECIMAL}");
		}
		if (record.getHeartbeat() != null) {
			sql.VALUES("heartbeat", "#{heartbeat,jdbcType=VARCHAR}");
		}
		if (record.getVitalCapacity() != null) {
			sql.VALUES("vital_capacity", "#{vitalCapacity,jdbcType=DECIMAL}");
		}
		if (record.getSex() != null) {
			sql.VALUES("sex", "#{sex,jdbcType=VARCHAR}");
		}
		if (record.getStuId() != null) {
			sql.VALUES("stu_id", "#{stuId,jdbcType=INTEGER}");
		}
		if (record.getDoctorId() != null) {
			sql.VALUES("doctor_id", "#{doctorId,jdbcType=INTEGER}");
		}
		if (record.getLeftEye() != null) {
			sql.VALUES("left_eye", "#{leftEye,jdbcType=VARCHAR}");
		}
		if (record.getRightEye() != null) {
			sql.VALUES("right_eye", "#{rightEye,jdbcType=VARCHAR}");
		}
		if (record.getHearing() != null) {
			sql.VALUES("hearing", "#{hearing,jdbcType=VARCHAR}");
		}
		if (record.getAdvice() != null) {
			sql.VALUES("advice", "#{advice,jdbcType=VARCHAR}");
		}
		return sql.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table physical_exam
	 * @mbg.generated  Thu Feb 27 11:23:34 CST 2020
	 */
	public String updateByPrimaryKeySelective(PhysicalExam record) {
		SQL sql = new SQL();
		sql.UPDATE("physical_exam");
		if (record.getHeight() != null) {
			sql.SET("height = #{height,jdbcType=DECIMAL}");
		}
		if (record.getWeight() != null) {
			sql.SET("weight = #{weight,jdbcType=DECIMAL}");
		}
		if (record.getHeartbeat() != null) {
			sql.SET("heartbeat = #{heartbeat,jdbcType=VARCHAR}");
		}
		if (record.getVitalCapacity() != null) {
			sql.SET("vital_capacity = #{vitalCapacity,jdbcType=DECIMAL}");
		}
		if (record.getSex() != null) {
			sql.SET("sex = #{sex,jdbcType=VARCHAR}");
		}
		if (record.getStuId() != null) {
			sql.SET("stu_id = #{stuId,jdbcType=INTEGER}");
		}
		if (record.getDoctorId() != null) {
			sql.SET("doctor_id = #{doctorId,jdbcType=INTEGER}");
		}
		if (record.getLeftEye() != null) {
			sql.SET("left_eye = #{leftEye,jdbcType=VARCHAR}");
		}
		if (record.getRightEye() != null) {
			sql.SET("right_eye = #{rightEye,jdbcType=VARCHAR}");
		}
		if (record.getHearing() != null) {
			sql.SET("hearing = #{hearing,jdbcType=VARCHAR}");
		}
		if (record.getAdvice() != null) {
			sql.SET("advice = #{advice,jdbcType=VARCHAR}");
		}
		sql.WHERE("test_id = #{testId,jdbcType=INTEGER}");
		return sql.toString();
	}
}