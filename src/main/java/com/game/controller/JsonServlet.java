package com.game.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.game.common.CommonView;
import com.game.service.BoardInfoService;
import com.game.service.impl.BoardInfoServiceImpl;
import com.game.vo.BoardInfoVO;
import com.google.gson.Gson;

@WebServlet("/json/*")
public class JsonServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Gson gson=new Gson();
	private BoardInfoService biService=new BoardInfoServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//BoardInfoVO biVO=new BoardInfoVO();
		//biVO.setBiNum(1);
		//biVO.setBiTitle("json 테스트");
		//biVO.setCredat("2023-08-07");
		String json="";
		String cmd=CommonView.getCmd(request);
		if("list".equals(cmd)) {
		
		BoardInfoVO board=new BoardInfoVO();
		board.setSearchStr(request.getParameter("searchStr"));
		board.setSearchType(request.getParameter("searchType"));
		
		//biList.add(biVO);
		//biList.add(biVO);
		//JSON 형태로 만들어질 때 NULL인 값은 무시되고 안나온다!
		
		/*
		Map<String,String> map=new HashMap<>();		 
		map.put("name", "hong");
		map.put("age", "20");
		*/
		//String json=map.toString();
		//String json=gson.toJson(biVO); //map이 가지고 있는 key, value를 json 형태로 바꿔줌
		List<BoardInfoVO> biList=biService.selectBoardInfoList(board);
		json=gson.toJson(biList);
		}else if("one".equals(cmd)) {
			String biNum=request.getParameter("biNum");
			json=gson.toJson(biService.selectBoardInfo(biNum));
			
		}
	    //response.setHeader("Access-Control-Allow-Origin", "*");
		
		//String json="{\"name\":\"hong\",\"age\":20}";
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(json);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {}
		
	/*
	public static void main(String[] args) throws ServletException, IOException {
		JsonServlet js=new JsonServlet();
		js.doGet(null, null);
	}
	*/

}
