package sistemaacademico

class LoginController {
	
	static scaffold = true

	def preLogin() {
		if (!session.login) {
			render view: 'login', model : [login: new Login()]
		} else {
			render view: '/index'
		}
	}
		
	def login() {
		def logins = Login.list()
		def login = logins.find { ((it.login == params.login) && (it.senha == params.senha)) }
		if (login) {
			session.login = login
			render view: '../index'
		} else {
			flash.message = "Autenticacao nao realizada!"
			render view: 'login', model: [login: new Login(params)]
		}
	}
	
	def logout() {
		session.login = null
		preLogin()
	}
}
