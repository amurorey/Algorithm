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
public class AlgoVisuailzer
{
    private static int DELAY = 20;
    private SelectionSortData data;//数据
    private AlgoFrame frame;//视图

    public AlgoVisuailzer(final int sceneWidth, final int sceneHeight, int N)
    {

        //初始化数据
        data = new SelectionSortData(N, sceneHeight);
        //事件分发线程（初始化视图）
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                frame = new AlgoFrame("Selection Sort Visualization12", sceneWidth, sceneHeight);

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        runlogic();
                    }
                }).start();
            }
        });
    }

    //动画逻辑
    private void runlogic()
    {
        setData(0, -1, -1);
        for (int i = 0; i < data.N(); i++)
        {
            //寻找[i,n)区间里的最小值得索引
            int minIndex = i;
            setData(i, -1, minIndex);
            for (int j = i + 1; j < data.N(); j++)
            {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex))
                {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }

            data.swap(i, minIndex);
            setData(i + 1, -1, -1);
            //AlgoVisHelper.pause(DELAY);
        }
        setData(data.N(), -1, -1);
    }

    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex)
    {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    //程序入口
    public static void main(String[] args)
    {

        final int sceneWidth = 800;
        final int sceneHeight = 800;
        int N = 100;

        AlgoVisuailzer algoVisuailzer = new AlgoVisuailzer(sceneWidth, sceneHeight, N);
    }
}
