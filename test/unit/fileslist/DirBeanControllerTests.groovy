package fileslist



import org.junit.*
import grails.test.mixin.*

@TestFor(DirBeanController)
@Mock(DirBean)
class DirBeanControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/dirBean/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.dirBeanInstanceList.size() == 0
        assert model.dirBeanInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.dirBeanInstance != null
    }

    void testSave() {
        controller.save()

        assert model.dirBeanInstance != null
        assert view == '/dirBean/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/dirBean/show/1'
        assert controller.flash.message != null
        assert DirBean.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/dirBean/list'


        populateValidParams(params)
        def dirBean = new DirBean(params)

        assert dirBean.save() != null

        params.id = dirBean.id

        def model = controller.show()

        assert model.dirBeanInstance == dirBean
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/dirBean/list'


        populateValidParams(params)
        def dirBean = new DirBean(params)

        assert dirBean.save() != null

        params.id = dirBean.id

        def model = controller.edit()

        assert model.dirBeanInstance == dirBean
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/dirBean/list'

        response.reset()


        populateValidParams(params)
        def dirBean = new DirBean(params)

        assert dirBean.save() != null

        // test invalid parameters in update
        params.id = dirBean.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/dirBean/edit"
        assert model.dirBeanInstance != null

        dirBean.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/dirBean/show/$dirBean.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        dirBean.clearErrors()

        populateValidParams(params)
        params.id = dirBean.id
        params.version = -1
        controller.update()

        assert view == "/dirBean/edit"
        assert model.dirBeanInstance != null
        assert model.dirBeanInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/dirBean/list'

        response.reset()

        populateValidParams(params)
        def dirBean = new DirBean(params)

        assert dirBean.save() != null
        assert DirBean.count() == 1

        params.id = dirBean.id

        controller.delete()

        assert DirBean.count() == 0
        assert DirBean.get(dirBean.id) == null
        assert response.redirectedUrl == '/dirBean/list'
    }
}
