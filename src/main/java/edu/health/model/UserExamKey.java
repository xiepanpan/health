package edu.health.model;

public class UserExamKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_exam.user_id
     *
     * @mbg.generated Tue Jan 14 12:40:34 CST 2020
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_exam.exam_id
     *
     * @mbg.generated Tue Jan 14 12:40:34 CST 2020
     */
    private Integer examId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_exam.user_id
     *
     * @return the value of user_exam.user_id
     *
     * @mbg.generated Tue Jan 14 12:40:34 CST 2020
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_exam.user_id
     *
     * @param userId the value for user_exam.user_id
     *
     * @mbg.generated Tue Jan 14 12:40:34 CST 2020
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_exam.exam_id
     *
     * @return the value of user_exam.exam_id
     *
     * @mbg.generated Tue Jan 14 12:40:34 CST 2020
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_exam.exam_id
     *
     * @param examId the value for user_exam.exam_id
     *
     * @mbg.generated Tue Jan 14 12:40:34 CST 2020
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }
}