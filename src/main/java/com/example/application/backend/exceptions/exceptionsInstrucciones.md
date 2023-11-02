
1) crear carpeta exceptions
2) crear clase ClientNotFoundException
3) crear clase RestResponseEntityExceptionHandler
4) crear carpeta dto
5) dentro de dto crear clase ErrorMessage
6) dentro de ErrorMessage, extends Exception y crear dos atributos(HttpStatus y message)
7) configurar ClientNotFoundException(crear constructor solo con message)
8) configurar RestResponseEntityExceptionHandler
9) ir al metodo que use get en controller, servicesInt, servicesImpl y 
   agregar throws ClientNotFoundException.
10) en serviceImpl cambiar la logica para que devuelva la exception.
