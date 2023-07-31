package com.game.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.game.common.CommonView;
import com.game.service.BoardInfoService;
import com.game.service.impl.BoardInfoServiceImpl;

@WebServlet("/board-info/*")
public class BoardInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardInfoService boardInfoService = new BoardInfoServiceImpl();

	private boolean isLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			request.setAttribute("msg", "로그인이 필요한 화면입니다.");
			request.setAttribute("url", "/user-info/login");
			CommonView.forwardMessage(request, response);
			return false;
		}
		return true;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!isLogin(request, response)) {
			return;
		}
		String cmd = CommonView.getCmd(request);
		if ("list".equals(cmd)) {
			Map<String, String> param = null;
			if (request.getParameter("searchType") != null) {
				param=new HashMap<>();
				String key = request.getParameter("searchType");
				String value = request.getParameter("searchStr");
				param.put("key", key);
				param.put("value", value);
			}
			List<Map<String, String>> list = boardInfoService.selectBoardInfoList(param);
			request.setAttribute("biList", list);
		} else if (("view".equals(cmd)) || ("update".equals(cmd))) {
			String biNum = request.getParameter("biNum");
			Map<String, String> board = boardInfoService.selectBoardInfo(biNum);
			request.setAttribute("board", board);

		}
		CommonView.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 현상 방지
		if (!isLogin(request, response)) {
			return; // 로그인 돼 있으면 리턴
		}
		String cmd = CommonView.getCmd(request);
		HttpSession session = request.getSession();
		Map<String, String> user = (Map<String, String>) session.getAttribute("user"); // 로그인 성공 후 유저에 대한 내용이 담겨있는 map

		if ("insert".equals(cmd)) {
			String biTitle = request.getParameter("biTitle");
			String biContent = request.getParameter("biContent");

			Map<String, String> board = new HashMap<>();
			board.put("biTitle", biTitle);
			board.put("biContent", biContent);
			board.put("uiNum", user.get("uiNum"));
			int result = boardInfoService.insertBoardInfo(board);
			request.setAttribute("msg", "등록에 실패하였습니다.");
			request.setAttribute("url", "/board-info/insert");
			if (result == 1) {
				request.setAttribute("msg", "등록에 성공하였습니다.");
				request.setAttribute("url", "/board-info/list");
			}
		} else if ("update".equals(cmd)) {
			String biNum = request.getParameter("biNum");
			String biTitle = request.getParameter("biTitle");
			String biContent = request.getParameter("biContent");

			Map<String, String> board = new HashMap<>();
			board.put("biNum", biNum);
			board.put("biTitle", biTitle);
			board.put("biContent", biContent);
			board.put("uiNum", user.get("uiNum"));
			int result = boardInfoService.updateBoardInfo(board);
			request.setAttribute("msg", "수정에 실패하였습니다.");
			request.setAttribute("url", "/board-info/update?biNum=\"+biNum");
			if (result == 1) {
				request.setAttribute("msg", "수정에 성공하였습니다.");
				request.setAttribute("url", "/board-info/list");
			}
		} else if ("delete".equals(cmd)) {
			String biNum = request.getParameter("biNum");
			int result = boardInfoService.deleteBoardInfo(biNum);
			request.setAttribute("msg", "삭제에 실패하였습니다.");
			request.setAttribute("url", "/board-info/delete?biNum=" + biNum);
			if (result == 1) {
				request.setAttribute("msg", "삭제에 성공하였습니다.");
				request.setAttribute("url", "/board-info/list");
			}
		}

		CommonView.forwardMessage(request, response);
	}

}