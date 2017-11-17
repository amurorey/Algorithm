import java.awt.*;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * Description:动画控制层
 * User: amuro
 * Date: 2017-11-12
 * Time: 18:12
 */
public class AlgoVisuailzer {

    private static final int DELAY = 10;
    private int[] money;//数据
    private AlgoFrame frame;//视图

    public AlgoVisuailzer(final int sceneWidth, final int sceneHeight) {
        //初始化数据
        money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }

        //事件分发线程（初始化视图）
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
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

            Arrays.sort(money);
            frame.render(money);
            AlgoVisHelper.pause(DELAY);

            for (int k = 0; k < 50; k++) {
                for (int i = 0; i < money.length; i++) {
                    //if (money[i] > 0) {
                        int j = (int) (Math.random() * money.length);
                        money[i] -= 1;
                        money[j] += 1;
                    //}
                }
            }
        }
    }

    //程序入口
    /*
     *模拟描述：房间内有 100 人，每人有 100 块，每分钟随机给另一个人 1 块，最后这个房间内的财富分布怎样？
     */
    public static void main(String[] args) {

        final int sceneWidth = 1000;
        final int sceneHeight = 800;

        AlgoVisuailzer algoVisuailzer = new AlgoVisuailzer(sceneWidth, sceneHeight);
    }
}
