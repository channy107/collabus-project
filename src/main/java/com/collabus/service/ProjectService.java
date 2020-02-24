package com.collabus.service;

import java.util.List;

import com.collabus.model.InviteDTO;
import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;
import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;
import com.collabus.model.WorkDTO;

public interface ProjectService {

	public abstract void regist(ProjectDTO dto) throws Exception;
	
	public abstract List<ProjectVO> retrieve(int user_id) throws Exception;
	
	public abstract List<ProjectVO> search(String searchKeyword, int user_id) throws Exception;
	
	public abstract List<UserVO> selectProjectMembers(int project_id) throws Exception;
	
	public List<UserDTO> SelectEmailAndNickname (String value) throws Exception;
	   //회원 닉네임 찾기 
	   
	public String selectName (int  send_id);
	//회원 이름 찾기 
	   
	public List<InviteDTO> userInviteSelect(int  user_id , int project_id ) throws Exception;
	//초대하기전
	
	public int userInvite(String send ,int user_id ,int project_id ) throws Exception;
	//회원 초대 테이블에 넣기 
	public List<WorkDTO> userWorkListMamber(int work_user,int work_project)throws Exception;   
	//초대받은거 확인 하는라인&& 프로젝트안에 이미 있는 지 확인 
	
	public int projectIdSelect(int user_id, int project_id)throws Exception;
	//프로젝트 방장 구함 
	
	public int userInviteId(String name) throws Exception;
	//   닉네임으로 id 찾기 
	
	   public abstract void updateProject(ProjectDTO dto) throws Exception;

	   public abstract void delProject(int project_id) throws Exception;

	   public abstract void projectPercomplete(int project_id) throws Exception;
	   
	   public abstract ProjectVO getProject(int project_id) throws Exception;
}
