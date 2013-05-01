package videoslist

class VideoBean {

    static constraints = {
		path nullable: true
    }
	
	String name
	String path 
	Boolean available
	
}
