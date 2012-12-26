

public class IpFilter implements IFilter
{
	private String ip;
	
	public boolean filter(ASample samp)
	{
		return ((LogModel)samp).source_ip.equals(this.ip);
	}

	public void setIp(String ip)
	{
		this.ip = ip;
	}

	public String getIp()
	{
		return this.ip;
	}
	
}
