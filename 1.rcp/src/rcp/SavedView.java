package rcp;


import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import javax.inject.Inject;
import javax.swing.SpringLayout;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.widgets.WidgetFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

import static org.eclipse.swt.events.SelectionListener.*;

public class SavedView extends ViewPart {
	public static final String ID = "1.rcp.view";
	//JDBCPostgreSQLConnect
	@Inject IWorkbench workbench;
	@Inject
	Display display;
	
	private TableViewer viewer;
	
	private class StringLabelProvider extends ColumnLabelProvider {
		
		// getText method is used from super class ColumnLabelProvider

		@Override
		public Image getImage(Object obj) {
			return workbench.getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}

	}
	
	public class ShowMapHandler {
	    @Execute
	    public void execute(IEclipseContext context) {
	        // add objects to the active local context injected into
	        // this handler
	        // ...
	    }

	}

	@Override
	public void createPartControl(Composite parent) {
		Font font = new Font(parent.getDisplay(),"Arial", 16, SWT.BOLD );
		//createSnippet(font);
        //createTable();
        //createGridLayout();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e2) {
			System.out.println("Драйвер не найден");
			//e2.printStackTrace();
			System.out.println(e2.getMessage());
		}
		System.out.println("Драйвер найден");
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1510kirs");
			System.out.println("Success connection");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		}
		GridLayout layout = new GridLayout(3,false); ;//= new Layout();
//		GridLayout grid = new GridLayout();
		
		//layout.numColumns = 3;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE|SWT.BORDER).setText("Введите ФИО через пробел");
		
		Text multiText = new Text(parent,SWT.MULTI|SWT.WRAP|SWT.BORDER|SWT.FILL);//Создание первого текстового поля
		multiText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		multiText.setText("Строка");
		//multiText.setEditable(true);
		
		Text text = new Text(parent, SWT.MULTI|SWT.WRAP|SWT.BORDER|SWT.FILL);//Создание второго текстового поля
		text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		text.setText("Ура!!!  Строка ввода!!!");
		
		Button b1 = new Button(parent, SWT.PUSH|SWT.CENTER);
		//b1.setFont(font);
		b1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		b1.setText("Press me, please!");
		
		Button b2 = new Button(parent, SWT.PUSH|SWT.CENTER);
		b2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,2,3));
		b2.setText("Нажми меня");
		b2.addSelectionListener(SelectionListener.widgetSelectedAdapter(e-> createTable()));
		
		WidgetFactory.button(SWT.PUSH).layoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true)).text("Press me").onSelect(e-> createGridLayout()).create(parent);
		WidgetFactory.button(SWT.PUSH).layoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true)).text("Нажми").onSelect(e-> multiText.setText("Ещё одна кнопка нажата!!!")).create(parent);
//		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
//		column.setLabelProvider(new StringLabelProvider());

//		viewer.getTable().getColumn(0).setWidth(200);
//		
//		viewer.setContentProvider(ArrayContentProvider.getInstance());
//		
//		// Provide the input to the ContentProvider
//		viewer.setInput(createInitialDataModel());
	}

	private void createGridLayout() {
		Shell shell = new Shell(display);

        // create a new GridLayout with two columns
        // of different size
        GridLayout layout1 = new GridLayout(2, false);
        //layout1.makeColumnsEqualWidth= false;
        // set the layout to the shell
        shell.setLayout(layout1);

        // create a label and a button
        Label label = new Label(shell, SWT.NONE);
        label.setText("A label");
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Press Me");

        // create a new label that will span two columns
        label = new Label(shell, SWT.BORDER);
        label.setText("This is a label");
        // create new layout data
        GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
        label.setLayoutData(data);

        // create a new label which is used as a separator
        label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);

        // create new layout data
        data = new GridData(SWT.FILL, SWT.TOP, true, false);
        data.horizontalSpan = 2;
        label.setLayoutData(data);

        // creates a push button
        Button b = new Button(shell, SWT.PUSH);
        b.setText("New Button");

        data = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        b.setLayoutData(data);

         // create a spinner with min value 0 and max value 1000
        Spinner spinner = new Spinner(shell, SWT.READ_ONLY);
        spinner.setMinimum(0);
        spinner.setMaximum(1000);
        spinner.setSelection(500);
        spinner.setIncrement(1);
        spinner.setPageIncrement(100);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.widthHint = SWT.DEFAULT;
        gridData.heightHint = SWT.DEFAULT;
        gridData.horizontalSpan = 2;
        spinner.setLayoutData(gridData);

        Composite composite = new Composite(shell, SWT.BORDER);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        composite.setLayoutData(gridData);
        composite.setLayout(new GridLayout(1, false));

        Text txtTest = new Text(composite, SWT.NONE);
        txtTest.setText("Testing");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        txtTest.setLayoutData(gridData);

        Text txtMoreTests = new Text(composite, SWT.NONE);
        txtMoreTests.setText("Another test");

        Group group = new Group(shell, SWT.NONE);
        group.setText("This is my group");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        group.setLayoutData(gridData);
        group.setLayout(new RowLayout(SWT.VERTICAL));
        Text txtAnotherTest = new Text(group, SWT.NONE);
        txtAnotherTest.setText("Another test");

        // Children of this widget should get a fixed size
        Composite fixedElements = new Composite(shell, SWT.BORDER);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        fixedElements.setLayoutData(gridData);
        fixedElements.setLayout(new GridLayout(2, false));
        Label label2 = new Label(fixedElements, SWT.BORDER);
        GridData layoutData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        layoutData.widthHint=200;
        label2.setLayoutData(layoutData);
        label2.setText("Fixed");
        Label label3 = new Label(fixedElements, SWT.BORDER);
        GridData layoutData2 = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
        layoutData2.widthHint=20;
        label3.setLayoutData(layoutData2);
        label3.setText("Small but still fixed");

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        //display.dispose();
	}

	private void createTable() {
		Shell shell = new Shell(display);
        shell.setLayout(new GridLayout());
        
        Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        
        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
        data.heightHint = 200;
        table.setLayoutData(data);

        String[] titles = { "First Name", "Last Name", "Age" };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }

        for (int i = 0 ; i<= 20 ; i++){
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText (0, "Person " +i );
            item.setText (1, "LastName " +i );
            item.setText (2, String.valueOf(i));
        }

        for (int i=0; i<titles.length; i++) {
            //table.getColumn (i).pack ();
        }
        shell.pack ();
        shell.open ();

//        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
	}

	private void createSnippet(Font font) {
		Shell shell = new Shell (display);
		shell.setText("Snippet 108");
		Label label = new Label (shell, SWT.NONE);
		label.setText ("Enter your name:");
		Text text2 = new Text (shell, SWT.BORDER);
		text2.setLayoutData (new RowData (100, SWT.DEFAULT));
		Button ok = new Button (shell, SWT.PUSH);
		ok.setFont( font );
		ok.setText ("OK");
		ok.addSelectionListener(widgetSelectedAdapter(e -> text2.setText("OK")));
		Button cancel = new Button (shell, SWT.PUSH);
		cancel.setText ("Cancel");
		cancel.addSelectionListener(widgetSelectedAdapter(e -> text2.setText("CANSEL")));
		shell.setDefaultButton (cancel);
		shell.setLayout (new RowLayout ());
		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public String getRequest(String table) {
		String selectTableSQL = "SELECT * FROM public.\""+ table +"\"\n"
				+  "ORDER BY book_id ASC ";
		return selectTableSQL;
	}
}