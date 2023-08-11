package com.game.common;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class JSON {
	private static final Gson GSON = new Gson();

	public static <T> String stringify(T t) {
		return GSON.toJson(t);
	}
	public static <T> T parse(String json, Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}
	public static <T> T parse(HttpServletRequest request, Class<T> clazz) throws IOException {
		BufferedReader br=request.getReader();
		StringBuffer sb=new StringBuffer();
		String str=null;
		while((str=br.readLine())!=null) {
			sb.append(str);
		}
		//reader로 한번 사용하면 재사용 불가
		
		return GSON.fromJson(sb.toString(), clazz);
	}

}
