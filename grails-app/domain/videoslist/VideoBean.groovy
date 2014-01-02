package videoslist

class VideoBean {

    static constraints = {
		name blank: false
		path nullable: true
		size nullable: true
		imdbId nullable: true
    }
	
	String name
	String path
	Double size 
	Boolean available
	String imdbId
	
}
