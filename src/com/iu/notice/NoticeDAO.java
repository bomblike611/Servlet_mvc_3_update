package com.iu.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.DBconnector;
import com.iu.util.MakeRow;

import jdk.management.resource.NotifyingMeter;

public class NoticeDAO implements BoardDAO {
	
	public int getNum() throws Exception{
		Connection con=DBconnector.getConnect();
		String sql="select BOARD_SEQ.nextval from dual";
		PreparedStatement st=con.prepareStatement(sql);
		ResultSet rs=st.executeQuery();
		
		rs.next();
		int num=rs.getInt(1);
		
		DBconnector.disConnect(rs, st, con);
		return num;
	}
	
	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		Connection con=DBconnector.getConnect();
		String sql="insert into notice values (?,?,?,?,sysdate,0)";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getWriter());
		st.setString(4, boardDTO.getContents());
		int result=st.executeUpdate();
		
		DBconnector.disConnect(st, con);
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con=DBconnector.getConnect();
		String sql="update notice set title=?, contents=? where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, boardDTO.getTitle());
		st.setString(2, boardDTO.getContents());
		st.setInt(3, boardDTO.getNum());
		int result=st.executeUpdate();
		DBconnector.disConnect(st, con);
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection con=DBconnector.getConnect();
		String sql="delete notice where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, num);
		int result=st.executeUpdate();
		DBconnector.disConnect(st, con);
		return result;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		NoticeDTO noticeDTO=null;
		Connection con=DBconnector.getConnect();
		String sql="select * from notice where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
			noticeDTO=new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
		}
		
		DBconnector.disConnect(rs, st, con);
		
		
		return noticeDTO;
	}

	@Override
	public List<BoardDTO> selectList(MakeRow makeRow) throws Exception {
		List<BoardDTO> ar=new ArrayList<>();
		Connection con=DBconnector.getConnect();
		String sql="select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice where "+makeRow.getKind()+" like ? order by num desc) N) "
				+ "where R between ? and ?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, "%"+makeRow.getSearch()+"%");
		st.setInt(2, makeRow.getStartRow());
		st.setInt(3, makeRow.getLastRow());
		ResultSet rs=st.executeQuery();
		while(rs.next()) {
			NoticeDTO noticeDTO=new NoticeDTO();
			noticeDTO.setNum(rs.getInt("num"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setContents(rs.getString("contents"));
			noticeDTO.setReg_date(rs.getDate("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			ar.add(noticeDTO);
		}
		DBconnector.disConnect(rs, st, con);
		return ar;
	}

	@Override
	public int getTotalCount(MakeRow makeRow) throws Exception {
		Connection con=DBconnector.getConnect();
		String sql="select nvl(count(num),0) from notice where "+makeRow.getKind()+" like ?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, "%"+makeRow.getSearch()+"%");
		ResultSet rs=st.executeQuery();
		rs.next();
		int totalCount=rs.getInt(1);
		
		DBconnector.disConnect(rs, st, con);
		
		return totalCount;
	}

	@Override
	public int hit(int num) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
