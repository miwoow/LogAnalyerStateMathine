import java.lang.Thread;
import java.util.*;
import org.apache.log4j.Logger;

public class AnalyThread extends Thread
{
    private String ip;
    private List<LogModel> logs;

    static Logger log = Logger.getLogger(AnalyThread.class);;

    public AnalyThread(String ip, List<LogModel> logs)
    {
        this.ip = ip;
        this.logs = logs;
    }


    public void run() {
        if (ip==null || logs==null){
            log.info("nothing to do");
        }
        //log.info("================"+ip+"("+logs.size()+")"+"================");

        LogAnaly la = new LogAnaly(this.ip);
        la.setIp(ip);

        for(LogModel lm : logs){
            la.AnalyOneLog(lm);
        }
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public void setLogs(List<LogModel> logs)
    {
        this.logs = logs;
    }

    public String getIp()
    {
        return ip;
    }

    public List<LogModel> getLogs()
    {
        return logs;
    }

}
