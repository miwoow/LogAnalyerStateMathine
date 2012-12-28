import java.util.HashMap;
import java.util.List;
import java.io.*;
import java.util.regex.*;
import java.util.LinkedList;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class FileData implements IData
{
    public File data_file;
	public FilterChain fchain;
	private IClassifier classifier; // 分类器

    public FileData(String file_path) throws FileNotFoundException
    {
        data_file = new File(file_path);
        if (!data_file.exists())
        {
            throw new FileNotFoundException(data_file+" is not exists.");
        }
		this.classifier = new DefaultClassifier();
    }

	public void setClassifier(IClassifier classifier)
	{
		this.classifier = classifier;
	}

	public void setFilterChain(FilterChain chain)
	{
		this.fchain = chain;
	}

    public HashMap<String, List<ASample>> getData()
    {
        Pattern ptn = Pattern.compile("(\\d+.\\d+.\\d+.\\d+) .+ \\[(.+) \\+0800\\] \"(POST|GET) (.+) (HTTP/1\\.(1|0))\" (\\d{3}) (\\d+) \"(.+)\" \"(.+)\"");
        BufferedReader br = null;
		SimpleDateFormat sdt =new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss");
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
					try {
						one_log.visit_time = sdt.parse(matcher.group(2));
					} catch(Exception dex)
					{
						dex.printStackTrace();
					}
                    one_log.method = matcher.group(3);
                    one_log.url = matcher.group(4);
                    one_log.http_version = matcher.group(5);
                    one_log.status = Integer.parseInt(matcher.group(7));
                    one_log.body_length = Integer.parseInt(matcher.group(8));
                    one_log.refer = matcher.group(9);
                    one_log.user_agent = matcher.group(10);

					if (this.fchain != null)
					{
						if (this.fchain.doFilter(one_log)) {
							this.classifier.add(one_log);
						}
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
        return this.classifier.getResult();
    }
}
