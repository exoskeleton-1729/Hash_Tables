
public class HashTester {
	public static void main(String[] args)
	{
		MyHashSet hashSet = new MyHashSet();
		System.out.println(hashSet.isEmpty());
		System.out.println(hashSet.contains(0));
		System.out.println(hashSet.add("anika"));
		System.out.println(hashSet.add("audrey"));
		System.out.println(hashSet.add("kensuke"));
		System.out.println(hashSet.add("eric"));
		System.out.println(hashSet.add("karen"));
		System.out.println(hashSet.toString());
		System.out.println(hashSet.add("dru"));
		System.out.println(hashSet.add("ben"));
		System.out.println(hashSet.add("kaito"));
		System.out.println(hashSet.add("andrew"));
		System.out.println(hashSet.toString());
	}
}
