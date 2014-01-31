package br.ufpe.cin.reviewer.ui.rcp.literaturereview;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.Section;

import br.ufpe.cin.reviewer.core.literaturereview.LiteratureReviewController;
import br.ufpe.cin.reviewer.model.literaturereview.LiteratureReview;
import br.ufpe.cin.reviewer.model.study.Study;
import br.ufpe.cin.reviewer.ui.rcp.common.BaseView;
import br.ufpe.cin.reviewer.ui.rcp.common.ReviewerViewRegister;
import br.ufpe.cin.reviewer.ui.rcp.common.UIConstants;
import br.ufpe.cin.reviewer.ui.rcp.util.WidgetsFactory;
import br.ufpe.cin.reviewer.ui.rcp.util.WidgetsUtil;

public class LiteratureReviewPhasesView extends BaseView {

	public static final String ID = "br.ufpe.cin.reviewer.ui.rcp.literaturereview.LiteratureReviewStudiesView";
	
	private Section section;
	private Composite phaseComposite;
	
	private Section sectionGroups;
	private ToolBar toolbarGroups;
	private Composite groupsComposite;
	private Table groupsTable;
	
	private Section sectionStudies;
	private Composite studiesComposite;
	private Table studiesTable;

	private Button exportButton;
	private Button importButton;

	public LiteratureReviewPhasesView() {
		ReviewerViewRegister.putView(ID, this);
	}
	
	public void createPartControlImpl(Composite parent) {
		configureView(parent);
		createLiteratureStudiesWidgets(parent);
	}
	
	public void setFocus() {

	}
	
	private void configureView(Composite parent) {
		super.form.setText(super.form.getText() + " - Literature review studies");
		form.getBody().setLayout(new GridLayout(2, false));
	}
	
	private void createLiteratureStudiesWidgets(Composite parent) {
	    section = toolkit.createSection(form.getBody(), Section.NO_TITLE);
	    section.setLayout(new GridLayout(1, false));
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		phaseComposite = toolkit.createComposite(section);
		phaseComposite.setLayout(new GridLayout(2, false));
		phaseComposite.setLayoutData(new GridData());
		
		//Section for Groups
		sectionGroups = toolkit.createSection(phaseComposite, Section.SHORT_TITLE_BAR);
		sectionGroups.setText("PHASE EVALUATOR GROUPS");	    
		sectionGroups.setLayout(new GridLayout(1, false));
	    GridData sectionGroupsLayout = new GridData(GridData.FILL_VERTICAL);
	    sectionGroupsLayout.horizontalSpan = 1;
	    sectionGroups.setLayoutData(sectionGroupsLayout);
		
	    toolbarGroups = new ToolBar (sectionGroups, SWT.NONE);
	    ToolItem itemAddReview = new ToolItem(toolbarGroups, SWT.BUTTON1);
	    itemAddReview.setImage(new Image(form.getDisplay(),"C:/Arthur/add-1-icon.png"));
	    ToolItem itemDeleteReview = new ToolItem(toolbarGroups, SWT.BUTTON1);
	    itemDeleteReview.setImage(new Image(form.getDisplay(),"C:/Arthur/add-1-icon.png"));
	    sectionGroups.setTextClient(toolbarGroups);
		
	    groupsComposite = toolkit.createComposite(sectionGroups);
	    groupsComposite.setLayout(new GridLayout(2, false));
	    groupsComposite.setLayoutData(new GridData());

		//Info Table
		groupsTable = toolkit.createTable(groupsComposite, SWT.BORDER | SWT.FULL_SELECTION);
		groupsTable.setLinesVisible (true);
		groupsTable.setHeaderVisible (true);
		GridData groupsTableLayoutData = new GridData(GridData.FILL_BOTH);
		groupsTable.setLayoutData(groupsTableLayoutData);
		
		//Section for Studies List
		sectionStudies = toolkit.createSection(phaseComposite, Section.SHORT_TITLE_BAR);
		sectionStudies.setText("STUDIES LIST");	    
		sectionStudies.setLayout(new GridLayout(1, false));
	    GridData sectionStudiesLayout = new GridData(GridData.FILL_VERTICAL);
	    sectionStudiesLayout.horizontalSpan = 1;
	    sectionStudies.setLayoutData(sectionStudiesLayout);
		
	    studiesComposite = toolkit.createComposite(sectionStudies);
	    studiesComposite.setLayout(new GridLayout(2, false));
	    studiesComposite.setLayoutData(new GridData());

		//Info Table
		studiesTable = toolkit.createTable(studiesComposite, SWT.BORDER | SWT.FULL_SELECTION);
		studiesTable.setLinesVisible (true);
		studiesTable.setHeaderVisible (true);
		GridData studiesTableLayoutData = new GridData(GridData.FILL_BOTH);
		studiesTable.setLayoutData(studiesTableLayoutData);
		
		exportButton = toolkit.createButton(phaseComposite, "evaluate studies", SWT.PUSH);
		GridData exportButtonLayoutData = new GridData();
		exportButtonLayoutData.horizontalAlignment = SWT.RIGHT;
		exportButton.setLayoutData(exportButtonLayoutData);
		
		importButton = toolkit.createButton(phaseComposite, "evaluate studies", SWT.PUSH);
		GridData importButtonLayoutData = new GridData();
		importButtonLayoutData.horizontalAlignment = SWT.RIGHT;
		importButton.setLayoutData(importButtonLayoutData);
	}
	
