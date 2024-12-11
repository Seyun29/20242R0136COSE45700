package com.seyun29.Model.Canvas;

import com.seyun29.Model.Shape.Shape;
import com.seyun29.Model.ShapeModel;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Getter
@Setter
public class CanvasModel {
    public enum Mode {
        NORMAL,
        SELECT_MULTIPLE,
        DRAW_LINE,
        DRAW_ELLIPSE,
        DRAW_RECT
    }
    private Point startPoint;
    private Shape currentDrawnShape = null;
    private Mode mode;
    private CanvasState currentState;

    public CanvasModel() {
        this.mode = Mode.NORMAL;
        this.currentState = new NormalState();
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        switch (mode) {
            case NORMAL:
                currentState = new NormalState();
                break;
            case SELECT_MULTIPLE:
                currentState = new SelectMultipleState();
                break;
            case DRAW_LINE:
                currentState = new DrawLineState();
                break;
            case DRAW_ELLIPSE:
                currentState = new DrawEllipseState();
                break;
            case DRAW_RECT:
                currentState = new DrawRectState();
                break;
        }
    }

    public void onMousePressed(MouseEvent e, ShapeModel shapeModel) {
        currentState.onMousePressed(e, this, shapeModel);
    }

    public void onMouseDragged(MouseEvent e, ShapeModel shapeModel) {
        currentState.onMouseDragged(e, this, shapeModel);
    }

    public void onMouseReleased(MouseEvent e) {
        currentState.onMouseReleased(e, this);
    }

    public Cursor onMouseMoved(boolean containsShape) {
        return currentState.onMouseMoved(containsShape, this);
    }
}
