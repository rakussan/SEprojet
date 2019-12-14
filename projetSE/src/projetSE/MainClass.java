package projetSE;
 import java.awt.Desktop;
import java.io.*;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;



public class MainClass {
	public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    public static String newList = "";
    public static String newHelp = "";
    public static String newChangeDirectory = "";
    public static String newCreate = "";
    public static String newWriteOnFile = "";
    public static String newExecuteFile = "";
    public static String newRemoveFile = "";
    public static String newPrecedentDirectory="";
	public static void ls() {
		String curDir = System.getProperty("user.dir");
		System.out.println ("$Le répertoire courant est: "+curDir);
		String chemin = curDir.replaceAll("/", "//");
		File dir = new File(chemin);
		File[] children = (File[]) dir.listFiles();
		if (children == null) {
		System.out.println("Le dossier n'existe pas.");
		} else {
		for (int i=0; i < children.length;i++){
		File fichier = children[i];
		System.out.println(fichier.getName());
		}
		}	
	}
	static ArrayList<String> allFiles = new ArrayList<String>();
	static ArrayList<String> allRepertoire = new ArrayList<String>();
	static ArrayList<String> all = new ArrayList<String>();
	public static void getLsList(ArrayList<String> allFiles, ArrayList<String> allRepertoire) {
		String curDir = System.getProperty("user.dir");
		allFiles.clear();
		allRepertoire.clear();
		all.clear();
		String chemin = curDir.replaceAll("/", "//");
		File dir = new File(chemin);
		File[] children = (File[]) dir.listFiles();
		if (children == null) {
		System.out.println(RED+"Le dossier n'existe pas.");
		} else {
		for (int i=0; i < children.length;i++){
			File fichier = children[i];
			all.add(fichier.getName());
			if(fichier.getName().indexOf(".")!=-1) {
				allRepertoire.add(fichier.getName());
			}
			else {
				allFiles.add(fichier.getName());
			}
		}
		}
	}
	
	public static void sortLs() {
		getLsList(allFiles, allRepertoire);
		java.util.Collections.sort(all);
		for(String elem: all)
	       {
	       	 System.out.println (GREEN+elem);
	       }
	}
	
	public static void lsPipe(String[] separated) {
		getLsList(allFiles, allRepertoire);
		switch(separated[2]) {
		case "countFiles":
				if (allFiles.size()==0) {
					System.out.println(RED+"Ce répértoire est vide!");
				} else {
					System.out.println(GREEN+"Ce répértoire contient "+allRepertoire.size()+ " fichiers.");
				}
				
				break;
		case "countRepo":
			if (allRepertoire.size()==0) {
				System.out.println(RED+"Ce répértoire est vide! ");
			} else {
				System.out.println(GREEN+"Ce répértoire contient "+allFiles.size()+ " dossiers.");
			}
			break;
		default:
			System.out.println(RED+"Commande invalide!");
			break;
		}			
			
	}
	
	//Récupérer home et path à partir du fichier
	public static void getPathAndHome() {
		try{
			InputStream flux=new FileInputStream("C:\\Users\\dabdoub85\\Desktop\\mini_projet_se\\Mini_Project_SE\\home&path.txt"); 
			InputStreamReader lecture=new InputStreamReader(flux);
			BufferedReader buff=new BufferedReader(lecture);
			String ligne;
			String path;
			String home;
			ligne=buff.readLine();
			path=ligne.substring(5);
			ligne=buff.readLine();
			home=ligne.substring(5);
			System.out.println(home);
			System.out.println(path);
			buff.close(); 
		}
				
			catch (Exception e){
			System.out.println(e.toString());
			}

	}
	
	//création d'un dossier
	public static void cd(String newDirectory) {
		System.setProperty("user.dir", newDirectory);
	}
	public static void cdTwo() {
		String curDir = System.getProperty("user.dir");
		int lastSlash = curDir.lastIndexOf("\\");
		System.setProperty("user.dir", curDir.substring(0, lastSlash));
	}
	
