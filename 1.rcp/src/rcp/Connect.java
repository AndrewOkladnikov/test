package rcp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

public class Connect implements MouseListener{
	View v ;
	Connect(View view){
		v=view;
	}
	static Connection conn;
	public void setConnection() {
		try{
		    Class.forName("org.postgresql.Driver");
		    } catch (ClassNotFoundException cnfe){
		      System.out.println("Could not find the JDBC driver!");
		      System.out.println(cnfe.getMessage());
		    }
		String url = "jdbc:postgresql://localhost:5432/test_db?user=postgres&password=1510kirs"; 
		try {
			conn = DriverManager.getConnection(url);
			v.databaseConnectionInfoLabel.setText("Соединение прошло успешно!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
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

}
