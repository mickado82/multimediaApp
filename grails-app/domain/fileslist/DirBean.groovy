package fileslist

class DirBean {
	static constraints = {
    }
	
	String name
	String fullPath
	String frontCover
	String backCover
	List files
	static hasMany = [files: FileBean]
}
