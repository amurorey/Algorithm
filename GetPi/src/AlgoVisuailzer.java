import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * Description:动画控制层
 * User: amuro
 * Date: 2017-11-12
 * Time: 18:12
 */
public class AlgoVisuailzer {

    private Circle circle;
    private LinkedList<Point> points;
    private AlgoFrame frame;//视图
    private int N;
    private static final int DELAY = 40;

    public AlgoVisuailzer(final int sceneWidth, final int sceneHeight, int N) {

        if (sceneWidth != sceneHeight) {
            throw new IllegalArgumentException("这个案列必须是个正方形窗口");
        }

        //初始化数据
        this.N = N;
        circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneWidth / 2);
        points = new LinkedList<Point>();

        //事件分发线程（初始化视图）
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new AlgoFrame("Get Pi With Monte Carlo", sceneWidth, sceneHeight);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runlogic();
                    }
                }).start();
            }
        });
    }

    //动画逻辑
    private void runlogic() {
        for (int i = 0; i < N; i++) {
            frame.render(circle, points);
            AlgoVisHelper.pause(DELAY);

            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());

            Point p = new Point(x, y);
            points.add(p);
        }
    }

    //程序入口
    public static void main(String[] args) {

        final int sceneWidth = 800;
        final int sceneHeight = 800;
        int N = 10000;

        AlgoVisuailzer algoVisuailzer = new AlgoVisuailzer(sceneWidth, sceneHeight, N);
    }
}
