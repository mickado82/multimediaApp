package fileslist

class DirBean {
	static constraints = {
    }
	
<<<<<<< HEAD
	//The name of the directory where the tracks are 
	String name
	
	//The Label of the dir : Will be dispalyed in the view
	String label
	
=======
	String name
>>>>>>> refs/heads/master
	String fullPath
	String frontCover
	String backCover
	List files
	static hasMany = [files: FileBean]
}
