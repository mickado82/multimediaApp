package fileslist

class DirBean {
	static constraints = {
    }
	
	String name
	String fullPath
	List files
	static hasMany = [files: FileBean]
}
