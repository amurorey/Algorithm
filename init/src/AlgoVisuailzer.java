import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created with IntelliJ IDEA.
 * Description:动画控制层
 * User: amuro
 * Date: 2017-11-12
 * Time: 18:12
 */
public class AlgoVisuailzer {

    private Circle[] circles;
    private AlgoFrame frame;
    private boolean isAnimated = true;

    public AlgoVisuailzer(final int sceneWidth, final int sceneHeight, int N) {

        circles = new Circle[N];
        int R = 50;
        for (int i = 0; i < N; i++) {
            int x = (int) Math.random() * (sceneWidth - 2 * R) + R;
            int y = (int) Math.random() * (sceneHeight - 2 * R) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }

        //事件分发线程
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
                frame.addKeyListener(new AlgoKeyListener());
                frame.addMouseListener(new AlgoMouseListener());
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
        while (true) {
            //绘制数据
            frame.render(circles);
            AlgoVisHelper.pause(20);
            //更新数据
            if (isAnimated) {
                for (Circle circle : circles) {
                    circle.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
                }
            }
        }
    }

    private class AlgoKeyListener extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent event) {

            if (event.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent event) {
            event.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
            //System.out.println(event.getPoint());

            for (Circle circle : circles){
                if(circle.contain(event.getPoint())){
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    //程序入口
    public static void main(String[] args) {

        final int sceneWidth = 800;
        final int sceneHeight = 800;

        int N = 10;

        AlgoVisuailzer algoVisuailzer = new AlgoVisuailzer(sceneWidth, sceneHeight, N);
    }
}