	/*
	public static final String ID = "br.ufpe.cin.reviewer.ui.rcp.literaturereview.LiteratureReviewStudiesView";
	
	private LiteratureReview literatureReview;
	
	private Section section;
	private Composite studiesComposite;
	private StyledText titleText;
	private Table table;
	
	public LiteratureReviewPhasesView() {
		ReviewerViewRegister.putView(ID, this);
	}

	public void setLiteratureReview(LiteratureReview literatureReview) {
		this.literatureReview = literatureReview;

		titleText.setText(this.literatureReview.getTitle());

		table.removeAll();
		
		for (Study study : this.literatureReview.getStudies()) {
			Color red = form.getDisplay().getSystemColor(SWT.COLOR_RED);
			Color green = form.getDisplay().getSystemColor(SWT.COLOR_GREEN);
			Color yellow = form.getDisplay().getSystemColor(SWT.COLOR_YELLOW);
			TableItem item = new TableItem (table, SWT.NONE);
			
			item.setText (0, study.getCode());
			
			if(study.getStatus() == Study.StudyStatus.NOT_EVALUATED){
				item.setBackground(1,yellow);
			}
			else if(study.getStatus() == Study.StudyStatus.INCLUDED){
				item.setBackground(1,green);
			}
			else if(study.getStatus() == Study.StudyStatus.EXCLUDED){
				item.setBackground(1,red);
			}
			
			if (study.getTitle() != null) {
				item.setText(2, study.getTitle());
			}

			if (study.getYear() != null) {
				item.setText (3, study.getYear());
			}
		}
		
		for (int i=0; i < 4; i++) {
			table.getColumn(i).pack ();
		}
		
		this.section.setVisible(true);
		WidgetsUtil.refreshComposite(form.getBody());
	}
	
	public void createPartControlImpl(Composite parent) {
		configureView(parent);
		createLiteratureStudiesWidgets(parent);
	}
	
	private void configureView(Composite parent) {
		super.form.setText(super.form.getText() + " - Literature review studies");
		form.getBody().setLayout(new GridLayout(2, false));
	}

	private void createLiteratureStudiesWidgets(Composite parent) {
	    section = toolkit.createSection(form.getBody(), Section.NO_TITLE);
	    section.setLayout(new GridLayout(1, false));
		section.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		studiesComposite = toolkit.createComposite(section);
		studiesComposite.setLayout(new GridLayout(2, false));
		studiesComposite.setLayoutData(new GridData());
		
		Label titleLabel = toolkit.createLabel(studiesComposite, "TITLE: ");
		titleLabel.setFont(new Font(UIConstants.APP_DISPLAY, UIConstants.SYSTEM_FONT_NAME, 10, SWT.BOLD));
		titleLabel.setLayoutData(new GridData());
		
		this.titleText = WidgetsFactory.createStyledText(studiesComposite, SWT.FULL_SELECTION | SWT.READ_ONLY | SWT.WRAP);
		this.titleText.setFont(new Font(UIConstants.APP_DISPLAY, UIConstants.SYSTEM_FONT_NAME, 10, SWT.BOLD));
		this.titleText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.toolkit.adapt(this.titleText, true, true);

		table = toolkit.createTable(studiesComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData tableGridData = new GridData(GridData.FILL_BOTH);
		tableGridData.horizontalSpan = 2;
		table.setLayoutData(tableGridData);
		table.addMouseListener(new StudyMouseHandler());

		String[] titles = {"Code", "Status", "Title", "Year"};
		for (int i=0; i< titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [i]);
		}

		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}
		
		Hyperlink exportLink = toolkit.createHyperlink(studiesComposite, "Export studies to spreedsheet...", SWT.NONE);
		exportLink.addHyperlinkListener(new ExportLinkHandler());
		GridData exportLinkGridData = new GridData(GridData.HORIZONTAL_ALIGN_END);
		exportLinkGridData.horizontalSpan = 2;
		exportLink.setLayoutData(exportLinkGridData);
		
		section.setClient(studiesComposite);
	}
	
	public void setFocus() {
		this.table.setFocus();
	}

	private class StudyMouseHandler implements MouseListener {

		public void mouseDoubleClick(MouseEvent e) {
			IPerspectiveRegistry perspectiveRegistry = PlatformUI.getWorkbench().getPerspectiveRegistry();
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			activePage.setPerspective(perspectiveRegistry.findPerspectiveWithId(StudyAnalysisPerspective.ID));
			
			
			TableItem item = ((Table)e.getSource()).getSelection()[0];
			
			for (Study study : literatureReview.getStudies()) {
				if (study.getCode().equals(item.getText())) {
					StudyAnalysisView view = (StudyAnalysisView) ReviewerViewRegister.getView(StudyAnalysisView.ID);
					view.setLiteratureReview(literatureReview);
					view.setStudy(study);
					break;
				}
			}
		}

		public void mouseDown(MouseEvent e) {
			
		}

		public void mouseUp(MouseEvent e) {
			
		}
		
	}
	
	private class ExportLinkHandler implements IHyperlinkListener {

		public void linkEntered(HyperlinkEvent e) {
			
		}

		public void linkExited(HyperlinkEvent e) {
			
		}

		public void linkActivated(HyperlinkEvent e) {
			FileDialog fileDialog = new FileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), SWT.SAVE);
			fileDialog.setFilterExtensions(new String[] {"*.xls"});
			fileDialog.setOverwrite(true);
			fileDialog.open();

			String filePath = fileDialog.getFilterPath() + File.separator + fileDialog.getFileName();
			if (filePath != null && !filePath.trim().isEmpty()) {
				LiteratureReviewController controller = new LiteratureReviewController();
				controller.exportSudies(literatureReview, filePath);
			}
		}

	}

	*/
}
