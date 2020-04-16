package framework;

import java.util.Vector;

/**
 * the class of folder
 * 
 * @author
 *
 */
public class Folder extends FolderItem {
	private Vector<FolderItem> folderItems;//the items in the folder

	/**
	 * Name the folder 
	 * store the file items in the folder by using
	 * <code>Vector<code>
	 * 
	 * @param initialName	the name of the folder
	 * 
	 */
	public Folder(String initialName) {
		super(initialName);
		folderItems = new Vector<FolderItem>();
	}

	public Vector<FolderItem> getFolderItems() {
		return folderItems;
	}

	/**
	 * Resets the file items in the folder
	 * 
	 * @param newFolderItems	the new items in the folder
	 * 
	 */
	public void setFolderItems(Vector<FolderItem> newFolderItems) {
		this.folderItems = newFolderItems;
	}

	/**
	 * Add the file item in the folder by using
	 * <code>FolderItem<code>
	 * 
	 * @param item	the item to join
	 * 
	 */
	public void addFolderItem(FolderItem item) {
		folderItems.add(item);
	}

	/**
	 * Delete the file item in the folder by using
	 * <code>FolderItem<code>
	 * 
	 * @param item	the item to delete
	 */
	public void removeFolderItem(FolderItem item) {
		folderItems.remove(item);
	}

	/**
	 * Find the file corresponding to index
	 * 
	 * @param index	the index of the file
	 * 
	 * @return	the specified file
	 * 
	 */
	public FolderItem getFolderItem(int index) {
		return folderItems.get(index);
	}

	public int getNumberOfFolderItems() {
		return folderItems.size();
	}
}