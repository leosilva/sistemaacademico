import sistemaacademico.Login

class BootStrap {

    def init = { servletContext ->
		def login = new Login(login: "leosilva", senha: '123')
		login.save()
    }
    def destroy = {
    }
}
