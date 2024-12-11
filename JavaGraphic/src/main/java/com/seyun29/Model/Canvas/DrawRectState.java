package com.seyun29.Model.Canvas;

import com.seyun29.Helper;
import com.seyun29.Model.Shape.Rectangle;
import com.seyun29.Model.Shape.Shape;
import com.seyun29.Model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DrawRectState implements CanvasState {
    @Override
    public void onMousePressed(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel) {
        canvasModel.setStartPoint(e.getPoint());
        Point startPoint = canvasModel.getStartPoint();
        com.seyun29.Model.Shape.Rectangle currentRect = new Rectangle(startPoint.getX(), startPoint.getY(), 0.0d, 0.0d, 3, Helper.randomColor());
        canvasModel.setCurrentDrawnShape(currentRect);
        shapeModel.addShape(currentRect);
    }

    @Override
    public void onMouseDragged(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel) {
        Shape currentShape = canvasModel.getCurrentDrawnShape();
        Point startPoint = canvasModel.getStartPoint();
        currentShape.setWidth((double) Math.abs(e.getX() - startPoint.x));
        currentShape.setHeight((double) Math.abs(e.getY() - startPoint.y));
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
