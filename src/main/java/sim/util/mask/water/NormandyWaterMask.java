package sim.util.mask.water;

import sim.util.mask.Mask;

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
public class NormandyWaterMask extends Mask {
    private static int[][] maskPoints = new int[][] {
            {287, 183}, {342, 548}, {453, 621}, {538, 685},
            {528, 656}, {530, 631}, {490, 577}, {474, 531},
            {468, 476}, {525, 493}, {567, 478}, {596, 484},
            {595, 508}, {586, 523}, {603, 552}, {616, 570},
            {631, 563}, {649, 563}, {684, 574}, {745, 577},
            {781, 592}, {847, 558}, {878, 545}, {839, 537},
            {836, 529}, {851, 475}, {938, 419}, {1050, 371},
            {967, 110}, {854, 171}, {766, 147}, {681, 166},
            {671, 185}, {635, 164}, {618, 195}, {578, 225},
            {442, 226}
    };

    public NormandyWaterMask() {
        super(maskPoints);
    }

    public NormandyWaterMask(double scaleX, double scaleY, int gutterHeight) {
        super(maskPoints, scaleX, scaleY, gutterHeight);
    }
}
