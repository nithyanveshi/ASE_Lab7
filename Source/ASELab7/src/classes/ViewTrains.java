package classes;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@WebServlet("/ViewTrains")
public class ViewTrains extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ViewTrains() {
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
		MongoClientURI uri= new MongoClientURI("mongodb://raparthiss:haiboss117@ds019268.mlab.com:19268/aselab7db");

		MongoClient client= new MongoClient(uri);
		@SuppressWarnings("deprecation")
		DB db=client.getDB(uri.getDatabase());
		DBCollection trains = db.getCollection("firstcollection");
		
		DBObject trainOne = trains.findOne();
		System.out.println(trainOne);
		
		DBCursor cursor = trains.find();
		try {
			while (cursor.hasNext())
			{
				System.out.println("<p>"+cursor.next()+"</p>");
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally 
		{
			client.close();
		}
	}
	
}
