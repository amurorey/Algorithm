import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 视图层
 * User: amuro
 * Date: 2017-11-12
 * Time: 15:22
 */
public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();
        setContentPane(canvas);
        //自动调整窗口大小
        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(rootPane);
        setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    //TODO:设置自己数据
    private Object data;


    public void render(Object data) {
        this.data = data;
        repaint();
    }

    //画布
    private class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            //双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            //抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_DEFAULT);
            g2d.addRenderingHints(hints);

            //具体绘制
            //TODO: 绘制自己的数据data

        }

        //自动决定画布大小
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
