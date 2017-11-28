package com.iu.student;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
		//update===================================================================
		//delete===================================================================
	
}
