package classes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;

@WebServlet("/AddTrain")
public class AddTrain extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MongoClient client;
	public AddTrain() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
		String train = req.getParameter("name");
		String from = req.getParameter("from");
		String to = req.getParameter("to");
		
		BasicDBObject document = new BasicDBObject();
		document.put("train", train);
		document.put("from", from);
		document.put("to", to);
		
		MongoClientURI uri=new MongoClientURI("mongodb://raparthiss:haiboss117@ds019268.mlab.com:19268/aselab7db");
		client = new MongoClient(uri);
		@SuppressWarnings("deprecation")
		DB db=client.getDB(uri.getDatabase());
		DBCollection trains=db.getCollection("firstcollection");
		
		WriteResult result=trains.insert(document);
		
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST");
		resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
		resp.setHeader("Access-Control-Max-Age", "86400");

		resp.getWriter().write(result.toString());
		
//		resp.sendRedirect("AddTrain.html");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally 
		{
			client.close();
		}
	}
}
