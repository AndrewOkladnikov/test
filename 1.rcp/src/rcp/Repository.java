package rcp;

//import java.sql.Date;

/*      Класс для выборки, добавления, удаления данных из БД           */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Repository {
	Connect connect;
	View view ;
	Repository(View view)	{this.view=view;}
	String resultStr = "";
	List<String> lst = new ArrayList<String>();
	
	/*      Метод для выборки всех книг из БД            */	
	
	public void getrequestBooks() {
		String selBookT = "SELECT * FROM books";
		try {
			var statement = connect.conn.createStatement();
			var queryResult = statement.executeQuery(selBookT);
			createTable(queryResult);
//			while (queryResult.next()) {
//				for (int i = 1; i<=queryResult.getMetaData().getColumnCount(); i++)
//				{
//					lst.add(queryResult.getString(i));
//				}
//				
//				for(String str:  lst) {
//					resultStr += str +"   ";
//					System.out.print(str +"   ");
//				}
//				resultStr += "\n";
//				lst.clear();
//			}
//			view.outtext.setText(resultStr);
//			//v.outtext.setText(resultStr);
//			resultStr = "";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			view.outtext.setText(e1.getMessage());
		}
	}
	
	/*      Метод для выборки всех читателей из БД            */	
	
	public void getrequestReaders() {
		String readers = "SELECT * FROM readers ";
		try {
			var statement = connect.conn.createStatement();
			var queryResult = statement.executeQuery(readers);
			createTable(queryResult);
//			while (queryResult.next()) {
//				for (int i = 1; i<=queryResult.getMetaData().getColumnCount(); i++)
//				{
//					lst.add(queryResult.getString(i));
//				}
//				for(String str:  lst) {
//					resultStr += str +"   ";
//					//System.out.print(str +"   ");
//				}
//				resultStr += "\n";
//				lst.clear();
//			}
//			view.outtext.setText(resultStr);
//			resultStr = "";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			view.outtext.setText(e1.getMessage());
		}
	}
	
	/*      Метод для выборки всех книг в пользовании из БД            */	
	
	public void getrequestInuse() {
		var selcol = "books.name,books.author,readers.last_name,readers.name,readers.patronymic,taken_books.issue_date,taken_books.planned_return_date ";
		String inuse = "SELECT "+ selcol+ "FROM taken_books "
				+ "JOIN books ON taken_books.book_id = books.id JOIN readers ON taken_books.reader_id = readers.id";// JOIN readers ON taken_books.reader_id = readers.reader_id";
		try {
			var statement = connect.conn.createStatement();
			var queryResult = statement.executeQuery(inuse);
			createTable(queryResult);
//			while (queryResult.next()) {
//				for (int i = 1; i<=queryResult.getMetaData().getColumnCount(); i++)
//				{
//					lst.add(queryResult.getString(i));
//				}
//				
//				for(String str:  lst) {
//					resultStr += str +"   ";
//					System.out.print(str +"   ");
//				}
//				resultStr += "\n";
//				lst.clear();
//			}
//			view.outtext.setText(resultStr);
//			resultStr = "";
		} catch (SQLException e1) {
			view.outtext.setText(e1.getMessage());
		}
	}

	/*      Метод для обновления записи в таблицах            */	
	
	public void renameRecord(String str) {
		switch (str) {
        case  ("Книги"):
        	JFrame jFrame = new JFrame();
        	var oldNamestr = JOptionPane.showInputDialog(jFrame, "Старое название книги");
        	var newNamestr = JOptionPane.showInputDialog(jFrame, "Новое название книги");
        	var authorNamestr = JOptionPane.showInputDialog(jFrame, "Автор");
        	var yearstr = JOptionPane.showInputDialog(jFrame, "Год выпуска");
        	var year = Integer.parseInt(yearstr);
        	JOptionPane.showMessageDialog(jFrame, "Your message: "+oldNamestr+" " + newNamestr+" " + yearstr);
        	String querystr = "UPDATE books	SET name = '"+newNamestr+"',author = '"+authorNamestr+"',year = "+yearstr+ " WHERE name = '"+oldNamestr+"'";
        	try {
        		var statement = connect.conn.createStatement();
				statement.executeQuery(querystr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case ("Читатели"):
        	JFrame jFrame1 = new JFrame();
        	var oldLastNamestr = JOptionPane.showInputDialog(jFrame1, "Старая фамилия");
        	var newLastNamestr = JOptionPane.showInputDialog(jFrame1, "Новая фамилия");
        	var namestr = JOptionPane.showInputDialog(jFrame1, "Имя");
        	var otchestvostr = JOptionPane.showInputDialog(jFrame1, "Отчество");
        	var gender = JOptionPane.showInputDialog(jFrame1, "Пол");
        	var godRogdeniastr = JOptionPane.showInputDialog(jFrame1, "Год рождения");
        	var birthYear = Integer.parseInt(godRogdeniastr);
        	JOptionPane.showMessageDialog(jFrame1, "Your message: "+oldLastNamestr+" " + newLastNamestr+" " +
        	namestr+" " + otchestvostr+" " +gender+" " +godRogdeniastr);
        	querystr = "UPDATE  readers SET last_name = '"+newLastNamestr+"',name = '"+namestr+"',patronymic = '"+otchestvostr+"',gender = '"+gender+"',age = "+birthYear+ " WHERE last_name = '"+oldLastNamestr+"'";
        	try {
        		var statement = connect.conn.createStatement();
				statement.executeQuery(querystr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case ("Выданные книги"):
        
            break;
        case (""):
            view.outtext.setText("Вы ничего не выбрали");
            break;
        default:
            
            break;
    }
		//return null;
	}

	/*      Метод для добавления записи в таблицы            */
	
	public void addRecord(String str) {

		Date date1 = new Date();
		Date date2 = new Date();
		switch (str) {
        case  ("Книги"):
        	JFrame jFrame = new JFrame();
        	String getMessage = JOptionPane.showInputDialog(jFrame, "Название книги");
        	String getMessage1 = JOptionPane.showInputDialog(jFrame, "Автор");
        	String getMessage2 = JOptionPane.showInputDialog(jFrame, "Год выпуска");
        	var birth = Integer.parseInt(getMessage2);
        	JOptionPane.showMessageDialog(jFrame, "Your message: "+getMessage+" " + getMessage1+" " + getMessage2);
        	String querystr = "INSERT INTO books (name,author,year) VALUES ('"+ getMessage+"','"+ getMessage1+"',"+ birth+")";
        	try {
        		var statement = connect.conn.createStatement();
				statement.executeQuery(querystr);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            break;
        case ("Читатели"):
        	JFrame jFrame1 = new JFrame();
        	getMessage = JOptionPane.showInputDialog(jFrame1, "Фамилия");
        	getMessage1 = JOptionPane.showInputDialog(jFrame1, "Имя");
        	getMessage2 = JOptionPane.showInputDialog(jFrame1, "Отчество");
        	String getMessage3 = JOptionPane.showInputDialog(jFrame1, "Пол");
        	String getMessage4 = JOptionPane.showInputDialog(jFrame1, "Год рождения");
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
        case ("Выданные книги"):
        	view.outtext.setText("Вы выбрали Выданные книги");
        var jFrame2 = new JFrame();
    	var LastNamestr = JOptionPane.showInputDialog(jFrame2, "Фамилия читателя");
    	var BookNamestr = JOptionPane.showInputDialog(jFrame2, "Название книги");
    	var datestr = JOptionPane.showInputDialog(jFrame2, "Дата выдачи книги");
    	var ReturnDatestr = JOptionPane.showInputDialog(jFrame2, "Планируемая дата возврата книги");
			try {
				date1=new SimpleDateFormat("dd/MM/yyyy").parse(datestr);
				date2=new SimpleDateFormat("dd/MM/yyyy").parse(ReturnDatestr);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
    	JOptionPane.showMessageDialog(jFrame2, "Your message: "+LastNamestr+" " + BookNamestr+" " + datestr+" "+ReturnDatestr);
    	querystr = "INSERT INTO taken_books(book_id, reader_id, issue_date, planned_return_date) "+ 
    			"VALUES (( SELECT books.id FROM books WHERE name='"+BookNamestr+"')," 
    			  +"( SELECT readers.id FROM readers WHERE last_name='"+LastNamestr+"'),'"+date1+"', '"+date2+"' )";
    	try {
    		var statement = connect.conn.createStatement();
			statement.executeQuery(querystr);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			view.outtext.setText(e.getMessage());
		}
            break;
        case (""):
            view.outtext.setText("Вы ничего не выбрали");
            break;
        default:
            
            break;
    }
		
	}

	/*      Метод создания виджета с таблицей для отображения результатов поиска            */
	
	private void createTable(ResultSet queryRes) throws SQLException {
		Shell shell = new Shell(view.display);
        shell.setLayout(new GridLayout());
        
        Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = 200;
        table.setLayoutData(data);

        var num = queryRes.getMetaData().getColumnCount();
        for (int i = 0; i < num; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText("                            ");
            table.getColumn(i).pack();
        }
        while (queryRes.next()) {
            TableItem item = new TableItem(table, SWT.NONE);
            for (int i = 0; i < num; i++) {
            	if(queryRes.getString(i+1)==null) {
            		item.setText (i,"  ");
            		continue;
            		}
            	item.setText (i,queryRes.getString(i+1));
            	
            }
        }

        shell.pack ();
        shell.open ();

        while (!shell.isDisposed()) {
            if (!view.display.readAndDispatch()) {
            	view.display.sleep();
            }
        }
	}
}
