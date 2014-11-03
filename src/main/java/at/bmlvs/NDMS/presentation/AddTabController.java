package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import at.bmlvs.NDMS.domain.helper.UUIDGenerator;
import at.bmlvs.NDMS.presentation.elements.RestrictiveTextField;
import at.bmlvs.NDMS.service.PresentationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddTabController
{
	private String tabname = "";
	
	private int tabid;
	private int portid;
	
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
	
	@FXML
	private void startconnection(ActionEvent event) throws IOException
	{
		
    	if(rbaddress.isSelected())
    	{
    		if((!ipaddress1.getText().equals("")) && (!ipaddress2.getText().equals("")) && (!ipaddress3.getText().equals("")) && (!ipaddress4.getText().equals("")))
    		{
    			tabname = ipaddress1.getText() + "." + ipaddress2.getText() + "." + ipaddress3.getText() + "." + ipaddress4.getText();
    			addTab(tabname);
    			
    		} else {
    			errorlabel.setText("Geben Sie alle Felder der IP-Addresse an!");
    		}
    	}
    	else if(rbrange.isSelected())
    	{
    		if((!iprange1.getText().equals("")) && (!iprange2.getText().equals("")) && (!iprange3.getText().equals("")) && (!iprange4.getText().equals("")) && (!iprange5.getText().equals("")) && (!iprange6.getText().equals("")) && (!iprange7.getText().equals("")) && (!iprange8.getText().equals("")))
    		{
    			tabname = iprange1.getText() + "." + iprange2.getText() + "." + iprange3.getText() + "." + iprange4.getText() + " - " + iprange5.getText() + "." + iprange6.getText() + "." + iprange7.getText() + "." + iprange8.getText();
    			addTab(tabname);
    			
    		} else {
    			errorlabel.setText("Geben Sie alle Felder der IP-Range an!");
    		}
    	}
    	else if(rboff.isSelected())
    	{
    		if(!offline1.getText().equals(""))
    		{
    			tabname = offline1.getText();
    			addTab(tabname);
    			
 			
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
	
	public int getTabid()
	{
		return tabid;
	}

	public void setTabid(int tabid)
	{
		this.tabid = tabid;
	}

	public int getPortid()
	{
		return portid;
	}

	public void setPortid(int portid)
	{
		this.portid = portid;
	}
	
	private void addTab(String tabname)
	{
		PresentationService.getMainWindowController().getTabPane().getTabs().add(getTabid(), new Tab(tabname));
		portview(getTabid());
		PresentationService.getMainWindowController().getStage().close();
		
		setTabid(getTabid() + 1);
	}
	
	//TEST
	
	private void portview(int id)
	{
		TilePane portview = new TilePane();
		
		int ports = 1;
		
		portview.setPadding(new Insets(5, 0, 5, 0));

		portview.setVgap(50);
		portview.setHgap(50);

		portview.setPrefColumns(2);
		portview.getChildren().add(portid,new Button("asdfasdf"));
		
		/*for (int i = 0; i < ports - 1; i++)
		{
			portview.getChildren().add(portid,new Button("" + i));

		}*/
		
	      //Add something in Tab
	      VBox tabbox = new VBox();
	      tabbox.getChildren().addAll(portview);
	      PresentationService.getMainWindowController().getTabPane().getTabs().get(id).setContent(tabbox);
	      PresentationService.getMainWindowController().getTabPane().getTabs().add(PresentationService.getMainWindowController().getTabPane().getTabs().get(id));
	     
	}
	
}
