import java.util.*;

public class DefaultClassifier implements IClassifier
{
	public HashMap<String, List<ASample>> result;
	private static final String key="0.0.0.0";

	public DefaultClassifier()
	{
		this.result = new HashMap<String, List<ASample>>();
		this.result.put(key, new LinkedList<ASample>());
	}

	public void add(ASample samp)
	{
		System.out.println(samp);
		this.result.get(key).add(samp);
	}

	public HashMap<String, List<ASample>> getResult()
	{
		return this.result;
	}

}
