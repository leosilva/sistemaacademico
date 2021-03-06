package sistemaacademico

class LoginController {
	
	static scaffold = true
	
	def simpleCaptchaService

	def login() {
		def logins = Login.list()
		def login = logins.find { ((it.login == params.login) && (it.senha == params.senha)) }
		if (login) {
			session.login = login
			render template: '/shared/loginForm'
		} else {
			flash.message = "Autenticacao nao realizada!"
			render template: '/shared/loginForm', model: [login: new Login(params)]
		}
	}
	
	def logout() {
		session.login = null
		render template: '/shared/loginForm'
	}
	
	def showTime() {
		render "The time is ${new Date()}"
	}
	
	def verificaLoginExistente() {
		def logins = Login.list()
		def login = logins.find { (it.login == params.login) }
		if (login) {
			render "Login ja existente!"
		} else {
			render ""
		}
	}
	
	def validateCaptcha() {
		render simpleCaptchaService.validateCaptcha(params.captcha)
	}
}
