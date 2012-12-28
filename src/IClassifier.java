import java.util.*;

public interface IClassifier
{
	public void add(ASample samp);

	public HashMap<String, List<ASample>> getResult();

}
