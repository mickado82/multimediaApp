package videoslist

class VideoBeanService {
	
	def grailsApplication

	//check if a file is wheter or not available
   def fileAvailable(String name, String path){
	   
	   if(path == null)
	   		return new File("${grailsApplication.config.my.files.videos}${File.separator}${name}").exists()
		else
			return new File("${grailsApplication.config.my.files.videos}${File.separator}${path}${File.separator}${name}").exists()
			
   }
   
   /**
    * Filters the list of videos and sets availablility
    * of the files according to their presence in the video directory  
    * Necessary to check if files are still there
    * 
    * @return PagedResultList containing the updated videos beans
    */
   def filteredVideoPagedResultList(params){
	   
	   //Get the paged result list from the query
	   def pagedResultList = VideoBean.list(params)
	   
	   //iterate through the list of results and check if files are still there
	   pagedResultList.list.each{
		   it.available =  fileAvailable(it.name, it.path)

		   if(!it.available){
			   log.warn("File ${it.name} is no longer available in dir ${it.path}")
		   }
		   else{
			   
			   Double size = 0
			   
			   if(it.path == null){
				   size = (Double)new File(grailsApplication.config.my.files.videos + File.separator + it.name).size()
				   println grailsApplication.config.my.files.videos + File.separator + it.name
			   }
			   else{
				   size = (Double)new File(grailsApplication.config.my.files.videos + File.separator + it.path + File.separator + it.name).size()
				   println grailsApplication.config.my.files.videos + File.separator + it.path + File.separator + it.name
			   }
			   it.size = (size/1e9).round(2)
		   }
	   }
	   
	   return pagedResultList
	   
   }
}
