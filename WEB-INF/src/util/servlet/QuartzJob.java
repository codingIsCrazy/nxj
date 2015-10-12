package util.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.ConstantValue;
import util.StringUtil;


/**
 * @author Bomb
 * 
 */
public class QuartzJob  extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3000163169526807135L;
	ServletContext servlet = null;
	TimedTaskDispatcher ttd = null;
//	HourTask task;
	public void init(ServletConfig config) throws ServletException {
		
		//Users/liujianjun/javaworkspace/gwb/WEB-INF/classes/
		//Constants.class.getResource("/").toURI().resolve("../config").getPath() + "/";
        
		String timeTask = config.getServletContext().getInitParameter("timeTask");
		if(timeTask != null && timeTask.equalsIgnoreCase("true")){
			System.out.println("[MSG]timer task started");
			
//		    task = new HourTask();
//	        task.start();
	        
	        // Quartz 定时任务
	        ttd = TimedTaskDispatcher.getInstance();
	        ttd.start();
	        
	        NodeList list = null;
	        try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setIgnoringElementContentWhitespace(true);
				DocumentBuilder builder = factory.newDocumentBuilder();
				FileInputStream fis;
				String jobPath = ConstantValue.class.getResource("/").toURI().resolve("resources").getPath() + "/";
				//System.out.println(jobPath);
				fis = new FileInputStream(jobPath + "jobs.xml");
				Document document = builder.parse(fis);
				fis.close();
				
				Element rootElement = document.getDocumentElement();
				if(rootElement.getNodeName().equals("jobs")) {
					list = rootElement.getElementsByTagName("job");
				}
	        } catch (Exception e) {
	        	System.out.println("[WARNING]jobs config load failed");
	        	e.printStackTrace();
	        }
	        
	        if(list != null) {
		        for(int i = 0;i < list.getLength();i++) {
		        	Node node = list.item(i);
		        	Node name = node.getAttributes().getNamedItem("name");
	        		Node class1 = node.getAttributes().getNamedItem("class");
	        		Node cron = node.getAttributes().getNamedItem("cron");
	        		Node interval = node.getAttributes().getNamedItem("interval");	// 与cron冲突，表示间隔秒数
	        		
		        	try {
		        		Class c = Class.forName(class1.getNodeValue());
		        		if(cron != null)
		        			ttd.addTimedTask(name.getNodeValue(), null, c, cron.getNodeValue());
		        		else
		        			ttd.addTimedTask(name.getNodeValue(), null, c, StringUtil.toInt(interval.getNodeValue()));
		        		System.out.println("[MSG]job " + name.getNodeValue() + " started - " + c.getName());
		        	} catch(Exception e) {
		        		System.out.println("[ERROR]job " + name.getNodeValue() + " failed");
		        		e.printStackTrace();
		        	}
		        }
	        }

		}


	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#destroy()
	 */
	public void destroy() {

		// 停止Quartz 定时任务
		if(ttd != null)
			ttd.shutdown();
		System.out.println("[MSG]timer task stopped");

//		if(task != null)
//			task.interrupt();
		if (servlet != null) {

		}

		super.destroy();
	}

}
