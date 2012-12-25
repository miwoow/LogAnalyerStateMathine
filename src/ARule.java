import java.util.*;

public abstract class ARule
{
    public int minNum;     // 下限触发时的临界值
    public int maxNum;     // 上限触发时的临界值
    public double avgNum;     // 平均值触发时的临界值

    public LinkedList<ASample> samples = new LinkedList<ASample>();  // 样本

    public abstract boolean isTriggered();
    public abstract void trigger();
    public abstract void keep(ASample samp);
    public abstract void reset();
    
}
