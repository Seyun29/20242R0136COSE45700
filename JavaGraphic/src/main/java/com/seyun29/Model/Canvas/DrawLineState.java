package com.seyun29.Model.Canvas;

import com.seyun29.Helper;
import com.seyun29.Model.Shape.Line;
import com.seyun29.Model.Shape.Shape;
import com.seyun29.Model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawLineState implements CanvasState {
    @Override
    public void onMousePressed(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel) {
        canvasModel.setStartPoint(e.getPoint());
        Point startPoint = canvasModel.getStartPoint();
        Line currentLine = new Line(startPoint.getX(), startPoint.getY(), startPoint.getX(), startPoint.getY(), 3, Helper.randomColor());
        canvasModel.setCurrentDrawnShape(currentLine);
        shapeModel.addShape(currentLine);
    }

    @Override
    public void onMouseDragged(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel) {
        Shape currentShape = canvasModel.getCurrentDrawnShape();
        currentShape.setX2((double) e.getX());
        currentShape.setY2((double) e.getY());
        shapeModel.setSingleSelectedShape(currentShape);
    }

    @Override
    public void onMouseReleased(MouseEvent e, CanvasModel canvasModel) {
        canvasModel.setCurrentDrawnShape(null);
        canvasModel.setMode(CanvasModel.Mode.NORMAL);
    }

    @Override
    public Cursor onMouseMoved(boolean containsShape, CanvasModel canvasModel) {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }
}
