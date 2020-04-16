package application;

import java.io.*;
import java.util.*;
import framework.File;
import framework.Folder;
import framework.FolderItem;

/**
 *  to operate the file in the system and your equipment
 * @author
 *
 */
public class FileSystem {	
	private Scanner input = new Scanner(System.in);//利用scanner输入
	private static String path=" " ; //用户输入的路径
	String str;

	/**
	 * The main function try to use 
	 * <code>File<code>, <code>Folder<code>, <code>FolderItem<code>, <code>FileSystem<code> 
	 * to operate the file according to the selection of the user 
	 * 
	 * @throws IOException 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		FileSystem filesystem = new FileSystem();
		Folder folder = new Folder(" ");

		filesystem.menu1();
		filesystem.readInInfo(filesystem,folder);

		int i = -1;

		/**
		 * Recursive printout the choice for user and operate their selection by using
		 * <code>File<code>, <code>Folder<code>, <code>FolderItem<code>, <code>FileSystem<code>
		 * 
		 */
		while (i != 0) {
			filesystem.menu2();

			/**
			 * to input until i is an integer
			 * 
			 */
			while (true){
				try {
					i = filesystem.input.nextInt();
					break;
				}catch(Exception e) {    
					System.out.println("         please input integers            ");
					continue;
				}
			}

			/**
			 * Recursive printout all folder items of the folder by
			 * using <code>File<code>, <code>Folder<code> and <code>FolderItem<code>
			 * 
			 */	
			if(i == 1) {
				System.out.println("_______________________________________________");
				System.out.println("|	"+folder.getName());
				filesystem.printFolderItems(folder, "--");	
				System.out.println("|_____________________________________________|");
				pauseEnter();
			}

			/**
			 * Recursive printout only folder items in the current folder by
			 * using <code>File<code>, <code>Folder<code> and <code>FolderItem<code>
			 * 
			 */	
			else if(i == 2) {
				System.out.println("_______________________________________________");
				System.out.println("|	"+folder.getName());
				filesystem.printFolderItems(folder);	
				System.out.println("|_____________________________________________|");	
				pauseEnter();
			}

			/**
			 * rename the folder by using the <code>Folder<code>, <code>FileSystem<code>
			 * 
			 */
			else if(i == 3) {
				filesystem.renameItem(folder);
				System.out.println("the name of the folder now is "+folder.getName());
				pauseEnter();
			}

			/**
			 * rename the item in folder by using 
			 * <code>Folder<code>, <code>FileSystem<code>, <code>FolderItem<code>
			 * 
			 */
			else if(i == 4) {
				System.out.println("please enter the index of item");
				int index;
				while (true){
					try {
						index = filesystem.input.nextInt();
						break;
					}catch(Exception e) {    
						System.out.println("         please input integers            ");
						continue;
					}
				}
				filesystem.renameItem(folder.getFolderItem(index),folder.getFolderItem(index).getName());
				System.out.println("the name of the item now is "+folder.getFolderItem(index).getName());
				pauseEnter();
			}

			/**
			 * create new item by using <code>Folder<code>
			 * 
			 */
			else if(i == 5) {
				filesystem.createNew(folder);
				System.out.println("_______________________________________________");
				System.out.println("|	"+folder.getName());
				filesystem.printFolderItems(folder);	
				System.out.println("|_____________________________________________|");	
				pauseEnter();
			}

			/**
			 * delete the item in the system but not in the equipment by using <code>Folder<code>
			 * 
			 */
			else if(i == 6) {
				filesystem.deleteTheItem(folder);
				System.out.println("_______________________________________________");
				System.out.println("|	"+folder.getName());
				filesystem.printFolderItems(folder, "--");	
				System.out.println("|_____________________________________________|");
				pauseEnter();
			}

			/**
			 * open a new folder by using
			 * <code>Folder<code>, <code>FileSystem<code>
			 * 
			 */
			else if(i == 7) {
				folder = new Folder(" ");
				filesystem.menu1();
				filesystem.readInInfo(filesystem,folder);
			}
			/**
			 * show the upper folder's name or its list by using
			 * <code>File</code>
			 */
			else if(i==8){
				java.io.File file = new java.io.File(path);		//获取当前path
				java.io.File parentDir = file.getParentFile();	//获取上一级file信息
				String parentPath = file.getParent();			//获取上一届file名称
				System.out.println("1 to show the name of the upper 2 to show the list ");	//进行选择
				while (true){
					try {
						//Scanner input = new Scanner(System.in);
						i = filesystem.input.nextInt();
						break;
					}catch(Exception e) {
						System.out.println("         please input integers            ");
						continue;
					}
				}
				if(i==1){			//只输出上级文件夹名称
					System.out.println("thie is the upper folder");
					System.out.println(parentPath);
				}
				else if(i==2)		//输出上级文件夹下所有信息
					printFolderItems(parentDir,"--");
				else{
					System.out.println("wrong");
				}
				pauseEnter();
			}
		}
	}

	/**
	 * read in the information in the folder by using
	 * <code>File<code>, <code>Folder<code>, <code>FolderItem<code>, <code>FileSystem<code>
	 * 
	 * @param filesystem	file system to operate 
	 * @param folder	the folder to open
	 * 
	 */
	public void readInInfo(FileSystem filesystem,Folder folder) {
		String folderName = " ";

		/** Get the current user path */
		path = input.next();

		/** Get the folder name */
		StringTokenizer tokenizer = new StringTokenizer(path, "\\");
		folderName = tokenizer.nextToken();
		for (; tokenizer.hasMoreTokens();) {
			folderName = tokenizer.nextToken();
		}
		/** Get all file items <code>files<code> of the current user path */
		java.io.File file = new java.io.File(path);
		java.io.File[] files = file.listFiles();

		if (files != null) {
			/**
			 * Recursive construct <code>folder<code> include all folder items
			 * by using <code>File<code>, <code>Folder<code>, <code>FolderItem<code>
			 * and java.io.File
			 * 
			 */
			folder.setName(folderName);

			for (java.io.File f : files) {
				FolderItem folderItem;
				if (f.isFile()) {
					folderItem = new File(f.getName(), "  ", (int) f.length());
					folder.addFolderItem(folderItem);
				}
				if (f.isDirectory()) {
					folderItem = new Folder(f.getName());
					folder.addFolderItem(folderItem);
					constructFolderItems(f, (Folder) folderItem);
				}
			}
		}
	}

	/**
	 * Recursive construct <code>folder<coder> object of one folder 
	 * by using <code>File<code>, <code>Folder<code> and <code>FolderItem<code>
	 * and class java.io.File
	 * 
	 * @param f  a java.io.File's object
	 * @param folder  Folder
	 * 
	 */
	public void constructFolderItems(java.io.File f, Folder folder) {
		java.io.File[] subFiles = f.listFiles();
		for (java.io.File i : subFiles) {
			FolderItem folderItem;
			if (i.isFile()) {
				folderItem = new File(i.getName(), "  ", (int) i.length());
				folder.addFolderItem(folderItem);
			}
			if (i.isDirectory()) {
				folderItem = new Folder(i.getName());
				folder.addFolderItem(folderItem);
				constructFolderItems(i, (Folder) folderItem);
			}
		}
	}

	/**
	 * the first menu
	 * to ask the user to enter the directory
	 * 
	 */
	public void menu1() {
		System.out.println("_______________________________________________");
		System.out.println("|          WELCOME TO the File System         |");
		System.out.println("|                                             |");
		System.out.println("|*********************************************|");
		System.out.println("|                                             |");
		System.out.println("|                                             |");
		System.out.println("|                                             |");
		System.out.println("|                                             |");
		System.out.println("|           Please enter the directory        |");
		System.out.println("|                                             |");
		System.out.println("|                                             |");
		System.out.println("|                                             |");
		System.out.println("|_____________________________________________|");
	}

	/**
	 * the second menu
	 * to ask the user to choice what to do in the file
	 * 
	 */
	public void menu2() {
		System.out.println("_______________________________________________");
		System.out.println("|                                             |");
		System.out.println("|          please choose a selection          |");
		System.out.println("|*********************************************|");
		System.out.println("|     1)display all                           |");
		System.out.println("|     2)only display the current directory    |");
		System.out.println("|     3)rename the folder                     |");
		System.out.println("|     4)rename the item in the directory      |");
		System.out.println("|     5)create a new item                     |");
		System.out.println("|     6)delete an item                        |");
		System.out.println("|     7)open a new directory                  |");
		System.out.println("|     8)show the upper directory              |");
		System.out.println("|     0)exit                                  |");
		System.out.println("|_____________________________________________|");
	}

	/**
	 * Recursive printout all folder items under one folder by using
	 * <code>File<code>, <code>Folder<code> and <code>FolderItem<code>
	 * 
	 * @param f  a Folder object
	 * @param indent  a String object for printing indentation
	 * 
	 */
	public void printFolderItems(Folder f, String indent) {
		for (FolderItem item : f.getFolderItems()) {
			System.out.println("|	"+indent + item.getName());
			if (item instanceof Folder) {
				String indentTemp = indent + "--";
				printFolderItems((Folder) item, indentTemp);
			}
		}
	}

	/**
	 * Recursive printout only folder items under current folder by using
	 * <code>File<code>, <code>Folder<code> and <code>FolderItem<code>
	 * 
	 * @param f  a Folder object
	 * @param indent  a String object for printing indentation
	 * 
	 */
	public void printFolderItems(Folder f) {
		for (FolderItem item : f.getFolderItems()) {
			System.out.println("|	"+"--" + item.getName());
		}
	}

	/**
	 * rename the folder in default path by using <code>FolderItem<code>
	 * 
	 * @param folderitem
	 * 
	 */
	public void renameItem(FolderItem folderitem) {
		java.io.File file = new java.io.File(path);

		System.out.println("Please enter the new name");		
		String foldername=input.next();

		folderitem.setName(foldername);	//重设此文件系统中的文件名
		file.renameTo(new java.io.File(file.getParent()+'\\'+foldername)); //重设此硬件系统中的文件名
		path = file.getParent()+'\\'+foldername; //重设路径
		System.out.println(path);
	}


	/**
	 * rename the item specified by the user by using <code>FolderItem<code>
	 * 
	 * @param folderitem
	 * 
	 */
	public void renameItem(FolderItem folderitem, String name) {
		String path1 = path+"\\"+name;
		java.io.File file = new java.io.File(path1);

		System.out.println("Please enter the new name");		
		String foldername=input.next();

		folderitem.setName(foldername);	//重设此文件系统中的文件名
		file.renameTo(new java.io.File(path+"\\"+foldername)); //重设此硬件系统中的文件名
		System.out.println(path);
	}


	/**
	 * create new items in the folder by using 
	 * <code>Folder<code>, <code>File<code>, <code>FolderItem<code>
	 * 
	 * @param folder
	 * @throws IOException
	 * 
	 */
	public void createNew(Folder folder) throws IOException {
		System.out.println("1 to create a folder, 2 to create a file, please do your chioce");
		int i;

		while (true){
			try {
				i = input.nextInt();
				break;
			}catch(Exception e) {    
				System.out.println("         please input integers            ");
				continue;
			}
		}

		if (i == 1) {
			System.out.println("please enter the name of the folder");
			String foldername = input.next();

			/**add the new folder in the system*/
			Folder folderNew = new Folder(foldername);
			folder.addFolderItem(folderNew);

			/**add the new folder in the equipment*/
			java.io.File file = new java.io.File(path+"\\"+foldername);
			file.mkdirs();
		}

		else if (i == 2) {
			System.out.println("please enter the filename.index");
			String fullname = input.next();

			/**get the extension*/
			StringTokenizer tokenizer = new StringTokenizer(fullname, ".");
			String extension = tokenizer.nextToken();
			extension = tokenizer.nextToken();

			/**add the new file in the system*/
			File fileNew = new File(fullname, extension, 0);
			folder.addFolderItem(fileNew);

			/**add the new file in the system*/
			java.io.File file = new java.io.File(path+"\\"+fullname);
			file.createNewFile();
		}
	}

	/**
	 * delete the item in the system but not in the equipment
	 * 
	 * @param folder
	 */
	public void deleteTheItem(Folder folder) {
		System.out.println("please enter the index of the item");
		int index;

		while (true){
			try {
				index = input.nextInt(); //输入序号
				break;
			}catch(Exception e) {    //异常处理
				System.out.println("         please input integers            ");
				continue;
			}
		}

		folder.removeFolderItem(folder.getFolderItem(index));

		System.out.println("This will not remove the item in the equipment."); //不会在设备中移除
	}
	/**
	 * Recursive printout all folder items under one folder
	 * only by using class java.io.File
	 *
	 * @param f  a java.io.File's object
	 * @param indent  a String object for printing indentation
	 *
	 */
	public static void printFolderItems(java.io.File f, String indent) {
		for (java.io.File i : f.listFiles()) {
			System.out.println(indent + i.getName());
			if (i.isDirectory()) {
				String indentTemp = indent + "--";
				printFolderItems(i, indentTemp);
			}
		}
	}

	public static void pauseEnter() throws IOException  { //按键继续
		System.out.println("Press any key to continue……");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}
}
