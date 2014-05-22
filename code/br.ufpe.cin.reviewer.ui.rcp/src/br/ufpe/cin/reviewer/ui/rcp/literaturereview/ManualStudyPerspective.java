package br.ufpe.cin.reviewer.ui.rcp.literaturereview;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class ManualStudyPerspective implements IPerspectiveFactory {

	public static final String ID = "br.ufpe.cin.reviewer.ui.rcp.ManualStudyPerspective";
	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
	}

}
