package rcp;

/*      ????? ??? ???????, ??????????, ???????? ?????? ?? ??           */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class Repository implements MouseListener{
	Connect connect;
	View view ;
	Repository(View view)	{this.view=view;}
	//Statement statement = null;
	String resultStr = "";
	List<String> lst = new ArrayList<String>();
	
/*      ????? ??? ???????? ???????, ??????????? ?? ??????? ????             */	
	
//	public void getrequest() {
//		try {
//			var statement = connect.conn.createStatement();
//			statement.executeQuery(view.intext.getText());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.getMessage();
//		}
//	}
	
	/*      ????? ??? ??????? ???? ???? ?? ??            */	
	
	public void getrequestBooks() {
		String selBookT = "SELECT * FROM books";
		try {
			var statement = connect.conn.createStatement();
			var queryResult = statement.executeQuery(selBookT);
			while (queryResult.next()) {
				for (int i = 1; i<=queryResult.getMetaData().getColumnCount(); i++)
				{
					lst.add(queryResult.getString(i));
				}
				for(String str:  lst) {
					resultStr += str +"   ";
					System.out.print(str +"   ");
				}
				resultStr += "\n";
				lst.clear();
			}
			view.outtext.setText(resultStr);
			//v.outtext.setText(resultStr);
			resultStr = "";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			view.outtext.setText(e1.getMessage());
		}
	}
	
	/*      ????? ??? ??????? ???? ????????? ?? ??            */	
	
	public void getrequestReaders() {
		String readers = "SELECT * FROM \"readers\"\n"
				+  "ORDER BY reader_id";
		try {
			var statement = connect.conn.createStatement();
			var queryResult = statement.executeQuery(readers);
			while (queryResult.next()) {
				//lst.add(queryResult.getString("READER_ID"));
				lst.add(queryResult.getString("LAST_NAME"));
				lst.add(queryResult.getString("NAME"));
				lst.add(queryResult.getString("PATRONYMIC"));
				lst.add(queryResult.getString("GENDER"));
				lst.add(queryResult.getString("AGE"));
				for(String str:  lst) {
					resultStr += str +"   ";
					System.out.print(str +"   ");
				}
				resultStr += "\n";
				lst.clear();
			}
			view.outtext.setText(resultStr);
			resultStr = "";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			view.outtext.setText(e1.getMessage());
		}
	}
	
	/*      ????? ??? ??????? ???? ???? ? ??????????? ?? ??            */	
	
	public void getrequestInuse() {
		String inuse = "SELECT * FROM \"taken_books\"\n"
				+ "JOIN books ON taken_books.book_id = Books.book_id JOIN readers ON taken_books.reader_id = readers.reader_id";// JOIN readers ON taken_books.reader_id = readers.reader_id";
		try {
			var statement = connect.conn.createStatement();
			var queryResult = statement.executeQuery(inuse);
			while (queryResult.next()) {
				lst.add(queryResult.getString("book_name"));
				lst.add(queryResult.getString("AUTHOR"));
				lst.add(queryResult.getString("ISSUE_YEAR"));
				lst.add(queryResult.getString("LAST_NAME"));
				lst.add(queryResult.getString("NAME"));
				lst.add(queryResult.getString("PATRONYMIC"));
				lst.add(queryResult.getString("GENDER"));
				lst.add(queryResult.getString("AGE"));
				lst.add(queryResult.getString("ISSUE_DATE"));
				lst.add(queryResult.getString("planned_return_DATE"));
				
				for(String str:  lst) {
					resultStr += str +"   ";
					System.out.print(str +"   ");
				}
				resultStr += "\n";
				lst.clear();
			}
			view.outtext.setText(resultStr);
			resultStr = "";
		} catch (SQLException e1) {
			view.outtext.setText(e1.getMessage());
		}
	}
	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		
		
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Object addRecord(String str) {
		switch (str) {
        case  ("?????"):
        	view.outtext.setText("?? ??????? ?????");
        	//view.createWindow();
        	JFrame jFrame = new JFrame();
        	String getMessage = JOptionPane.showInputDialog(jFrame, "???????? ?????");
        	String getMessage1 = JOptionPane.showInputDialog(jFrame, "?????");
        	String getMessage2 = JOptionPane.showInputDialog(jFrame, "??? ???????");
        	var birth = Integer.parseInt(getMessage2);
        	JOptionPane.showMessageDialog(jFrame, "Your message: "+getMessage+" " + getMessage1+" " + getMessage2);
        	String querystr = "INSERT INTO books (book_name,author,issue_year) VALUES ('"+ getMessage+"','"+ getMessage1+"',"+ birth+")";
        	try {
        		var statement = connect.conn.createStatement();
				statement.executeQuery(querystr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case ("????????"):
        	view.outtext.setText("?? ??????? ????????");
        	JFrame jFrame1 = new JFrame();
        	getMessage = JOptionPane.showInputDialog(jFrame1, "???????");
        	getMessage1 = JOptionPane.showInputDialog(jFrame1, "???");
        	getMessage2 = JOptionPane.showInputDialog(jFrame1, "????????");
        	String getMessage3 = JOptionPane.showInputDialog(jFrame1, "???");
        	String getMessage4 = JOptionPane.showInputDialog(jFrame1, "??? ????????");
        	//var gender = Integer.parseInt(getMessage3);
        	var birth1 = Integer.parseInt(getMessage4);
        	JOptionPane.showMessageDialog(jFrame1, "Your message: "+getMessage+" " + getMessage1+" " + getMessage2+" " + getMessage3);
        	querystr = "INSERT INTO readers (last_name,name,patronymic,gender,age) VALUES ('"+ getMessage+"','"+ getMessage1+"','"+ getMessage2+"','"+ getMessage3+"',"+birth1+")";
        	try {
        		var statement = connect.conn.createStatement();
				statement.executeQuery(querystr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case ("???????? ?????"):
        	view.outtext.setText("?? ??????? ???????? ?????");
            break;
        case (""):
            view.outtext.setText("?? ?????? ?? ???????");
            break;
        default:
            
            break;
    }
		return null;
	}

}
