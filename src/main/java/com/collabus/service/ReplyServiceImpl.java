package com.collabus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.collabus.mapper.ReplyMapper;
import com.collabus.model.ReplyDTO;
import com.collabus.model.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyMapper mapper;
	

	@Override
	public List<ReplyVO> retrieveReplies(int task_id) throws Exception {
		return mapper.selectReplies(task_id);
	}
	
	@Override
	public List<ReplyDTO> infiniteScroll(ReplyDTO dto) throws Exception {
		
		return mapper.infiniteScroll(dto);
	}

	@Override
	public void addReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		mapper.insertReply(dto);
		
		
	}

	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		mapper.updateReply(dto);
	
	}

	@Override
	public ReplyDTO getRecentlyReply() throws Exception {
		
		return mapper.getRecentReply();
	}

	@Override
	public void deleteReply(int reply_id) throws Exception {
		// TODO Auto-generated method stub
		mapper.deleteReply(reply_id);
	}

	


}
