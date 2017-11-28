package com.iu.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
		//update===================================================================
		//delete===================================================================
}
