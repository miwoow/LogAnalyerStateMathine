import java.util.Date;

public class LogModel extends ASample {
    public String source_ip;
    Date visit_time;
    String method;
    String url;
    String http_version;
    int status;
    int body_length;
    String refer;
    String user_agent;

    public String toString()
    {
        return "source_ip = "+source_ip+"\nvisit_time = "+visit_time+"\nmethod = "+method+"\nurl = "+url+"\nhttp_version = "+http_version+"\nstatus = "+status+"\nbody_length = "+body_length+"\nrefer = "+refer+"\nuser_agent = "+user_agent;
    }
}

