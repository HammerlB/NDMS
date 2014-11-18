package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import javax.swing.event.ChangeEvent;

import javafx.beans.value.ChangeListener;

import com.sshtools.j2ssh.transport.Service;

import at.bmlvs.NDMS.domain.instances.InstanceOnline;
import at.bmlvs.NDMS.domain.templates.Command;
import at.bmlvs.NDMS.domain.templates.Parameter;
import at.bmlvs.NDMS.domain.templates.Section;
import at.bmlvs.NDMS.domain.templates.Snippet;
import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.linker.TemplateToPathLinker;
import at.bmlvs.NDMS.service.PresentationService;
import at.bmlvs.NDMS.service.ServiceFactory;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Sample custom control hosting a text field and a button.
 */
@SuppressWarnings("unused")
public class MainWindowController extends VBox
{
	@FXML
	private TabPane tabPane;
	private AddTabController tabcontrol = new AddTabController();
	private SnapshotController snapcontrol = new SnapshotController();
	private Stage stage;
	@FXML
	private ComboBox<String> templateBox;

	public MainWindowController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"xml/MainWindow.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try
		{
			fxmlLoader.load();
		}
		catch (IOException exception)
		{
			throw new RuntimeException(exception);
		}
		templatusBox();
	}

	@FXML
	private void addnew(ActionEvent event) throws IOException
	{

		stage = new Stage();
		Parent root = FXMLLoader
				.load(getClass().getResource("xml/AddTab.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Verbinden zu...");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	private void showsnapshotview(ActionEvent event) throws IOException
	{

		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource(
				"xml/SnapshotWindow.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Snapshot-Manager");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	@FXML
	private void templatusBox()
	{
		templateBox.getItems().add("Templates");

		for (TemplateToPathLinker template : ServiceFactory
				.getPersistenceService().getTemplates())
		{
			
			templateBox.getItems().add((template.getElement().getFullName()));
		
		}

		templateBox.setOnAction((event) -> {
			// System.out.println(templateBox.getSelectionModel().getSelectedItem());

				if (!templateBox.getSelectionModel().getSelectedItem()
						.equals("Templates"))
				{
					templateview(tabPane.getSelectionModel().getSelectedIndex());
				}
			});
	}

	private void templateview(int id)
	{

		try
		{
			StackPane viewstack = new StackPane();

			VBox leftbox = new VBox();

			SplitPane splitter = new SplitPane();
			splitter.setOrientation(Orientation.HORIZONTAL);

			TextArea show = new TextArea();
			show.disableProperty();
			show.setEditable(false);

			for (TemplateToPathLinker template : ServiceFactory
					.getPersistenceService().getTemplates())
			{
				if (template
						.getElement()
						.getFullName()
						.equals(templateBox.getSelectionModel()
								.getSelectedItem()))
				{
					// UEBERSCHRIFT NAME DES TEMPLATES

					Label tempnamelabel = new Label(template.getElement()
							.getFullName());

					tempnamelabel
							.setStyle("-fx-font-weight: bold;-fx-font-size: 15;");
					tempnamelabel.setPadding(new Insets(10, 10, 10, 10));

					leftbox.getChildren().add(tempnamelabel);

					for (Snippet snippet : template.getElement().getSnippets())
					{
						// UEBERSCHRIFT NAME DES SNIPPETS
						Label snipnamelabel = new Label(snippet.getName());

						snipnamelabel
								.setStyle("-fx-font-weight: bold;-fx-font-size: 14;");
						snipnamelabel.setPadding(new Insets(10, 10, 10, 10));

						leftbox.getChildren().add(snipnamelabel);

						for (Section section : snippet.getSections())
						{
							// UEBERSCHRIFT NAME DER SECTION
							Label secnamelabel = new Label(section.getName());

							secnamelabel
									.setStyle("-fx-font-weight: bold;-fx-font-size: 11;");
							secnamelabel.setPadding(new Insets(10, 10, 10, 10));

							leftbox.getChildren().add(secnamelabel);

							for (Command command : section.getCommands())
							{

								for (Parameter parameter : command
										.getParameters())
								{

									GridPane paraPane = new GridPane();

									Label paranamelabel = new Label(
											parameter.getName());

									// paranamelabel.setStyle("-fx-font-size: 11;");
									paranamelabel.setPadding(new Insets(10, 10,
											10, 10));

									paraPane.add(paranamelabel, 0, 0);

									if (parameter.getType().equals(
											"DatatypeString"))
									{
										TextField dataString = new TextField(
												parameter.getDefaultValue());

										dataString
												.setId("" + parameter.getId());

										paraPane.add(dataString, 1, 0);
										dataString
												.focusedProperty()
												.addListener(
														new ChangeListener<Boolean>()
														{
															@Override
															public void changed(
																	ObservableValue<? extends Boolean> arg0,
																	Boolean oldPropertyValue,
																	Boolean newPropertyValue)
															{
																if (newPropertyValue)
																{
																	// System.out.println("Textfield on focus");
																}
																else
																{
																	// System.out.println("Textfield lost focus");
																	parameter
																			.setValue(dataString
																					.getText());
																	parameter
																			.setDefaultValue(dataString
																					.getText());
																	template.setChanged(true);
																	System.out
																			.println(template
																					.getPath());
																	show.setText(template
																			.getElement()
																			.receiveTemplateOutputAsString());
																}
															}
														});

									}
									if (parameter.getType().equals(
											"DatatypeVlan"))
									{
										// WICHTIG Restriced Textfield!!!!
										TextField dataString = new TextField(
												parameter.getDefaultValue());

										dataString
												.setId("" + parameter.getId());

										paraPane.add(dataString, 1, 0);
										dataString
												.focusedProperty()
												.addListener(
														new ChangeListener<Boolean>()
														{
															@Override
															public void changed(
																	ObservableValue<? extends Boolean> arg0,
																	Boolean oldPropertyValue,
																	Boolean newPropertyValue)
															{
																if (newPropertyValue)
																{
																	// System.out.println("Textfield on focus");
																}
																else
																{
																	// System.out.println("Textfield lost focus");
																	parameter
																			.setValue(dataString
																					.getText());
																	parameter
																			.setDefaultValue(dataString
																					.getText());
																	System.out
																			.println(template
																					.getPath());
																	template.setChanged(true);
																	show.setText(template
																			.getElement()
																			.receiveTemplateOutputAsString());
																}
															}
														});
									}

									leftbox.getChildren().add(paraPane);
								}
							}
						}
					}
				}
			}

			Button einspielen = new Button("Einspielen");

			einspielen.setOnAction(new EventHandler<ActionEvent>()
			{
				@SuppressWarnings("static-access")
				@Override
				public void handle(ActionEvent e)
				{
					for (TemplateToPathLinker template : ServiceFactory
							.getPersistenceService().getTemplates())
					{
						if (template
								.getElement()
								.getFullName()
								.equals(templateBox.getSelectionModel()
										.getSelectedItem()))
						{
							try
							{
								if (ServiceFactory
										.getDomainService()
										.getInstances()
										.getInstances()
										.get(ServiceFactory
												.getPresentationService()
												.getMainWindowController()
												.getTabPane()
												.getSelectionModel()
												.getSelectedIndex()).getClass() == InstanceOnline.class)
								{
									InstanceOnline inst = (InstanceOnline) ServiceFactory
											.getDomainService()
											.getInstances()
											.getInstances()
											.get(ServiceFactory
													.getPresentationService()
													.getMainWindowController()
													.getTabPane()
													.getSelectionModel()
													.getSelectedIndex());

									inst.getSshConnector()
											.doSendMultipleCMD(
													template.getElement()
															.receiveTemplateOutputAsArrayList());
								}
							}
							catch (Exception exc)
							{

							}
						}
					}

					ServiceFactory.getPersistenceService()
							.saveAllChangedTemplates();
				}
			});

			leftbox.getChildren().add(einspielen);

			splitter.getItems().addAll(leftbox, show);
			splitter.setDividerPositions(0.6f, 0.4f);

			viewstack.getChildren().add(splitter);

			PresentationService.getMainWindowController().getTabPane()
					.getTabs().get(id).setContent(viewstack);

		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
	}

	@FXML
	private void showportview()
	{
		try
		{
			templateBox.getSelectionModel().select("Templates");
			tabcontrol.portview(tabPane.getSelectionModel().getSelectedIndex());

		}
		catch (Exception e)
		{
		}

	}

	public TabPane getTabPane()
	{
		return tabPane;
	}

	public void setTabPane(TabPane tabPane)
	{
		this.tabPane = tabPane;
	}

	public AddTabController getTabcontrol()
	{
		return tabcontrol;
	}

	public void setTabcontrol(AddTabController tabcontrol)
	{
		this.tabcontrol = tabcontrol;
	}

	public Stage getStage()
	{
		return stage;
	}

	public void setStage(Stage stage)
	{
		this.stage = stage;
	}
}