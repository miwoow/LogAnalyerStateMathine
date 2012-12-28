import java.util.*;

public class IpClassifier implements IClassifier
{
	public HashMap<String, List<ASample>> result;

	public IpClassifier()
	{
		this.result = new HashMap<String, List<ASample>>();
	}

	public void add(ASample samp)
	{
		if (this.result.containsKey(samp.getIp()))
		{
			this.result.get(samp.getIp()).add(samp);
		} else {
			LinkedList<ASample> logs = new LinkedList<ASample>();
			logs.add(samp);
			this.result.put(samp.getIp(), logs);
		}
	}

	public HashMap<String, List<ASample>> getResult()
	{
		return this.result;
	}
}
