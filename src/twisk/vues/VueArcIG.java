package twisk.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;
import twisk.ecouteur.EcouteurArc;
import twisk.mondeIG.ArcIG;
import twisk.mondeIG.MondeIG;

/**
 * Classe VueArcIG.
 *
 * @author Lambert Calvin & Mathieu Steinbach Hugo
 * @version 1.0
 */

public class VueArcIG extends Pane implements Observateur {

    /**
     * Constructeur VueArcIG.
     *
     * @param arc ArcIG
     * @param m   MondeIG
     */
    public VueArcIG(ArcIG arc, MondeIG m) {
        Line l = new Line();
        l.setStrokeWidth(2);
        l.setOnMouseClicked(new EcouteurArc(m, arc));
        double pointDepX = arc.getPoint(0).getPosX();
        double pointDepY = arc.getPoint(0).getPosY();
        double pointArrX = arc.getPoint(1).getPosX();
        double pointArrY = arc.getPoint(1).getPosY();

        l.setStartX(pointDepX);
        l.setStartY(pointDepY);
        l.setEndX(pointArrX);
        l.setEndY(pointArrY);

        // Fleche
        Polyline triangle = new Polyline();
        triangle.getPoints().addAll(
                pointArrX, pointArrY,
                pointArrX + 15.0, pointArrY + 5,
                pointArrX + 15.0, pointArrY - 5,
                pointArrX, pointArrY);

        double d = (pointDepY - pointArrY) / (pointDepX - pointArrX);
        double tanangle = Math.abs(d);
        double angle = Math.toDegrees(Math.atan(tanangle));

        if (pointDepX >= pointArrX && pointDepY < pointArrY) {
            angle = -angle;
        } else if (pointDepX < pointArrX && pointDepY >= pointArrY) {
            angle = 180 - angle;
        } else if (pointDepX < pointArrX && pointDepY < pointArrY) {
            angle = -(180 - angle);
        }

        Rotate rotate = new Rotate();
        rotate.setPivotX(pointArrX);
        rotate.setPivotY(pointArrY);
        rotate.setAngle(angle);

        if (arc.getSelectionne()) {
            triangle.setFill(Color.RED);
            l.setStroke(Color.RED);
        } else {
            triangle.setFill(Color.BLACK);
            l.setStroke(Color.BLACK);
        }

        triangle.getTransforms().add(rotate);
        this.setPickOnBounds(false);
        this.getChildren().addAll(l, triangle);
    }

    @Override
    public void reagir() {

    }
}
