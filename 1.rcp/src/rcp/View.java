package rcp;

import java.sql.Connection;
import javax.inject.Inject;
import javax.swing.border.Border;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "1_.view";
	public Button execute,Conbt,showBooksbutton,showReadersbutton,showBooksInuseButton,deleteButton,addButton ;
	public Label databaseConnectionInfoLabel;
	Combo dropListcombo;
	Text intext,outtext;
	Font font1,font;
	Repository repository = new Repository(this);
	Connect connect = new Connect(this);
	@Inject IWorkbench workbench;
	@Inject Display display;
	@Override
	public void createPartControl(Composite parent) {
		Font boldFont16 = new Font( parent.getDisplay(), new FontData( "Arial", 16, SWT.BOLD ) );
		Font boldFont12 = new Font( parent.getDisplay(), new FontData( "Arial", 12, SWT.BOLD ) );
		GridLayout grid = new GridLayout(); 
		grid.numColumns =2;
		parent.setLayout(grid);
		
		//execute = new Button(parent, SWT.PUSH);
		//execute.setFont(boldFont16);
		//execute.setText("Выполнить");
		
		//createInputTextField(parent, boldFont12);
		
		//
		
		createOutputTextField(parent, boldFont12);
		
		createShouBooks(parent, boldFont12);
		
		createShowReaders(parent, boldFont12);
		
		createShowBooksInuse(parent, boldFont12);
		
		createAddButton(parent, boldFont12);
		
		createDeleteButton(parent, boldFont12);
		
		createDropList(parent, boldFont12);
		//createInputTextField(parent, boldFont12);
//		Conbt = new Button(parent,SWT.PUSH|SWT.CENTER);
//		Conbt.setFont(boldFont12);
//		Conbt.setText("Соединиться с БД");
		
		createStateConnectionLabel(parent, boldFont12);
		/*/Здесь все события на нажатия кнопок*/
		connect.setConnection();
		showBooksbutton.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequestBooks()));//12222222
		showReadersbutton.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequestReaders()));
		showBooksInuseButton.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequestInuse()));
		addButton.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.addRecord(dropListcombo.getText())));
		//intext.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> intext.setRedraw(true)));
		//execute.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequest()));

	}
	private void createAddButton(Composite parent, Font boldFont12) {
		addButton = new Button(parent,SWT.PUSH);
		addButton.setFont(boldFont12);
		addButton.setText("Новая запись");
	}
	private void createDeleteButton(Composite parent, Font boldFont12) {
		deleteButton = new Button(parent,SWT.PUSH);
		deleteButton.setFont(boldFont12);
		deleteButton.setText("Удалить запись");
	}

	private void createDropList(Composite parent, Font boldFont12) {
		dropListcombo=new Combo(parent,SWT.ABORT);
		dropListcombo.add("Книги");
		dropListcombo.add("Читатели");
		dropListcombo.add("Выданные книги");
		dropListcombo.setFont(boldFont12);
	}

	private void createStateConnectionLabel(Composite parent, Font boldFont12) {
		databaseConnectionInfoLabel = new Label(parent, SWT.CENTER|SWT.FILL);
		databaseConnectionInfoLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		databaseConnectionInfoLabel.setFont(boldFont12);
	}

	private void createShowBooksInuse(Composite parent, Font boldFont12) {
		showBooksInuseButton = new Button(parent,SWT.PUSH);
		showBooksInuseButton.setFont(boldFont12);
		showBooksInuseButton.setText("Выданные книги читателю.");
	}

	private void createShowReaders(Composite parent, Font boldFont12) {
		showReadersbutton = new Button(parent,SWT.PUSH);
		showReadersbutton.setFont(boldFont12);
		showReadersbutton.setText("Читатели");
	}

	private void createShouBooks(Composite parent, Font boldFont12) {
		showBooksbutton = new Button(parent,SWT.PUSH);
		showBooksbutton.setFont(boldFont12);
		showBooksbutton.setText("Все книги");
	}

	private void createOutputTextField(Composite parent, Font boldFont12) {
		outtext = new Text(parent, SWT.MULTI | SWT.BORDER  | SWT.V_SCROLL | SWT.CENTER  );        
		outtext.setLayoutData(new GridData(GridData.FILL_BOTH));
		outtext.setFont(boldFont12);
	}

	private void createInputTextField(Composite parent, Font boldFont12) {
		intext = new Text( parent, SWT.MULTI|SWT.WRAP);            
		intext.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		intext.setFont(boldFont12);
		intext.setLocation(100, 100);
	}

	@Override
	public void setFocus() {
		//parent.getControl().setFocus();
		outtext.setFocus();
	}
	
	public void createWindow() {
		
		Shell shell = new Shell (display);
		shell.setText("Snippet 108");
		Label label = new Label (shell, SWT.NONE);
		label.setText ("Enter your name:");
		Text text2 = new Text (shell, SWT.BORDER);
		text2.setLayoutData (new RowData (10, SWT.DEFAULT));
		Button ok = new Button (shell, SWT.PUSH);
		ok.setText ("OK");
        shell.setLayout(new FillLayout());
        shell.pack ();
        //TODO add some widgets to the Shell
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        //display.dispose();
		}
	}
