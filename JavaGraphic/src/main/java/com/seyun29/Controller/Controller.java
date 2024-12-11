package com.seyun29.Controller;

import com.seyun29.Helper;
import com.seyun29.Model.Canvas.CanvasModel;
import com.seyun29.Model.ShapeModel;
import com.seyun29.Model.Shape.*;
import com.seyun29.Model.Shape.Image;
import com.seyun29.Model.Shape.Rectangle;
import com.seyun29.Model.Shape.Shape;
import com.seyun29.View.Panel.CanvasPanel;
import com.seyun29.View.Panel.PropertyPanel;
import lombok.Getter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

@Getter
public class Controller {
    private final ShapeModel shapeModel;
    private final CanvasModel canvasModel;
    private PropertyPanel propertyPanel;
    private CanvasPanel canvasPanel;

    public Controller(ShapeModel shapeModel, CanvasModel canvasModel) {
        this.shapeModel = shapeModel;
        this.canvasModel = canvasModel;
    }

    public void setPanels(PropertyPanel propertyPanel, CanvasPanel canvasPanel) {
        this.propertyPanel = propertyPanel;
        this.canvasPanel = canvasPanel;
        shapeModel.addObserver(propertyPanel);
        shapeModel.addObserver(canvasPanel);
    }

    public void onMousePressed(MouseEvent e){
        canvasModel.onMousePressed(e, shapeModel);
    }

    public void onMouseReleased(MouseEvent e){
        canvasModel.onMouseReleased(e);
    }

    public void onMouseMoved(MouseEvent e){
        boolean containsShape = shapeModel.containsShape(e.getPoint()) != null;
        Cursor cursor = canvasModel.onMouseMoved(containsShape);
        canvasPanel.setCursor(cursor);
    }

    public void onMouseDragged(MouseEvent e){
        canvasModel.onMouseDragged(e, shapeModel);
    }

    public void drawAllShapes(Graphics2D g2d) {
        for (Shape shape : shapeModel.getShapes()) {
            shape.draw(g2d);
        }
    }

    public void createRandomShape() {
        Shape shape = Helper.createRandomShape();
        shapeModel.addShape(shape);
        canvasModel.setMode(CanvasModel.Mode.NORMAL);
    }

    public void setCanvasMode(CanvasModel.Mode mode) {
        canvasModel.setMode(mode);
    }

    public void drawText(String textInput) {
        Text text = Text.createText(textInput);
        shapeModel.addShape(text);
    }

    public void drawImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // Set default directory
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp", "jpeg"));
        int result = fileChooser.showOpenDialog(canvasPanel);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            Image image = Helper.createImage(selectedFile.getAbsolutePath());
            shapeModel.addShape(image);
        }
    }

    public void clearShapes() {
        shapeModel.clearShapes();
    }

    public void applyPropertyUpdate(){
        ArrayList<Shape> selectedShapes = shapeModel.getSelectedShapes();
        if (selectedShapes != null) {
            Shape currentShape = selectedShapes.get(0);
            shapeModel.updateShape(currentShape, propertyPanel);
        }
    }

    public void applyBringToFront() {
        ArrayList<Shape> selectedShape = shapeModel.getSelectedShapes();
        if (selectedShape != null) {
            shapeModel.bringToFront(selectedShape.get(0));
        }
    }

    public void applySendToBack(){
        ArrayList<Shape> selectedShape = shapeModel.getSelectedShapes();
        if (selectedShape != null) {
            shapeModel.sendToBack(selectedShape.get(0));
        }
    }

    public void applyRemoveShape(){
        ArrayList<Shape> selectedShape = shapeModel.getSelectedShapes();
        if (selectedShape != null) {
            shapeModel.removeShape(selectedShape.get(0));
        }
    }
}
