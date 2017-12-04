package com.iu.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.util.DBconnector;

public class StudentDAO {

	//insert===================================================================
		public int join(StudentDTO studentDTO) throws Exception{
			Connection con=DBconnector.getConnect();
			String sql="insert into student values (?,?,?,?)";
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, studentDTO.getId());
			st.setInt(2, studentDTO.getGrade());
			st.setString(3, studentDTO.getTid());
			st.setString(4, studentDTO.getAddr());
			int result=st.executeUpdate();
			DBconnector.disConnect(st, con);
			return result;
		}
		
		//login====================================================================
		public StudentDTO login(String id,String pw) throws Exception{
			Connection con=DBconnector.getConnect();
			String sql="select * from student where id=?";
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs=st.executeQuery();
			StudentDTO studentDTO=null;
			if(rs.next()) {
				studentDTO=new StudentDTO();
				studentDTO.setId(rs.getString("id"));
				studentDTO.setAddr(rs.getString("addr"));
				studentDTO.setGrade(rs.getInt("grade"));
				studentDTO.setTid(rs.getString("tid"));
			}
			DBconnector.disConnect(rs, st, con);
			return studentDTO;
		}
		//update===================================================================
		//delete===================================================================
	
}
