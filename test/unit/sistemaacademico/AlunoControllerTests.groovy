package sistemaacademico



import org.junit.*
import grails.test.mixin.*

@TestFor(AlunoController)
@Mock(Aluno)
class AlunoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/aluno/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.alunoInstanceList.size() == 0
        assert model.alunoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.alunoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.alunoInstance != null
        assert view == '/aluno/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/aluno/show/1'
        assert controller.flash.message != null
        assert Aluno.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/aluno/list'

        populateValidParams(params)
        def aluno = new Aluno(params)

        assert aluno.save() != null

        params.id = aluno.id

        def model = controller.show()

        assert model.alunoInstance == aluno
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/aluno/list'

        populateValidParams(params)
        def aluno = new Aluno(params)

        assert aluno.save() != null

        params.id = aluno.id

        def model = controller.edit()

        assert model.alunoInstance == aluno
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/aluno/list'

        response.reset()

        populateValidParams(params)
        def aluno = new Aluno(params)

        assert aluno.save() != null

        // test invalid parameters in update
        params.id = aluno.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/aluno/edit"
        assert model.alunoInstance != null

        aluno.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/aluno/show/$aluno.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        aluno.clearErrors()

        populateValidParams(params)
        params.id = aluno.id
        params.version = -1
        controller.update()

        assert view == "/aluno/edit"
        assert model.alunoInstance != null
        assert model.alunoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/aluno/list'

        response.reset()

        populateValidParams(params)
        def aluno = new Aluno(params)

        assert aluno.save() != null
        assert Aluno.count() == 1

        params.id = aluno.id

        controller.delete()

        assert Aluno.count() == 0
        assert Aluno.get(aluno.id) == null
        assert response.redirectedUrl == '/aluno/list'
    }
}
