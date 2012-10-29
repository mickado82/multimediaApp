package fileslist

class FileBean {

    static constraints = {
    }
	
	String name;
	Long size;
	
	static belongsTo = [dir: DirBean]
}
