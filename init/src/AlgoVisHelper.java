import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created with IntelliJ IDEA.
 * Description:工具类
 * User: amuro
 * Date: 2017-11-12
 * Time: 16:15
 */
public class AlgoVisHelper {

    private AlgoVisHelper() {
    }

    //设置画笔宽度
    public static void setStrokeWidth(Graphics2D g2d, int w) {
        int strokeWidth = w;
        //后两个参数，断点线段平滑，拐点线段平滑
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void setColor(Graphics2D g2d,Color color){
        g2d.setColor(color);
    }

    //画空心圆
    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        //单精度浮点数运算更快点
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.draw(circle);
    }

    //画实心圆
    public static void fillCircle(Graphics2D g2d, int x, int y, int r){
        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g2d.fill(circle);
    }

    public static void pause(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
