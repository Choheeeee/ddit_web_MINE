package kr.or.ddit.mbti.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.common.exception.CustomPersistenceException;
import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MbtiVO;

public class MbtiDAOImpl implements MbtiDAO {

	@Override
	public List<MbtiVO> selectMbtiList() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT TYPE, TITLE, LOGICAL_PATH FROM MBTI ");
		
		List<MbtiVO> mbtiList = new ArrayList<>();
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			ResultSet rs = stmt.executeQuery(sql.toString());
			while(rs.next()) {
				MbtiVO mbtiVO = new MbtiVO();
				mbtiList.add(mbtiVO);
				mbtiVO.setType(rs.getString("type"));
				mbtiVO.setTitle(rs.getString("title"));
				mbtiVO.setLogicalPath(rs.getString("logical_path"));
			}
			return mbtiList;
		}catch(SQLException e) {
			throw new CustomPersistenceException(e);
		}
	}

	@Override
	public MbtiVO selectMbti(String type) {
		StringBuffer sql = new StringBuffer();
		MbtiVO mbtiVO = null;
		
		sql.append(" SELECT TYPE, TITLE, LOGICAL_PATH ");
		sql.append(" FROM MBTI ");
		sql.append(String.format(" WHERE TYPE = '%s' ", type));
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
		){
			ResultSet rs = stmt.executeQuery(sql.toString());
			if(rs.next()) {
				mbtiVO = new MbtiVO();
				mbtiVO.setType(rs.getString("type"));
				mbtiVO.setTitle(rs.getString("title"));
				mbtiVO.setLogicalPath(rs.getString("logical_path"));
			}
			return mbtiVO;
		}catch(SQLException e){
			throw new CustomPersistenceException(e);
		}
	}
}
