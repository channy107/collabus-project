package com.collabus.service;

import java.util.List;

import com.collabus.model.ReplyDTO;
import com.collabus.model.ReplyVO;

public interface ReplyService {

	public abstract void addReply(ReplyDTO dto) throws Exception;
	
	public abstract void updateReply(ReplyDTO dto) throws Exception;
	
	public abstract List<ReplyDTO> infiniteScroll(ReplyDTO dto) throws Exception;
	
	public abstract List<ReplyVO> retrieveReplies(int task_id) throws Exception;
	
	public abstract ReplyDTO getRecentlyReply() throws Exception;
	
	public abstract void deleteReply(int reply_id) throws Exception;
	
}
