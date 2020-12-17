package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.APIHelper;

/**
 * Servlet implementation class APIController
 */
@WebServlet("/APIController")
public class APIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求参数编码格式为utf-8，防止中文乱码
		request.setCharacterEncoding("utf-8");
		// 通过request获取ajax传来的msg值
		String msg = request.getParameter("msg");
		System.out.println("接收到的msg为：" + msg);
		// 准备返回的map
		Map<String, Object> map = new HashMap<String, Object>();

		APIHelper apiHelper = new APIHelper(msg);
		try {
			String jsonMsg = apiHelper.getJsonMsg();
			System.out.println("servle中接收到处理后的msg串为："+jsonMsg);
			map.put("msg", jsonMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String jsonStr = new Gson().toJson(map);
		// 字符流输出字符串
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
