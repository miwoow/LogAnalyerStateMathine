import org.apache.log4j.Logger;
import java.util.*;

/*
 * 连续触发5次404的时候，触发这条规则。
 *
 * */
public class TooMuch404 extends ARule
{
    static Logger log = Logger.getLogger(QuickAccess.class);

    private LinkedList<ASample> samples = new LinkedList<ASample>();  // 样本

    public TooMuch404()
    {
        maxNum = 3;
    }

    public boolean isTriggered(ASample samp)
    {
        return samples.size() >= maxNum;
    }

    public void trigger(ASample samp)
    {
        log.warn("samples.size()="+samples.size()+"=="+((LogModel)samples.peek()).source_ip+" Too much 404.");
        this.reset();
    }

    public void keep(ASample samp)
    {
        samples.add(samp);
    }

    public void reset()
    {
       samples.clear();
    }
}
