# springboot-crud
очень простое crud приложение 

основанно на springboot 
используется встроенный tomcat

для сборки проекта,  достаточно выполнить mvn install в корневой папке springboot-crud/

проект собирается в исполняемый jar файл (с манифестом), при выполнении которого, запускается tomcat на порту 8080

для доступа к rest сервисам, необходима пара логин пароль
admin
adminpassword

/users 
список пользователей 

на эти URL 
/create
создать 
/delete
удалить
/update
редактировать

передается объект {"id":"new_id","password":"new_password"}
по id которого определяется объект над которым будет совершена операция

при создании и обновленнии длинна id не меньше 3, длинна пароля не менее 8 символов

//TO DO: в данный момент для delete необходимо вводить пароль имеющий минимальную длинну. т.к. валидатор проверяет и его тоже. Переделать контроллер на прием только id.






