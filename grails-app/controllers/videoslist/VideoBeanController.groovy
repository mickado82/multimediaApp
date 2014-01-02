package videoslist

import grails.gorm.PagedResultList;

import org.springframework.dao.DataIntegrityViolationException


class VideoBeanController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def videoBeanService
	
	/**
	 * Dependency injection for the springSecurityService.
	 */
	def springSecurityService

    def index() {
        redirect(action: "browse", params: params)
    }

	/**
	 * For admin usage only : list all the files of the database and display result in list.gsp
	 * Checks if the files are still there
	 * @param max
	 * @return
	 */
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
        [videoBeanInstanceList: videoBeanService.filteredVideoPagedResultList(params), videoBeanInstanceTotal: VideoBean.count()]
    }
	
	
	/**
	 * For 'Regular user' Same as list action 
	 * but result is displayed with less infos in browse.gsp
	 * @param max
	 * @return
	 */
	def browse(Integer max) {
		
		def user = springSecurityService.getPrincipal()
		
		log.info("${user.username} Accessed Videos list")
		
		params.max = max ?: 50
		
		[videoBeanInstanceList: videoBeanService.filteredVideoPagedResultList(params), videoBeanInstanceTotal: VideoBean.count()]
	}

    def create() {
        [videoBeanInstance: new VideoBean(params)]
    }

    def save() {
        def videoBeanInstance = new VideoBean(params)
		
		log.info("Adding video File with name ${params.name}")
		log.info("Checking File ${params.name} availability in path ${params.path}")
		
		def available = videoBeanService.fileAvailable(params.name, params.path)
		
		if(available)
			log.info("File ${params.name} is available")
		else
			log.warn("File ${params.name} is NOT available !!")
		
		//check if video File is here and set its availability
		videoBeanInstance.available = new Boolean(available)
		
        if (!videoBeanInstance.save(flush: true)) {
            render(view: "create", model: [videoBeanInstance: videoBeanInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'videoBean.label', default: 'VideoBean'), videoBeanInstance.id])
        redirect(action: "show", id: videoBeanInstance.id)
    }
	
	def edit() {
		def videoBeanInstance = VideoBean.get(params.id)
		if (!videoBeanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'dirBean.label', default: 'DirBean'), params.id])
			redirect(action: "list")
			return
		}

		[videoBeanInstance: videoBeanInstance]
	}
	
	def update(Long id, Long version) {
		def videoBeanInstance = VideoBean.get(id)
		if (!videoBeanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'video.label', default: 'Video'), id])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (videoBeanInstance.version > version) {
				videoBeanInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						  [message(code: 'video.label', default: 'Video')] as Object[],
						  "Another user has updated this Video while you were editing")
				render(view: "edit", model: [userInstance: videoBeanInstance])
				return
			}
		}

		videoBeanInstance.properties = params

		if (!videoBeanInstance.save(flush: true)) {
			render(view: "edit", model: [userInstance: videoBeanInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'Video'), videoBeanInstance.id])
		log.info("Video ${videoBeanInstance.name} edited")

		//We need to check again if video is her coz name / path may have been changed 
		def available = videoBeanService.fileAvailable(params.name, params.path)
		//check if video File is here and set its availability
		videoBeanInstance.available = new Boolean(available)
		
		redirect(action: "show", id: videoBeanInstance.id)
	}

    def show(Long id) {
        def videoBeanInstance = VideoBean.get(id)
        if (!videoBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'videoBean.label', default: 'VideoBean'), id])
            redirect(action: "list")
            return
        }

        [videoBeanInstance: videoBeanInstance]
    }

    def delete(Long id) {
        def videoBeanInstance = VideoBean.get(id)
        if (!videoBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'videoBean.label', default: 'VideoBean'), id])
            redirect(action: "list")
            return
        }

        try {
            videoBeanInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'videoBean.label', default: 'VideoBean'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'videoBean.label', default: 'VideoBean'), id])
            redirect(action: "show", id: id)
        }
    }
}
