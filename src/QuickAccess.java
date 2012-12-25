import org.apache.log4j.Logger;
import java.util.*;

public class QuickAccess extends ARule
{
    static Logger log = Logger.getLogger(QuickAccess.class);

    private LinkedList<ASample> samples = new LinkedList<ASample>();  // 样本 这里把样本数写死了

    public QuickAccess()
    {
        // 在 sampleNum 次的连续访问中，如果平均访问时间小于0.5秒，则触发这个规则。
		maxNum = 10;
        avgNum = 100;
    }

    public boolean isTriggered(ASample samp)
    {
		samples.add(samp);
		if (samples.size() > maxNum)
		{
			samples.pop();
		} else if (samples.size() != maxNum)
		{
			return false;
		}
		double now_avg = (((LogModel)samples.peekLast()).visit_time.getTime() - ((LogModel)samples.peekFirst()).visit_time.getTime())/maxNum/1000;

        return now_avg < avgNum;
    }

    public void trigger(ASample samp)
    {
		/*
		for(ASample sm: samples)
		{
			System.out.println((LogModel)sm);
		}
		*/
		log.warn(((LogModel)samples.peek()).source_ip+" too quick.");
		this.reset();
    }
    
    public void keep(ASample samp)
    {
		// 已经在isTriggered收集到了这个样本
        //samples.add(samp);
    }

    public void reset()
    {
		samples.clear();
    }
    
}
