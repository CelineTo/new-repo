/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudoku.grid.editor;

import com.sudoku.data.model.Grid;
import com.sudoku.grid.ihm_grid_cells.IhmGridLines;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author mecton08
 */
public class SudokuTest extends Application {

  @Override
  public void start(Stage primaryStage) {

    IhmGridEditorRandomlyFilled ihm_test;
    IhmGridEditorManuallyFilled ihm_test2;

    //Grid gr = generateRandomGrid();
    Grid g = new Grid();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        g.setFixedCell((byte) i, (byte) j, (byte) 8);
      }
    }

    ihm_test = new IhmGridEditorRandomlyFilled("test randomly filled", IhmGridLines.ALL_VIEW, g);
    ihm_test2 = new IhmGridEditorManuallyFilled("test manually filled", IhmGridLines.ALL_EDITABLE, g);

    //Scene scene = new Scene(ihm_test, 800, 1000);
    Scene scene = new Scene(ihm_test, 800, 1000);

    primaryStage.setTitle("Sudoku Editor");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
