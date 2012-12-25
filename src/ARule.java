import java.util.*;

public abstract class ARule
{
    public int minNum;     // 下限触发时的临界值
    public int maxNum;     // 上限触发时的临界值
    public double avgNum;     // 平均值触发时的临界值


    public abstract boolean isTriggered(ASample samp);
    public abstract void trigger(ASample samp);
    public abstract void keep(ASample samp);
    public abstract void reset();
    
}
