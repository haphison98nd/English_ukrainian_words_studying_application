package project.entities;
import java.util.LinkedList;
import java.util.List;

public abstract class Item
{
	private List<ItemProperty> list;

	//Todo: refactor program to make this field protected
	public List<ItemProperty> getList()

	{
		if (list != null) return list;
		return new LinkedList<>();
	}
	public void addPropertyToList(ItemProperty prop)
	{
	    if (list==null) list = new LinkedList<>();
	    list.add(prop);
	}
}
