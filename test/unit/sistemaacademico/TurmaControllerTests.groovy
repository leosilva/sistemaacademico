package sistemaacademico



import org.junit.*
import grails.test.mixin.*

@TestFor(TurmaController)
@Mock(Turma)
class TurmaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/turma/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.turmaInstanceList.size() == 0
        assert model.turmaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.turmaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.turmaInstance != null
        assert view == '/turma/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/turma/show/1'
        assert controller.flash.message != null
        assert Turma.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/turma/list'

        populateValidParams(params)
        def turma = new Turma(params)

        assert turma.save() != null

        params.id = turma.id

        def model = controller.show()

        assert model.turmaInstance == turma
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/turma/list'

        populateValidParams(params)
        def turma = new Turma(params)

        assert turma.save() != null

        params.id = turma.id

        def model = controller.edit()

        assert model.turmaInstance == turma
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/turma/list'

        response.reset()

        populateValidParams(params)
        def turma = new Turma(params)

        assert turma.save() != null

        // test invalid parameters in update
        params.id = turma.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/turma/edit"
        assert model.turmaInstance != null

        turma.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/turma/show/$turma.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        turma.clearErrors()

        populateValidParams(params)
        params.id = turma.id
        params.version = -1
        controller.update()

        assert view == "/turma/edit"
        assert model.turmaInstance != null
        assert model.turmaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/turma/list'

        response.reset()

        populateValidParams(params)
        def turma = new Turma(params)

        assert turma.save() != null
        assert Turma.count() == 1

        params.id = turma.id

        controller.delete()

        assert Turma.count() == 0
        assert Turma.get(turma.id) == null
        assert response.redirectedUrl == '/turma/list'
    }
}
