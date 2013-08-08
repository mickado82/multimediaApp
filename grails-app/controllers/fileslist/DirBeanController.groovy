package fileslist

import grails.converters.JSON

import org.codehaus.groovy.grails.web.json.JSONObject
import org.springframework.dao.DataIntegrityViolationException


class DirBeanController {	
	
	def dirBeanService
	
	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def download(){
		
		def id = params.id
		
		//No param was given to the ajax call: should not happen
		if(id == null){
			log.error("NO DOWNLOAD CALL SHOULD BE LAUNCHED WITH id = null !!!")
			return redirect(action: "list", params: params)
		}
		
		log.info("Download request received for id ${id}")
		
		def name = null
		
		try{
			
			//Here, the json list is retrieved as a linkedHashmap (Optimize ?)
			name = session["jsonList"].get(id.toInteger()).get("name")
			
			File theZip = new File(dirBeanService.zipDir(name))
			
			println "application directory : ${System.properties['base.dir']}"
			
			log.info("Generating download link for file ${theZip.getName()}")
			render "<A HREF='${request.getContextPath()}/resources/${theZip.getName()}'><button class='dlLinkBtn' style='font-size: 20px;'>Download</button></A>"
		
		//Invalid param was given to the ajax call: should not happen
		}catch(NumberFormatException e){
			log.error("NO DOWNLOAD CALL SHOULD BE LAUNCHED WITH id = ${params.id} !!!")
			return redirect(action: "list", params: params)
		}
		
		
	}

    def index() {
		// Default dirs sort is based on name
		params.sort = 'name'
        redirect(action: "list", params: params)
    }

    def list() {
		
		def user = springSecurityService.getPrincipal()
		
        log.info("${user.username} Accessed Dirs list")
		
    }
	
	def queryList(){
		
		def list = dirBeanService.createList(params.sort)
			
		def converter = new JSON() 
		
		def builder = new groovy.json.JsonBuilder()
		
		def jsonList = builder(list.collect{ album ->
			[name : album.name,
			 cover : album.frontCover,
			songs:album.files.collect{song ->
				name : song.name
				}]
			})
		
		//Store the list in session to retrieve an album with its ID when downloading data
		session["jsonList"] = jsonList;
			
		render jsonList as JSON
	}

    def create() {
        [dirBeanInstance: new DirBean(params)]
    }

    def save() {
        def dirBeanInstance = new DirBean(params)
        if (!dirBeanInstance.save(flush: true)) {
            render(view: "create", model: [dirBeanInstance: dirBeanInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'dirBean.label', default: 'DirBean'), dirBeanInstance.id])
        redirect(action: "show", id: dirBeanInstance.id)
    }

    def show() {
        def dirBeanInstance = DirBean.get(params.id)
        if (!dirBeanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
            redirect(action: "list")
            return
        }

        [dirBeanInstance: dirBeanInstance]
    }

    def edit() {
        def dirBeanInstance = DirBean.get(params.id)
        if (!dirBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
            redirect(action: "list")
            return
        }

        [dirBeanInstance: dirBeanInstance]
    }

    def update() {
        def dirBeanInstance = DirBean.get(params.id)
        if (!dirBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (dirBeanInstance.version > version) {
                dirBeanInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dirBean.label', default: 'DirBean')] as Object[],
                          "Another user has updated this DirBean while you were editing")
                render(view: "edit", model: [dirBeanInstance: dirBeanInstance])
                return
            }
        }

        dirBeanInstance.properties = params

        if (!dirBeanInstance.save(flush: true)) {
            render(view: "edit", model: [dirBeanInstance: dirBeanInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'dirBean.label', default: 'DirBean'), dirBeanInstance.id])
        redirect(action: "show", id: dirBeanInstance.id)
    }

    def delete() {
        def dirBeanInstance = DirBean.get(params.id)
        if (!dirBeanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
            redirect(action: "list")
            return
        }

        try {
            dirBeanInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
            redirect(action: "show", id: params.id)
        }
    }

}
