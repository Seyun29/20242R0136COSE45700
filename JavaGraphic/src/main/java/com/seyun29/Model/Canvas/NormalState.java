package com.seyun29.Model.Canvas;

import com.seyun29.Model.Shape.Shape;
import com.seyun29.Model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NormalState implements CanvasState {
    @Override
    public void onMousePressed(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel) {
        Shape pressedShape = shapeModel.containsShape(e.getPoint());
        shapeModel.setSingleSelectedShape(pressedShape);
        canvasModel.setStartPoint(pressedShape == null ? null : e.getPoint());
    }

    @Override
    public void onMouseDragged(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel) {
        if (shapeModel.getSelectedShapes() != null) {
            //Move the shape
            ArrayList<Shape> selectedShapes = shapeModel.getSelectedShapes();
            int deltaX = e.getX() - canvasModel.getStartPoint().x;
            int deltaY = e.getY() - canvasModel.getStartPoint().y;
            for (Shape shape : selectedShapes) {
                shape.move(deltaX, deltaY);
            }
            canvasModel.setStartPoint(e.getPoint());
            shapeModel.setSelectedShapes(selectedShapes);
        }
    }

    @Override
    public void onMouseReleased(MouseEvent e, CanvasModel canvasModel) {}

    @Override
    public Cursor onMouseMoved(boolean containsShape, CanvasModel canvasModel) {
        if (containsShape) {
            return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        } else {
            return Cursor.getDefaultCursor();
        }
    }
}
