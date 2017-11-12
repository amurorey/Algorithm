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

    //TODO:创建自己的数据
    private Object data;//数据
    private AlgoFrame frame;//视图

    public AlgoVisuailzer(final int sceneWidth, final int sceneHeight) {
        //初始化数据
        //TODO:初始化数据

        //事件分发线程（初始化视图）
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
                //TODO:根据情况决定是否添加键盘鼠标监听事件
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
        //TODO:编写自己的动画逻辑
    }

    //TODO:根据情况决定是否实现键盘鼠标等交互事件监听类
    private class AlgoKeyListener extends KeyAdapter {

    }


    private class AlgoMouseListener extends MouseAdapter {

    }

    //程序入口
    public static void main(String[] args) {

        final int sceneWidth = 800;
        final int sceneHeight = 800;

        AlgoVisuailzer algoVisuailzer = new AlgoVisuailzer(sceneWidth, sceneHeight);
    }
}
