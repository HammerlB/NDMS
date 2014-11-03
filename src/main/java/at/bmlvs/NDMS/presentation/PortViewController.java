package at.bmlvs.NDMS.presentation;

import at.bmlvs.NDMS.service.PresentationService;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;

public class PortViewController
{

	public void buildview()
	{
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
		
		Scene scene = new Scene(portview, 350, 300);  //Set the satge and launch the JavaFX application 
		
		//primaryStage.setScene(scene);
		PresentationService.getMainWindowController().getStage().setScene(scene);
	}
}
