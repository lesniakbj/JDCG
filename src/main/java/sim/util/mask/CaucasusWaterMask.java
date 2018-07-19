package sim.util.mask;

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
public class CaucasusWaterMask extends WaterMask {
    private static int[][] maskPoints = new int[][] {{456, 146}, {396, 162}, {387, 657}, {404, 685},
                                                    {962, 687}, {976, 672}, {1004, 628}, {1020, 586},
                                                    {989, 516}, {973, 468}, {934, 455}, {916, 428},
                                                    {872, 423}, {843, 413}, {833, 389}, {797, 380},
                                                    {693, 278}, {605, 243}};

    public CaucasusWaterMask() {
        super(maskPoints);
    }

    public CaucasusWaterMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}
