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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "1_.view";
	public Button RenameBut,Conbt,showBooksBut,showReadersBut,showBooksInuseBut,deleteBut,addBut,AddTakenBookBut ;
	public Label databaseConnectionInfoLabel;
	Combo dropListcombo;
	Text outtext;
	Font font1,font;
	Repository repository = new Repository(this);
	Connect connect = new Connect();
	@Inject IWorkbench workbench;
	@Inject Display display;
	@Override
	public void createPartControl(Composite parent) {
		Font boldFont16 = new Font( parent.getDisplay(), new FontData( "Arial", 16, SWT.BOLD ) );
		Font boldFont12 = new Font( parent.getDisplay(), new FontData( "Arial", 12, SWT.BOLD ) );
		GridLayout grid = new GridLayout(5,true); 
		parent.setLayout(grid);
		
		createRenameBut(parent, boldFont16);
		
		createOutputTextField(parent, boldFont12);
		
		createShowBooksBut(parent, boldFont12);
		
		createShowReadersBut(parent, boldFont12);
		
		createShowBooksInuseBut(parent, boldFont12);
		
		createAddBut(parent, boldFont12);
		
		createDeleteBut(parent, boldFont12);
		
		//createAddTakenBookBut(parent,boldFont16);
		
		createDropList(parent, boldFont12);
		
		createStateConnectionLabel(parent, boldFont12);
		
		var isSuccessfulConnection = connect.setConnection();
		if (isSuccessfulConnection)
		{
			databaseConnectionInfoLabel.setText("Соединение прошло успешно!");
		}
		else
		{
			databaseConnectionInfoLabel.setText("Не удалось установить соединение с базой.");
		}
		showBooksBut.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequestBooks()));
		showReadersBut.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequestReaders()));
		showBooksInuseBut.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.getrequestInuse()));
		addBut.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.addRecord(dropListcombo.getText())));
		RenameBut.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> repository.renameRecord(dropListcombo.getText())));
		//AddTakenBookBut.addSelectionListener(null);
	}
	private void createAddTakenBookBut(Composite parent, Font boldFont16) {
		AddTakenBookBut = new Button(parent, SWT.PUSH);
		AddTakenBookBut.setFont(boldFont16);
		AddTakenBookBut.setText("Обновить");
	}
	
	private void createRenameBut(Composite parent, Font boldFont16) {
		RenameBut = new Button(parent, SWT.PUSH);
		RenameBut.setFont(boldFont16);
		RenameBut.setText("Обновить");
	}
	private void createAddBut(Composite parent, Font boldFont12) {
		addBut = new Button(parent,SWT.PUSH);
		addBut.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		addBut.setFont(boldFont12);
		addBut.setText("Новая запись");
	}
	private void createDeleteBut(Composite parent, Font boldFont12) {
		deleteBut = new Button(parent,SWT.PUSH);
		deleteBut.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		deleteBut.setFont(boldFont12);
		deleteBut.setText("Удалить запись");
	}

	private void createDropList(Composite parent, Font boldFont12) {
		dropListcombo=new Combo(parent,SWT.ABORT);
		dropListcombo.add("Книги");
		dropListcombo.add("Читатели");
		dropListcombo.add("Выданные книги");
		dropListcombo.setFont(boldFont12);
	}

	private void createStateConnectionLabel(Composite parent, Font boldFont12) {
		databaseConnectionInfoLabel = new Label(parent, SWT.CENTER);
		databaseConnectionInfoLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true,4,1));
		databaseConnectionInfoLabel.setFont(boldFont12);
	}

	private void createShowBooksInuseBut(Composite parent, Font boldFont12) {
		showBooksInuseBut = new Button(parent,SWT.PUSH);
		showBooksInuseBut.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		showBooksInuseBut.setFont(boldFont12);
		showBooksInuseBut.setText("Выданные книги читателю");
	}

	private void createShowReadersBut(Composite parent, Font boldFont12) {
		showReadersBut = new Button(parent,SWT.PUSH);
		showReadersBut.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		showReadersBut.setFont(boldFont12);
		showReadersBut.setText("Читатели");
	}

	private void createShowBooksBut(Composite parent, Font boldFont12) {
		showBooksBut = new Button(parent,SWT.PUSH);
		showBooksBut.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		showBooksBut.setFont(boldFont12);
		showBooksBut.setText("Книги");
	}

	private void createOutputTextField(Composite parent, Font boldFont12) {
		outtext = new Text(parent, SWT.MULTI | SWT.BORDER  | SWT.V_SCROLL | SWT.CENTER  );        
		outtext.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,2,3));
		outtext.setFont(boldFont12);
	}

//	private void createInputTextField(Composite parent, Font boldFont12) {
//		intext = new Text( parent, SWT.MULTI|SWT.WRAP);            
//		intext.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
//		intext.setFont(boldFont12);
//		intext.setLocation(100, 100);
//	}

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
