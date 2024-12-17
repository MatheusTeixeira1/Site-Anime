# 🎌 Venda de Blu-rays de Anime  

🚧 **Status**: Em desenvolvimento  

## 📝 Descrição  
Um site criado para a venda de Blu-rays de animes, com backend em **Spring Boot** e frontend em **HTML, CSS e JS**.  
Este projeto foca em demonstrar minhas habilidades em desenvolvimento **stateless** e APIs seguras com **JWT**.  

## 🚀 Funcionalidades  
- 🔒 Autenticação Stateless com JWT  
- 📦 Gerenciamento de Blu-rays  
- 🔧 Endpoints RESTful para consumo de dados  
- 💻 Frontend leve e estático  

## 🛠️ Tecnologias Utilizadas  
| Tecnologia      | Descrição                       |
|-----------------|---------------------------------|
| **Java 17**     | Backend principal com Spring    |
| **Spring Boot** | Framework robusto e eficiente   |
| **JWT**         | Autenticação segura             |
| **MySQL**       | Banco de dados relacional       |
| **HTML/CSS/JS** | Frontend básico e responsivo    |

## 📷 Screenshots  
### Login ###  
![image](https://github.com/user-attachments/assets/b29633b6-64a6-4391-96a2-8d0ac6e99c42)

### Home ###
![image](https://github.com/user-attachments/assets/0e2318d9-9b16-40c8-b383-0a18627089b5)

### Perfil do Usuário ###
![image](https://github.com/user-attachments/assets/8f6cd093-0e36-4c1e-b3dd-c19226af2c9f)

### Detalhes de um Blu-ray ###
![image](https://github.com/user-attachments/assets/7e29ab31-d2e1-4db4-b93e-c1ea6d2ce7d0)

### Tela para Criar/Editar Bue-rays ###
![image](https://github.com/user-attachments/assets/7f39cb41-4c1c-4fb8-bc69-1f07b41ac2ed)

## 🔧 Como Executar  

### 👨‍💻 Para Desenvolvedores (Rodando o Backend com Spring Boot)  

Se você deseja executar a aplicação em sua máquina para desenvolvimento ou testes, siga os passos abaixo:

1. **Pré-requisitos**  
   - **Java 17** instalado e configurado (JDK).  
   - **Maven** instalado (Você também pode utilizar alguma IDE).  
   - **MySQL** instalado e rodando localmente.  

2. **Clone o projeto**  
   Faça o download do código-fonte para sua máquina local:  
   ```bash
   git clone https://github.com/seu-usuario/venda-de-blueray.git
   cd venda-de-blueray

3. **Configurando o banco de dados**  
   No arquivo application.properties adapte as seguintes linhas:
      ```bash
      #Crie um novo schema com o nome de siteanime, ou adapte a linha:
      spring.datasource.url=jdbc:mysql://localhost:3306/siteanime
      #Coloque o nome do seu usuario no mysql
      spring.datasource.username=root
      #Coloque a senha do seu mysql
      spring.datasource.password=1234

      # (OPCIONAL) altere o local para o upload de fotos e arquivos
      app.upload.dir=C:/uploads/
      spring.web.resources.static-locations=file:/C:/uploads/
4. **Compilar e Executar com Maven**  
  (Opcional caso utilize uma IDE) 
   No terminal, estando na raiz do projeto (onde está o arquivo pom.xml), execute o seguinte comando para compilar e rodar o projeto:
      ```bash
      mvn spring-boot:run


## 📅 Melhorias Futuras  
- 🌟 Melhorar o frontend e torná-lo responsivo.  
- 🔐 Realizar um pente-fino na segurança do backend.  
- ✅ Criar testes unitários e de integração.
- 🔧 Ajustes finos no front-end
- 📁 Documentar 100%

## 👨‍💻 Contato  
- **Email**: [mathesteix@gmail.com](mailto:mathesteix@gmail.com)  
- **GitHub**: [MatheusTeixeira1](https://github.com/MatheusTeixeira1/)  
- **LinkedIn**: [Matheus Teixeira](https://www.linkedin.com/in/matheusteixeirap/)  
