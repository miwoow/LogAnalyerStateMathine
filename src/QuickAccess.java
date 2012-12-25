import org.apache.log4j.Logger;

public class QuickAccess extends ARule
{
    static Logger log = Logger.getLogger(QuickAccess.class);

    public QuickAccess()
    {
        // 在 sampleNum 次的连续访问中，如果平均访问时间小于0.5秒，则触发这个规则。
        avgNum = 0.5;
    }

    public boolean isTriggered()
    {
        return false;
    }

    public void trigger()
    {
        
    }
    
    public void keep(ASample samp)
    {
        samples.add(samp);
    
    }

    public void reset()
    {
    
    }
    
}
