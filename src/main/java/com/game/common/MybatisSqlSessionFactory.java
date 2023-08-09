package com.game.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.game.mapper.UserInfoMapper;
import com.game.vo.UserInfoVO;

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
		SqlSessionFactory ssf=getSqlSessionFactory();
		SqlSession session=ssf.openSession();//세션 만들고
		UserInfoMapper uiMapper=session.getMapper(UserInfoMapper.class);//매퍼 가져옴
		List<UserInfoVO> list=uiMapper.selectUserInfoList(null);
		
		System.out.println(list);
	}

}
