Projeto JavaEE com AngularJS

Descrição do Projeto

Este projeto é uma aplicação web que utiliza Java EE e AngularJS para realizar operações CRUD em entidades específicas. A aplicação está configurada para rodar no servidor de aplicações Wildfly e utiliza o Maven para gerenciamento de dependências. O banco de dados H2 é utilizado para armazenamento dos dados.

Tecnologias Utilizadas
•	Java: 8
•	Wildfly: 10.0.0.Final
•	AngularJS: 1.8.2
•	Hibernate: 5.4.21.Final
•	Maven: 3.6.3
•	Banco de Dados: H2 1.4.200
•	JAX-RS: 2.0 (para serviços REST)
•	JPA: 2.1 (para persistência de dados)

Pré-requisitos

Antes de iniciar, certifique-se de que as seguintes ferramentas estão instaladas em sua máquina:
•	JDK 8 - Necessário para compilar e executar a aplicação.
•	Maven 3.6.3 ou superior - Para gerenciar as dependências do projeto.
•	Wildfly 10 - Servidor de aplicação para rodar a aplicação Java EE.
•	Node.js e npm - Para gerenciamento de pacotes do AngularJS.
•	Git - Para controle de versão (opcional, caso queira clonar o repositório).

Instruções para Configuração

1. Clonar o Repositório
Clone o repositório do projeto em sua máquina local e navegue até o diretório do projeto:
bash

Copiar código
git clone <URL_DO_REPOSITORIO>
cd javaee-angularJs

3. Configuração do Maven
Certifique-se de que o Maven está corretamente configurado e instalado. Verifique a instalação executando o comando:
bash
Copiar código
mvn -v

4. Compilação do Projeto
Para compilar o projeto e resolver as dependências, execute:
bash
Copiar código
mvn clean install

5. Configuração do Wildfly

1.	Baixe e instale o Wildfly 10.

2.	Adicione o driver do banco de dados H2 ao Wildfly.

3.	Configure o datasource no arquivo standalone.xml do Wildfly com as seguintes entradas:
xml
Copiar código
<datasources>
    <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
        <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
        <driver>h2</driver>
        <security>
            <user-name>sa</user-name>
            <password>sa</password>
        </security>
    </datasource>
    <datasource jndi-name="java:jboss/datasources/javaeeAngularDs" pool-name="javaeeAngularDs" enabled="true" use-java-context="true">
        <connection-url>jdbc:h2:mem:javaee-angular;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
        <driver>h2</driver>
        <security>
            <user-name>sa</user-name>
            <password>sa</password>
        </security>
    </datasource>
    <drivers>
        <driver name="h2" module="com.h2database.h2">
            <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
        </driver>
    </drivers>
</datasources>

5. Deploy da Aplicação
1.	Copie o arquivo javaee-angularJs/target/javaee-angularJs.war para o diretório standalone/deployments do Wildfly.
2.	Inicie o Wildfly executando o seguinte comando:
bash
Copiar código
cd <CAMINHO_DO_WILDFLY>/bin
./standalone.sh (ou standalone.bat no Windows)

6. Acessando a Aplicação
Após iniciar o Wildfly, a aplicação estará disponível em:
•	Frontend AngularJS: http://localhost:8080/javaee-angularJs/

Execução do Projeto
1.	Certifique-se de que o Wildfly está em execução.
2.	Acesse a aplicação através do navegador na URL especificada.
3.	Utilize as funcionalidades do sistema para realizar operações CRUD nas entidades configuradas.

