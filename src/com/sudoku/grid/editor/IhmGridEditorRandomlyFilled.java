/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sudoku.grid.editor;

import com.sudoku.data.model.Grid;
import com.sudoku.grid.ihm_grid_cells.IhmCell;
import com.sudoku.grid.ihm_grid_cells.IhmCellView;
import com.sudoku.grid.ihm_grid_cells.IhmGridLines.Flags;
import com.sudoku.grid.ihm_popups.IhmPopupsList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Mehdi KANE, Céline TO This class generates an entire filled grid, the
 * user can hide cells to make his own grid to be played
 */
public class IhmGridEditorRandomlyFilled extends IhmGridEditor {

  private Button deleteCells;
  private TextField deleteCellsField;

  public IhmGridEditorRandomlyFilled(String title, Flags flagStatus, Grid gr) {
    // Grid gr = generateRandomGrid();
    super(title, flagStatus, gr);

    // button with the number of cases that the user wants to hide randomly
    deleteCells = new Button("Delete Cells");
    deleteCellsField = new TextField("0");

    VBox leftLayout = (VBox) border.getLeft();
    leftLayout.getChildren().addAll(deleteCellsField, deleteCells);
    // A FAIRE : bloquer la case pour ne pas pouvoir entrer nombres négatifs

    deleteCells.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        IhmCell[][] cells = gridLines.getCells();
        int number = Integer.parseInt(deleteCellsField.getText());
        int nbNotHiddenCells = 0;
        // count number of visible cells
        for (int i = 0; i < cells.length; i++) {
          for (int j = 0; j < cells[i].length; j++) {
            if (!((IhmCellView) cells[i][j]).isHidden()) {
              nbNotHiddenCells++;
            }
          }
        }
        if ((nbNotHiddenCells - number) < 0) {
          String title = new String("Not enough filled cells");
          String text = new String(
                  "You can't hide that many cells");
          IhmPopupsList.getInstance().addPopup(title, text, 10);
        } else {
          LinkedList<IhmCellView> notHiddenCells = new LinkedList<IhmCellView>();
          // make a list of visible cells
          for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
              IhmCellView tmp = (IhmCellView) cells[i][j];
              if (!tmp.isHidden()) {
                notHiddenCells.add(tmp);
              }
            }
          }
          Collections.shuffle(notHiddenCells);
          // hide n cells (hidden = true)
          for (int i = 0; i < number; i++) {
            (notHiddenCells.poll()).setHidden(true);
          }
        }
      }
    });

  }

}
