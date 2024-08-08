# Microservice de Cadastro de Produto
### Tech Challenge | Fase 4 | Pós Software Architecture - FIAP
Este projeto é um microserviço para cadastro de produtos em uma base de dados SQL MySql8, utilizando Terraform para gerenciamento de infraestrutura. Abaixo estão os detalhes técnicos e as instruções para configuração e execução do projeto.

## Stack Utilizada

- **Spring Boot 3.2.5:** Framework para construção de aplicações Java, proporcionando uma maneira simplificada de criar e gerenciar aplicações Spring.
- **Java 17:** Versão LTS da linguagem Java, oferecendo melhorias de desempenho e novas funcionalidades.
- **Mockito 5.3.1:** Framework de testes para Java que permite a criação de testes unitários ao simular o comportamento de objetos.
- **SonaCloud:** Plataforma de nuvem utilizada para hospedagem e execução de serviços.
- **Lombok:** Biblioteca que facilita a escrita de código Java, reduzindo o boilerplate através de anotações.
- **AWS Java SDK 5.1.0:** Conjunto de ferramentas que permite a interação com os serviços da AWS a partir de aplicações Java.

## Descrição do Serviço

O microserviço de cadastro de produtos é responsável por gerenciar as informações de produtos em uma base de dados SQL MySql8. A infraestrutura é provisionada e gerenciada utilizando Terraform, garantindo uma implantação consistente e automatizada.

## Funcionalidades

- **Cadastro de Produtos:** Permite o cadastro de novos produtos na base de dados.
- **Atualização de Produtos:** Permite a atualização das informações de produtos já cadastrados.
- **Consulta de Produtos:** Permite a consulta das informações de produtos cadastrados.
- **Deleção de Produtos:** Permite a remoção de produtos da base de dados.

## Pré-requisitos

Para rodar o projeto localmente, você precisará ter instalado:

- **JDK 17**
- **Maven 3.6.3 ou superior**
- **AWS CLI configurado**
- **Terraform 1.0.0 ou superior**

## Configuração do Ambiente

### 1. Clonar o Repositório

```sh
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

2. Configurar Variáveis de Ambiente
   Certifique-se de configurar as variáveis de ambiente necessárias para acessar a AWS e o MySql.
```sh
sh
Copy code
export AWS_ACCESS_KEY_ID=your_access_key_id
export AWS_SECRET_ACCESS_KEY=your_secret_access_key
export AWS_REGION=your_region
```

3. Provisionar Infraestrutura com Terraform
   Navegue até o diretório de infraestrutura e aplique as configurações do Terraform.

``` sh
sh
Copy code
cd infra
terraform init
terraform apply

```

4. Construir o Projeto
   Navegue de volta ao diretório raiz do projeto e construa o projeto utilizando Maven.

``` sh
Copy code
cd ..
mvn clean install
```

5. Executar o Projeto
   Após a construção, execute o projeto utilizando o comando Maven.

```sh
Copy code
mvn spring-boot:run
```

## Testes
Para rodar os testes unitários, utilize o comando:

```sh
Copy code
mvn test
```

Os testes utilizam Mockito para simular o comportamento dos objetos e verificar se os componentes do sistema estão funcionando conforme o esperado.


### Group
- [Thales Jolo](https://github.com/orgs/tech-challenge-team-fiap/people/thalesjolo)
- [Erikson Bastos](https://github.com/orgs/tech-challenge-team-fiap/people/EriksonB)
- [Samuel Almeida](https://github.com/orgs/tech-challenge-team-fiap/people/samucatezu)
- [Jair Cavalcante](https://github.com/orgs/tech-challenge-team-fiap/people/jaircavalcante)
- [Diego Fontgalland](https://github.com/orgs/tech-challenge-team-fiap/people/fontgalland)
