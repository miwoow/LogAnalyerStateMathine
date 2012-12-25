import org.apache.log4j.Logger;
import java.util.*;

/*
 * 连续触发5次404的时候，触发这条规则。
 *
 * */
public class TooMuch404 extends ARule
{
    static Logger log = Logger.getLogger(QuickAccess.class);

    public TooMuch404()
    {
        maxNum = 3;
    }

    public boolean isTriggered()
    {
        for(ASample s: samples)
        {
            System.out.println(s);
        }
        return samples.size() >= maxNum;
    }

    public void trigger()
    {
        log.warn(samples.peek().source_ip+" Too much 404.");
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
