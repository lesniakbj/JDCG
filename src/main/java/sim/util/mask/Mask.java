package sim.util.mask;

import java.awt.geom.Path2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * (c) Copyright 2018 Calabrio, Inc.
 * All Rights Reserved. www.calabrio.com LICENSED MATERIALS
 * Property of Calabrio, Inc., Minnesota, USA
 * <p>
 * No part of this publication may be reproduced, stored or transmitted,
 * in any form or by any means (electronic, mechanical, photocopying,
 * recording or otherwise) without prior written permission from Calabrio Software.
 * <p>
 *
 * Created by Brendan.Lesniak on 7/19/2018.
 */
public abstract class Mask extends Path2D.Double {
    private static final Logger log = LogManager.getLogger(Mask.class);

    private int[][] points;

    public Mask(int[][] points) {
        this.points = points;
        if(points.length == 0) {
            return;
        }

        moveTo(points[0][0], points[0][1]);
        for(int i = 1; i < points.length; i++) {
            lineTo(points[i][0], points[i][1]);
        }
        closePath();
    }

    public Mask(int[][] points, double scaleX, double scaleY, int gutterHeight) {
        if(points.length == 0) {
            return;
        }

        moveTo(points[0][0] * scaleX, (points[0][1] - gutterHeight) * scaleY);
        for(int i = 1; i < points.length; i++) {
            lineTo(points[i][0] * scaleX,  (points[i][1] - gutterHeight) * scaleY);
        }
        closePath();
    }
}
