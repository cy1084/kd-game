package com.game.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.game.common.MybatisSqlSessionFactory;
import com.game.dao.BoardInfoDAO;
import com.game.dao.impl.BoardInfoDAOImpl;
import com.game.mapper.BoardInfoMapper;
import com.game.service.BoardInfoService;
import com.game.vo.BoardInfoVO;

public class BoardInfoServiceImpl implements BoardInfoService {
	private BoardInfoDAO boardInfoDAO = new BoardInfoDAOImpl();
	private SqlSessionFactory ssf=MybatisSqlSessionFactory.getSqlSessionFactory();

	@Override
	public List<BoardInfoVO> selectBoardInfoList(BoardInfoVO boardInfo) {
		
		try(SqlSession session=ssf.openSession()){
			BoardInfoMapper boardInfoMapper=session.getMapper(BoardInfoMapper.class);
			return boardInfoMapper.selectBoardInfoList(boardInfo);  //처음엔 리턴 타입이 안 맞아서 오류			
		}catch(Exception e) {
			throw e;
		}		
	}

	@Override
	public BoardInfoVO selectBoardInfo(String biNum) {
		try(SqlSession session=ssf.openSession()){
			BoardInfoMapper boardInfoMapper=session.getMapper(BoardInfoMapper.class);
			return boardInfoMapper.selectBoardInfo(biNum);  
			
		}catch(Exception e) {
			throw e;
		}
		//-> 마이바티스 형태

		
		//return boardInfoDAO.selectBoardInfo(biNum); -> dao 형태
	}

	@Override
	public int insertBoardInfo(Map<String, String> boardInfo) {

		return boardInfoDAO.insertBoardInfo(boardInfo);
	}

	@Override
	public int updateBoardInfo(Map<String, String> boardInfo) {

		return boardInfoDAO.updateBoardInfo(boardInfo);
	}

	@Override
	public int deleteBoardInfo(String biNum) {

		return boardInfoDAO.deleteBoardInfo(biNum);
	}

}
