package videoslist

class VideoBean {

    static constraints = {
		name blank: false
		path nullable: true
		size nullable: true
		tMovieDbId nullable: true
		label nullable: true
    }
	
	//Name of the file
	String name
	//Path where the file can be found in the main video dir (If any)
	String path
	//Size of the file in GB
	Double size 
	//Is it available on the server ?
	Boolean available
	//ID to match data on webservice
	String tMovieDbId
	//Label: Display on the GSP :  More "understandable" 
	String label
	
}
