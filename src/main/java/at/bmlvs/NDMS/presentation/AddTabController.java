package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import at.bmlvs.NDMS.presentation.elements.RestrictiveTextField;
import at.bmlvs.NDMS.service.PresentationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddTabController
{
	private String tabname = "";
	private int tabcount = 0;
	
	@FXML
	private ToggleGroup radiotoggle;
	@FXML
	private RadioButton rbaddress;
	@FXML
	private RadioButton rbrange;
	@FXML
	private RadioButton rboff;
	@FXML
	private RestrictiveTextField ipaddress1;
	@FXML
	private RestrictiveTextField ipaddress2;
	@FXML
	private RestrictiveTextField ipaddress3;
	@FXML
	private RestrictiveTextField ipaddress4;
	@FXML
	private RestrictiveTextField iprange1;
	@FXML
	private RestrictiveTextField iprange2;
	@FXML
	private RestrictiveTextField iprange3;
	@FXML
	private RestrictiveTextField iprange4;
	@FXML
	private RestrictiveTextField iprange5;
	@FXML
	private RestrictiveTextField iprange6;
	@FXML
	private RestrictiveTextField iprange7;
	@FXML
	private RestrictiveTextField iprange8;
	@FXML
	private TextField offline1;
	@FXML
	private Label errorlabel;
	
	public int getTabcount()
	{
		return tabcount;
	}

	public void setTabcount(int tabcount)
	{
		this.tabcount = tabcount;
	}

	@FXML
	private void startconnection(ActionEvent event) throws IOException
	{
		
		final String id = UUIDGenerator.generateUUID();
		
    	if(rbaddress.isSelected())
    	{
    		if((!ipaddress1.getText().equals("")) && (!ipaddress2.getText().equals("")) && (!ipaddress3.getText().equals("")) && (!ipaddress4.getText().equals("")))
    		{
    			tabname = ipaddress1.getText() + "." + ipaddress2.getText() + "." + ipaddress3.getText() + "." + ipaddress4.getText();
    			PresentationService.getMainWindowController().getTabPane().getTabs().add(new Tab(tabname));
    			PresentationService.getMainWindowController().getStage().close();
    			
    		} else {
    			errorlabel.setText("Geben Sie alle Felder der IP-Addresse an!");
    		}
    	}
    	else if(rbrange.isSelected())
    	{
    		if((!iprange1.getText().equals("")) && (!iprange2.getText().equals("")) && (!iprange3.getText().equals("")) && (!iprange4.getText().equals("")) && (!iprange5.getText().equals("")) && (!iprange6.getText().equals("")) && (!iprange7.getText().equals("")) && (!iprange8.getText().equals("")))
    		{
    			tabname = iprange1.getText() + "." + iprange2.getText() + "." + iprange3.getText() + "." + iprange4.getText() + " - " + iprange5.getText() + "." + iprange6.getText() + "." + iprange7.getText() + "." + iprange8.getText();
    			PresentationService.getMainWindowController().getTabPane().getTabs().add(1, new Tab(tabname));
    			PresentationService.getMainWindowController().getStage().close();
    			
    		} else {
    			errorlabel.setText("Geben Sie alle Felder der IP-Range an!");
    		}
    	}
    	else if(rboff.isSelected())
    	{
    		if(!offline1.getText().equals(""))
    		{
    			tabname = offline1.getText();
    			PresentationService.getMainWindowController().getTabPane().getTabs().add(new Tab(tabname));
    			PresentationService.getMainWindowController().getStage().close();
    			
    			TilePane portview = new TilePane();

    			portview.setPadding(new Insets(5, 0, 5, 0));

    			portview.setVgap(3);
    			portview.setHgap(3);

    			portview.setPrefColumns(2);

    			for (int i = 0; i < 33; i++)
    			{
    				String[] days = { "", "", "1", "2", "3", "4", "5", "6", "7", "8",
    						"9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
    						"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
    						"29", "30", "31" };
    				portview.getChildren().add(new Text(days[i]));

    			}
    			
    			
    		} else {
    			errorlabel.setText("Geben Sie einen Namen an!");
    		}
    	}
    	PortViewController pv = new PortViewController();
    	pv.buildview();
	}

	@FXML
	private void offtoggle(ActionEvent event) throws IOException
	{
		iprange1.setDisable(true);
		iprange2.setDisable(true);
		iprange3.setDisable(true);
		iprange4.setDisable(true);
		iprange5.setDisable(true);
		iprange6.setDisable(true);
		iprange7.setDisable(true);
		iprange8.setDisable(true);
		
		iprange1.setText("");
		iprange2.setText("");
		iprange3.setText("");
		iprange4.setText("");
		iprange5.setText("");
		iprange6.setText("");
		iprange7.setText("");
		iprange8.setText("");
		
		ipaddress1.setDisable(true);
		ipaddress2.setDisable(true);
		ipaddress3.setDisable(true);
		ipaddress4.setDisable(true);
		
		ipaddress1.setText("");
		ipaddress2.setText("");
		ipaddress3.setText("");
		ipaddress4.setText("");
		
		offline1.setDisable(false);
		
	}

	@FXML
	private void iptoggle(ActionEvent event) throws IOException
	{
		iprange1.setDisable(true);
		iprange2.setDisable(true);
		iprange3.setDisable(true);
		iprange4.setDisable(true);
		iprange5.setDisable(true);
		iprange6.setDisable(true);
		iprange7.setDisable(true);
		iprange8.setDisable(true);

		iprange1.setText("");
		iprange2.setText("");
		iprange3.setText("");
		iprange4.setText("");
		iprange5.setText("");
		iprange6.setText("");
		iprange7.setText("");
		iprange8.setText("");

		offline1.setDisable(true);
		offline1.setText("");
		
		ipaddress1.setDisable(false);
		ipaddress2.setDisable(false);
		ipaddress3.setDisable(false);
		ipaddress4.setDisable(false);
	
	}

	@FXML
	private void rangetoggle(ActionEvent event) throws IOException
	{
		iprange1.setDisable(false);
		iprange2.setDisable(false);
		iprange3.setDisable(false);
		iprange4.setDisable(false);
		iprange5.setDisable(false);
		iprange6.setDisable(false);
		iprange7.setDisable(false);
		iprange8.setDisable(false);
		
		offline1.setDisable(true);
		offline1.setText("");

		ipaddress1.setDisable(true);
		ipaddress2.setDisable(true);
		ipaddress3.setDisable(true);
		ipaddress4.setDisable(true);

		ipaddress1.setText("");
		ipaddress2.setText("");
		ipaddress3.setText("");
		ipaddress4.setText("");
	}
	
	private void addTab(String tabname)
	{
		PresentationService.getMainWindowController().getTabPane().getTabs().add(getTabcount(), new Tab(tabname));
		setTabcount(getTabcount() + 1);
	}
	
	
}
