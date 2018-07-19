package sim.util.mask;


import java.awt.geom.Path2D;

public class PersianGulfWaterMask extends Path2D.Double {
    public PersianGulfWaterMask() {
        moveTo(561, 337);
        lineTo(551, 685);
        lineTo(596, 668);
        lineTo(643, 673);
        lineTo(688, 669);
        lineTo(740, 643);
        lineTo(850, 541);
        lineTo(880, 505);
        lineTo(896, 477);
        lineTo(917, 477);
        lineTo(896, 539);
        lineTo(904, 586);
        lineTo(916, 632);
        lineTo(942, 669);
        lineTo(957, 687);
        lineTo(1035, 687);
        lineTo(1015, 544);
        lineTo(978, 531);
        lineTo(955, 472);
        lineTo(942, 421);
        lineTo(921, 411);
        lineTo(893, 408);
        lineTo(883, 439);
        lineTo(851, 450);
        lineTo(765, 467);
        lineTo(728, 445);
        lineTo(693, 444);
        lineTo(605, 381);
        closePath();
    }

    public PersianGulfWaterMask(double scaleX, double scaleY, int gutterHeight) {
        moveTo(561 * scaleX, (337 - gutterHeight) * scaleY);
        lineTo(551 * scaleX, (685 - gutterHeight) * scaleY);
        lineTo(596 * scaleX, (668 - gutterHeight) * scaleY);
        lineTo(643 * scaleX, (673 - gutterHeight) * scaleY);
        lineTo(688 * scaleX, (669 - gutterHeight) * scaleY);
        lineTo(740 * scaleX, (643 - gutterHeight) * scaleY);
        lineTo(850 * scaleX, (541 - gutterHeight) * scaleY);
        lineTo(880 * scaleX, (505 - gutterHeight) * scaleY);
        lineTo(896 * scaleX, (477 - gutterHeight) * scaleY);
        lineTo(917 * scaleX, (477 - gutterHeight) * scaleY);
        lineTo(896 * scaleX, (539 - gutterHeight) * scaleY);
        lineTo(904 * scaleX, (586 - gutterHeight) * scaleY);
        lineTo(916 * scaleX, (632 - gutterHeight) * scaleY);
        lineTo(942 * scaleX, (669 - gutterHeight) * scaleY);
        lineTo(957 * scaleX, (687 - gutterHeight) * scaleY);
        lineTo(1034 * scaleX, (687 - gutterHeight) * scaleY);
        lineTo(1015 * scaleX, (544 - gutterHeight) * scaleY);
        lineTo(978 * scaleX, (531 - gutterHeight) * scaleY);
        lineTo(955 * scaleX, (472 - gutterHeight) * scaleY);
        lineTo(942 * scaleX, (421 - gutterHeight) * scaleY);
        lineTo(921 * scaleX, (411 - gutterHeight) * scaleY);
        lineTo(893 * scaleX, (408 - gutterHeight) * scaleY);
        lineTo(883 * scaleX, (439 - gutterHeight) * scaleY);
        lineTo(851 * scaleX, (450 - gutterHeight) * scaleY);
        lineTo(765 * scaleX, (467 - gutterHeight) * scaleY);
        lineTo(728 * scaleX, (445 - gutterHeight) * scaleY);
        lineTo(693 * scaleX, (444 - gutterHeight) * scaleY);
        lineTo(605 * scaleX, (381 - gutterHeight) * scaleY);
        closePath();
    }
}
