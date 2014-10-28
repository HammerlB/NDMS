/*
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.bmlvs.NDMS.presentation;

import java.io.IOException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Sample custom control hosting a text field and a button.
 */
public class MainWindowController extends VBox {

    public MainWindowController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();            
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}

/*
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.image.*?>
<?import javafx.scene.shape.*?>
<?import java.lang.*?>
<?import java.util.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.paint.*?>
<?import javafx.scene.text.*?>

<VBox maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="803.0" prefWidth="915.0" xmlns="http://javafx.com/javafx/8" xmlns:fx="http://javafx.com/fxml/1">
  <children>
    <MenuBar VBox.vgrow="NEVER">
         <menus>
            <Menu mnemonicParsing="false" text="Datei">
              <items>
                <MenuItem mnemonicParsing="false" text="Neu">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallnew.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <SeparatorMenuItem mnemonicParsing="false" />
                  <MenuItem mnemonicParsing="false" text="Import">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallimport.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <MenuItem mnemonicParsing="false" text="Export">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallexport.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <SeparatorMenuItem mnemonicParsing="false" />
                  <MenuItem mnemonicParsing="false" text="Einstellungen">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallsettings.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <SeparatorMenuItem mnemonicParsing="false" />
                  <MenuItem mnemonicParsing="false" text="Beenden">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallclose.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
              </items>
            </Menu>
            <Menu mnemonicParsing="false" text="Konfiguration">
               <items>
                  <MenuItem mnemonicParsing="false" text="Portkonfiguration">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallportkonf.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <SeparatorMenuItem mnemonicParsing="false" />
                  <MenuItem mnemonicParsing="false" text="Vorlagen">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallvorlagen.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
               </items>
            </Menu>
            <Menu mnemonicParsing="false" text="Werkzeug">
               <items>
                  <MenuItem mnemonicParsing="false" text="Zur Standard-Konfiguration zur�cksetzen">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallresetstandart.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <SeparatorMenuItem mnemonicParsing="false" />
                  <MenuItem mnemonicParsing="false" text="Diagnose">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smalldiag.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
               </items>
            </Menu>
            <Menu mnemonicParsing="false" text="Hilfe">
               <items>
                  <MenuItem mnemonicParsing="false" text="Benutzerhandbuch">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallbook.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
                  <SeparatorMenuItem mnemonicParsing="false" />
                  <MenuItem mnemonicParsing="false" text="�ber NDSM">
                     <graphic>
                        <ImageView>
                           <image>
                              <Image url="@icons/smallabout.png" />
                           </image>
                        </ImageView>
                     </graphic>
                  </MenuItem>
               </items>
            </Menu>
         </menus>
    </MenuBar>
      <ToolBar>
        <items>
          <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/new.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
            <Separator orientation="VERTICAL" prefHeight="20.0" />
            <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/import.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
            <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/export.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
            <Separator orientation="VERTICAL" prefHeight="20.0" />
            <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/portkonf.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
            <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/vorlagen.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
            <Separator orientation="VERTICAL" prefHeight="20.0" />
            <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/resetstandart.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
            <Button contentDisplay="CENTER" focusTraversable="false" mnemonicParsing="false">
               <graphic>
                  <ImageView>
                     <image>
                        <Image url="@icons/diag.png" />
                     </image>
                  </ImageView>
               </graphic>
            </Button>
        </items>
      </ToolBar>
      <TabPane focusTraversable="false" prefHeight="730.0" prefWidth="1280.0" tabClosingPolicy="UNAVAILABLE">
         <tabs>
            <Tab text="Prolog" />
            <Tab text="Prolug" />
         </tabs>
      </TabPane>
  </children>
</VBox>
*/