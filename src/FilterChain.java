import java.util.*;

public class FilterChain
{
	public LinkedList<IFilter> filters;

	public FilterChain()
	{
		filters = new LinkedList<IFilter>();
	}

	public void add(IFilter filter)
	{
		filters.add(filter);
	}

	public boolean doFilter(ASample samp)
	{
		for(IFilter f: filters)
		{
			if (!f.filter(samp)){
				return false;
			}
		}

		return true;
	}

	

}
