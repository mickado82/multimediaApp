package fileslist

import org.springframework.dao.DataIntegrityViolationException

class FileBeanController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [fileBeanInstanceList: FileBean.list(params), fileBeanInstanceTotal: FileBean.count()]
    }

    def create() {
        [fileBeanInstance: new FileBean(params)]
    }

    def save() {
        def fileBeanInstance = new FileBean(params)
        if (!fileBeanInstance.save(flush: true)) {
            render(view: "create", model: [fileBeanInstance: fileBeanInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'fileBean.label', default: 'FileBean'), fileBeanInstance.id])
        redirect(action: "show", id: fileBeanInstance.id)
    }

    def show() {
        def fileBeanInstance = FileBean.get(params.id)
        if (!fileBeanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileBean.label', default: 'FileBean'), params.id])
            redirect(action: "list")
            return
        }

        [fileBeanInstance: fileBeanInstance]
    }

    def edit() {
        def fileBeanInstance = FileBean.get(params.id)
        if (!fileBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileBean.label', default: 'FileBean'), params.id])
            redirect(action: "list")
            return
        }

        [fileBeanInstance: fileBeanInstance]
    }

    def update() {
        def fileBeanInstance = FileBean.get(params.id)
        if (!fileBeanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileBean.label', default: 'FileBean'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (fileBeanInstance.version > version) {
                fileBeanInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fileBean.label', default: 'FileBean')] as Object[],
                          "Another user has updated this FileBean while you were editing")
                render(view: "edit", model: [fileBeanInstance: fileBeanInstance])
                return
            }
        }

        fileBeanInstance.properties = params

        if (!fileBeanInstance.save(flush: true)) {
            render(view: "edit", model: [fileBeanInstance: fileBeanInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'fileBean.label', default: 'FileBean'), fileBeanInstance.id])
        redirect(action: "show", id: fileBeanInstance.id)
    }

    def delete() {
        def fileBeanInstance = FileBean.get(params.id)
        if (!fileBeanInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'fileBean.label', default: 'FileBean'), params.id])
            redirect(action: "list")
            return
        }

        try {
            fileBeanInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'fileBean.label', default: 'FileBean'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fileBean.label', default: 'FileBean'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
