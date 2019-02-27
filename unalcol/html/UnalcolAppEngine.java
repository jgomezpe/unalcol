import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import unalcol.js.vc.mode.server.JSServerManager;
import unalcol.js.vc.mode.server.Servlet;

@WebServlet(
    name = "UnalcolAppEngine",
    urlPatterns = {"/unalcol"}
)
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{ doPost(request, response); }
  
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServerException, IOException {
		System.out.println("[UnalcolAppEngine.doPost]");
		String cmd = doPost(request.getReader());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print((cmd!=null)?cmd:"");  
	}
}