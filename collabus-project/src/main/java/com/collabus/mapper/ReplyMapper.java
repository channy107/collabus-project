package com.collabus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.collabus.model.ReplyDTO;
import com.collabus.model.ReplyVO;

public interface ReplyMapper {

	@Select("SELECT * FROM reply WHERE reply_task = #{task_id} limit 3;")
	@ResultType(ReplyVO.class)
	public List<ReplyVO> selectReplies(int task_id) throws Exception;
	
	@Select("SELECT * FROM reply WHERE reply_task = #{reply_task} and reply_id > #{reply_id} limit 3;")
	@ResultType(ReplyDTO.class)
	public List<ReplyDTO> infiniteScroll(ReplyDTO dto) throws Exception;
	
	@Select("SELECT * FROM reply ORDER BY created_at DESC LIMIT 1")
	@ResultType(ReplyDTO.class)
	public ReplyDTO getRecentReply() throws Exception;
	
	@Insert("INSERT INTO reply (reply_task, reply_user, reply_text) " + 
			"VALUES (#{reply_task}, #{reply_user}, #{reply_text})")
	@Options(useGeneratedKeys = true, keyProperty = "reply_id")
	public void insertReply(ReplyDTO dto) throws Exception;
	
	@Update("UPDATE reply SET "
	         + "reply_text = #{reply_text}"
	         + "WHERE (reply_id = #{reply_id});")
	public void updateReply(ReplyDTO dto) throws Exception;
	
	@Delete("DELETE FROM reply WHERE reply_id = #{reply_id}")
	public void deleteReply(int reply_id) throws Exception;
	
}
