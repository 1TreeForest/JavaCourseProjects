package framework;

/**
 * the class of the items in files
 * @author
 *
 */
public class FolderItem {
	private String name;//the name of the item

	/**
	 * Name the item
	 * 
	 * @param initialName	the name of the item
	 */
	public FolderItem(String initialName) {
		this.name = initialName;
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}
}
