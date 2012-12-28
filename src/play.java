import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.log4j.Logger;

public class play
{    
    static Logger log = Logger.getLogger(play.class);
        
    public static void main(String args[])
    {
        HashMap<String, List<ASample>> ip_to_logs = null;
        log.info("log file is "+args[0]);
        File logFile = new File(args[0]);

        if (!logFile.exists())
        {
            log.fatal(args[0]+" file not exists!");
        }

        FileData fd = null;
        try {
            fd = new FileData(args[0]);
        } catch(FileNotFoundException ex1){
            ex1.printStackTrace();
            return;
        }
		FilterChain fchain = new FilterChain();
		IpFilter ipFilter = new IpFilter();
		ipFilter.setIp("113.108.12.123");
		fchain.add(ipFilter);
		fd.setFilterChain(fchain);
		fd.setClassifier(new IpClassifier());
        ip_to_logs = fd.getData();

        LinkedList<AnalyThread> threads = new LinkedList<AnalyThread>();

        
        for (Iterator it = ip_to_logs.keySet().iterator(); it.hasNext();)
        {
            String ip = (String)it.next();
            List<ASample> logs = (List<ASample>)ip_to_logs.get(ip);
            AnalyThread analyThread = new AnalyThread(ip, logs);
            analyThread.start();
            threads.add(analyThread);
        }

        try {
            for(AnalyThread th : threads)
            {
                th.join();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
