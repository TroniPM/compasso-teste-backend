# Compasso teste backend
Requerido:
   - Mysql 8
   - Java 8
   
### Modificar linhas de configuração em [```src/main/resources/application.properties```](https://github.com/TroniPM/compasso-teste-backend/blob/master/src/main/resources/application.properties):
Alterar ```compasso``` para alterar o banco de dados.

```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/compasso
spring.datasource.username=root
spring.datasource.password=
```
