package com.game.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mapper.BoardInfoMapper;
import com.game.vo.BoardInfoVO;

public class MybatisSqlSessionFactory {
	private static SqlSessionFactory SSF;
	private final static String CONFIG_PATH = "config/mybatis-config.xml";

	// 메인 메소드 실행 전에 실행- static
	static {
		try {
			InputStream is = Resources.getResourceAsStream(CONFIG_PATH);
			SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
			SSF = ssfb.build(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return SSF;
	}

	public static void main(String[] args) {
		SqlSessionFactory ssf = getSqlSessionFactory();
		SqlSession session = ssf.openSession();
		BoardInfoMapper biMapper = session.getMapper(BoardInfoMapper.class);
		try {
			
			BoardInfoVO bi = new BoardInfoVO();
			bi.setBiNum(4);
			bi.setBiTitle("마이바티스 인서트 테스트");
			bi.setBiContent("집 보내줘");
			bi.setUiNum(1);
			int result=biMapper.insertBoardInfo(bi);
			System.out.println("insert result: "+result);
			bi=biMapper.selectBoardInfo(bi);
			bi.setBiTitle("마이바티스 업데이트 테스트");
			result=biMapper.updateBoardInfo(bi);
			System.out.println("update result: "+result);
			// session.getMapper(BoardInfoMapper.class);
	
			List<BoardInfoVO> list = biMapper.selectBoardInfoList(bi);
			// System.out.println(list);
			for (BoardInfoVO board : list) {
				System.out.println(board);
			}
			BoardInfoVO board = biMapper.selectBoardInfo(bi);
			System.out.println(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
