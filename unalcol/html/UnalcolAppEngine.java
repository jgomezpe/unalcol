import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unalcol.js.vc.mode.server.JSServerManager;
import unalcol.js.vc.mode.server.Servlet;

public class UnalcolAppEngine extends HttpServlet implements Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7738963051443120008L;

	protected JSServerManager frontend = null;
	
	public UnalcolAppEngine(){}
	
	@Override
	public JSServerManager frontend(){ return frontend; }

	@Override
	public void set(JSServerManager frontend){ this.frontend = frontend; }	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ doPost(request, response); }
  
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
        response.setContentType("text/plain");
		try{
			String cmd = doPost(request.getReader());
			response.getOutputStream().print((cmd!=null)?cmd:"");
		}catch(Exception e){
			response.getOutputStream().print(e.getMessage());
		}
/*		System.out.println("[UnalcolAppEngine.doPost]");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print((cmd!=null)?cmd:""); */  
	}
}