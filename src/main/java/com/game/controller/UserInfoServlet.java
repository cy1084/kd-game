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

import com.game.common.CommonView;
import com.game.service.UserInfoService;
import com.game.service.impl.UserInfoServiceImpl;

@WebServlet("/user-info/*")
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService userInfoService = new UserInfoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = CommonView.getCmd(request);
		if ("list".equals(cmd)) {
			List<Map<String, String>> userList = userInfoService.selectUserInfoList(null);
			request.setAttribute("userList", userList);
		} else if ("view".equals(cmd)) {
			String uiNum = request.getParameter("uiNum");
			Map<String, String> userInfo = userInfoService.selectUserInfo(uiNum);
			request.setAttribute("userInfo", userInfo);
		} else if ("update".equals(cmd)) {
			String uiNum = request.getParameter("uiNum");
			Map<String, String> userInfo = userInfoService.selectUserInfo(uiNum);
			request.setAttribute("userInfo", userInfo);
		}
		CommonView.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cmd = CommonView.getCmd(request);
		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("uiId", request.getParameter("uiId"));
		userInfo.put("uiName", request.getParameter("uiName"));
		userInfo.put("uiPwd", request.getParameter("uiPwd"));
		userInfo.put("uiDesc", request.getParameter("uiDesc"));
		if (request.getParameter("uiBirth") != null) {
			userInfo.put("uiBirth", request.getParameter("uiBirth").replace("-", ""));
		}
		if ("insert".equals(cmd)) {
			int result=userInfoService.insertUserInfo(userInfo);
			request.setAttribute("msg", "유저 등록 성공");
			request.setAttribute("url", "/user-info/login");
			if(result != 1) {
				request.setAttribute("msg", "유저 등록 실패");
				request.setAttribute("url", "/user-info/insert");
			}

		} else if ("update".equals(cmd)) {
			String uiNum = request.getParameter("uiNum");
			userInfo.put("uiNum", uiNum);
			int result = userInfoService.updateUserInfo(userInfo);
			request.setAttribute("msg", "유저 수정 성공");
			request.setAttribute("url", "/user-info/view?uiNum=" + uiNum);
			if (result != 1) {
				request.setAttribute("msg", "유저 수정 실패");
				request.setAttribute("url", "/user-info/update?uiNum=" + uiNum);

			}
		} else if ("delete".equals(cmd)) {
			String uiNum = request.getParameter("uiNum");
			int result = userInfoService.deleteUserInfo(uiNum);
			request.setAttribute("msg", "유저 삭제 성공");
			request.setAttribute("url", "/user-info/list");
			if (result != 1) {
				request.setAttribute("msg", "유저 삭제 실패");
				request.setAttribute("url", "/user-info/view?uiNum=" + uiNum);
			}
		}

	}

}