	public static void mkDir(String filename) {
		String curDir = System.getProperty("user.dir");
		File f = new File(curDir+"\\"+filename); 
        if (f.mkdir()) { 
            System.out.println(GREEN+"Directory is created"); 
        } 
        else { 
            System.out.println(RED+"Directory cannot be created"); 
        } 
	}

	
	//Ecrire dans un fichier txt
	public static void WriteOnANewFileWord(String fileName) throws IOException {
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir+"\\"+fileName+".txt");
		//Create the file
		if (file.createNewFile())
		{
		    System.out.println(GREEN+"Fichié créé!");
		} else {
		    System.out.println(RED+"Fichié existe déjà!.");
		}
		try {
		     if (Desktop.isDesktopSupported()) {
		       Desktop.getDesktop().open(new File(file.getAbsolutePath()));
		     }
		   } catch (IOException ioe) {
		     ioe.printStackTrace();
		  }
	}
	
	public static void help() {
		System.out.println(CYAN+"help: Afficher des informations sur chaque commande.");
		System.out.println(CYAN+"changeDirectory: Changer l'environnement de travail.");
		System.out.println(CYAN+"precedentDirectory: Retourner vers le répértoire précédent");
		System.out.println(CYAN+"list: Afficher les les dossiers et les fichiers dans l'environnement de travail courant.");
		System.out.println(CYAN+"      list: peut être utilisée avec l'option -s qui sert à trier la liste.");
		System.out.println(CYAN+"      list: peut être utilisée avec '| countFiles' qui sert à compter le nombre des fichiers dans le répértoire courant.");
		System.out.println(CYAN+"      list: peut être utilisée avec '| countRepo' qui sert à compter le nombre des dossiers dans le répértoire courant.");
		
		System.out.println(CYAN+"create fileName(.extension): Créer un dossier ou un fichier.");
		System.out.println(CYAN+"removeRepo repositoryName: supprimer un dossier.");
		System.out.println(CYAN+"writeOnFile fileName: Créer un nouveau fichier text et ouvrir le fichier avec bloc note.");
		System.out.println(CYAN+"executeFile fileName: Exécuter les commandes qui se trouvent dans un fichier text.");
		System.out.println(CYAN+"commande > fileName: Ecrire le résultat dans un fichier avec écrasement du contenu." );
		System.out.println(CYAN+"commande >> fileName: Ecrire le résultat dans un fichier sans écrasement du contenu." );
	}
	
	public static void ExecuteCommandsOnFile(String cmd) throws IOException {
		String curDir = System.getProperty("user.dir");
		String[] separated = cmd.split(" ");
		switch(separated[0]) {
		case "help":
			help();
			break;
		case "change_directory":
			cd(separated[1]);
			break;
		case "create":
			mkDir(separated[1]);
			break;
		case "list":
			ls();
			break;
		case "write_on_file":
			WriteOnANewFileWord(separated[1]);
			break;
		case "executeFile":
			ExecuteFile(separated[1]);
			break;
		default:
			System.out.println(RED+"Commande invalide!");
		}	
	}
	
	public static void ExecuteFile(String fileName) {
		String curDir = System.getProperty("user.dir");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(curDir+"\\"+fileName+".txt"));
			String line = reader.readLine();
			while (line != null) {
				// read next line
				ExecuteCommandsOnFile(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void alias(String[] separated) {
		switch(separated[1]) {
		case "help":
			newHelp=separated[2];
			break;
		case "changeDirectory":
			newChangeDirectory=separated[2];
			break;
		case "create":
			newCreate=separated[2];
			break;
		case "list":
			newList=separated[2];
			break;
		case "writeOnFile":
			newWriteOnFile=separated[2];
			break;
		case "removeFile":
			newRemoveFile=separated[2];
			break;
		case "executeFile":
			newExecuteFile=separated[2];
			break;
		case "precedentDirectory":
			newPrecedentDirectory=separated[2];
			break;
		default:
			System.out.println(RED+"Commande invalide!");
			break;
		}	
	}		
	
	public static void Redirection(String[] separated) throws IOException {
		String curDir = System.getProperty("user.dir");
		String chemin = curDir.replaceAll("/", "//");
			switch(separated[0]) {
			case "help":
				if(separated[1].equals(">>")) {			
					File file = new File(curDir+"\\"+separated[2]+".txt");
					//Create the file
					if (file.createNewFile())
					{
					    System.out.println(RED+"Fichié créé!");
					    
					} else {
					    System.out.println(GREEN+"Fichié existe déjà!.");
					}		
					BufferedWriter  output = new BufferedWriter(new FileWriter(file,true));
					output.append("help: Des informations sur chaque commande.");
					output.newLine();
					output.append("Change_directory: Change l'environnement de travail.");
					output.newLine();
					output.append("List: Affiche les les dossiers et les fichiers dans l'environnement de travail courant.");
					output.newLine();
					output.append("Create fileName(.extension): Permet de créer un dossier ou un fichier.");
					output.newLine();
					output.append("Write_on_file fileName: Cree un nouveau fichier text et ouvre le fichier avec bloc note.");
					output.newLine();
					output.append("ExecuteCommandsOnFile fileName: Exécute les commandes qui se trouvent dans un fichier text.");
					output.close();
				} else if (separated[1].equals(">"))  {
					File file = new File(curDir+"\\"+separated[2]+".txt");
					//Create the file
					if (file.createNewFile())
					{
					    System.out.println(GREEN+"Fichié créé!");
					    
					} else {
					    System.out.println(PURPLE+"Fichié existe déjà!.");
					}		
					BufferedWriter  output = new BufferedWriter(new FileWriter(file));
					output.append("help: Des informations sur chaque commande.");
					output.newLine();
					output.append("Change_directory: Change l'environnement de travail.");
					output.newLine();
					output.append("List: Affiche les les dossiers et les fichiers dans l'environnement de travail courant.");
					output.newLine();
					output.append("Create fileName(.extension): Permet de créer un dossier ou un fichier.");
					output.newLine();
					output.append("Write_on_file fileName: Cree un nouveau fichier text et ouvre le fichier avec bloc note.");
					output.newLine();
					output.append("ExecuteCommandsOnFile fileName: Exécute les commandes qui se trouvent dans un fichier text.");
					output.close();
				} else {
					System.out.println(RED+"Commande invalide!");
				}
				break;
			case "list":
				
				File dir = new File(chemin);
				File[] children = (File[]) dir.listFiles();
				if (children == null) {
				} else {
					if(separated[1].equals(">>")) {
						File file = new File(curDir+"\\"+separated[2]+".txt");
						//Create the file
						if (file.createNewFile())
						{
						    System.out.println(GREEN+"Fichié créé!");
						    
						} else {
						    System.out.println(PURPLE+"Fichié existe déjà!.");
						}		
						BufferedWriter  output = new BufferedWriter(new FileWriter(file,true));		 
						for (int i=0; i < children.length;i++){
							File fichier = children[i];
							output.append(fichier.getName());
							output.newLine();
						}
						output.close();
					}
					else if (separated[1].equals(">")){
						File file = new File(curDir+"\\"+separated[2]+".txt");
						//Create the file
						if (file.createNewFile())
						{
						    System.out.println(GREEN+"Fichié créé!");
						    
						} else {
						    System.out.println(PURPLE+"Fichié existe déjà!.");
						}		
						BufferedWriter  output = new BufferedWriter(new FileWriter(file));
						for (int i=0; i < children.length;i++){
							File fichier = children[i];
							output.append(fichier.getName());
							output.newLine();
						}
						output.close();
					}
					else if (separated[1].equals("|")) {
						lsPipe(separated);
					}
					else {
						System.out.println(RED+"Commande invalide!");
					}
				}
				break;
			default:
				System.out.println(RED+"Commande invalide!");
			}
			
		}
	//}
	
	public static void removeFile(String fileName) {
		String curDir = System.getProperty("user.dir");
		File file = new File(curDir+"\\"+fileName);
		if(file.delete()) 
        { 
            System.out.println("Le fichier a été supprimé avec succès."); 
        } 
        else
        { 
            System.out.println("Fichier innexistant."); 
        } 
    } 
	
	
	////// Ordonnancement
	public static void Priority() {
		Scanner s = new Scanner(System.in);       
        int x,n,p[],pp[],bt[],w[],t[],awt,atat,i;
        p = new int[10];
        pp = new int[10];
        bt = new int[10];
        w = new int[10];
        t = new int[10];
		System.out.print(CYAN+"Entrez le nombre des processus : ");
		n = s.nextInt();
		System.out.print(CYAN+"\n\t Enter burst time : time priorities \n");
		
		for(i=0;i<n;i++)
		{
		   System.out.print("\nProcessus["+(i+1)+"]:");
		  bt[i] = s.nextInt();
		  pp[i] = s.nextInt();
		  p[i]=i+1;
		}
		
		//sorting on the basis of priority
		for(i=0;i<n-1;i++)
		{
		 for(int j=i+1;j<n;j++)
		 {
		   if(pp[i]>pp[j])
		   {
		 x=pp[i];
		 pp[i]=pp[j];
		 pp[j]=x;
		 x=bt[i];
		 bt[i]=bt[j];
		 bt[j]=x;
		 x=p[i];
		 p[i]=p[j];
		 p[j]=x;
		  }
		}
		}
		w[0]=0;
		awt=0;
		t[0]=bt[0];
		atat=t[0];
		for(i=1;i<n;i++)
		{
		w[i]=t[i-1];
		awt+=w[i];
		t[i]=w[i]+bt[i];
		atat+=t[i];
		}
		
		//Displaying the process
		
		System.out.print("\n\nProcessus \t Temps d exécution \t Temps d attente \t Temps de rotation   Priorité \n");
		for(i=0;i<n;i++)
		System.out.print("\n   "+p[i]+"\t\t   "+bt[i]+"\t\t     "+w[i]+"\t\t     "+t[i]+"\t\t     "+pp[i]+"\n");
		awt/=n;
		atat/=n;
		System.out.print("\n Temps d attente moyene : "+awt);
		System.out.print("\n Temps de rotation : "+atat);
		System.out.println("\n");
	}
	 	
	//Round robin
	public static void RoundRobin(String p[], int a[], 
            int b[], int n) {
			{ 
			// result of average times 
			int res = 0; 
			int resc = 0; 
			
			// for sequence storage 
			String seq = new String(); 
			
			// copy the burst array and arrival array 
			// for not effecting the actual array 
			int res_b[] = new int[b.length]; 
			int res_a[] = new int[a.length]; 
			
			for (int i = 0; i < res_b.length; i++) { 
			res_b[i] = b[i]; 
			res_a[i] = a[i]; 
			} 
			
			// critical time of system 
			int t = 0; 
			
			// for store the waiting time 
			int w[] = new int[p.length]; 
			
			// for store the Completion time 
			int comp[] = new int[p.length]; 
			
			while (true) { 
			boolean flag = true; 
			for (int i = 0; i < p.length; i++) { 
			
			// these condition for if 
			// arrival is not on zero 
			
			// check that if there come before qtime 
			if (res_a[i] <= t) { 
			   if (res_a[i] <= n) { 
			       if (res_b[i] > 0) { 
			           flag = false; 
			           if (res_b[i] > n) { 
			
			               // make decrease the b time 
			               t = t + n; 
			               res_b[i] = res_b[i] - n; 
			               res_a[i] = res_a[i] + n; 
			               seq += "->" + p[i]; 
			           } 
			           else { 
			
			               // for last time 
			               t = t + res_b[i]; 
			
			               // store comp time 
			               comp[i] = t - a[i]; 
			
			               // store wait time 
			               w[i] = t - b[i] - a[i]; 
			               res_b[i] = 0; 
			
			               // add sequence 
			               seq += "->" + p[i]; 
			           } 
			       } 
			   } 
			   else if (res_a[i] > n) { 
			
			       // is any have less arrival time 
			       // the coming process then execute them 
			       for (int j = 0; j < p.length; j++) { 
			
			           // compare 
			           if (res_a[j] < res_a[i]) { 
			               if (res_b[j] > 0) { 
			                   flag = false; 
			                   if (res_b[j] > n) { 
			                       t = t + n; 
			                       res_b[j] = res_b[j] - n; 
			                       res_a[j] = res_a[j] + n; 
			                       seq += "->" + p[j]; 
			                   } 
			                   else { 
			                       t = t + res_b[j]; 
			                       comp[j] = t - a[j]; 
			                       w[j] = t - b[j] - a[j]; 
			                       res_b[j] = 0; 
			                       seq += "->" + p[j]; 
			                   } 
			               } 
			           } 
			       } 
			
			       // now the previous porcess according to 
			       // ith is process 
			       if (res_b[i] > 0) { 
			           flag = false; 
			
			           // Check for greaters 
			           if (res_b[i] > n) { 
			               t = t + n; 
			               res_b[i] = res_b[i] - n; 
			               res_a[i] = res_a[i] + n; 
			               seq += "->" + p[i]; 
			           } 
			           else { 
			               t = t + res_b[i]; 
			               comp[i] = t - a[i]; 
			               w[i] = t - b[i] - a[i]; 
			               res_b[i] = 0; 
			               seq += "->" + p[i]; 
			           } 
			       } 
			   } 
			} 
			
			// if no process is come on thse critical 
			else if (res_a[i] > t) { 
			   t++; 
			   i--; 
			} 
			} 
			// for exit the while loop 
			if (flag) { 
			break; 
			} 
			} 
			
			System.out.println("Nom  FExecution  TAttente"); 
			for (int i = 0; i < p.length; i++) { 
			System.out.println(" " + p[i] + "    " + comp[i] 
			              + "    " + w[i]); 
			
			res = res + w[i]; 
			resc = resc + comp[i]; 
			} 
			
			System.out.println("Le temps d'attente moyenne est:  "
			          + (float)res / p.length); 
			System.out.println("Le temps d'éxécution moyenne est "
			          + (float)resc / p.length); 
			System.out.println("La séquence est comme suit " + seq); 
} 
	}
	
// Fifo

	public static void  main ( String [ ] args ) throws IOException 
	 {		
	
		 
		 
		System.out.println(BLUE+"*****************************************Bienvenu à ISI'S Shell*******************************");		
		Scanner sc = new Scanner(System.in);
		System.out.println(CYAN+"Vous vouslez étudier la première ou la deuxieme partie?");
		System.out.println(CYAN+"Pressez 1 pour utiliser les commandes");
		System.out.println(CYAN+"Pressez 2 pour tester l ordonnancement");
		int part= sc.nextInt();
		
		while(true) {
			if(part==1){
			String curDir = System.getProperty("user.dir");
			//getPathAndHome();
			System.out.println(curDir+" > ");
			Scanner sc1 = new Scanner(System.in);
			String commande = sc1.nextLine();
			String[] separated = commande.split(" ");
			
			switch(separated[0]) {
			case "help":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				}
				else if(separated.length==3) {
				Redirection(separated);
				} else {
				help();
				}
				break;
			case "changeDirectory":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				}
				else {
				cd(separated[1]);
				}
				break;
			case "create":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				}
				else {
				mkDir(separated[1]);
				}
				break;
			case "list":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				} else if(separated.length==2){
					if(separated[1].equals("-s")) {
						sortLs();
					}
					else {
						System.out.println(RED+"Commande invalide!");
					}
				}
				else if(separated.length==3) {
					Redirection(separated);
				} else {
					ls();
					}
				break;
			case "writeOnFile":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				}
				else {
					WriteOnANewFileWord(separated[1]);
				}
				break;
			case "removeFile":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				}
				else {
					removeFile(separated[1]);
				}
				break;
			case "executeFile":
				if (separated.length > 3 || separated.length == 0) {
					System.out.println(RED+"Commande invalide!");
				}
				else {
				ExecuteFile(separated[1]);
				}
			
				break;
			case "precedentDirectory":
				cdTwo();
				break;
			case "alias":
				alias(separated);
				break;
			case "exit": 
				System.out.println(CYAN+"Vous vouslez étudier la première ou la deuxieme partie?");
				System.out.println(CYAN+"Pressez 1 pour utiliser les commandes");
				System.out.println(CYAN+"Pressez 2 pour tester l ordonnancement");
				 part= sc.nextInt();
				 break;
				
			default:
				if(newHelp.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					}
					else if(separated.length==3) {
					Redirection(separated);
					} else {
					help();
					}
				}
				else if(newChangeDirectory.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					}
					else {
					cd(separated[1]);
					}
				}
				else if(newCreate.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					}
					else {
					mkDir(separated[1]);
					}
				}
				else if(newList.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					} else if(separated.length==2){
						if(separated[1].equals("-s")) {
							sortLs();
						}
						else {
							System.out.println(RED+"Commande invalide!");
						}
					}
					else if(separated.length==3) {
						Redirection(separated);
					} else {
						ls();
						}
				}
				else if(newWriteOnFile.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					}
					else {
						WriteOnANewFileWord(separated[1]);
					}
				}
				else if(newRemoveFile.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					}
					else {
						removeFile(separated[1]);
					}
				}
				else if(newExecuteFile.length()!=0) {
					if (separated.length > 3 || separated.length == 0) {
						System.out.println(RED+"Commande invalide!");
					}
					else {
					ExecuteFile(separated[1]);
					}
				}
				else if(newPrecedentDirectory.length()!=0) {
					cdTwo();
				}
				else {
				System.out.println(RED+"Commande invalide!");
				}
			}	
			}else if(part == 2) {
				int ordonancement;
				while(true) {
				System.out.println(CYAN+"Pressez 1 si vous voulez ordonnancer les processus selon la priorité");
				System.out.println(CYAN+"Pressez 2 si vous voulez ordonnancer les processus selon l algorithme Round Robin");
				System.out.println(CYAN+"Pressez 3 si vous voulez ordonnancer les processus selon l algorithme Fifo");
				System.out.println(CYAN+"Pressez 4 si vous voulez ordonnancer les processus selon l algorithme SJF");
				System.out.println(CYAN+"Pressez 5 si vous voulez sortire du partie ordonnacent  ");
				ordonancement= sc.nextInt();
				if(ordonancement==1) {
					Priority();
				}
				else if(ordonancement==2) {
			        String name[] = { "p1", "p2", "p3", "p4" }; 
			        int arrivaltime[] = { 0, 0, 0, 0 }; 
			        int bursttime[] = { 10, 4, 5, 3 }; 
			        int q = 2; 
			        RoundRobin(name, arrivaltime, bursttime, q); 
			    } else if(ordonancement==3) {
			    	System.out.println("Entrez le nombre des processus");
			        Scanner in = new Scanner(System.in);
			        int numberOfProcesses = in.nextInt();

			        int pid[] = new int[numberOfProcesses];
			        int bt[] = new int[numberOfProcesses];
			        int ar[] = new int[numberOfProcesses];
			        int ct[] = new int[numberOfProcesses];
			        int ta[] = new int[numberOfProcesses];
			        int wt[] = new int[numberOfProcesses];

			        for(int i = 0; i < numberOfProcesses; i++) {
			            System.out.println("Entrez le temps d arrivé du processus " + (i+1) + " :");
			            ar[i] = in.nextInt();
			            System.out.println("Entrez le temps d éxécution du processus " + (i+1) + " :");
			            bt[i] = in.nextInt();
			            pid[i] = i+1;
			        }
			        int temp;
			        for (int i = 0; i < numberOfProcesses; i++) {
			            for (int j = i+1; j < numberOfProcesses; j++) {

			                if(ar[i] > ar[j]) {
			                    temp = ar[i];
			                    ar[i] = ar[j];
			                    ar[j] = temp;

			                    temp = pid[i];
			                    pid[i] = pid[j];
			                    pid[j] = temp;
			                    temp = bt[i];
			                    bt[i] = bt[j];
			                    bt[j] = temp;
			                }
			            }
			        }

			        System.out.println();
			        ct[0] = bt[0] + ar[0];
			        for(int i = 1; i < numberOfProcesses; i++) {
			            ct[i] = ct[i - 1] + bt[i];
			        }
			        for(int i = 0; i < numberOfProcesses; i++) {
			            ta[i] = ct[i] - ar[i];
			            wt[i] = ta[i] - bt[i];
			        }
			        System.out.println("Processus\t\tAT\t\tBT\t\tCT\t\tTAT\t\tWT");
			        for(int i = 0; i < numberOfProcesses; i++) {
			            System.out.println(pid[i]+"\t\t\t" + ar[i] + "\t\t" + bt[i]+ "\t\t" + ct[i]+ "\t\t" + ta[i]+ "\t\t" + wt[i]);
			        }

			        System.out.println("Diagramme de gant: ");
			        for(int i = 0; i < numberOfProcesses; i++) {
			            System.out.print("P" + pid[i] +" ");
			        }
			        System.out.println("\n");
			} else if(ordonancement == 4) {
				 Scanner scR = new Scanner(System.in);
			        System.out.println ("enter no of process:");
			        int n = scR.nextInt();
			        int pid[] = new int[n];
			        int at[] = new int[n];
			        int bt[] = new int[n];
			        int ct[] = new int[n];
			        int ta[] = new int[n];
			        int wt[] = new int[n];
			        int f[] = new int[n];

			        int st=0, tot=0;
			        float avgwt=0, avgta=0;

			        for(int i=0;i<n;i++)
			        {
			            System.out.println ("enter process " + (i+1) + " arrival time:");
			            at[i] = sc.nextInt();
			            System.out.println ("enter process " + (i+1) + " brust time:");
			            bt[i] = sc.nextInt();
			            pid[i] = i+1;
			            f[i] = 0;
			        }


			        while(true)
			        {
			            int c=n, min = 999999;

			            if (tot == n)
			                break;

			            for (int i=0; i<n; i++)
			            {

			                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
			                {
			                    min=bt[i];
			                    c=i;
			                }
			            }
			            if (c==n)
			                st++;
			            else
			            {
			                ct[c]=st+bt[c];
			                st+=bt[c];
			                ta[c]=ct[c]-at[c];
			                wt[c]=ta[c]-bt[c];
			                f[c]=1;
			                pid[tot] = c + 1;
			                tot++;
			            }
			        }

			        System.out.println("\npid              arrival brust  complete turn waiting");
			        for(int i=0;i<n;i++)
			        {
			            avgwt+= wt[i];
			            avgta+= ta[i];
			            System.out.println(pid[i]+"\t\t"+at[i]+"\t\t"+bt[i]+"\t\t"+ct[i]+"\t\t"+ta[i]+"\t\t"+wt[i]);
			        }
			        System.out.println ("\naverage tat is "+ (float)(avgta/n));
			        System.out.println ("average wt is "+ (float)(avgwt/n));
			        
			        for(int i=0;i<n;i++)
			        {
			            System.out.print(pid[i] + " ");
			           
			        }
			        System.out.println("\n");
			    }else if (ordonancement == 5) {
			    	System.out.println(CYAN+"Vous vouslez étudier la première ou la deuxieme partie?");
					System.out.println(CYAN+"Pressez 1 pour utiliser les commandes");
					System.out.println(CYAN+"Pressez 2 pour tester l ordonnancement");
					part= sc.nextInt();
					break;
			    	
			    }

			}
		}		
	 }
	
			}
		
	 }

	    


		
	
	 
	 
	

