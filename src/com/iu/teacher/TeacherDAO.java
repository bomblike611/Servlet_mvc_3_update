package com.iu.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.util.DBconnector;

public class TeacherDAO {

	//insert===================================================================
		public int join(TeacherDTO teacherDTO) throws Exception{
			Connection con=DBconnector.getConnect();
			String sql="insert into teacher values (?,?,?,?)";
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, teacherDTO.getId());
			st.setString(2, teacherDTO.getSubject());
			st.setInt(3, teacherDTO.getSalary());
			st.setDate(4, teacherDTO.getBirth());
			int result=st.executeUpdate();
			DBconnector.disConnect(st, con);
			
			
			return result;
		}
		
		//login====================================================================
		public TeacherDTO login(String id,String pw) throws Exception{
			Connection con=DBconnector.getConnect();
			String sql="select * from teacher where id=? and pw=?";
			PreparedStatement st=con.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, pw);
			ResultSet rs=st.executeQuery();
			TeacherDTO teacherDTO=null;
			if(rs.next()) {
				teacherDTO=new TeacherDTO();
				teacherDTO.setId(rs.getString("id"));
				teacherDTO.setSubject(rs.getString("subject"));
				teacherDTO.setBirth(rs.getDate("birth"));
				teacherDTO.setSalary(rs.getInt("salary"));
			}
			DBconnector.disConnect(rs, st, con);
			return teacherDTO;
		}
		//update===================================================================
		//delete===================================================================
}
