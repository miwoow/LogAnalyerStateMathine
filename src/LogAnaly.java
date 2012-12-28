import org.apache.log4j.Logger;

public class LogAnaly
{
    private LogAnalyContext _fsm;
    private String ip;

    static Logger log = Logger.getLogger(LogAnaly.class);
	private QuickAccess qa_plugin = new QuickAccess();
	private TooMuch404 t4_plugin = new TooMuch404();

    public LogAnaly(String ip)
    {
        _fsm = new LogAnalyContext(this);
        this.ip = "["+ip+"]";
    }

    public void AnalyOneLog(ASample lm)
    {
		_fsm.getOnePkg(qa_plugin, lm);
		/*
        if(lm.status==404)
        {
            _fsm.get404(t4_plugin, lm);
        }
		*/
    }

    public void trigger(ARule flag, ASample samp)
    {
        flag.trigger(samp);
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
