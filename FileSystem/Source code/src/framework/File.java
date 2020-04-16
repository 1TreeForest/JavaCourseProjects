package framework;

/**
 * the class of the file
 * 
 * @author
 *
 */
public class File extends FolderItem {
	private String extension;//extension
	private int size;//size of the file

	/**
	 * save the name, extension, size of the file
	 * 
	 * @param initialName	the name of file
	 * @param initialExtension	the extension of the file
	 * @param initialSize	the size of the file
	 * 
	 */
	public File(String initialName, String initialExtension, int initialSize) {
		super(initialName);
		this.extension = initialExtension;
		this.size = initialSize;
	}

	public String getExtension() {
		return extension;
	}

	public int getSize() {
		return size;
	}

	public void setExtension(String newExtension) {
		this.extension = newExtension;
	}
}
