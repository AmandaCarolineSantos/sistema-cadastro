Projeto JavaEE com AngularJS
Descrição do Projeto
Este projeto é uma aplicação web baseada em Java EE e AngularJS, com o objetivo de realizar operações CRUD em entidades específicas. O sistema está configurado para funcionar com Wildfly como servidor de aplicações e Maven como gerenciador de dependências. A aplicação utiliza um banco de dados H2 para armazenamento dos dados.

Tecnologias Utilizadas
Java: 8
Java EE: 7
Wildfly: 10.0.0.Final
AngularJS: 1.8.2
Hibernate: 5.4.21.Final
Maven: 3.6.3
Banco de Dados: H2 1.4.200
JAX-RS: 2.0 (para serviços REST)
JPA: 2.1 (para persistência de dados)
Pré-requisitos
Antes de iniciar, certifique-se de que as seguintes ferramentas estão instaladas em sua máquina:

JDK 8 - Necessário para compilar e executar a aplicação.
Maven 3.6.3 ou superior - Para gerenciar as dependências do projeto.
Wildfly 10 - Servidor de aplicação para rodar a aplicação Java EE.
Node.js e npm - Para gerenciamento de pacotes do AngularJS.
Git - Para controle de versão (opcional, caso queira clonar o repositório).
Instruções para Configuração
1. Clone o repositório
bash
Copiar código
git clone <URL_DO_REPOSITORIO>
cd javaee-angularJs
2. Configuração do Maven
Certifique-se de que o Maven está corretamente configurado e instalado. Você pode verificar isso executando o comando:

bash
Copiar código
mvn -v
3. Compilação do Projeto
Para compilar o projeto e resolver as dependências, execute:

bash
Copiar código
mvn clean install
4. Configuração do Wildfly
Baixe e instale o Wildfly 10.
Adicione o driver do banco de dados H2 ao Wildfly.
Configure o datasource no arquivo standalone.xml do Wildfly com as seguintes entradas:
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
Copie o arquivo javaee-angularJs/target/javaee-angularJs.war para o diretório standalone/deployments do Wildfly.
Inicie o Wildfly:
bash
Copiar código
cd <CAMINHO_DO_WILDFLY>/bin
./standalone.sh (ou standalone.bat no Windows)
6. Acessando a Aplicação
Frontend AngularJS: http://localhost:8080/javaee-angularJs/
Execução do Projeto
Certifique-se de que o Wildfly está em execução.
Acesse a aplicação através do navegador na URL especificada.
Utilize as funcionalidades do sistema para realizar operações CRUD nas entidades configuradas.