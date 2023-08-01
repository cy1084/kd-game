package com.game.mapper;

import java.util.List;

import com.game.vo.BoardInfoVO;

public interface BoardInfoMapper {
	List<BoardInfoVO> selectBoardInfoList(BoardInfoVO board);

	BoardInfoVO selectBoardInfo(BoardInfoVO board);
	// BoardInfoVO selectBoardInfo(int biNum);

	int insertBoardInfo(BoardInfoVO board);

	int updateBoardInfo(BoardInfoVO board);

	int deleteBoardInfo(int biNum);

}
