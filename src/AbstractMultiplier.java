public abstract class AbstractMultiplier
{
	MultiplierImplementor imp;
	
	public int multiply(int sum)
	{
		return imp.multiplyImp(sum);
	}// end multiply
}// end AbstractMultiplier - class
