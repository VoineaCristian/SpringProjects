import java.time.LocalDateTime;
import java.util.*;

public class ArrayMap<K,V> extends AbstractMap<K,V>{
	
	Vector<ArrayMapEntry<K,V>> List = new Vector<ArrayMapEntry<K,V>>();
	@SuppressWarnings("hiding")
	class ArrayMapEntry<K,V> implements Map.Entry<K,V>{
		private K key;
		private V value;
		
		ArrayMapEntry(K Key, V Value)
		{
			this.key = Key;
			this.value = Value;
		}
		public K getKey()
		{
			return this.key;
		}
		
		public V getValue()
		{
			return this.value;
		}
		
		V setValue(K Key, V Value)
		{
			this.key = Key;
			this.value = Value;
			return Value;
		}
		
		public String toString()
		{
			return value.toString(); 
		}
		
		public boolean equals(Object o)
		{
			if(o instanceof Map.Entry )
			{
				if(((Map.Entry<K,V>) o).getKey() == this.key)
				{
					if(((Map.Entry<K,V>) o).getValue() == this.value)
					{
						return true;
					}
				}
			}
			return false;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	
	public V put(K Key, V Value)
	{
		
		ArrayMapEntry<K,V> entry = new ArrayMapEntry<K,V>(Key,Value);
		if(List.contains(entry) != true)
		{
		
			List.add(entry);
			return Value;
		}
		return null;
		
	}
	public V getValues(K key)
	{
		
		if(List.size() == 0)
		{
			
			return null;
		}
		for(int i = 0; i < List.size();i++)
		{
			
			if(key.equals(List.get(i).key))
				return List.get(i).value;
			
		}
		
		return null;
	}
	
	public Set entrySet() {
		return null;
	}
	public int size()
	{
		return this.List.size();
	}
	
	


public static void main(String args[])
	{
	
		
		
	
	}
}
