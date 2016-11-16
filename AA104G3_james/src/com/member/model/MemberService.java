package com.member.model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(String memid,	String mempsw,String memname,String mememail,String memaddr,String memphone,
			byte[] mempic,String memnick,String memident,Date membirth,String memgender){
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemid(memid);
		memberVO.setMempsw(mempsw);
		memberVO.setMemname(memname);
		memberVO.setMememail(mememail);
		memberVO.setMemaddr(memaddr);
		memberVO.setMemphone(memphone);
		memberVO.setMempic(mempic);
		memberVO.setRegdate(new Date(System.currentTimeMillis()));
		memberVO.setMemnick(memnick);
		memberVO.setMemident(memident);
		memberVO.setMembirth(membirth);
		memberVO.setMemstate("0");
		memberVO.setMemgender(memgender);
		dao.insert(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMember(String memno,String memid,	String mempsw,String memname,String mememail,String memaddr,String memphone,
			byte[] mempic,Date regdate,String memnick,String memident,Date membirth,String memstate,String memgender){
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemno(memno);
		memberVO.setMemid(memid);
		memberVO.setMempsw(mempsw);
		memberVO.setMemname(memname);
		memberVO.setMememail(mememail);
		memberVO.setMemaddr(memaddr);
		memberVO.setMemphone(memphone);
		memberVO.setMempic(mempic);
		memberVO.setRegdate(regdate);
		memberVO.setMemnick(memnick);
		memberVO.setMemident(memident);
		memberVO.setMembirth(membirth);
		memberVO.setMemstate(memstate);
		memberVO.setMemgender(memgender);
		dao.update(memberVO);
		
		return memberVO;
	}
	
	public void deleteMember(String memno){
		
		dao.delete(memno);
		
	}
	
	public MemberVO getOneMember(String memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
