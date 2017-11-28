package com.iu.qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.DBconnector;
import com.iu.util.MakeRow;

import sun.security.pkcs11.Secmod.DbMode;

public class QnaDAO implements BoardDAO {

	public int getNum() throws Exception{
		Connection con=DBconnector.getConnect();
		String sql="select qna_seq.nextval from dual";
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
		String sql="insert into qna values (?,?,?,?,0,sysdate,?,0,0)";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNum());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
		st.setInt(5, boardDTO.getNum());
		int result=st.executeUpdate();
		DBconnector.disConnect(st, con);
		
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		Connection con=DBconnector.getConnect();
		String sql="update qna set title=?, contents=? where num=?";
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
		String sql="delete qna where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, num);
		int result=st.executeUpdate();
		DBconnector.disConnect(st, con);
		
		return result;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		QnaDTO qnaDTO=null;
		Connection con=DBconnector.getConnect();
		String sql="select * from qna where num=?";
		PreparedStatement st=con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs=st.executeQuery();
		if(rs.next()) {
			qnaDTO=new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
		}
		DBconnector.disConnect(rs, st, con);
		return qnaDTO;
	}

	@Override
	public List<BoardDTO> selectList(MakeRow makeRow) throws Exception {
		List<BoardDTO> ar=new ArrayList<>();
		Connection con=DBconnector.getConnect();
		String sql="select * from (select rownum R,N.* from (select * from qna where "+makeRow.getKind()+" like ? order by ref desc, step asc) N) where R between ? and ?"; 
		PreparedStatement st=con.prepareStatement(sql);
		st.setString(1, "%"+makeRow.getSearch()+"%");
		st.setInt(2, makeRow.getStartRow());
		st.setInt(3, makeRow.getLastRow());
		ResultSet rs=st.executeQuery();
		while(rs.next()) {
			QnaDTO qnaDTO=new QnaDTO();
			qnaDTO.setNum(rs.getInt("num"));
			qnaDTO.setTitle(rs.getString("title"));
			qnaDTO.setWriter(rs.getString("writer"));
			qnaDTO.setContents(rs.getString("contents"));
			qnaDTO.setReg_date(rs.getDate("reg_date"));
			qnaDTO.setHit(rs.getInt("hit"));
			qnaDTO.setRef(rs.getInt("ref"));
			qnaDTO.setStep(rs.getInt("step"));
			qnaDTO.setDepth(rs.getInt("depth"));
			ar.add(qnaDTO);
		}
		DBconnector.disConnect(rs, st, con);
		
		return ar;
	}

	@Override
	public int getTotalCount(MakeRow makeRow) throws Exception {
		Connection con=DBconnector.getConnect();
		String sql="select nvl(count(num),0) from qna where "+makeRow.getKind()+" like ?";
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
	
	public int reply(BoardDTO boardDTO) throws Exception{
	
		
		
		return 0;
	}

}
