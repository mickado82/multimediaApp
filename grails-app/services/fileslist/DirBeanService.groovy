package fileslist

import java.awt.ModalEventFilter.ApplicationModalEventFilter;
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class DirBeanService {

	//Max file name length : Cosmetics to disp properly names in GSP
	static final int MAX_NAME_LENGTH = 60

		def grailsApplication

    def createList(String sortType) {	
		
		//Main list with the beans that will be displayed in the view 
		def list = []
		def mainDir = grailsApplication.config.my.files.dir
		def nDir = 0

		log.info("Listing docs from ${mainDir}")
		
		//Temp list used to sort the dirs if needed
		def dirList = []
		
		new File(mainDir).eachDir { theDir ->
			dirList.add(theDir)
		}

		//We need to sort the list when a chronological order is required
		if(sortType == 'newest'){
			dirList = dirList.sort{ it.lastModified()}.reverse()
		}
		
		//For each dir found, we add all the useful files (audio and covers) 
		dirList.each { theDir->
			def dirBean = new DirBean(name: theDir.name, fullPath: theDir.getPath())
			dirBean.id = nDir
			nDir++
			theDir.eachFile(){theFile -> 
				sortFiles(dirBean, theFile)
			}
			list.add(dirBean)
		}
		
		list
    }
	
	def sortFiles(DirBean dirBean, File theFile){
		def fileName = theFile.name
		//Select audio file and add them to the files list
		if(fileName ==~ /(?i).+\.(mp3|mp4|wmv|m4a|flac)/){
			
			if(fileName.length() > MAX_NAME_LENGTH)
				fileName = formatName(fileName)
			
			dirBean.addToFiles(new FileBean(name: fileName, size: theFile.size()))
			
			return
		}
			
		//Now identify image which could be the front cover
		if(fileName ==~ /(?i).*(FRONT).*\.(jpg|bmp|png)/)
			dirBean.frontCover = theFile.name
		
		//Finally the back cover if any
		if(fileName ==~ /(?i).*(BACK).*\.(jpg|bmp|png)/)
			dirBean.backCover = theFile.name
	}
	
	/**
	 * Cut a file name so it can be properly displayed in a GSP
	 * @param fileName the file name
	 * @return the filename with max 70 chars incl file ext
	 */
	def formatName(String fileName){
		def fileExt = fileName.substring(fileName.lastIndexOf('.'))
		fileName = fileName.substring(0, MAX_NAME_LENGTH - 6)
		fileName = fileName + '...' + fileExt
		fileName
	}
	
	def zipDir(dirBeanInstance){
		def fs = new File(grailsApplication.config.home.folder).getFreeSpace()
		
		log.info("approx free space available on disk: ${fs/1e9} (limit = ${grailsApplication.config.min.free.space})")
		
		if(fs/1e9 < grailsApplication.config.min.free.space){
			log.info("Not enough space available in temp dir")
			return
		}
		
		def zipFileName = "${dirBeanInstance.name}.zip"
		def inputDir = dirBeanInstance.fullPath
		
		def zipFileFullName = grailsApplication.config.temp.dir + zipFileName
		
		log.info("Looking for file in ${grailsApplication.config.temp.dir} ...")
		
		if((new File(zipFileFullName)).exists()){
			log.info("ZipFile already exists")
			return zipFileFullName
		}
		
		log.info("ZipFile does not exist : Starting zip process ...")
		
		File topDir = new File(inputDir)
		ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFileFullName));
		
		int topDirLength = topDir.absolutePath.length()
		
		topDir.eachFileRecurse{ file ->
		  def relative = file.absolutePath.substring(topDirLength).replace('\\', '/')
		  if ( file.isDirectory() && !relative.endsWith('/')){
			relative += "/"
		  }
		  
		  log.info("zipping ${relative}")
		
		  ZipEntry entry = new ZipEntry(relative)
		  entry.time = file.lastModified()
		  zipOutput.putNextEntry(entry)
		  if( file.isFile() ){
			zipOutput << new FileInputStream(file)
		  }
		}
		zipOutput.close()

		log.info("Zip process done")
		
		zipFileFullName
		
	}
}
