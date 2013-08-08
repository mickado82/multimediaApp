package videoslist

class VideoBean {

    static constraints = {
		name blank: false
		path nullable: true
    }
	
	String name
	String path 
	Boolean available
	
}
