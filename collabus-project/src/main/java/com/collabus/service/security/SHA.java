package com.collabus.service.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.collabus.model.UserDTO;

import lombok.extern.log4j.Log4j;

@Log4j
public class SHA {
	
	/**  dto 로 들어와 이메일과 비밀번호를 가져오는 기능 이다.
	 * @param dto 회원 정보를 가져오기위해 
	 * @return 암호화를 받아와서 리턴을 한다. 
	 * @throws NoSuchAlgorithmException
	 * 
	 * @Date 2019-20-28
	 * @Author 강정훈 
	 */
	public static String sha256(UserDTO dto) throws NoSuchAlgorithmException{
		return sha256(dto.getUser_email(),dto.getUser_pw());
	}
	
	/** security 중 SHA256 기법으로 암호화 기능
	 * @param email 아래 설명
	 * @param pw    아래 설명 
	 * 			(기본키)email
	 * 			(기본키)PassWord 
	 * 			- 를 가져와서 복합키처럼 사용한다.
	 * 			password 와 email 을 합치고  여기서 비밀키 암호화 알고리즘을 사용해서 키를 하나 더 추가해준다 .
	 * 			이를 복합해 SHA_256 의 암호화가 이루어진다. 
	 * 				 
	 * @return 암호화된 스트링값을 반환 해준다. 
	 * @throws NoSuchAlgorithmException
	 * 
	 * @Date 2019-10-28
	 * @Author 강정훈 
	 */
	private static String sha256(String email,String pw) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		String msg="";
		
		msg = pw;
		msg +=email;
		msg +=shaPlusSecurity(email) ; 

		
		for(int i=0;i<3;i++) {
			
			md.update(msg.getBytes());
			
			msg=bytesto(md.digest());

		}
		
		return msg;
	}
	
	/** 스트링 타입으로 반환해주는 기능이다.
	 * @param bytes 이기능 을 수행하기위해 바이트 배열을 가져온다 .
	 * @return String Type 의 문자열을 반환해준다. 
	 */
	private static String bytesto(byte[] bytes) {	
		StringBuilder builder = new StringBuilder();	
		
		for(byte b:bytes) {
			builder.append(String.format("%02x",b));	
		}
	
		return builder.toString();
	}
	
	/** 유저의 비밀키 반환이다.   
	 * @param email 을 가져와서 비밀키를 만든다 .
	 * @return 알고리즘을 거져 비밀키를  반환해준다. 
	 * 
	 * @Date 2019-10-28
	 * @Author 강정훈 
	 */
	private static  String shaPlusSecurity(String email) {
		
		int elength = email.length();
		boolean  seq = false;
		
		String KEY ="LOL";
		
		String plusString =""+email.charAt(0);
		
		
		for(int i=0;i<elength;i++) {
			
			if(seq == false) {
				
			}
			
	    	if(email.charAt(i) == '@' && seq == false) {
	    		
	    		plusString+=email.charAt(i-1);
	    		
	    		seq = true;
	    		
	    		continue;
	    	}
	    	
	    	if(elength <= i+1 || email.charAt(i+1) == '.') break;
	    	
	    	if(seq == true && email.charAt(i+1) != '.' ) {
	    		
	    		plusString+=email.charAt(i);
	    	
	    	}
	    
	    }//for end 
		plusString +=KEY;
		
		return plusString;
	}

	

}
