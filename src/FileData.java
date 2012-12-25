import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.regex.*;
import java.util.LinkedList;
import java.io.FileNotFoundException;

public class FileData implements IData
{
    public File data_file;

    public FileData(String file_path) throws FileNotFoundException
    {
        data_file = new File(file_path);
        if (!data_file.exists())
        {
            throw new FileNotFoundException(data_file+" is not exists.");
        }
    }

    public HashMap<String, List<LogModel>> getData()
    {
        Pattern ptn = Pattern.compile("(\\d+.\\d+.\\d+.\\d+) .+ \\[(.+) \\+0800\\] \"(POST|GET) (.+) (HTTP/1\\.(1|0))\" (\\d{3}) (\\d+) \"(.+)\" \"(.+)\"");
        HashMap<String, List<LogModel>> ip_to_logs = new HashMap<String, List<LogModel>>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(data_file));
            String line = null;
            while ((line = br.readLine())!= null)
            {
                Matcher matcher = ptn.matcher(line);
                if (matcher.find())
                {
                    LogModel one_log = new LogModel();
                    one_log.source_ip = matcher.group(1);
                    one_log.visit_time = matcher.group(2);
                    one_log.method = matcher.group(3);
                    one_log.url = matcher.group(4);
                    one_log.http_version = matcher.group(5);
                    one_log.status = Integer.parseInt(matcher.group(7));
                    one_log.body_length = Integer.parseInt(matcher.group(8));
                    one_log.refer = matcher.group(9);
                    one_log.user_agent = matcher.group(10);
                    if (ip_to_logs.containsKey(one_log.source_ip)){
                        List<LogModel> logs = ip_to_logs.get(one_log.source_ip);
                        logs.add(one_log);
                    } else {
                        List<LogModel> logs = new LinkedList<LogModel>();
                        logs.add(one_log);
                        ip_to_logs.put(one_log.source_ip, logs);
                    }
                }
            }
        } catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch(IOException ex)
        {
            ex.printStackTrace();
        } finally
        {
            if (br != null) {
                try {
                    br.close();
                }catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return ip_to_logs;
    }
}
