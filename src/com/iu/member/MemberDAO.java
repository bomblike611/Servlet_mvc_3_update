package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.student.StudentDTO;
import com.iu.teacher.TeacherDTO;
import com.iu.util.DBconnector;

public class MemberDAO {

	//insert===================================================================
	public int join(MemberDTO memberDTO) throws Exception{
		Connection con=DBconnector.getConnect();
		String sql="insert into member values (?,?,?,?,?,?,?)";
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getEmail());
		st.setString(5, memberDTO.getPhone());
		st.setInt(6, memberDTO.getAge());
		st.setString(7, memberDTO.getJob());
		int result=st.executeUpdate();
		DBconnector.disConnect(st, con);
		
		return result;
	}
	
	//login====================================================================
	public MemberDTO login(String id) throws Exception{
			Connection con=DBconnector.getConnect();
			String sql="select * from member where id=?";
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs=st.executeQuery();
			MemberDTO memberDTO=null;
			if(rs.next()) {
				memberDTO=new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setJob(rs.getString("job"));
				memberDTO.setEmail(rs.getString("email"));
				memberDTO.setAge(rs.getInt("age"));
				memberDTO.setPhone(rs.getString("phone"));
			}
			DBconnector.disConnect(rs, st, con);
			
			return memberDTO;
		
	}
	
	//update===================================================================
	//delete===================================================================
	
	
}
