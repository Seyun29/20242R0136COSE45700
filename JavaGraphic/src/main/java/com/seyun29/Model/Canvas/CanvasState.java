package com.seyun29.Model.Canvas;

import com.seyun29.Model.Shape.Shape;
import com.seyun29.Model.ShapeModel;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface CanvasState {
    void onMousePressed(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel);
    void onMouseDragged(MouseEvent e, CanvasModel canvasModel, ShapeModel shapeModel);
    void onMouseReleased(MouseEvent e, CanvasModel canvasModel);
    Cursor onMouseMoved(boolean containsShape, CanvasModel canvasModel);
}
