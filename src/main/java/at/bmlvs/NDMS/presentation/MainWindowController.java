package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import javax.naming.Binding;
import javax.swing.event.ChangeEvent;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;

import com.sshtools.j2ssh.transport.Service;
import com.sun.javafx.binding.BidirectionalBinding;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
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
	private BorderPane tabBorderPane;

	public MainWindowController()
	{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"xml/MainWindow.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		tabBorderPane = new BorderPane();

		try
		{
			fxmlLoader.load();
		}
		catch (IOException exception)
		{
			throw new RuntimeException(exception);
		}
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
	public void templatusBox()
	{
		templateBox.getItems().add("Templates");

		for (TemplateToPathLinker template : ServiceFactory
				.getPersistenceService().getTemplates())
		{
			try
			{

				if ((template.getElement().getOs_version()
						.equals(ServiceFactory
								.getDomainService()
								.getInstances()
								.getInstances()
								.get(ServiceFactory.getPresentationService()
										.getMainWindowController().getTabPane()
										.getSelectionModel().getSelectedIndex())
								.getOs_Version()))
						&& (template.getElement().getDevice_type()
								.equals(ServiceFactory
										.getDomainService()
										.getInstances()
										.getInstances()
										.get(ServiceFactory
												.getPresentationService()
												.getMainWindowController()
												.getTabPane()
												.getSelectionModel()
												.getSelectedIndex())
										.getDevice_Type())))
				{

					templateBox.getItems().add(
							(template.getElement().getFullName()));
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

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
			ScrollPane sp = new ScrollPane();
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
						Label snipnamelabel = new Label(snippet.getName());
						snipnamelabel
								.setStyle("-fx-font-weight: bold;-fx-font-size: 14;");
						snipnamelabel.setPadding(new Insets(10, 10, 10, 10));

						snippet.setActivated(true);

						snippet.activatedProperty().addListener(
								new ChangeListener<Object>()
								{
									@Override
									public void changed(
											ObservableValue<? extends Object> arg0,
											Object old_val, Object new_val)
									{
										boolean oldValue = (Boolean) old_val;
										boolean newValue = (Boolean) new_val;
										
										System.out.println("FIRED SNIPPETPROPERTY: " + newValue);

//										snippet.setActivated(!newValue);

										if (newValue == true)
										{
											snippet.activateChildren();
										}
										else
										{
											snippet.deactivateChildren();
										}

										show.setText(template
												.getElement()
												.receiveTemplateOutputAsString());

										snipnamelabel.setDisable(!newValue);
									}
								});

						// UEBERSCHRIFT NAME DES SNIPPETS
						GridPane snippetGrid = new GridPane();

						CheckBox checksnippet = new CheckBox();

						snippetGrid.add(checksnippet, 0, 0);
						snippetGrid.add(snipnamelabel, 1, 0);
						leftbox.getChildren().add(snippetGrid);

						checksnippet.selectedProperty().addListener(
								new ChangeListener<Boolean>()
								{
									@Override
									public void changed(
											ObservableValue<? extends Boolean> observable,
											Boolean old_val, Boolean new_val)
									{
										System.out.println("FIRED SNIPPETCHECKBOX: " + !new_val);
										
										snippet.setActivated(!new_val);
									}
								});

						// snippet.activatedProperty().bind(checksnippet.selectedProperty());

						// Bindings.bindBidirectional(snippet.activatedProperty(),
						// checksnippet.selectedProperty());

						for (Section section : snippet.getSections())
						{
							section.setActivated(true);

							// UEBERSCHRIFT NAME DER SECTION
							Label secnamelabel = new Label(section.getName());
							secnamelabel
									.setStyle("-fx-font-weight: bold;-fx-font-size: 11;");
							secnamelabel.setPadding(new Insets(10, 10, 10, 10));

							section.activatedProperty().addListener(
									new ChangeListener<Object>()
									{
										@Override
										public void changed(
												ObservableValue<? extends Object> arg0,
												Object old_val, Object new_val)
										{
											boolean oldValue = (Boolean) old_val;
											boolean newValue = (Boolean) new_val;
											
											System.out.println("FIRED SECTIONPROPERTY: " + newValue);

//											section.setActivated(newValue);

											if (newValue == true)
											{
												section.activateChildren();
											}
											else
											{
												section.deactivateChildren();
											}

											show.setText(template
													.getElement()
													.receiveTemplateOutputAsString());

											secnamelabel.setDisable(!newValue);
										}
									});

							GridPane sectionGrid = new GridPane();

							CheckBox checksection = new CheckBox();
							checksection.selectedProperty().addListener(
									new ChangeListener<Boolean>()
									{
										public void changed(
												ObservableValue<? extends Boolean> ov,
												Boolean old_val, Boolean new_val)
										{
											System.out.println("FIRED SECTIONCHECKBOX: " + !new_val);
											
											section.setActivated(!new_val);
										}
									});

							// section.activatedProperty().bind(checksection.selectedProperty());

							// Bindings.bindBidirectional(section.activatedProperty(),
							// checksection.selectedProperty());

							sectionGrid.add(checksection, 0, 0);
							sectionGrid.add(secnamelabel, 1, 0);
							leftbox.getChildren().add(sectionGrid);

							for (Command command : section.getCommands())
							{
								
								command.setActivated(true);

								if (command.isHidden() == false)
								{
									GridPane commandPane = new GridPane();
									CheckBox checkcommand = new CheckBox();
									Label commandlabel = new Label(
											command.getAlias());
									commandlabel.setPadding(new Insets(10, 10,
											10, 10));
									commandPane.add(checkcommand, 0, 0);
									commandPane.add(commandlabel, 1, 0);

									command.activatedProperty().addListener(
											new ChangeListener<Object>()
											{
												@Override
												public void changed(
														ObservableValue<? extends Object> arg0,
														Object old_val,
														Object new_val)
												{
													boolean oldValue = (Boolean) old_val;
													boolean newValue = (Boolean) new_val;
													
													System.out.println("FIRED COMMANDPROPERTY: " + newValue);

//													command.setActivated(newValue);
													
													if (newValue == true)
													{
														command.activateChildren();
													}
													else
													{
														command.deactivateChildren();
													}

													show.setText(template
															.getElement()
															.receiveTemplateOutputAsString());

													commandlabel
															.setDisable(!newValue);
												}
											});

									
									checkcommand
											.selectedProperty()
											.addListener(
													new ChangeListener<Boolean>()
													{
														public void changed(
																ObservableValue<? extends Boolean> ov,
																Boolean old_val,
																Boolean new_val)
														{
															System.out.println("FIRED COMMANDCHECKBOX: " + !new_val);
															
															command.setActivated(!new_val);
														}
													});

									// Bindings.bindBidirectional(command.activatedProperty(),
									// checkcommand.selectedProperty());

									// command.activatedProperty().bind(checkcommand.selectedProperty());

									leftbox.getChildren().add(new CheckBox());
									leftbox.getChildren().add(commandPane);
								}

								for (Parameter parameter : command
										.getParameters())
								{

									GridPane paraPane = new GridPane();

									Label paranamelabel = new Label(
											parameter.getAlias());

									// paranamelabel.setStyle("-fx-font-size: 11;");
									paranamelabel.setPadding(new Insets(10, 10,
											10, 10));

									paraPane.add(paranamelabel, 1, 0);
									
									parameter.activatedProperty().addListener(
											new ChangeListener<Object>()
											{
												@Override
												public void changed(
														ObservableValue<? extends Object> arg0,
														Object old_val,
														Object new_val)
												{
													boolean oldValue = (Boolean) old_val;
													boolean newValue = (Boolean) new_val;
													
													System.out.println("FIRED PARAMETERPROPERTY: " + newValue);

//													parameter.setActivated(newValue);

													show.setText(template
															.getElement()
															.receiveTemplateOutputAsString());

													paranamelabel.setDisable(!newValue);
													paraPane.setDisable(!newValue);
												}
											});


									if (parameter.getType().equals(
											"DatatypeString"))
									{
										TextField dataString = new TextField(
												parameter.getDefaultValue());

										dataString
												.setId("" + parameter.getId());

										paraPane.add(dataString, 2, 0);
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
																	// System.out.println(template.getPath());
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
																	// System.out.println(template.getPath());
																	template.setChanged(true);
																	show.setText(template
																			.getElement()
																			.receiveTemplateOutputAsString());
																}
															}
														});
									}
									if (parameter.getType().equals("DatatypeObject"))
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
																	// System.out.println(template.getPath());
																	template.setChanged(true);
																	show.setText(template
																			.getElement()
																			.receiveTemplateOutputAsString());
																}
															}
														});
									}
									show.setText(template.getElement()
											.receiveTemplateOutputAsString());

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
											.sendMultipleCMD(
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
			sp.setContent(leftbox);
			splitter.getItems().addAll(sp, show);
			splitter.setDividerPositions(0.6f, 0.4f);

			viewstack.getChildren().add(splitter);

			// PresentationService.getMainWindowController().getTabPane().getTabs().get(id).getContent();

			PresentationService.getMainWindowController().getTabBorderPane()
					.setCenter(viewstack);

		}
		catch (Exception e)
		{
			e.printStackTrace();
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

	public BorderPane getTabBorderPane()
	{
		return tabBorderPane;
	}

	public void setTabBorderPane(BorderPane tabBorderPane)
	{
		this.tabBorderPane = tabBorderPane;
	}
}