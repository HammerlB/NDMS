/**
 * 
 */
package at.bmlvs.NDMS.presentation;

import java.io.IOException;
import java.util.ArrayList;

import at.bmlvs.NDMS.domain.instances.InstanceOnline;
import at.bmlvs.NDMS.domain.instances.Interface;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;

/**
 * @author GWD
 *
 */
public class PortKonfController {
	@FXML
	private Button portStatus;
	@FXML
	private RadioButton vlanDynamic;
	@FXML
	private RadioButton vlanAccess;
	@FXML
	private RadioButton vlanTrunk;
	@FXML
	private TextField vlanNumber;
	@FXML
	private TextField portNumber;

	public void initData(Interface intf) {
		portNumber.setText(intf.getPortnameshortDisplay());
		if(intf.getPortstatus().equals("2")){
			portStatus.setStyle("-fx-base: #FFB2B2;");
			portStatus.setText("Down");
		}
		else{			
			portStatus.setStyle("-fx-base: #CCFF99;");
			portStatus.setText("Up");
		}
		if(intf.getVlan().equals("All"))
			vlanTrunk.setSelected(true);
		else if(isNumeric(intf.getVlan())){
			vlanAccess.setSelected(true);
			vlanNumber.setText(intf.getVlan());
			vlanNumber.setDisable(false);
		}
		else
			vlanDynamic.setSelected(true);
	}
	
	/**
	 * 
	 * @param number
	 * @return
	 */
	private boolean isNumeric(String number) {		
		try{
			Integer.parseInt(number);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	
	@FXML
	public void changePortStatus(){
		if(portStatus.getText().equals("Down")){
			portStatus.setText("Up");
			portStatus.setStyle("-fx-base: #CCFF99;");
		}
		else{
			portStatus.setStyle("-fx-base: #FFB2B2;");
			portStatus.setText("Down");
		}
	}
	
	
	@FXML
	public void disableVlan(final ActionEvent event) throws IOException{
		vlanNumber.setDisable(true);
		vlanNumber.setText("");
	}
	
	@FXML
	public void enableVlan(final ActionEvent event) throws IOException{
		vlanNumber.setDisable(false);
	}
	
	@FXML
	public void sendConfiguration(){
		@SuppressWarnings("static-access")
		InstanceOnline inst = (InstanceOnline) ServiceFactory
				.getDomainService()
				.getInstances()
				.getInstances()
				.get(ServiceFactory.getPresentationService().getMainWindowController().getTabPane().getSelectionModel()
						.getSelectedIndex());
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("interface "+ portNumber.getText());
		if(portStatus.getText().equals("Down"))
			commands.add("shutdown");
		else
			commands.add("no shutdown");
		if(vlanTrunk.isSelected())
			commands.add("switchport mode trunk");
		if(vlanAccess.isSelected()){
			commands.add("switchport mode access");
			commands.add("switchport access vlan "+Integer.parseInt(vlanNumber.getText()));
		}
		if(vlanDynamic.isSelected()){
			commands.add("switchport mode dynamic auto");
		}
		inst.getSshConnector().sendMultipleCMD(commands);
		PresentationService.getMainWindowController().getStage().close();
	}
}
