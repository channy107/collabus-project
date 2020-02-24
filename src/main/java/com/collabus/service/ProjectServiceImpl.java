package com.collabus.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.collabus.mapper.ProjectMapper;
import com.collabus.model.InviteDTO;
import com.collabus.model.ProjectDTO;
import com.collabus.model.ProjectVO;
import com.collabus.model.UserDTO;
import com.collabus.model.UserVO;
import com.collabus.model.WorkDTO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Inject
	private ProjectMapper mapper;

	@Override
	public void regist(ProjectDTO dto) throws Exception {
		
		mapper.insertProject(dto);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("work_user", dto.getProject_leader());
		map.put("work_project", dto.getProject_id());
		
		
		mapper.insertWork(map);
	}

	@Override
	public List<ProjectVO> retrieve(int user_id) throws Exception {
		
		return mapper.selectProjects(user_id);
	}

	@Override
	public List<ProjectVO> search(String searchKeyword, int user_id) throws Exception {
		return mapper.selectProjectsByName("%" + searchKeyword + "%", user_id);
	}

	@Override
	public List<UserVO> selectProjectMembers(int project_id) throws Exception {
		
		return mapper.selectProjectMembers(project_id);
	}
	

	@Override
	public List<UserDTO> SelectEmailAndNickname(String value) throws Exception {
	   return mapper.SelectEmailAndNickname(value);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public int userInvite(String send_id,int user_id, int project_id) throws Exception {

	   try {
	      
	      int insertInvite = mapper.userInvite(send_id,user_id, project_id);   
	      System.out.println("insertInvite : "+insertInvite);
	      
	      if (insertInvite == 0) {

	         return 0;

	      } else if (insertInvite >= 2) {
	        System.out.println("롤백");
	         throw new Exception();
	      }
	      System.out.println("return : "+insertInvite);
	      return insertInvite; // 1

	      } catch (Exception e) {

	         e.printStackTrace();
	         System.out.println("오류");
	         return -1;

	      }
	      // -1 (알수없는 오류로 초대하지 못합니다.) , 0 (실패) , 1(성공) , 2(이미 초대되어있음 ) 들이 리턴남
	   }
	   @Transactional
	   @Override
	   public int userInviteId(String name) throws Exception {
	      return mapper.userInviteId(name);
	   }
	   @Transactional
	   @Override
	   public List<InviteDTO> userInviteSelect(int user_id, int project_id) throws Exception {
	      return mapper.userInviteSelect(user_id, project_id);

	   }
	   @Transactional
	   @Override
	   public List<WorkDTO> userWorkListMamber(int work_user, int work_project) throws Exception {
	      return mapper.userWorkListMamber(work_user, work_project);
	   }
	   @Override
	   public int projectIdSelect(int user_id, int project_id) throws Exception {
	      return mapper.projectIdSelect(user_id, project_id);
	   }

	   @Override
	   public String selectName(int send_id) {
	      return mapper.selectName(send_id);
	   }
	   
	   @Override
	   public void updateProject(ProjectDTO dto) throws Exception {
	      mapper.updateProject(dto);
	      
	   }

	   @Override
	   public ProjectVO getProject(int project_id) throws Exception {
	      return mapper.getProject(project_id);
	   }

	   @Override
	   public void delProject(int project_id) throws Exception {
	      mapper.delProject(project_id);
	      
	   }


	   @Override
	   public void projectPercomplete(int project_id) throws Exception {
	      mapper.projectPercomplete(project_id);
	      
	   }
}
