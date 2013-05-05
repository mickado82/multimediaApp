package fileslist

import org.springframework.dao.DataIntegrityViolationException


/*@Secured(['ROLE_ADMIN', 'ROLE_USER'])*/
class DirBeanController {	
	
	def dirBeanService
	
	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def download(){

		/*
		Cookie cookie = new Cookie("fileDownload","true")
		cookie.setPath("/")
		response.addCookie(cookie)
		*/
		
		//No param was given to the ajax call: should not happen
		if(params.id == null){
			log.error("NO DOWNLOAD CALL SHOULD BE LAUNCHED WITH id = null !!!")
			return redirect(action: "list", params: params)
		}
		
		log.info("Download request received for id ${params.id}")

		List dirBeanInstanceList = servletContext["dirBeanInstanceList"]
		
		def theDir = null
		
		try{
			
			theDir = dirBeanInstanceList.get(params.id.toInteger())
			File theZip = new File(dirBeanService.zipDir(theDir))
			
			println "application directory : ${System.properties['base.dir']}"
			
			/*
			response.setHeader("Content-Type", "application/zip;")
			response.setHeader("Content-Disposition", "attachment; filename=\"${theZip.name}\"")
			response.setHeader("Content-Length", "${theZip.size()}")
			response.outputStream << theZip.bytes
			response.outputStream.flush()
			 */
			
			log.info("Generating download link for file ${theZip.getName()}")
			render "<A HREF='${request.getContextPath()}/resources/${theZip.getName()}'><button class='dlLinkBtn'>Download</button></A>"
			//redirect(action: "list")
		
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
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		servletContext["dirBeanInstanceList"] = dirBeanService.createList(params.sort)
        [dirBeanInstanceList: servletContext["dirBeanInstanceList"], dirBeanInstanceTotal: DirBean.count()]
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
