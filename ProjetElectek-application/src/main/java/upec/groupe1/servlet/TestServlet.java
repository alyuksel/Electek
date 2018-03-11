package upec.groupe1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import upec.groupe1.entities.VoteOffices;
import upec.groupe1.session.ConcretEJB;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ConcretEJB<VoteOffices> tsb;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BEGIN");
		VoteOffices vo = new VoteOffices();
		vo.setAdress("test");
		vo.setCaption("test");
		vo.setNumber("test");
		vo.setPostalCode("test");
		
		tsb.create(vo);
		
		PrintWriter pw = response.getWriter();
		pw.println("ok");
		System.out.println("END");
	}

}
