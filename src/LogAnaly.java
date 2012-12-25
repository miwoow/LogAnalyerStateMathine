import org.apache.log4j.Logger;

public class LogAnaly
{
    private LogAnalyContext _fsm;
    private String ip;

    static Logger log = Logger.getLogger(LogAnaly.class);

    public LogAnaly(String ip)
    {
        _fsm = new LogAnalyContext(this);
        this.ip = "["+ip+"]";
    }

    public void AnalyOneLog(LogModel lm)
    {
        if(lm.status==404)
        {
            _fsm.get404(new TooMuch404(), lm);
        }
    }

    public void trigger(ARule flag)
    {
        flag.trigger();
    }

    public void keep(ARule flag, ASample samp)
    {
        flag.keep(samp);
    }


    public static void print(String str)
    {
        System.out.println(str);
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

}
