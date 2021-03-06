package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import javax.naming.Binding;
import javax.swing.event.ChangeEvent;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;

import com.sshtools.j2ssh.transport.Service;

import at.bmlvs.NDMS.domain.instances.InstanceOnline;
import at.bmlvs.NDMS.domain.instances.Interface;
import at.bmlvs.NDMS.domain.templates.Command;
import at.bmlvs.NDMS.domain.templates.Parameter;
import at.bmlvs.NDMS.domain.templates.Section;
import at.bmlvs.NDMS.domain.templates.Snippet;
import at.bmlvs.NDMS.domain.templates.Template;
import at.bmlvs.NDMS.domain.templates.Value;
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
import javafx.scene.control.CheckBoxTreeItem;
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
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("xml/MainWindow.fxml"));
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

	public void configurePort(Interface intf){
		stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("xml/PortKonfWindow.fxml"));
		Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setTitle("Portkonfiguration");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		PortKonfController konfController = loader.<PortKonfController>getController();
		konfController.initData(intf);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	@FXML
	private void addnew(ActionEvent event) throws IOException
	{

		stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("xml/AddTab.fxml"));
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
		Parent root = FXMLLoader.load(getClass().getResource("xml/SnapshotWindow.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Snapshot-Manager");
		stage.getIcons().add(new Image("file:icons/ndms.png"));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(this.getScene().getWindow());
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

	@SuppressWarnings("static-access")
	@FXML
	public void templatusBox()
	{
		//templateBox.getItems().add("Templates");

		for (TemplateToPathLinker template : ServiceFactory.getPersistenceService().getTemplates())
		{
			if (template.getElement() != null)
			{
				try
				{
					if ((template.getElement().getOs_version().equals(ServiceFactory.getDomainService().getInstances().getInstances()
							.get(ServiceFactory.getPresentationService().getMainWindowController().getTabPane().getSelectionModel().getSelectedIndex())
							.getOs_Version()))
							&& (template.getElement().getDevice_type().equals(ServiceFactory.getDomainService().getInstances().getInstances()
									.get(ServiceFactory.getPresentationService().getMainWindowController().getTabPane().getSelectionModel().getSelectedIndex())
									.getDevice_Type())))
					{

						templateBox.getItems().add((template.getElement().getFullName()));
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}

		templateBox.setOnAction((event) -> {
			// System.out.println(templateBox.getSelectionModel().getSelectedItem());

				if (!templateBox.getSelectionModel().getSelectedItem().equals("Templates"))
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
			VBox overtheleftbox = new VBox();
			VBox leftbox = new VBox();
			FlowPane flowpie = new FlowPane();
			TitledPane titlepane = new TitledPane();
			
			
			SplitPane splitter = new SplitPane();
			splitter.setOrientation(Orientation.HORIZONTAL);

			TextArea show = new TextArea();
			show.disableProperty();
			show.setEditable(false);

			for (TemplateToPathLinker template : ServiceFactory.getPersistenceService().getTemplates())
			{
				if (template.getElement().getFullName().equals(templateBox.getSelectionModel().getSelectedItem()))
				{
					// UEBERSCHRIFT NAME DES TEMPLATES

					Label tempnamelabel = new Label(template.getElement().getFullName());
					tempnamelabel.setStyle("-fx-font-weight: bold;-fx-font-size: 15;");
					tempnamelabel.setPadding(new Insets(10, 10, 10, 10));
					
					template.getElement().setActivated(true);

					template.getElement().activatedProperty().addListener(new ChangeListener<Object>()
					{
						@Override
						public void changed(ObservableValue<? extends Object> arg0, Object old_val, Object new_val)
						{
							// // boolean oldValue = (Boolean) old_val;
							boolean newValue = (Boolean) new_val;
							//
							System.out.println("FIRED TEMPLATEPROPERTY: " + newValue);
							//
							// template.getElement().setActivated(newValue);
							//
							if (newValue == true)
							{
								template.getElement().activateChildren();
							}
							else
							{
								template.getElement().deactivateChildren();
							}
							//
							show.setText(template.getElement().receiveTemplateOutputAsString());
							//
							tempnamelabel.setDisable(!newValue);
						}
					});

					template.getElement().getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>()
					{
						@Override
						public void changed(ObservableValue<? extends Boolean> observable, Boolean old_val, Boolean new_val)
						{
							System.out.println("FIRED TEMPLATECHECKBOX: " + new_val);

							template.getElement().setActivated(new_val);
						}
					});

					// UEBERSCHRIFT NAME DES SNIPPETS
					GridPane templateGrid = new GridPane();

					// CheckBoxTreeItem<String> checktemplate =
					// new
					// CheckBoxTreeItem<String>(template.getElement().getFullName());
					// checktemplate.setExpanded(true);

					templateGrid.add(template.getElement().getCheckbox(), 0, 0);
					templateGrid.add(tempnamelabel, 1, 0);
					leftbox.getChildren().add(templateGrid);

					for (Snippet snippet : template.getElement().getSnippets())
					{
						Label snipnamelabel = new Label(snippet.getName());
						snipnamelabel.setStyle("-fx-font-weight: bold;-fx-font-size: 14;");
						snipnamelabel.setPadding(new Insets(10, 10, 10, 10));

						snippet.setActivated(true);

						snippet.activatedProperty().addListener(new ChangeListener<Object>()
						{
							@Override
							public void changed(ObservableValue<? extends Object> arg0, Object old_val, Object new_val)
							{
								// boolean oldValue = (Boolean) old_val;
								boolean newValue = (Boolean) new_val;

								System.out.println("FIRED SNIPPETPROPERTY: " + newValue);

								// snippet.setActivated(!newValue);

								if (newValue == true)
								{
									snippet.activateChildren();
								}
								else
								{
									snippet.deactivateChildren();
								}

								show.setText(template.getElement().receiveTemplateOutputAsString());

								snipnamelabel.setDisable(!newValue);
							}
						});

						snippet.getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>()
						{
							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean old_val, Boolean new_val)
							{
								System.out.println("FIRED SNIPPETCHECKBOX: " + new_val);

								snippet.setActivated(new_val);
							}
						});

						// UEBERSCHRIFT NAME DES SNIPPETS
						GridPane snippetGrid = new GridPane();

						Label spaceInvader0 = new Label("   ");

						snippetGrid.add(spaceInvader0, 1, 0);
						snippetGrid.add(snippet.getCheckbox(), 2, 0);
						snippetGrid.add(snipnamelabel, 3, 0);
						leftbox.getChildren().add(snippetGrid);

						// snippet.activatedProperty().bind(checksnippet.selectedProperty());

						// Bindings.bindBidirectional(snippet.activatedProperty(),
						// checksnippet.selectedProperty());

						for (Section section : snippet.getSections())
						{
							// UEBERSCHRIFT NAME DER SECTION
							Label secnamelabel = new Label(section.getName());
							secnamelabel.setStyle("-fx-font-weight: bold;-fx-font-size: 11;");
							secnamelabel.setPadding(new Insets(10, 10, 10, 10));
							
							section.setActivated(true);

							section.activatedProperty().addListener(new ChangeListener<Object>()
							{
								@Override
								public void changed(ObservableValue<? extends Object> arg0, Object old_val, Object new_val)
								{
//									boolean oldValue = (Boolean) old_val;
									boolean newValue = (Boolean) new_val;

									System.out.println("FIRED SECTIONPROPERTY: " + newValue);

									// section.setActivated(newValue);

									if (newValue == true)
									{
										section.activateChildren();
									}
									else
									{
										section.deactivateChildren();
									}

									show.setText(template.getElement().receiveTemplateOutputAsString());

									secnamelabel.setDisable(!newValue);
								}
							});

							section.getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>()
							{
								public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val)
								{
									System.out.println("FIRED SECTIONCHECKBOX: " + new_val);
//
//									section.setActivated(new_val);
								}
							});
							
							section.getCheckbox().indeterminateProperty().addListener(new ChangeListener<Boolean>()
							{
								public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val)
								{
									System.out.println("FIREDINDETERMINATE SECTIONCHECKBOX: " + new_val);
//
//									section.setActivated(new_val);
								}
							});

							// section.activatedProperty().bind(checksection.selectedProperty());

							// Bindings.bindBidirectional(section.activatedProperty(),
							// checksection.selectedProperty());
							GridPane sectionGrid = new GridPane();
							Label spaceInvader1 = new Label("   ");
							Label spaceInvader2 = new Label("   ");

							sectionGrid.add(spaceInvader1, 1, 0);
							sectionGrid.add(spaceInvader2, 2, 0);
							sectionGrid.add(section.getCheckbox(), 3, 0);
							sectionGrid.add(secnamelabel, 4, 0);
							leftbox.getChildren().add(sectionGrid);

							for (Command command : section.getCommands())
							{
								command.setActivated(true);

								if (command.isHidden() == false)
								{
									GridPane commandPane = new GridPane();

									Label spaceInvader3 = new Label("   ");
									Label spaceInvader4 = new Label("   ");
									Label spaceInvader5 = new Label("   ");
									Label commandlabel = new Label(command.getAlias());
									commandlabel.setPadding(new Insets(10, 10, 10, 10));

									commandPane.add(spaceInvader3, 0, 0);
									commandPane.add(spaceInvader4, 1, 0);
									commandPane.add(spaceInvader5, 2, 0);
									commandPane.add(command.getCheckbox(), 3, 0);
									commandPane.add(commandlabel, 4, 0);

									command.activatedProperty().addListener(new ChangeListener<Object>()
									{
										@Override
										public void changed(ObservableValue<? extends Object> arg0, Object old_val, Object new_val)
										{
//											boolean oldValue = (Boolean) old_val;
											boolean newValue = (Boolean) new_val;
											System.out.println("FIRED COMMANDPROPERTY: " + newValue);

											// command.setActivated(newValue);

											if (newValue == true)
											{
												command.activateChildren();
											}
											else
											{
												command.deactivateChildren();
											}

											show.setText(template.getElement().receiveTemplateOutputAsString());

											commandlabel.setDisable(!newValue);
										}
									});

									command.getCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>()
									{
										public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val)
										{
											System.out.println("FIRED COMMANDCHECKBOX: " + new_val);

											command.setActivated(new_val);
										}
									});

									// Bindings.bindBidirectional(command.activatedProperty(),
									// checkcommand.selectedProperty());

									// command.activatedProperty().bind(checkcommand.selectedProperty());

									leftbox.getChildren().add(commandPane);
								}

								for (Parameter parameter : command.getParameters())
								{
									parameter.setActivated(true);
									
									GridPane paraPane = new GridPane();

									Label paranamelabel = new Label(parameter.getAlias());

									Label spaceInvader6 = new Label("   ");
									Label spaceInvader7 = new Label("   ");
									Label spaceInvader8 = new Label("   ");
									Label spaceInvader9 = new Label("   ");
									Label spaceInvader10 = new Label("   ");

									// paranamelabel.setStyle("-fx-font-size: 11;");
									paranamelabel.setPadding(new Insets(10, 10, 10, 10));

									paraPane.add(spaceInvader6, 0, 0);
									paraPane.add(spaceInvader7, 1, 0);
									paraPane.add(spaceInvader8, 2, 0);
									paraPane.add(spaceInvader9, 3, 0);
									paraPane.add(spaceInvader10, 4, 0);

									paraPane.add(paranamelabel, 5, 0);

									parameter.activatedProperty().addListener(new ChangeListener<Object>()
									{
										@Override
										public void changed(ObservableValue<? extends Object> arg0, Object old_val, Object new_val)
										{
//											boolean oldValue = (Boolean) old_val;
											boolean newValue = (Boolean) new_val;

											System.out.println("FIRED PARAMETERPROPERTY: " + newValue);

											// parameter.setActivated(newValue);

											show.setText(template.getElement().receiveTemplateOutputAsString());

											paranamelabel.setDisable(!newValue);
											paraPane.setDisable(!newValue);
										}
									});

									if (parameter.getType().equals("DatatypeString"))
									{
										System.out.println("PARAM-DEFAULT:" + parameter.getDefaultValue());
										TextField dataString = new TextField(parameter.getDefaultValue());

										dataString.setId("" + parameter.getId());

										paraPane.add(dataString, 6, 0);
										dataString.focusedProperty().addListener(new ChangeListener<Boolean>()
										{
											@Override
											public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
											{
												if (newPropertyValue)
												{
													// System.out.println("Textfield on focus");
												}
												else
												{
													// System.out.println("Textfield lost focus");
													parameter.getValue().setValue(dataString.getText());

													if (parameter.getDefaultValue() != null)
													{
														parameter.getDefaultValues().getSelected().setValue(dataString.getText());
													}

													template.setChanged(true);
													// System.out.println(template.getPath());
													show.setText(template.getElement().receiveTemplateOutputAsString());
												}
											}
										});

									}
									if (parameter.getType().equals("DatatypeVlan"))
									{
										// WICHTIG Restriced Textfield!!!!
										TextField dataString = new TextField(parameter.getDefaultValue());

										dataString.setId("" + parameter.getId());

										paraPane.add(dataString, 6, 0);
										dataString.focusedProperty().addListener(new ChangeListener<Boolean>()
										{
											@Override
											public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
											{
												if (newPropertyValue)
												{
													// System.out.println("Textfield on focus");
												}
												else
												{
													// System.out.println("Textfield lost focus");
													parameter.getValue().setValue(dataString.getText());

													if (parameter.getDefaultValue() != null)
													{
														parameter.getDefaultValues().getSelected().setValue(dataString.getText());
													}

													// System.out.println(template.getPath());
													template.setChanged(true);
													show.setText(template.getElement().receiveTemplateOutputAsString());
												}
											}
										});
									}
									if (parameter.getType().equals("DatatypeChooseOneString"))
									{
										// WICHTIG Restriced Textfield!!!!

										ComboBox<Value> dataCombo = new ComboBox<Value>();
										dataCombo.setItems(FXCollections.observableArrayList(parameter.getDefaultValues()));

										//dataCombo.setId("" + parameter.getId());

										paraPane.add(dataCombo, 6, 0);
										// dataCombo.focusedProperty().addListener(new
										// ChangeListener<Boolean>()
										// {
										// @Override
										// public void changed(ObservableValue<?
										// extends Boolean> arg0, Boolean
										// oldPropertyValue, Boolean
										// newPropertyValue)
										// {
										// if (newPropertyValue)
										// {
										// //
										// System.out.println("Textfield on focus");
										// }
										// else
										// {
										// //
										// System.out.println("Textfield lost focus");
										// parameter.setValue(dataString.getText());
										//
										// if
										// (parameter.getDefaultValues().get(0)
										// != null)
										// {
										// parameter.getDefaultValues().set(0,
										// dataString.getText());
										// }
										//
										// //
										// System.out.println(template.getPath());
										// template.setChanged(true);
										// show.setText(template.getElement().receiveTemplateOutputAsString());
										// }
										// }
										// });
									}
									show.setText(template.getElement().receiveTemplateOutputAsString());

									leftbox.getChildren().add(paraPane);
								}
							}
						}
					}
				}

			}

			Button einspielen = new Button("Einspielen");
			einspielen.setPadding(new Insets(10, 10, 10, 10));
			einspielen.setAlignment(Pos.CENTER_RIGHT);

			einspielen.setOnAction(new EventHandler<ActionEvent>()
			{
				@SuppressWarnings("static-access")
				@Override
				public void handle(ActionEvent e)
				{
					for (TemplateToPathLinker template : ServiceFactory.getPersistenceService().getTemplates())
					{
						if (template.getElement().getFullName().equals(templateBox.getSelectionModel().getSelectedItem()))
						{
							try
							{
								if (ServiceFactory
										.getDomainService()
										.getInstances()
										.getInstances()
										.get(ServiceFactory.getPresentationService().getMainWindowController().getTabPane().getSelectionModel()
												.getSelectedIndex()).getClass() == InstanceOnline.class)
								{
									InstanceOnline inst = (InstanceOnline) ServiceFactory
											.getDomainService()
											.getInstances()
											.getInstances()
											.get(ServiceFactory.getPresentationService().getMainWindowController().getTabPane().getSelectionModel()
													.getSelectedIndex());

									inst.getSshConnector().sendMultipleCMD(template.getElement().receiveTemplateOutputAsArrayList());
								}
							}
							catch (Exception exc)
							{

							}
						}
					}

					ServiceFactory.getPersistenceService().saveAllChangedTemplates();
				}
			});

			sp.setContent(leftbox);

			flowpie.setFocusTraversable(false);
			flowpie.setAlignment(Pos.BASELINE_RIGHT);

			overtheleftbox.setFocusTraversable(false);
			overtheleftbox.getChildren().add(sp);
			flowpie.getChildren().add(einspielen);
			overtheleftbox.getChildren().add(flowpie);

			splitter.getItems().addAll(overtheleftbox, show);
			splitter.setDividerPositions(0.6f, 0.4f);

			viewstack.getChildren().add(splitter);

			//PresentationService.getMainWindowController().getTabPane().getTabs().get(id).getContent();
			
			PresentationService.getMainWindowController().getTabBorderPane().setCenter(viewstack);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// TEST

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