package fileslist

import java.awt.ModalEventFilter.ApplicationModalEventFilter;
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class DirBeanService {

		def grailsApplication

    def createList() {	
		
		def list = []
		def mainDir = grailsApplication.config.my.files.dir
		def nDir = 0
		
		new File(mainDir).eachDir() { theDir->

			def dirBean = new DirBean(name: theDir.name, fullPath: theDir.getPath())
			dirBean.id = nDir
			nDir++
			theDir.eachFile(){theFile -> 
				dirBean.addToFiles(new FileBean(name: theFile.name, size: theFile.size()))
				}
			list.add(dirBean)
		}
		list
    }
	
	def zipDir(dirBeanInstance){
		def fs = new File(grailsApplication.config.home.folder).getFreeSpace()
		
		println "approx free space available on disk: ${fs/1e9} (limit = ${grailsApplication.config.min.free.space})"
		
		if(fs/1e9 < grailsApplication.config.min.free.space){
			println "Not enough space available in temp dir"
			return
		}
		
		def zipFileName = "${dirBeanInstance.name}.zip"
		def inputDir = dirBeanInstance.fullPath
		
		def zipFileFullName = grailsApplication.config.temp.dir + zipFileName
		
		if((new File(zipFileFullName)).exists()){
			println "ZipFile already exists"
			return zipFileFullName
		}
		
		println "ZipFile does not exist : Starting zip process ..."
		
		File topDir = new File(inputDir)
		ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipFileFullName));
		
		int topDirLength = topDir.absolutePath.length()
		
		topDir.eachFileRecurse{ file ->
		  def relative = file.absolutePath.substring(topDirLength).replace('\\', '/')
		  if ( file.isDirectory() && !relative.endsWith('/')){
			relative += "/"
		  }
		  
		  println "zipping ${relative}"
		
		  ZipEntry entry = new ZipEntry(relative)
		  entry.time = file.lastModified()
		  zipOutput.putNextEntry(entry)
		  if( file.isFile() ){
			zipOutput << new FileInputStream(file)
		  }
		}
		zipOutput.close()
		
		println "Zip process done"
		
		zipFileFullName
		
	}
}
