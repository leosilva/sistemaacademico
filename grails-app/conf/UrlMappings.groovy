class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		"/"(controller: 'login', action: 'preLogin')
		"500"(view:'/error')
		"404" (view: '/error404')
	}

}